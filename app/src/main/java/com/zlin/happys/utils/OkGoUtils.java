package com.zlin.happys.utils;

import android.content.Context;
import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import java.io.File;
import java.util.Map;

public class OkGoUtils {
    private static final String TAG = OkGoUtils.class.getName();
    /**
     * get请求获取数据
     *
     * @param url
     */
    public static void getByOkGo(String url,Map params,AbsCallback absCallback) {
        OkGo.<String>get(url)//
                .tag(TAG)
                .params(params)//传入请求参数
                .cacheKey("cachekey")//作为缓存的key
                .cacheMode(CacheMode.NO_CACHE)//设置缓存模式
                //StringCallback只返回成功
                .execute(absCallback);
    }
    /**
     * post请求
     * @param url
     */
    public static void postByOkGo(String url, Map params,AbsCallback absCallback){
        OkGo.<String>post(url)
                .tag(TAG)
                .params(params)
                .execute(absCallback);
    }
    /**
     * @param context 上下文
     * @param fileUrl 下载完整url
     * @param destFileDir  SD路径
     * @param destFileName  文件名
     * @param mFileRelativeUrl 下载相对地址
     * （我们从服务器端获取到的数据都是相对的地址）例如： "filepath": "/movie/20180511/1526028508.txt"
     */
    private static String mBasePath; //本地文件存储的完整路径  /storage/emulated/0/book/a.txt
    public static void downloadFile(Context context, String fileUrl, String destFileDir, String destFileName, String mFileRelativeUrl) {
        String mDestFileName = destFileName + mFileRelativeUrl.substring(mFileRelativeUrl.lastIndexOf("."), mFileRelativeUrl.length());
        OkGo.<File>get(fileUrl).tag(context).execute(new FileCallback(destFileDir, mDestFileName) { //文件下载时指定下载的路径以及下载的文件的名称
            @Override
            public void onStart(Request<File, ? extends Request> request) {
                super.onStart(request);
                Log.d(TAG,"开始下载文件");
            }

            @Override
            public void onSuccess(com.lzy.okgo.model.Response<File> response) {
                Log.d(TAG, "下载文件成功:" + response.body().length());
                mBasePath = response.body().getAbsolutePath();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                Log.d(TAG, "下载文件完成");
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<File> response) {
                super.onError(response);
                Log.e(TAG, "下载文件出错:" + response.message());

            }

            @Override
            public void downloadProgress(Progress progress) {
                super.downloadProgress(progress);
                float dLProgress = progress.fraction;
                Log.d(TAG, "文件下载的进度:" + dLProgress);
            }
        });
    }
}
