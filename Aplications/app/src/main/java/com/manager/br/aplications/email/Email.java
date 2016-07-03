package com.manager.br.aplications.email;

import android.content.Context;
import android.content.Intent;

/**
 * Created by junior on 29/06/2016.
 * This class is responsable for email.
 * Send a email required a knowledge on 'Intent'. This mechanism is responsable for send
 * mensage bethen applications.
 */
public class Email {
    private Intent emailIntent = null;//This is a intent responsable for send a email

    /**
     * This constructor is responsable for starting the prcessing for open email
     */
    public Email(){
        this.emailIntent = new Intent(Intent.ACTION_SEND);//This line open new Intent
    }

    /**
     * This method is responsable fr send a email
     * @param Message msg is message for send
     * @param context is this context
     * @throws Exception
     */
    public void send(Message msg,Context context) throws Exception{
        try{
            this.emailIntent = msg.configureExtras(this.emailIntent);//Configure
            context.startActivity(Intent.createChooser(this.emailIntent, "Send mail..."));
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }

    }
}
