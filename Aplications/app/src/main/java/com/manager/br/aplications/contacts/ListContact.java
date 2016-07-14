package com.manager.br.aplications.contacts;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.manager.br.aplications.R;

import java.util.Arrays;
import java.util.List;

public class ListContact extends ListActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listarViagens()));
        ListView listView = getListView();
        listView.setOnItemClickListener(this);
    }

    private List<String> listarViagens() {
        return Arrays.asList("São Paulo", "Bonito", "Maceió");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        TextView textView = (TextView)view;
        Toast.makeText(this, "Escolheu: "+textView.getText().toString(), Toast.LENGTH_LONG).show();
    }
}
