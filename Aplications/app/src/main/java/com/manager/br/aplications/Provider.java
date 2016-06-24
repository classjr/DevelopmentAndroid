package com.manager.br.aplications;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

        String[] projecao = new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER,ContactsContract.CommonDataKinds.Phone.TYPE};
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = null;

        //Create a cursor
        Cursor cursor = contentResolver.query(uri,projecao, selection, selectionArgs, sortOrder);
        cursor.moveToFirst(); //Mover para a primeira linha
        String[] number = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++){
            number[i] = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            cursor.moveToNext();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,number);
        this.listView.setAdapter(adapter);
    }
}
