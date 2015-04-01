package capr.com.glup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import capr.com.dialog.Dialog_Prenda;
import capr.com.master.Master;
import capr.com.util.Session_Manager;
import capr.com.util.Util_Fonts;

/**
 * Created by Gantz on 20/02/15.
 */
public class Prenda extends Master implements View.OnClickListener {

    private Button prendaa;
    private Button prendab;
    private Button prendac;
    private Button salir;
    private TextView user;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Modelo de Prenda");
        setContentView(R.layout.prenda);
        getSupportActionBar().hide();

        title = (TextView) findViewById(R.id.title);
        prendaa = (Button) findViewById(R.id.prendaa);
        prendab = (Button) findViewById(R.id.prendab);
        prendac = (Button) findViewById(R.id.prendac);
        user = (TextView) findViewById(R.id.user);
        salir = (Button) findViewById(R.id.salir);

        prendaa.setTypeface(Util_Fonts.setPNASemiBold(this));
        prendab.setTypeface(Util_Fonts.setPNASemiBold(this));
        prendac.setTypeface(Util_Fonts.setPNASemiBold(this));
        salir.setTypeface(Util_Fonts.setPNASemiBold(this));
        title.setTypeface(Util_Fonts.setPNASemiBold(this));
        user.setTypeface(Util_Fonts.setPNASemiBold(this));

        prendaa.setOnClickListener(this);
        prendab.setOnClickListener(this);
        prendac.setOnClickListener(this);
        salir.setOnClickListener(this);

        prendaa.setText(getData().getPrendas().get(0).getTitle());
        prendab.setText(getData().getPrendas().get(1).getTitle());

        /*
        Name user
         */
        user.setText(new Session_Manager(this).getName());
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if(v.equals(salir)){
            Intent intent = new Intent(Prenda.this,Glup.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            Prenda.this.finish();
            new Session_Manager(Prenda.this).cerrarSession();
        }else{
            String value = ((Button)v).getText().toString();
            Dialog_Prenda dialog_prenda = new Dialog_Prenda(Prenda.this,getData().getModelos(value),Prenda.this);
            dialog_prenda.show();
        }
    }
}
