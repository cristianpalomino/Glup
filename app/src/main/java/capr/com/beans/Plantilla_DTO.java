package capr.com.beans;

import android.util.Log;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by S30839 on 28/02/2015.
 */
public class Plantilla_DTO {

    private String genero;
    private String tipo;
    private String modelo;
    private String tipomodelo;
    private String correlativo;
    private String estilo;
    private String lado;
    private String nombre;
    private int image_id;

    private ArrayList<String> names = new ArrayList<>();

    public Plantilla_DTO(String nombre, int image_id) {
        this.nombre = nombre;
        this.image_id = image_id;

        StringTokenizer st = new StringTokenizer(nombre, "_");
        while (st.hasMoreTokens()) {
            names.add(st.nextToken());
        }
    }

    public int getImage_id() {
        return image_id;
    }

    public String getGenero() {
        return names.get(0);
    }

    public String getTipo() {
        return names.get(1);
    }

    public String getModelo() {
        return names.get(2);
    }

    public String getTipomodelo() {
        return names.get(3);
    }

    public String getCorrelativo() {
        return names.get(4);

    }

    public String getNombre() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < (names.size() - 1); i++) {
            String output = names.get(i).substring(0, 1).toUpperCase() + names.get(i).substring(1);
            sb.append(output+"_");
        }
        return sb.toString();
    }

    public String getEstilo() {
        return names.get(5);
    }

    public String getLado() {
        return names.get(6);
    }
}
