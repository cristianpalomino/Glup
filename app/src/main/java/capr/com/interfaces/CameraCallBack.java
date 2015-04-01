package capr.com.interfaces;

import android.graphics.Bitmap;

/**
 * Created by usuario on 28/03/15.
 */
public interface CameraCallBack {
    public void onCameraError();
    public void onPictureTaken(Bitmap bitmap);
}
