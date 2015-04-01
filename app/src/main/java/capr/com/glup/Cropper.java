package capr.com.glup;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.edmodo.cropper.CropImageView;
import com.edmodo.cropper.cropwindow.CropOverlayView;

import capr.com.application.Glup_Application;
import capr.com.dialog.Dialog_Cropper;

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

        Bitmap current_bitmap = ((Glup_Application)getApplication()).getCurrent_bitmap();

        Matrix m = new Matrix();
        m.setRectToRect(new RectF(0, 0, current_bitmap.getWidth(), current_bitmap.getHeight()), new RectF(0, 0, width, height), Matrix.ScaleToFit.CENTER);
        Bitmap resizedBitmap = Bitmap.createBitmap(current_bitmap, 0, 0, current_bitmap.getWidth(),current_bitmap.getHeight()/2, m, true);

        cropper = (CropImageView)findViewById(R.id.cropperimgview);
        cropper.setImageBitmap(resizedBitmap);
        cropper.setFixedAspectRatio(true);
        cropper.setAspectRatio(mAspectRatioX, mAspectRatioY);

        crop = (Button)findViewById(R.id.crop);
        crop.setOnClickListener(Cropper.this);

    }

    @Override
    public void onClick(View v) {
        bitmap = cropper.getCroppedImage();
    }
}
