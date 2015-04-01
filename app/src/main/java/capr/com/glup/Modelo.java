package capr.com.glup;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import capr.com.adapter.Adapter_Modelo;
import capr.com.application.Glup_Application;
import capr.com.beans.Plantilla_DTO;
import capr.com.beans.Tipo_DTO;
import capr.com.util.Util_Fonts;


public class Modelo extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private ListView modelos;
    private TextView title;
    private Button back;

    private ArrayList<Tipo_DTO> tipos;
    private Adapter_Modelo adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.tipo);

        tipos = ((Glup_Application) getApplication()).getTipos();
        modelos = (ListView) findViewById(R.id.modelos);
        title = (TextView) findViewById(R.id.title);
        back = (Button) findViewById(R.id.back);

        title.setTypeface(Util_Fonts.setPNASemiBold(this));
        back.setTypeface(Util_Fonts.setPNASemiBold(this));
        title.setText("Modelos");

        adapter = new Adapter_Modelo(Modelo.this, tipos);
        modelos.setAdapter(adapter);

        modelos.setOnItemClickListener(this);

        /*
        Back
         */
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Modelo.this.finish();
            }
        });
    }

    /**
     * Callback method to be invoked when an item in this AdapterView has
     * been clicked.
     * <p/>
     * Implementers can call getItemAtPosition(position) if they need
     * to access the data associated with the selected item.
     *
     * @param parent   The AdapterView where the click happened.
     * @param view     The view within the AdapterView that was clicked (this
     *                 will be a view provided by the adapter)
     * @param position The position of the view in the adapter.
     * @param id       The row id of the item that was clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Tipo_DTO tipo_dto = (Tipo_DTO) parent.getItemAtPosition(position);
        ((Glup_Application) getApplication()).setTipo_dto(tipo_dto);
        startActivity(new Intent(Modelo.this, Detalle.class));
    }
}
