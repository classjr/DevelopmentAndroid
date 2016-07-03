package com.manager.br.aplications.email;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by junior on 03/07/2016.
 * This class is responsable for send message fr email
 */
public class SendEmail extends Message{

    public SendEmail(Context context){
        Email email = new Email();
        try{
             email.send(this,context);
        }catch(Exception ex){
            Toast.makeText(context,"Problemas a enviar. " + ex.getMessage() , Toast.LENGTH_LONG );
        }
    }
    @Override
    protected String[] sendTO() {
        return new String[]{"estrutura.pilha@gmail.comm"};
    }

    @Override
    protected String[] sendCC() {
        return new String[]{"estrutura.pilha@gmail.com"};
    }

    @Override
    protected String sendSubject() {
        return "Teste de android";
    }

    @Override
    protected String sendMsg() {
        return "Ola esta e a minha primeira mensagem";
    }

    @Override
    protected String sendType() {
        return "text/plain";
    }


}
