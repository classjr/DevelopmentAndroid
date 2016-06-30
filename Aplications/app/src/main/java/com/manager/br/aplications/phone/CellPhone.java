package com.manager.br.aplications.phone;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import java.util.ArrayList;

/**
 * Created by junior on 29/06/2016.
 * This class is responsable for working with phone
 */
public abstract class CellPhone {

    private Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;//for called for URI
    private String[] projecao = new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER,ContactsContract.CommonDataKinds.Phone.TYPE,ContactsContract.CommonDataKinds.Phone._ID};
    private String selection = null;
    private String[] selectionArgs = null;
    private String sortOrder = null;
    private Cursor cursor = null; // This is cursor for Open cursor
    private ContentResolver contentResolver = null;

    /**
     * This is construtor for this class
     * @param contentResolver
     */
    public CellPhone(ContentResolver contentResolver){
        this.contentResolver = contentResolver;
    }

    /**
     * This method is responsable for open conection
     * @throws Exception
     */
    public void openConnection() throws  Exception{
        try {
            this.cursor = contentResolver.query(uri, projecao, selection, selectionArgs, sortOrder);
            cursor.moveToFirst();                                //Mover para a primeira linha
            for(int i  = 0; i < this.cursor.getCount(); i++){
                this.extractInformationPhone(cursor);            //Called this module
                cursor.moveToNext();                             //Moved for next line
            }
            cursor.close();
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    /**
     * This method is responsable for change number of phone Number
     * @param id is the identifier of line phone number.
     * @param numberNew is the new number phone
     * @return True for processing ok or False for processing not k.
     * @throws Exception
     */
    public Boolean changePhoneNumber(Long id,String numberNew) throws Exception{

        ContentProviderOperation.Builder builder = ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI); // For update this number
        ArrayList<ContentProviderOperation> listOperations = new ArrayList<ContentProviderOperation>();

        builder.withSelection(ContactsContract.CommonDataKinds.Phone._ID + "=?" , new String[]{String.valueOf(id)});
        builder.withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, numberNew);
        listOperations.add(builder.build());

        try{
            this.contentResolver.applyBatch(ContactsContract.AUTHORITY, listOperations);
        }catch(Exception ex){
            throw new Exception("Error: "+ex.getMessage());
        }
        return true;
    }

    public abstract void extractInformationPhone(Cursor c);

}
