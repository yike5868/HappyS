package com.zlin.happys.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.zlin.happys.model.DaoMaster;
import com.zlin.happys.model.DaoSession;

public class DbManager {
    public static final boolean ENCRYPTED = true; // 是否加密

    private static final String DB_NAME = "zbc_test.db"; //数据库名

    private static DbManager mDbManager;

    private static DaoMaster.DevOpenHelper mDevOpenHelper; // 需要升级时用MySQLiteOpenHelper替换DaoMaster.DevOpenHelper，保障更新版本时保存老数据

    private static DaoMaster mDaoMaster;

    private static DaoSession mDaoSession;

    private Context mContext;

    private DbManager(Context context) {
        this.mContext = context; // 初始化数据库信息

        mDevOpenHelper = new DaoMaster.DevOpenHelper(context, DB_NAME);

        getDaoMaster(context);

        getDaoSession(context);

    }

    public static DbManager getInstance(Context context) {
        if (null == mDbManager) {
            synchronized (DbManager.class) {
                if (null == mDbManager) {
                    mDbManager = new DbManager(context); } } }

        return mDbManager;

    }

    /** * 获取可读数据库 *

     * @param context

     * @return */

    public static SQLiteDatabase getReadableDatabase(Context context) {
        if (null == mDevOpenHelper) {
            getInstance(context); }

        return mDevOpenHelper.getReadableDatabase();

    }

    /** * 获取可写数据库 *

     * @param context

     * @return */

    public static SQLiteDatabase getWritableDatabase(Context context) {
        if (null == mDevOpenHelper) { getInstance(context); }

        return mDevOpenHelper.getWritableDatabase();

    }

    /** * 获取DaoMaster *

     * 判断是否存在数据库，如果没有则创建数据库

     * @param context

     * @return */

    public static DaoMaster getDaoMaster(Context context) {
        if (null == mDaoMaster) { synchronized (DbManager.class) {
            if (null == mDaoMaster) {
//升级对应的代码，请关注下面的一个类

                FuSQLiteOpenHelper helper = new FuSQLiteOpenHelper(context,DB_NAME,null);

                mDaoMaster = new DaoMaster(helper.getWritableDatabase()); } } }

        return mDaoMaster;

    }

    /** * 获取DaoSession *

     * @param context

     * @return */

    public static DaoSession getDaoSession(Context context) {
        if (null == mDaoSession) {
            synchronized (DbManager.class) {
                mDaoSession = getDaoMaster(context).newSession(); } }

        return mDaoSession; }
}
