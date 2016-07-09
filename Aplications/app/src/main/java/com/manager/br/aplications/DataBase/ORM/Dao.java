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
    private Dao<Object,Integer> objectDao;

    public Dao(Context context){
        connectionSource = Connection.getConnectionSource(context);
    }

    /**
     * This method is responsable for save this object in database.
     */ 
    public Integer save(Object object)throws Exception{
        try{
            this.objectDao = DaoManager.createDao(connectionSource, object.class);//Create new Object
            this.objectDao.create(objet);                                         //Save this objet in dataBase
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
