package com.zlin.happys.ui.classes;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.alibaba.fastjson.JSON;
import com.baidu.tts.chainofresponsibility.logger.LoggerProxy;
import com.baidu.tts.client.SpeechSynthesizer;
import com.baidu.tts.client.SpeechSynthesizerListener;
import com.baidu.tts.client.TtsMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zlin.happys.R;
import com.zlin.happys.base.BaseFragment;
import com.zlin.happys.model.Classbody;
import com.zlin.happys.model.ClassbodyDao;
import com.zlin.happys.model.ClassexercisesDao;
import com.zlin.happys.model.Classlesson;
import com.zlin.happys.model.ClasslessonDao;
import com.zlin.happys.model.ClasspointsDao;
import com.zlin.happys.model.ClassunitDao;
import com.zlin.happys.model.ResultJson;
import com.zlin.happys.utils.OkGoUtils;
import com.zlin.happys.utils.UrlUtil;
import com.zlin.happys.utils.tts.Auth;
import com.zlin.happys.utils.tts.AutoCheck;
import com.zlin.happys.utils.tts.FileUtil;
import com.zlin.happys.utils.tts.IOfflineResourceConst;
import com.zlin.happys.utils.tts.control.InitConfig;
import com.zlin.happys.utils.tts.listener.UiMessageListener;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassBodyFragment extends BaseFragment implements IOfflineResourceConst {
    private static String ARG_PARAM = "param_key";
    private String lessonId;

    private static final String TAG = "ClassBodyFragment";
    protected String appId;

    protected String appKey;

    protected String secretKey;

    private MediaPlayer mediaPlayer = new MediaPlayer();

    private WebView wv_body;

    protected String sn; // 纯离线合成SDK授权码；离在线合成SDK没有此参数

    // TtsMode.MIX; 离在线融合，在线优先； TtsMode.ONLINE 纯在线； 没有纯离线
    private TtsMode ttsMode = DEFAULT_SDK_TTS_MODE;

    private boolean isOnlineSDK = TtsMode.ONLINE.equals(DEFAULT_SDK_TTS_MODE);
    // ================ 纯离线sdk或者选择TtsMode.ONLINE  以下参数无用;
    private static final String TEMP_DIR = "/sdcard/baiduTTS"; // 重要！请手动将assets目录下的3个dat 文件复制到该目录

    // 请确保该PATH下有这个文件
    private static final String TEXT_FILENAME = TEMP_DIR + "/" + TEXT_MODEL;

    // 请确保该PATH下有这个文件 ，m15是离线男声
    private static final String MODEL_FILENAME = TEMP_DIR + "/" + VOICE_MALE_MODEL;

    // ===============初始化参数设置完毕，更多合成参数请至getParams()方法中设置 =================

    protected SpeechSynthesizer mSpeechSynthesizer;

    private String desc; // 说明文件

    Classbody classbody;

    Button btn_play;
    Button btn_play2;

    protected Handler mainHandler;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_classbody, container, false);

        wv_body = root.findViewById(R.id.wv_body);
        btn_play = root.findViewById(R.id.btn_play);
        lessonId = getArguments().getString( ARG_PARAM);
        mainHandler = new Handler() {
            /*
             * @param msg
             */
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.obj != null) {
                    print(msg.obj.toString());
                }
            }

        };
        btn_play2= root.findViewById(R.id.btn_play2);
        btn_play2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer!=null&&mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                }else {
                    openAssetMusics();
                }
            }
        });
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speak();
            }
        });
        initTTSt();
        initPermission();
        initTTs();
        getData();
//        TextView view = root.findViewById(R.id.tv_body);
//        mParam = getArguments().getString( ARG_PARAM);
//        view.setText(mParam);
        return root;
    }

    public void setWvBody(){
       List<Classbody> lesson = getDaoSession().getClassbodyDao().queryBuilder().where(ClassbodyDao.Properties.Lessonid.eq(lessonId)).list();
       if(lesson!=null&&lesson.size()>0){
           classbody = lesson.get(0);
           wv_body.loadData(lesson.get(0).getBodypy(),"text/html", "UTF-8");
       }

    }

    public void getData(){
        Map<String,String> params = new HashMap<>();
        params.put("lessonId",lessonId);
        OkGoUtils.getByOkGo(UrlUtil.LESSON_BODY_LIST, params, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                if(response.code() == 200){
                    String str = response.body();
                    ResultJson resultModel = JSON.parseObject(str, ResultJson.class);
                    if(resultModel.getData()!=null){
                        if(resultModel.getData().getClassbodyList()!=null&&resultModel.getData().getClassbodyList().size()>0) {
                            ClassbodyDao classbodyDao = getDaoSession().getClassbodyDao();
                            classbodyDao.insertOrReplaceInTx(resultModel.getData().getClassbodyList());
                        }
                        if(resultModel.getData().getClassexercisesList()!=null&&resultModel.getData().getClassexercisesList().size()>0){
                            ClassexercisesDao classexercisesDao = getDaoSession().getClassexercisesDao();
                            classexercisesDao.insertOrReplaceInTx(resultModel.getData().getClassexercisesList());
                        }
                        if(resultModel.getData().getClasspointsList()!=null&& resultModel.getData().getClasspointsList().size()>0){
                            ClasspointsDao classpointsDao = getDaoSession().getClasspointsDao();
                            classpointsDao.insertOrReplaceInTx(resultModel.getData().getClasspointsList());
                        }
                    }
                    setWvBody();
                }
            }

            @Override
            public void onError(Response response) {
                super.onError(response);

            }

            @Override
            public void onFinish() {
                super.onFinish();
            }
        });
    }

    public void initTTSt(){
        appId = Auth.getInstance(getContext()).getAppId();
        appKey = Auth.getInstance(getContext()).getAppKey();
        secretKey = Auth.getInstance(getContext()).getSecretKey();
        sn = Auth.getInstance(getContext()).getSn(); // 纯离线合成必须有此参数；离在线合成SDK没有此参数
        desc = FileUtil.getResourceText(getContext(), R.raw.mini_activity_description);
    }
    /**
     * 注意此处为了说明流程，故意在UI线程中调用。
     * 实际集成中，该方法一定在新线程中调用，并且该线程不能结束。具体可以参考NonBlockSyntherizer的写法
     */
    private void initTTs() {
        LoggerProxy.printable(true); // 日志打印在logcat中
        boolean isSuccess;
        if (!isOnlineSDK) {
            // 检查2个离线资源是否可读
            isSuccess = checkOfflineResources();
            if (!isSuccess) {
                return;
            } else {
                print("离线资源存在并且可读, 目录：" + TEMP_DIR);
            }
        }
        // 日志更新在UI中，可以换成MessageListener，在logcat中查看日志
        SpeechSynthesizerListener listener = new UiMessageListener(mainHandler);

        // 1. 获取实例
        mSpeechSynthesizer = SpeechSynthesizer.getInstance();
        mSpeechSynthesizer.setContext(getContext());

        // 2. 设置listener
        mSpeechSynthesizer.setSpeechSynthesizerListener(listener);

        // 3. 设置appId，appKey.secretKey
        int result = mSpeechSynthesizer.setAppId(appId);
        checkResult(result, "setAppId");
        result = mSpeechSynthesizer.setApiKey(appKey, secretKey);
        checkResult(result, "setApiKey");

        // 4. 如果是纯离线SDK需要离线功能的话
        if (!isOnlineSDK) {
            // 文本模型文件路径 (离线引擎使用)， 注意TEXT_FILENAME必须存在并且可读
            mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_TTS_TEXT_MODEL_FILE, TEXT_FILENAME);
            // 声学模型文件路径 (离线引擎使用)， 注意TEXT_FILENAME必须存在并且可读
            mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_TTS_SPEECH_MODEL_FILE, MODEL_FILENAME);

            mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_MIX_MODE, SpeechSynthesizer.MIX_MODE_DEFAULT);
            // 该参数设置为TtsMode.MIX生效。
            // MIX_MODE_DEFAULT 默认 ，wifi状态下使用在线，非wifi离线。在线状态下，请求超时6s自动转离线
            // MIX_MODE_HIGH_SPEED_SYNTHESIZE_WIFI wifi状态下使用在线，非wifi离线。在线状态下， 请求超时1.2s自动转离线
            // MIX_MODE_HIGH_SPEED_NETWORK ， 3G 4G wifi状态下使用在线，其它状态离线。在线状态下，请求超时1.2s自动转离线
            // MIX_MODE_HIGH_SPEED_SYNTHESIZE, 2G 3G 4G wifi状态下使用在线，其它状态离线。在线状态下，请求超时1.2s自动转离线



        }

        // 5. 以下setParam 参数选填。不填写则默认值生效
        // 设置在线发声音人： 0 普通女声（默认） 1 普通男声  3 情感男声<度逍遥> 4 情感儿童声<度丫丫>
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEAKER, "5");
        // 设置合成的音量，0-15 ，默认 5
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_VOLUME, "9");
        // 设置合成的语速，0-15 ，默认 5
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEED, "5");
        // 设置合成的语调，0-15 ，默认 5
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_PITCH, "5");

        // mSpeechSynthesizer.setAudioStreamType(AudioManager.MODE_IN_CALL); // 调整音频输出

        if (sn != null) {
            // 纯离线sdk这个参数必填；离在线sdk没有此参数
            mSpeechSynthesizer.setParam(PARAM_SN_NAME, sn);
        }

        // x. 额外 ： 自动so文件是否复制正确及上面设置的参数
        Map<String, String> params = new HashMap<>();
        // 复制下上面的 mSpeechSynthesizer.setParam参数
        // 上线时请删除AutoCheck的调用
        if (!isOnlineSDK) {
            params.put(SpeechSynthesizer.PARAM_TTS_TEXT_MODEL_FILE, TEXT_FILENAME);
            params.put(SpeechSynthesizer.PARAM_TTS_SPEECH_MODEL_FILE, MODEL_FILENAME);
        }

        // 检测参数，通过一次后可以去除，出问题再打开debug
        InitConfig initConfig = new InitConfig(appId, appKey, secretKey, ttsMode, params, listener);
        AutoCheck.getInstance(getContext().getApplicationContext()).check(initConfig, new Handler() {
            @Override
            /**
             * 开新线程检查，成功后回调
             */
            public void handleMessage(Message msg) {
                if (msg.what == 100) {
                    AutoCheck autoCheck = (AutoCheck) msg.obj;
                    synchronized (autoCheck) {
                        String message = autoCheck.obtainDebugMessage();
                        print(message); // 可以用下面一行替代，在logcat中查看代码
                        // Log.w("AutoCheckMessage", message);
                    }
                }
            }

        });

        // 6. 初始化
        result = mSpeechSynthesizer.initTts(ttsMode);
        checkResult(result, "initTts");

    }
    private void speak() {
        /* 以下参数每次合成时都可以修改
         *  mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEAKER, "0");
         *  设置在线发声音人： 0 普通女声（默认） 1 普通男声  3 情感男声<度逍遥> 4 情感儿童声<度丫丫>
         *  mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_VOLUME, "5"); 设置合成的音量，0-9 ，默认 5
         *  mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEED, "5"); 设置合成的语速，0-9 ，默认 5
         *  mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_PITCH, "5"); 设置合成的语调，0-9 ，默认 5
         *
         */

        if (mSpeechSynthesizer == null) {
            print("[ERROR], 初始化失败");
            return;
        }
//        int result = mSpeechSynthesizer.speak(classbody.getBody());
        int result = mSpeechSynthesizer.speak("离离原上草，一岁一枯荣。野火烧不尽，春风吹又生");
        print("合成并播放 按钮已经点击");
        checkResult(result, "speak");
    }

    private void checkResult(int result, String method) {
        if (result != 0) {
            print("error code :" + result + " method:" + method);
        }
    }

    public static ClassBodyFragment newInstance(String str) {
        ClassBodyFragment frag = new ClassBodyFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PARAM, str);
        frag.setArguments(bundle);//设置参数
        return frag;
    }
    /**
     * 在线SDK不需要调用，纯离线SDK会检查资源文件
     *
     * 检查 TEXT_FILENAME, MODEL_FILENAME 这2个文件是否存在，不存在请自行从assets目录里手动复制
     *
     * @return 检测是否成功
     */
    private boolean checkOfflineResources() {
        String[] filenames = {TEXT_FILENAME, MODEL_FILENAME};
        for (String path : filenames) {
            File f = new File(path);
            if (!f.canRead()) {
                print("[ERROR] 文件不存在或者不可读取，请从demo的assets目录复制同名文件到："
                        + f.getAbsolutePath());
                print("[ERROR] 初始化失败！！！");
                return false;
            }
        }
        return true;
    }
    private void print(String message) {
        Log.i(TAG, message);
//        mShowText.append(message + "\n");
    }
    /**
     * android 6.0 以上需要动态申请权限
     */
    private void initPermission() {
        String[] permissions = {
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.MODIFY_AUDIO_SETTINGS,
                Manifest.permission.WRITE_SETTINGS,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.CHANGE_WIFI_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        ArrayList<String> toApplyList = new ArrayList<>();

        for (String perm : permissions) {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(getContext(), perm)) {
                toApplyList.add(perm);
                // 进入到这里代表没有权限.
            }
        }
        String[] tmpList = new String[toApplyList.size()];
        if (!toApplyList.isEmpty()) {
            ActivityCompat.requestPermissions(getActivity(), toApplyList.toArray(tmpList), 123);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // 此处为android 6.0以上动态授权的回调，用户自行实现。
    }
    /**
     * 打开assets下的音乐mp3文件
     */
    private void openAssetMusics() {
        //打开Asset目录
        AssetManager am = getContext().getAssets();

        try {
            //打开音乐文件shot.mp3
            AssetFileDescriptor assetFileDescriptor = am.openFd("fdgy.mp3");
            mediaPlayer.reset();
            //设置媒体播放器的数据资源
            mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("GsonUtils", "IOException" + e.toString());
        }
    }
}
