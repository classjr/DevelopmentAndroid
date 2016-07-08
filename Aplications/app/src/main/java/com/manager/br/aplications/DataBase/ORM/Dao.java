package com.manager.br.aplications.DataBase.ORM;

import android.content.Context;

import com.j256.ormlite.support.ConnectionSource;

import java.util.List;

/**
 * Created by junior on 08/07/2016.
 * This class is responsable for string and retrive of database
 */
public class Dao {

    private static ConnectionSource connectionSource = null;

    public Dao(Context context){
        connectionSource = Connection.getConnectionSource(context);
    }

    public Integer save()throws Exception{
        try{

        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
        return 1;
    }

    public void delete()throws Exception{
        try{

        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
    public List list()throws Exception{
        try{

        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
        return null;
    }
}
