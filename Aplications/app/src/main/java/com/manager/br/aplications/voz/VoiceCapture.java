package com.manager.br.aplications.voz;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.manager.br.aplications.R;

import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

/**
 * Reference: http://www.androidhive.info/2014/07/android-speech-to-text-tutorial/
 */
public class VoiceCapture extends AppCompatActivity {

    private static final int REQ_CODE_SPEECH_INPUT = 10;
    private TextView saida;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_capture);

        saida  = (TextView)findViewById(R.id.saida);
        button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    captureVoice();
                }catch (Exception ex){
                    Toast.makeText(null,"Problemas a executar", Toast.LENGTH_LONG);
                }
            }
        });
    }

    /**
     * This method is responsable for capturing voice
     * @throws Exception
     */
    public void captureVoice() throws Exception{
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Ola mundo");
    }

    /**
     * This method is responsable for capture voice and putting in text
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    saida.setText(result.get(0));
                }
                break;
            }

        }
    }
}
