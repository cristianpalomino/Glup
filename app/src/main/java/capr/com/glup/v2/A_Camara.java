package capr.com.glup.v2;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import capr.com.application.Glup_Application;
import capr.com.fragments.CameraFragment;
import capr.com.glup.R;
import capr.com.interfaces.CameraCallBack;

/**
 * Created by usuario on 28/03/15.
 */
public class A_Camara extends ActionBarActivity implements CameraCallBack {

    private static final int PICTURE_QUALITY = 40;
    private ImageView plantilla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);

        plantilla = (ImageView) findViewById(R.id.plantilla);
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

        int x = bitmap.getWidth() / 2;
        int y = bitmap.getHeight() / 2;
        int w = bitmap.getWidth() / 2;
        int h = bitmap.getHeight() / 2;

        Log.e("TAMANIO", bitmap.getWidth() + " - " + bitmap.getHeight());
        Log.e("CORDENADAS", x + " - " + y);
        Log.e("MARGEN", w + " - " + h);

        Bitmap map = Bitmap.createBitmap(bitmap, x, y, w, h);
        resizedBitmap = Bitmap.createScaledBitmap(map, 850, 850, false);

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), getString(R.string.app_name));

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile = new File(mediaStorageDir.getPath() + File.separator + "GLUP_" + timeStamp + ".jpg");

        try {
            FileOutputStream stream = new FileOutputStream(mediaFile);
            resizedBitmap.compress(Bitmap.CompressFormat.JPEG, PICTURE_QUALITY, stream);
        } catch (IOException exception) {
            Log.e(A_Camara.this.getClass().getName(), "IOException during saving bitmap");
            return;
        }

        MediaScannerConnection.scanFile(this, new String[]{mediaFile.toString()}, new String[]{"image/jpeg"}, null);
        Toast.makeText(this, "Saved : " + mediaFile.toString(), Toast.LENGTH_SHORT).show();

        Glup_Application glup_application = ((Glup_Application) getApplication());
        Picasso.with(this).load(mediaFile).into(glup_application.getPreviewCurrent());
        glup_application.getPreviewCurrent().setVisibility(View.VISIBLE);
        glup_application.getOnTake().OnTake(mediaFile, mediaFile.toString());


        finish();
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
