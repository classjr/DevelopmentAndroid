package com.manager.br.aplications.DataBase.ORM;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.ObjectCache;
import com.j256.ormlite.support.ConnectionSource;

import java.util.List;

/**
 * Created by junior on 08/07/2016.
 * This class is responsable for string and retrive of database
 */
public class DataDao {

    private static ConnectionSource connectionSource = null;
    private Dao<Object,Integer> objectDao;

    public DataDao(Context context){
        connectionSource = Connection.getConnectionSource(context);
    }

    /**
     * This method is responsable for save this object in database.
     */ 
    public Integer save(Object object, Class<Object> obj)throws Exception{
        Integer id = null;
        try{
            this.objectDao = DaoManager.createDao(connectionSource, obj);//Create new Object
            id = this.objectDao.create(object);                                                       //Save this objet in dataBase
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
        return id;
    }

    public void delete(Object object , Class<Object> obj)throws Exception{
        try{
            this.objectDao = DaoManager.createDao(connectionSource,obj);
            this.objectDao.delete(object);
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
    public List list(Object object, Class<Object> obj)throws Exception{
        List list = null;
        try{
            this.objectDao = DaoManager.createDao(connectionSource,obj);
            list = this.objectDao.queryForAll();
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
        return list;
    }
}
