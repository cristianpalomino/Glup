package capr.com.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
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
public class Dialog_Croper extends AlertDialog {

    private String imagen_dto;

    public Dialog_Croper(Context context, String imagen_dto) {
        super(context);
        this.imagen_dto = imagen_dto;
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
    public Dialog_Croper(Context context, int theme, String imagen_dto) {
        super(context, theme);
        this.imagen_dto = imagen_dto;
        initDialog();
    }

    public Dialog_Croper(Context context, boolean cancelable, OnCancelListener cancelListener, String imagen_dto) {
        super(context, cancelable, cancelListener);
        this.imagen_dto = imagen_dto;
        initDialog();
    }

    private void initDialog() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.dialog_cropper, null);
        setView(view);

    }
}