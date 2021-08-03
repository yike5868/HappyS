package com.zlin.happys.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.zlin.happys.model.DaoMaster;

import org.greenrobot.greendao.database.Database;

public class FuSQLiteOpenHelper extends DaoMaster.OpenHelper{

    public FuSQLiteOpenHelper(Context context, String name) {
        super(context, name);
    }

    public FuSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        //把需要管理的数据库表DAO作为最后一个参数传入到方法中（只传更改过的表，新增的不用）
        MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {
            @Override
            public void onCreateAllTables(Database db, boolean ifNotExists) {
                DaoMaster.createAllTables(db, ifNotExists);
            }

            @Override
            public void onDropAllTables(Database db, boolean ifExists) {
                DaoMaster.dropAllTables(db, ifExists);
            }
        }, null);//可添加多个改动过的表对象Dao
        super.onUpgrade(db, oldVersion, newVersion);
    }
}
