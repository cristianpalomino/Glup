package capr.com.modulos;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import capr.com.glup.R;
import capr.com.interfaces.Interface_Notification;

/**
 * Created by Gantz on 3/11/14.
 */
public class Modulo_Notificacion implements Interface_Notification {

    private final int id = 1;
    private NotificationCompat.Builder builder;
    private NotificationManager manager;

    @Override
    public void createNotification(Context context) {
        manager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        builder = new NotificationCompat.Builder(context);

        builder.setContentTitle("Enviando Imagenes");
        builder.setContentText("Cargando Imagenes al Servidor");
        builder.setSmallIcon(R.drawable.ic_stat_file_file_upload);
        builder.setShowWhen(true);
    }

    @Override
    public void setProgress(int max,int progress) {
        builder.setProgress(max,progress,false);
        manager.notify(id, builder.build());
    }

    @Override
    public void onFinish() {
        builder.setContentText("Download complete").setProgress(0,0,false);
        manager.notify(id,builder.build());
    }

    @Override
    public void invalidate() {
        manager.notify(id,builder.build());
    }

    @Override
    public void vibrate() {
        builder.setVibrate(new long[]{1000,1000});
    }

    @Override
    public NotificationManager getManager() {
        return manager;
    }

    @Override
    public NotificationCompat.Builder getBuilder() {
        return builder;
    }
}