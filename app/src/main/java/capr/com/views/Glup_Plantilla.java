package capr.com.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import capr.com.datasource.Glup_Datasource;
import capr.com.glup.Modelo;
import capr.com.glup.Prenda;
import capr.com.glup.R;
import capr.com.util.Util_Fonts;

/**
 * Created by S30839 on 28/02/2015.
 */
public class Glup_Plantilla extends ImageView implements View.OnClickListener {

    protected View view;
    private Button option;
    private ActionBarActivity activity;
    private OnSizeChanged onSizeChanged;

    public void setOnSizeChanged(OnSizeChanged onSizeChanged) {
        this.onSizeChanged = onSizeChanged;
    }

    public Glup_Plantilla(Context context) {
        super(context);
    }

    public Glup_Plantilla(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Glup_Plantilla(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public Glup_Plantilla(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        onSizeChanged.onSizeChanged(w,h,oldw,oldh);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        ((Prenda)activity).setTipos(new Glup_Datasource().getTipos(option.getText().toString()));
        activity.startActivity(new Intent(activity, Modelo.class));
    }

    public interface OnSizeChanged {
        void onSizeChanged(int w, int h, int oldw, int oldh);
    }
}
