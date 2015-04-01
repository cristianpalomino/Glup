package capr.com.glup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Required;
import com.squareup.picasso.Picasso;

import org.apache.http.Header;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

import capr.com.application.Glup_Application;
import capr.com.beans.Imagen_DTO;
import capr.com.beans.Plantilla_DTO;
import capr.com.beans.Prenda_DTO;
import capr.com.beans.Tipo_DTO;
import capr.com.dialog.Dialog_Foto;
import capr.com.glup.v2.A_Camara;
import capr.com.interfaces.Interface_Upload_Image;
import capr.com.modulos.Modulo_Upload_Image;
import capr.com.util.Glup_WS;
import capr.com.util.Session_Manager;
import capr.com.util.Util_Fonts;


public class Detalle extends ActionBarActivity implements Glup_Application.OnTake {

    private static final int REQ_CAMERA_IMAGE = 123;
    private ProgressDialog dialog;

    private TextView ladoa;
    private TextView ladob;

    private ImageView imagea;
    private ImageView imageb;

    @Required(order = 1, message = "El campo Marca es requerido")
    private EditText marca;
    @Required(order = 1, message = "El campo Talla es requerido")
    private EditText talla;

    private TextView genero;
    private TextView tipoprenda;
    private TextView modeloprenda;
    private TextView tipomodelo;
    private TextView codeuser;
    private TextView estilo;
    private TextView correlativo;

    private Button enviar;

    private Tipo_DTO tipo_dto;
    private ArrayList<String> uris;
    String corre = "NONE";
    private Validator validator;

    private ImageView preview_a;
    private ImageView preview_b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.detail);
        validator = new Validator(this);
        ((Glup_Application) getApplication()).setOnTake(this);

        tipo_dto = ((Glup_Application) getApplication()).getTipo_dto();

        ladoa = (TextView) findViewById(R.id.ladoa);
        ladob = (TextView) findViewById(R.id.ladob);
        imagea = (ImageView) findViewById(R.id.imageladoa);
        imageb = (ImageView) findViewById(R.id.imageladob);
        marca = (EditText) findViewById(R.id.marca);
        talla = (EditText) findViewById(R.id.talla);
        genero = (TextView) findViewById(R.id.genero);
        tipoprenda = (TextView) findViewById(R.id.tipoprenda);
        modeloprenda = (TextView) findViewById(R.id.modeloprenda);
        tipomodelo = (TextView) findViewById(R.id.tipomodelo);
        codeuser = (TextView) findViewById(R.id.codigo);
        estilo = (TextView) findViewById(R.id.estilo);
        correlativo = (TextView) findViewById(R.id.correlativo);
        enviar = (Button) findViewById(R.id.enviar);
        preview_a = (ImageView) findViewById(R.id.preview_a);
        preview_b = (ImageView) findViewById(R.id.preview_b);

        ladoa.setTypeface(Util_Fonts.setPNASemiBold(this));
        ladob.setTypeface(Util_Fonts.setPNASemiBold(this));
        marca.setTypeface(Util_Fonts.setPNASemiBold(this));
        talla.setTypeface(Util_Fonts.setPNASemiBold(this));
        genero.setTypeface(Util_Fonts.setPNASemiBold(this));
        tipoprenda.setTypeface(Util_Fonts.setPNASemiBold(this));
        modeloprenda.setTypeface(Util_Fonts.setPNASemiBold(this));
        tipomodelo.setTypeface(Util_Fonts.setPNASemiBold(this));
        codeuser.setTypeface(Util_Fonts.setPNASemiBold(this));
        estilo.setTypeface(Util_Fonts.setPNASemiBold(this));
        correlativo.setTypeface(Util_Fonts.setPNASemiBold(this));
        enviar.setTypeface(Util_Fonts.setPNASemiBold(this));

        final Plantilla_DTO plantilla_dto = tipo_dto.getPlantillas().get(0);

        codeuser.setText(new Session_Manager(this).getCode().toUpperCase());
        estilo.setText(plantilla_dto.getEstilo().substring(0, 1).toUpperCase() + plantilla_dto.getEstilo().substring(1));
        tipoprenda.setText(plantilla_dto.getTipo().substring(0, 1).toUpperCase() + plantilla_dto.getTipo().substring(1));
        modeloprenda.setText(plantilla_dto.getModelo().substring(0, 1).toUpperCase() + plantilla_dto.getModelo().substring(1));
        tipomodelo.setText(plantilla_dto.getTipomodelo().substring(0, 1).toUpperCase() + plantilla_dto.getTipomodelo().substring(1));
        genero.setText(plantilla_dto.getGenero().substring(0, 1).toUpperCase() + plantilla_dto.getGenero().substring(1));
        correlativo.setText(plantilla_dto.getCorrelativo().substring(0, 1).toUpperCase() + plantilla_dto.getCorrelativo().substring(1));

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.setValidationListener(new Validator.ValidationListener() {
                    @Override
                    public void onValidationSucceeded() {
                        Prenda_DTO prenda_dto = new Prenda_DTO();
                        prenda_dto.setCodUser(codeuser.getText().toString());
                        prenda_dto.setDatoMarca(marca.getText().toString());
                        prenda_dto.setDatoTalla(talla.getText().toString());
                        prenda_dto.setEstilo(estilo.getText().toString());
                        prenda_dto.setTipoPrenda(tipoprenda.getText().toString());
                        prenda_dto.setModeloPrenda(modeloprenda.getText().toString());
                        prenda_dto.setTipoModelo(tipomodelo.getText().toString());
                        prenda_dto.setDatoGenero(genero.getText().toString());
                        prenda_dto.setCorrePrenda(correlativo.getText().toString());

                        dialog = ProgressDialog.show(Detalle.this, null, "Wait...", true, false);

                        RequestParams params = new RequestParams();
                        params.put("codUser", prenda_dto.getCodUser());
                        params.put("datoMarca", prenda_dto.getDatoMarca().substring(0, 1).toUpperCase() + prenda_dto.getDatoMarca().substring(1));
                        params.put("datoTalla", prenda_dto.getDatoTalla().substring(0, 1).toUpperCase() + prenda_dto.getDatoTalla().substring(1));
                        params.put("estilo", prenda_dto.getEstilo());
                        params.put("correPrenda", prenda_dto.getCorrePrenda());
                        params.put("posicionPrenda", prenda_dto.getTipoPrenda());
                        params.put("modeloPrenda", prenda_dto.getModeloPrenda());
                        params.put("tipoModelo", prenda_dto.getTipoModelo());
                        params.put("datoGenero", prenda_dto.getDatoGenero());

                        AsyncHttpClient httpClient = new AsyncHttpClient();
                        httpClient.post(Detalle.this, Glup_WS.DETALLE_PRENDA_GLUP, params, new TextHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, String response) {
                                dialog.hide();
                                try {
                                    Toast.makeText(Detalle.this, new JSONObject(response).getString("mensaje"), Toast.LENGTH_SHORT).show();
                                    corre = new JSONObject(response).getString("correIMG");

                                    // Send Photos

                                    ArrayList<Imagen_DTO> images = new ArrayList<Imagen_DTO>();

                                    Imagen_DTO imagen_dto = new Imagen_DTO(Detalle.this);
                                    imagen_dto.setImagenId(((Glup_Application) getApplication()).getCurrent_uri().get(0));
                                    imagen_dto.setImagenData(((Glup_Application) getApplication()).getCurrent_uri().get(0));
                                    images.add(imagen_dto);

                                    Imagen_DTO imagen_dtb = new Imagen_DTO(Detalle.this);
                                    imagen_dtb.setImagenId(((Glup_Application) getApplication()).getCurrent_uri().get(1));
                                    imagen_dtb.setImagenData(((Glup_Application) getApplication()).getCurrent_uri().get(1));
                                    images.add(imagen_dtb);

                                    images.add(imagen_dto);
                                    images.add(imagen_dtb);

                                    if (!images.isEmpty()) {
                                        String names_a = tipo_dto.getPlantillas().get(0).getNombre() + codeuser.getText().toString() + corre;
                                        String names_b = tipo_dto.getPlantillas().get(0).getNombre() + codeuser.getText().toString() + corre;

                                        Interface_Upload_Image interface_upload_image = new Modulo_Upload_Image();
                                        interface_upload_image.uploadImages(Detalle.this, images, names_a, names_b);
                                    }

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, String errorResponse, Throwable throwable) {
                                dialog.hide();
                                Toast.makeText(Detalle.this, errorResponse, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onValidationFailed(View failedView, Rule<?> failedRule) {
                        ((EditText)failedView).setError(failedRule.getFailureMessage());
                    }
                });
                validator.validate();
            }
        });

        imagea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Glup_Application) getApplication()).setPlantilla_dto(tipo_dto.getPlantillas().get(0));
                ((Glup_Application) getApplication()).setCurrentImageView(imagea);
                ((Glup_Application) getApplication()).setPreviewCurrent(preview_a);

                Intent intent = new Intent(Detalle.this, A_Camara.class);
                startActivityForResult(intent, REQ_CAMERA_IMAGE);

                /*
                PopupMenu popupMenu = new PopupMenu(Detalle.this, v);
                popupMenu.getMenuInflater().inflate(R.menu.menu_image, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.take_photo:
                                ((Glup_Application) getApplication()).setPlantilla_dto(tipo_dto.getPlantillas().get(0));
                                ((Glup_Application) getApplication()).setCurrentImageView(preview_a);

                                Intent intent = new Intent(Detalle.this, A_Camara.class);
                                startActivityForResult(intent, REQ_CAMERA_IMAGE);
                                break;

                            case R.id.show_photo:
                                uris = ((Glup_Application) getApplication()).getCurrent_uri();
                                if (uris != null && !uris.isEmpty()) {

                                    try {

                                        Intent intsent = new Intent();
                                        intsent.setAction(Intent.ACTION_VIEW);
                                        intsent.setDataAndType(Uri.parse("file://" + uris.get(0)), "image/*");
                                        startActivity(intsent);

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
                */
            }
        });

        imageb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((Glup_Application) getApplication()).setPlantilla_dto(tipo_dto.getPlantillas().get(1));
                ((Glup_Application) getApplication()).setCurrentImageView(imageb);
                ((Glup_Application) getApplication()).setPreviewCurrent(preview_b);

                Intent intent = new Intent(Detalle.this, A_Camara.class);
                startActivityForResult(intent, REQ_CAMERA_IMAGE);

                /*
                PopupMenu popupMenu = new PopupMenu(Detalle.this, v);
                popupMenu.getMenuInflater().inflate(R.menu.menu_image, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.take_photo:
                                ((Glup_Application) getApplication()).setPlantilla_dto(tipo_dto.getPlantillas().get(1));
                                ((Glup_Application) getApplication()).setCurrentImageView(preview_b);

                                Intent intent = new Intent(Detalle.this, A_Camara.class);
                                startActivityForResult(intent, REQ_CAMERA_IMAGE);
                                break;

                            case R.id.show_photo:
                                uris = ((Glup_Application) getApplication()).getCurrent_uri();
                                if (uris != null && !uris.isEmpty()) {

                                    Intent intsent = new Intent();
                                    intsent.setAction(Intent.ACTION_VIEW);
                                    intsent.setDataAndType(Uri.parse("file://" + uris.get(1)), "image/*");
                                    startActivity(intsent);
                                }
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
                */
            }
        });
    }


    /*
    Camera - UTIL
     */
    private boolean isCamera() {
        return !getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    @Override
    public void OnTake(File file, String uri) {
        ImageView imageview = ((Glup_Application) getApplication()).getCurrentImageView();
        if(imageview.equals(imagea)){
            Log.e("LOCAL","A");
            ((Glup_Application) getApplication()).getCurrent_uri().set(0,uri);
        }else{
            Log.e("LOCAL","B");
            ((Glup_Application) getApplication()).getCurrent_uri().set(1,uri);
        }
    }

}
