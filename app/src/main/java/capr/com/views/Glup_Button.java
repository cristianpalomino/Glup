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
import android.widget.LinearLayout;

import capr.com.datasource.Glup_Datasource;
import capr.com.glup.Modelo;
import capr.com.glup.Prenda;
import capr.com.glup.R;
import capr.com.util.Util_Fonts;

/**
 * Created by S30839 on 28/02/2015.
 */
public class Glup_Button extends LinearLayout implements View.OnClickListener {

    protected View view;
    private Button option;
    private ActionBarActivity activity;

    public Glup_Button(Context context,ActionBarActivity activity) {
        super(context);
        this.activity = activity;
        initView();
    }

    public Glup_Button(Context context, AttributeSet attrs,ActionBarActivity activity) {
        super(context, attrs);
        this.activity = activity;
        initView();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Glup_Button(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes,ActionBarActivity activity) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.activity = activity;
        initView();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public Glup_Button(Context context, AttributeSet attrs, int defStyleAttr,ActionBarActivity activity) {
        super(context, attrs, defStyleAttr);
        this.activity = activity;
        initView();
    }

    protected void initView() {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.glup_button, this, true);
        option = (Button) view.findViewById(R.id.option);
        option.setTypeface(Util_Fonts.setPNASemiBold(getContext()));
        option.setOnClickListener(this);
    }

    public void setButtonTitle(String title){
        option.setText(title);
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
}
