package capr.com.modulos;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

import capr.com.beans.Imagen_DTO;
import capr.com.interfaces.Interface_Notification;
import capr.com.interfaces.Interface_Upload_Image;
import capr.com.util.Glup_WS;

/**
 * Created by Gantz on 3/11/14.
 */
public class Modulo_Upload_Image implements Interface_Upload_Image {

    private Interface_Notification interface_notification = new Modulo_Notificacion();

    @Override
    public void uploadImages(Context context, ArrayList<Imagen_DTO> imagen_dtos,String a,String b) {
        if (imagen_dtos.size() > 0) {
            interface_notification.createNotification(context);
            interface_notification.getBuilder().setContentTitle("Subiendo Imagenes - Glup");
            interface_notification.setProgress(imagen_dtos.size(), 0);
            upload(context, imagen_dtos,a,b);
        }
    }

    public void upload(final Context context, final ArrayList<Imagen_DTO> imagen_dtos,String a,String b) {
        try {

            AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
            asyncHttpClient.setTimeout(9000000);

            RequestParams params = new RequestParams();
            params.put("uploaded_fileA", imagen_dtos.get(0).getImagenFile());
            params.put("name_fileA", a);
            params.put("uploaded_fileB", imagen_dtos.get(1).getImagenFile());
            params.put("name_fileB", b);

            Log.e("URI",imagen_dtos.get(0).getImagenFile().getAbsolutePath());

            asyncHttpClient.post(context, Glup_WS.IMAGES_PRENDA_GLUP, params, new TextHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, String response) {
                    interface_notification.getBuilder().setContentTitle("Listo").setProgress(0, 0, false);
                    interface_notification.invalidate();
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    Log.e(Modulo_Upload_Image.class.getName(), responseString);
                    interface_notification.getBuilder().setContentTitle("Listo").setProgress(0, 0, false);
                    interface_notification.invalidate();
                }

                @Override
                public void onProgress(int bytesWritten, int totalSize) {
                    super.onProgress(bytesWritten, totalSize);

                    int progressPercentage = 100 * bytesWritten / totalSize;
                    if (progressPercentage > 95 && progressPercentage < 101) {
                        interface_notification.getBuilder().setProgress(0, 0, true);
                        interface_notification.getManager().notify(1, interface_notification.getBuilder().build());
                    } else {
                        interface_notification.setProgress(100, progressPercentage);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}