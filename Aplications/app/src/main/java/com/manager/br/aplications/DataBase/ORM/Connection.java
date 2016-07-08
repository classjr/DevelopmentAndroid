package com.manager.br.aplications.DataBase.ORM;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

/**
 * Created by jr on 08/07/2016.
 * Reference: http://www.juliocnsouza.com.br/app-com-persistencia-usando-ormlite/
 */
public class Connection extends OrmLiteSqliteOpenHelper {
    private static  final String nameDataBase = "firstApplications.db";
    private static  final Integer version = 1;

    public Connection(Context context) {
        super(context,nameDataBase, null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, com.j256.ormlite.support.ConnectionSource connectionSource) {
        try{
            TableUtils.createTable(connectionSource, Person.class); // Create Table
        }catch (Exception ex){
            Toast.makeText(null,"Problemas ao gravar no banco",Toast.LENGTH_LONG);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, com.j256.ormlite.support.ConnectionSource connectionSource, int i, int i1) {
        try{
            TableUtils.dropTable(connectionSource, Person.class, true);
            onCreate(sqLiteDatabase,connectionSource);
        }catch (Exception ex){
            Toast.makeText(null,"Problemas ao atualizar no banco",Toast.LENGTH_LONG);
        }
    }

    public static ConnectionSource getConnectionSource(Context context) {
        return new Connection(context).getConnectionSource();
    }
}
