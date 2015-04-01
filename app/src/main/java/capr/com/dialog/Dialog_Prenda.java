package capr.com.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import capr.com.beans.Modelo_DTO;
import capr.com.glup.R;
import capr.com.util.Util_Fonts;
import capr.com.views.Glup_Button;

/**
 * Created by Gantz on 20/02/15.
 */
public class Dialog_Prenda extends AlertDialog {

    private Button prenda;
    private TextView title;
    private ArrayList<Modelo_DTO> modelos;
    private LinearLayout container;
    private ActionBarActivity activity;

    public Dialog_Prenda(Context context,ArrayList<Modelo_DTO> modelos,ActionBarActivity activity) {
        super(context);
        this.activity = activity;
        this.modelos = modelos;
        initDialog();
    }

    public Dialog_Prenda(Context context, int theme,ArrayList<Modelo_DTO> modelos,ActionBarActivity activity) {
        super(context, theme);
        this.activity = activity;
        this.modelos = modelos;
        initDialog();
    }

    private void initDialog() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.dialog_prenda, null);
        setView(view);

        title = (TextView) view.findViewById(R.id.title);
        container = (LinearLayout) view.findViewById(R.id.container);
        title.setTypeface(Util_Fonts.setPNASemiBold(getContext()));

        for (int i = 0; i < modelos.size(); i++) {
            Glup_Button glup_button = new Glup_Button(getContext(),activity);
            glup_button.setButtonTitle(modelos.get(i).getTitle());
            container.addView(glup_button);
        }
    }


    public void setText(String text) {
        title.setText(text);
    }
}
