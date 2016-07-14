package com.manager.br.aplications.DataBase.ORM.activity;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.manager.br.aplications.DataBase.ORM.DataDao;
import com.manager.br.aplications.DataBase.ORM.Person;
import com.manager.br.aplications.R;

import java.util.Arrays;
import java.util.List;

public class ActivityOrm extends AppCompatActivity {

    private TextView name;
    private TextView age;
    private Person person;
    private DataDao dataDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_orm);
        this.name = (TextView)findViewById(R.id.editText);
        this.name =  (TextView)findViewById(R.id.editText2);
    }

    private List<String> listarViagens() {
        return Arrays.asList("São Paulo", "Bonito", "Maceió");
    }

    public void save(View view){

        this.person  = new Person();
        this.dataDao = new DataDao(this);

        this.person.setName(this.name.getText().toString());
        this.person.setAge(Integer.valueOf(this.age.getText().toString()));
        try {

            this.dataDao.save(this.person,(Class<Object>)this.person.getClass().getComponentType());
            Toast.makeText(this,"Save with SucessFull !! ",Toast.LENGTH_LONG);
        }catch(Exception ex){
            Toast.makeText(this,"Problems for save: "+ex.getMessage(),Toast.LENGTH_LONG);
        }

    }

}
