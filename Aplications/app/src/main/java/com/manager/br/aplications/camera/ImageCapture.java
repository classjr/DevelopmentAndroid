package com.manager.br.aplications.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.manager.br.aplications.R;

import org.bytedeco.javacpp.*;
import org.bytedeco.javacv.*;

import java.io.ByteArrayOutputStream;

import static org.bytedeco.javacpp.opencv_core.cvSize;
import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgproc.cvCvtColor;
import static org.bytedeco.javacpp.opencv_imgproc.*;

/**
 * Search reference: http://stackoverflow.com/questions/5991319/capture-image-from-camera-and-display-in-activity
 */ 

public class ImageCapture extends AppCompatActivity {

    private Button button;
    private ImageView imageView;
    private Intent intentCam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_capture);

        button    = (Button)findViewById(R.id.button);
        imageView = (ImageView)findViewById(R.id.imageView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentCam = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                startActivityForResult(intentCam, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bp = (Bitmap) data.getExtras().get("data");

        IplImage img = bitMapToIplImage(bp);
        IplImage grayS = opencv_core.IplImage.create(cvSize(bp.getWidth(), bp.getHeight()), IPL_DEPTH_8U, 1);
        cvCvtColor(img , grayS , CV_RGB2GRAY);
        Bitmap gray = iplImageToBitmap(grayS);

        imageView.setImageBitmap(gray);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public IplImage bitMapToIplImage(Bitmap bitmap){
        opencv_core.IplImage img = opencv_core.IplImage.create(cvSize(bitmap.getWidth(), bitmap.getHeight()), IPL_DEPTH_8U, 3);
        try{
            bitmap.copyPixelsToBuffer(img.getByteBuffer());
        }catch (Exception ex){
            Toast.makeText(this,"Problems for converter in bitmapToIplImage", Toast.LENGTH_LONG);
        }
        return img;
    }
    public Bitmap iplImageToBitmap(IplImage iplImage){
        Bitmap bitmap = null;
        try{
            bitmap = Bitmap.createBitmap(iplImage.width(), iplImage.height(), Bitmap.Config.ARGB_8888);
            bitmap.copyPixelsFromBuffer(iplImage.getByteBuffer());
        }catch (Exception ex){
            Toast.makeText(this,"Problems for converter in iplImageTBitmap", Toast.LENGTH_LONG);
        }
        return bitmap;
    }
}
