package com.mahang.weather.model.db;

import android.content.Context;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBase;

import java.util.ArrayList;

/**
 * Created by hasee on 2016/12/19.
 */

public class SQLiteWrapper{

    private static SQLiteWrapper sInstance;

    private Context mContext;

    private String mDbName;


    public SQLiteWrapper(Context context) {
        mContext = context.getApplicationContext();
    }

    public synchronized static SQLiteWrapper getInstance(Context context){
        if (sInstance == null){
            sInstance = new SQLiteWrapper(context);
        }
        return sInstance;
    }

    public void setDbName(String dbName){
        mDbName = dbName;
    }

    public void delete(Object object){
    }

    public void save(Object object){

    }

    public <T> ArrayList<T> loadData(Class<T> targer){
        DataBase liteOrm = LiteOrm.newCascadeInstance(mContext, mDbName);
        ArrayList<T> result  = liteOrm.cascade().query(targer);
        liteOrm.close();
        return result;
    }

    public void swape(){

    }

}
