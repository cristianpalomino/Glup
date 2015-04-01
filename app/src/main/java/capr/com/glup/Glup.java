package capr.com.glup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import capr.com.beans.User_DTO;
import capr.com.master.Master;
import capr.com.util.Glup_WS;
import capr.com.util.Session_Manager;
import capr.com.util.Util_Fonts;


public class Glup extends Master implements View.OnClickListener {

    private Button login;
    private EditText user;
    private EditText password;
    private ProgressDialog dialog;


    @Override
    protected void onStart() {
        super.onStart();
        Session_Manager session_manager = new Session_Manager(this);
        if(session_manager.isLogin()){
            Glup.this.finish();
            startActivity(new Intent(this,Prenda.class));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_glup);

        login = (Button) findViewById(R.id.login);
        user = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.password);

        login.setTypeface(Util_Fonts.setPNASemiBold(this));
        user.setTypeface(Util_Fonts.setPNASemiBold(this));
        password.setTypeface(Util_Fonts.setPNASemiBold(this));

        user.setFilters(new InputFilter[] {new InputFilter.AllCaps()});
        login.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_glup, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        String usuario = user.getText().toString();
        String pass = password.getText().toString();
        openLogin(usuario,pass);
    }

    private void openLogin(String user,String pass){

        dialog = ProgressDialog.show(Glup.this,null,"Wait...",true,false);

        RequestParams params = new RequestParams();
        params.put("user",user);
        params.put("pass",pass);

        AsyncHttpClient httpClient = new AsyncHttpClient();
        httpClient.post(this,Glup_WS.LOGIN_GLUP,params,new TextHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String response) {

                dialog.hide();
                dialog.dismiss();

                Session_Manager session_manager = new Session_Manager(Glup.this);

                try {
                    JSONObject rpta = new JSONObject(response);
                    String indicator = rpta.getString("indicador");
                    if(indicator.equals("true")){
                        User_DTO user_dto = new User_DTO();
                        user_dto.setCodigo_empresa(rpta.getString("codEmpresa"));
                        user_dto.setNombre_empresa(rpta.getString("nomEmpresa"));

                        session_manager.crearSession(user_dto);

                        Intent intent = new Intent(Glup.this,Prenda.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        Glup.this.finish();

                    }else{
                        Toast.makeText(Glup.this,"Usuario o Contraseña invalidos",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(Glup.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String errorResponse, Throwable throwable) {
                dialog.hide();
                Toast.makeText(Glup.this,errorResponse,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
