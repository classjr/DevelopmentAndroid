package com.manager.br.aplications.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.manager.br.aplications.DataBase.ORM.activity.ActivityOrm;
import com.manager.br.aplications.contacts.ListContact;
import com.manager.br.aplications.contacts.Provider;
import com.manager.br.aplications.R;
import com.manager.br.aplications.camera.ImageCapture;
import com.manager.br.aplications.email.SendEmail;
import com.manager.br.aplications.phone.CellPhone;

public class Main extends AppCompatActivity {
    private Intent intent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void microphone(View view){
        this.intent = new Intent(this, CellPhone.class); //called this intent
        startActivity(this.intent);
    }

    public void bluetooth(View view){

    }

    public void camera(View view){
        this.intent = new Intent(this,ImageCapture.class); //called this intent
        startActivity(this.intent);
    }

    public void contacts(View view){
        //this.intent = new Intent(this,Provider.class); //called this intent
        this.intent = new Intent(this,ListContact.class); //called this intent
        startActivity(this.intent);
    }

    public void email(View view){
        SendEmail send = new SendEmail(this);

    }
    public void orm(View view){
        this.intent = new Intent(this, ActivityOrm.class);
        startActivity(this.intent);
    }
}
