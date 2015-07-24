package capr.com.glup;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.edmodo.cropper.CropImageView;
import com.edmodo.cropper.cropwindow.CropOverlayView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import capr.com.application.Glup_Application;
import capr.com.dialog.Dialog_Cropper;
import capr.com.util.DocumentExifTransformation;

/**
 * Created by usuario on 1/04/15.
 */
public class Cropper extends ActionBarActivity implements View.OnClickListener {

    private CropImageView cropper;
    private Button crop;

    private Bitmap bitmap;
    private Dialog_Cropper.OnCroppedImage onCroppedImage;

    private int mAspectRatioX = 20;
    private int mAspectRatioY = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_cropper);

        Display display = getWindowManager().getDefaultDisplay();
        int width = display.getWidth();  // deprecated
        int height = display.getHeight();

        Bitmap current_bitmap = ((Glup_Application) getApplication()).getCurrent_bitmap();

        Matrix m = new Matrix();
        m.setRectToRect(new RectF(0, 0, current_bitmap.getWidth(), current_bitmap.getHeight()), new RectF(0, 0, width, height), Matrix.ScaleToFit.CENTER);
        Bitmap resizedBitmap = Bitmap.createBitmap(current_bitmap, 0, 0, current_bitmap.getWidth(), current_bitmap.getHeight(), m, true);

        cropper = (CropImageView) findViewById(R.id.cropperimgview);
        cropper.setImageBitmap(resizedBitmap);
        cropper.setFixedAspectRatio(true);
        cropper.setAspectRatio(mAspectRatioX, mAspectRatioY);

        crop = (Button) findViewById(R.id.crop);
        crop.setOnClickListener(Cropper.this);

    }

    @Override
    public void onClick(View v) {

        new AsyncTask<Void, Void, Void>() {

            private ProgressDialog dialog;
            private File mediaFile;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                dialog = ProgressDialog.show(Cropper.this, null, "Wait", true, false);
            }

            @Override
            protected Void doInBackground(Void... params) {
                bitmap = cropper.getCroppedImage();

                ByteArrayOutputStream bo = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, bo);
                byte[] byteArray = bo.toByteArray();

                Bitmap b = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                Bitmap resizedBitmap = Bitmap.createScaledBitmap(b, 850, 850, false);

                File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), getString(R.string.app_name));
                if (!mediaStorageDir.exists()) {
                    if (!mediaStorageDir.mkdirs()) {
                    }
                }

                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String path = mediaStorageDir.getPath() + File.separator + "GLUP_" + timeStamp + ".jpg";
                mediaFile = new File(path);

                try {
                    FileOutputStream stream = new FileOutputStream(mediaFile);
                    resizedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                } catch (IOException exception) {
                    Log.e(Cropper.this.getClass().getName(), "IOException during saving bitmap");
                }

                MediaScannerConnection.scanFile(Cropper.this,
                        new String[]{mediaFile.toString()},
                        new String[]{"image/jpeg"}, null);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                Glup_Application glup_application = ((Glup_Application) getApplication());
                glup_application.getOnTake().OnTake(mediaFile, mediaFile.toString());

                dialog.hide();
                finish();
            }
        }.execute();
    }
}
