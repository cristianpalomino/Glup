package capr.com.glup.v2;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.Image;
import android.media.MediaScannerConnection;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import capr.com.application.Glup_Application;
import capr.com.dialog.Dialog_Cropper;
import capr.com.fragments.CameraFragment;
import capr.com.glup.Cropper;
import capr.com.glup.R;
import capr.com.interfaces.CameraCallBack;
import capr.com.views.Glup_Plantilla;

/**
 * Created by usuario on 28/03/15.
 */
public class A_Camara extends ActionBarActivity implements CameraCallBack {

    private static final int PICTURE_QUALITY = 100;
    private Glup_Plantilla plantilla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);

        plantilla = (Glup_Plantilla) findViewById(R.id.plantilla);
        plantilla.setOnSizeChanged(new Glup_Plantilla.OnSizeChanged() {
            @Override
            public void onSizeChanged(int w, int h, int oldw, int oldh) {

                int ih=plantilla.getMeasuredHeight();//height of imageView
                int iw=plantilla.getMeasuredWidth();//width of imageView
                int iH=plantilla.getDrawable().getIntrinsicHeight();//original height of underlying image
                int iW=plantilla.getDrawable().getIntrinsicWidth();//original width of underlying image

                if (ih/iH<=iw/iW)
                    iw=iW*ih/iH;//rescaled width of image within ImageView
                else
                    ih= iH*iw/iW;//rescaled height of image within ImageView

                  Log.e("PLANTILLA TAMANIO",iw + " - " + ih);
            }
        });

        plantilla.setImageResource(((Glup_Application) getApplication()).getPlantilla_dto().getImage_id());

        findViewById(R.id.take).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CameraFragment fragment = (CameraFragment) getSupportFragmentManager().findFragmentById(R.id.camera_fragment);
                fragment.takePicture();
            }
        });
    }

    @Override
    public void onCameraError() {
        Toast.makeText(this, "Take photo", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPictureTaken(Bitmap btm) {
        /*
        Redimiensionando
         */
        Bitmap resizedBitmap = Bitmap.createBitmap(btm, 0, 0, btm.getWidth(), btm.getHeight());
        Bitmap bitmap = RotateBitmap(resizedBitmap, 90);

        ((Glup_Application)getApplication()).setCurrent_bitmap(bitmap);
        Intent intent = new Intent(A_Camara.this, Cropper.class);
        startActivity(intent);


        A_Camara.this.finish();

        /*
        Dialog_Cropper dialog_cropper = new Dialog_Cropper(A_Camara.this,bitmap);
        dialog_cropper.setOnCroppedImage(A_Camara.this);
        dialog_cropper.show();
        */
    }

    public static Bitmap RotateBitmap(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
