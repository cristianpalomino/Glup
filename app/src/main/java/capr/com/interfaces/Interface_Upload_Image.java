package capr.com.interfaces;

import android.content.Context;

import java.io.File;
import java.util.ArrayList;

import capr.com.beans.Imagen_DTO;

/**
 * Created by Gantz on 3/11/14.
 */
public interface Interface_Upload_Image {

    public void uploadImages(Context context,ArrayList<Imagen_DTO> imagen_dtos,String a,String b);
}