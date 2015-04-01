package capr.com.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import capr.com.glup.Detalle;
import capr.com.glup.R;

/**
 * Created by Gantz on 22/10/14.
 */
public class Dialog_Foto extends AlertDialog {

    private String imagen_dto;

    public Dialog_Foto(Context context, String imagen_dto) {
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
    public Dialog_Foto(Context context, int theme, String imagen_dto) {
        super(context, theme);
        this.imagen_dto = imagen_dto;
        initDialog();
    }

    public Dialog_Foto(Context context, boolean cancelable, OnCancelListener cancelListener, String imagen_dto) {
        super(context, cancelable, cancelListener);
        this.imagen_dto = imagen_dto;
        initDialog();
    }

    private void initDialog() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.dialog_preview, null);
        setView(view);

        ImageView imgfoto = (ImageView)view.findViewById(R.id.image_preview);
        Picasso.with(getContext()).load(new File("file:///storage/emulated/0/A.png")).into(imgfoto);

    }


    public String getRealPathFromURI(Uri contentUri)
    {
        try
        {
            String[] proj = {MediaStore.Images.Media.DATA};
            Cursor cursor = ((Detalle)getContext()).managedQuery(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        catch (Exception e)
        {
            return contentUri.getPath();
        }
    }

}