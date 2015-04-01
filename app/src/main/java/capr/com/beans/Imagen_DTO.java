package capr.com.beans;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

/**
 * Created by Gantz on 21/10/14.
 */
public class Imagen_DTO {

    private String imagenData;
    private String imagenId;

    private File imagenFile;
    private String imagenLocal;
    private String imagenRecurso;
    private Context context;

    public Imagen_DTO(Context context) {
        this.context=context;
    }

    public Imagen_DTO(String imagenData, String imagenId) {
        this.imagenData = imagenData;
        this.imagenId = imagenId;
    }

    public File getImagenFile() throws Exception{
        Log.e("DATA","file:/"+getImagenData());
        File file = new File(getImagenData());
        return file;
    }

    public void setImagenFile(File imagenFile) {
        this.imagenFile = imagenFile;
    }

    public String getImagenLocal() {
        return imagenLocal;
    }

    public void setImagenLocal(String imagenLocal) {
        this.imagenLocal = imagenLocal;
    }

    public String getImagenRecurso() {
        return imagenRecurso;
    }

    public void setImagenRecurso(String imagenRecurso) {
        this.imagenRecurso = imagenRecurso;
    }

    public String getImagenData() {
        return imagenData;
    }

    public void setImagenData(String imagenData) {
        this.imagenData = imagenData;
    }

    public String getImagenId() {
        return imagenId;
    }

    public void setImagenId(String imagenId) {
        this.imagenId = imagenId;
    }

    public Bitmap getResizedBitmap(Bitmap image, int bitmapWidth,int bitmapHeight) {
        return Bitmap.createScaledBitmap(image, bitmapWidth, bitmapHeight,true);
    }

    @Override
    public String toString() {
        return "Imagen_DTO{" +
                "imagenFile=" + imagenFile +
                ", imagenLocal='" + imagenLocal + '\'' +
                ", imagenRecurso='" + imagenRecurso + '\'' +
                '}';
    }
}