package capr.com.application;

import android.app.Application;
import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

import capr.com.beans.Plantilla_DTO;
import capr.com.beans.Tipo_DTO;

/**
 * Created by S30839 on 28/02/2015.
 */
public class Glup_Application extends Application {

    private Bitmap current_bitmap;
    private ArrayList<Tipo_DTO> tipos;
    private Tipo_DTO tipo_dto;
    private Plantilla_DTO plantilla_dto;
    private ImageView currentImageView;
    private ImageView previewCurrent;
    private ArrayList<String> current_uri = new ArrayList<>();
    private int orientation;

    public OnTake onTake;

    public OnTake getOnTake() {
        return onTake;
    }

    public void setOnTake(OnTake onTake) {
        this.onTake = onTake;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        current_uri.add("");
        current_uri.add("");

        DisplayImageOptions displayimageOptions = new DisplayImageOptions.Builder().cacheInMemory().cacheOnDisc().build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext()).
                                              defaultDisplayImageOptions(displayimageOptions).build();
        ImageLoader.getInstance().init(config);
    }

    public Bitmap getCurrent_bitmap() {
        return current_bitmap;
    }

    public void setCurrent_bitmap(Bitmap current_bitmap) {
        this.current_bitmap = current_bitmap;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public int getOrientation() {
        return orientation;
    }

    public ArrayList<String> getCurrent_uri() {
        return current_uri;
    }

    public void setCurrent_uri(ArrayList<String> current_uri) {
        this.current_uri = current_uri;
    }

    public ImageView getCurrentImageView() {
        return currentImageView;
    }

    public void setCurrentImageView(ImageView currentImageView) {
        this.currentImageView = currentImageView;
    }

    public void setPlantilla_dto(Plantilla_DTO plantilla_dto) {
        this.plantilla_dto = plantilla_dto;
    }

    public Plantilla_DTO getPlantilla_dto() {
        return plantilla_dto;
    }

    public void setTipo_dto(Tipo_DTO tipo_dto) {
        this.tipo_dto = tipo_dto;
    }

    public Tipo_DTO getTipo_dto() {
        return tipo_dto;
    }

    public ArrayList<Tipo_DTO> getTipos() {
        return tipos;
    }

    public void setTipos(ArrayList<Tipo_DTO> tipos) {
        this.tipos = tipos;
    }

    public ImageView getPreviewCurrent() {
        return previewCurrent;
    }

    public void setPreviewCurrent(ImageView previewCurrent) {
        this.previewCurrent = previewCurrent;
    }

    public interface OnTake{
        void OnTake(File file,String uri);
    }
}

