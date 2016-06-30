package com.manager.br.aplications;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Provider extends AppCompatActivity {

    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);

        //Recuperar objeto  da lista
        this.listView = (ListView)findViewById(R.id.listView);

        //Recuperar um content provider
        ContentResolver contentResolver = getContentResolver();
        // Recuperar a lista de numeros de telefone
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        String[] projecao = new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER,ContactsContract.CommonDataKinds.Phone.TYPE,ContactsContract.CommonDataKinds.Phone._ID};
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = null;

        //Create a cursor
        Cursor cursor = contentResolver.query(uri,projecao, selection, selectionArgs, sortOrder);
        cursor.moveToFirst(); //Mover para a primeira linha
        String[] number = new String[cursor.getCount()];  //Capture the number
        Long[]   id     = new Long[cursor.getCount()];    ///Cpature the id of this phone

        for (int i = 0; i < cursor.getCount(); i++){
            number[i] = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            id[i]     = cursor.getLong(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID));
            /*if(number[i].equals("+554488383993")){
                try {
                    changePhoneNumber(Long.valueOf(id[i]), "+554488383993");
                }catch (Exception ex){
                    Toast.makeText(this,"Problemas a alterar numero ",Toast.LENGTH_LONG);
                }
            } */
            cursor.moveToNext();
        }
        cursor.close();//close the cursor
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,number);
        this.listView.setAdapter(adapter);
    }
    /**
     * This method is responsable for change the value of Phone number
     */
    public Boolean changePhoneNumber(Long id,String numberNew) throws Exception{
        //References: http://stackoverflow.com/questions/9907751/android-update-a-contact
        //References: http://stackoverflow.com/questions/3351545/how-to-update-contact-number-using-android
         ContentProviderOperation.Builder builder = ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI); // For update this number
         ArrayList<ContentProviderOperation> listOperations = new ArrayList<ContentProviderOperation>();

         builder.withSelection(ContactsContract.CommonDataKinds.Phone._ID + "=?" , new String[]{String.valueOf(id)});
         builder.withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, numberNew);
         listOperations.add(builder.build());
         
         try{
             getContentResolver().applyBatch(ContactsContract.AUTHORITY, listOperations);
         }catch(Exception ex){
             throw new Exception("Error: "+ex.getMessage());
         }
        return true;
    }
}
