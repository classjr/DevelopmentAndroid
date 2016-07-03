package com.manager.br.aplications.email;

import android.content.Intent;
import android.net.Uri;

/**
 * Created by junior on 03/07/2016.
 * This class is responsable for send mensage
 */
public abstract  class Message {

    protected abstract String[] sendTO();    //Send for Whose
    protected abstract String[] sendCC();    // For command
    protected abstract String sendSubject(); //subject of message
    protected abstract String sendMsg();     // Message
    protected abstract String sendType();    // Type of message

    /**
     * This method is responsable for configure extras
     * @param intent
     * @return
     * @throws Exception
     */
    protected Intent configureExtras(Intent intent)throws Exception{
        try{
            intent.setData(Uri.parse("mailto:"));
            intent.setType(sendType());
            intent.putExtra(Intent.EXTRA_EMAIL,sendTO());
            intent.putExtra(Intent.EXTRA_CC,sendCC());
            intent.putExtra(Intent.EXTRA_SUBJECT,sendSubject());
            intent.putExtra(Intent.EXTRA_TEXT,sendMsg());
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
        return intent;
    }
}
