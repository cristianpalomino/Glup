package capr.com.dialog;

import android.app.AlertDialog;
import android.app.admin.DeviceAdminInfo;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.edmodo.cropper.CropImageView;
import com.squareup.picasso.Picasso;

import java.io.File;

import capr.com.glup.Detalle;
import capr.com.glup.R;

/**
 * Created by Gantz on 22/10/14.
 */
public class Dialog_Cropper extends AlertDialog {

    private Bitmap bitmap;
    private OnCroppedImage onCroppedImage;

    private int mAspectRatioX = 5;
    private int mAspectRatioY = 5;

    public void setOnCroppedImage(OnCroppedImage onCroppedImage) {
        this.onCroppedImage = onCroppedImage;
    }

    public Dialog_Cropper(Context context, Bitmap bitmap) {
        super(context);
        this.bitmap = bitmap;
        initDialog();
    }

    /**
     * Construct an AlertDialog that uses an explicit theme.  The actual style
     * that an AlertDialog uses is a private implementation, however you can
     * here supply either the name of an attribute in the theme from which
     * to get the dialog's style (such as {@link android.R.attr#alertDialogTheme}
     * or one of the constants {@link #THEME_TRADITIONAL},
     * {@link #THEME_HOLO_DARK}, or {@link #THEME_HOLO_LIGHT}.
     *
     * @param context
     * @param theme
     */
    public Dialog_Cropper(Context context, int theme, Bitmap bitmap) {
        super(context, theme);
        this.bitmap = bitmap;
        initDialog();
    }

    public Dialog_Cropper(Context context, boolean cancelable, OnCancelListener cancelListener, Bitmap bitmap) {
        super(context, cancelable, cancelListener);
        this.bitmap = bitmap;
        initDialog();
    }

    private void initDialog() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.dialog_cropper, null);
        setView(view);

        final CropImageView cropImageView = (CropImageView)view.findViewById(R.id.cropperimgview);

        cropImageView.setImageBitmap(bitmap);
        cropImageView.setFixedAspectRatio(true);
        cropImageView.setAspectRatio(mAspectRatioX, mAspectRatioY);

        view.findViewById(R.id.crop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_Cropper.this.dismiss();
                Dialog_Cropper.this.hide();

                Bitmap croppedImage = cropImageView.getCroppedImage();
                onCroppedImage.onCroppedImage(croppedImage);
            }
        });
    }

    public interface OnCroppedImage {
        void onCroppedImage(Bitmap bmp);
    }
}