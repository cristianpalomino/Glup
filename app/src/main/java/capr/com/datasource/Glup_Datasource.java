package capr.com.datasource;

import android.widget.Toast;

import java.util.ArrayList;

import capr.com.beans.Modelo_DTO;
import capr.com.beans.Plantilla_DTO;
import capr.com.beans.Prenda_DTO;
import capr.com.beans.Tipo_DTO;
import capr.com.glup.R;

/**
 * Created by S30839 on 28/02/2015.
 */
public class Glup_Datasource {

    public ArrayList<Prenda_DTO> getPrendas() {
        Prenda_DTO prendaa = new Prenda_DTO();
        prendaa.setTitle("Prenda A");
        prendaa.setDescription("Description A");

        Prenda_DTO prendab = new Prenda_DTO();
        prendab.setTitle("Prenda B");
        prendab.setDescription("Description B");

        ArrayList<Prenda_DTO> prendas = new ArrayList<>();
        prendas.add(prendaa);
        prendas.add(prendab);

        return prendas;
    }

    public ArrayList<Modelo_DTO> getModelos(String tipe) {
        ArrayList<Modelo_DTO> modelos = new ArrayList<>();
        Modelo_DTO polo = new Modelo_DTO();
        Modelo_DTO blusa = new Modelo_DTO();
        Modelo_DTO pantalon = new Modelo_DTO();
        Modelo_DTO chort = new Modelo_DTO();
        Modelo_DTO falda = new Modelo_DTO();

        polo.setTitle("Polo");
        polo.setDescription("D. Polo");

        blusa.setTitle("Blusa");
        blusa.setDescription("D. Blusa");

        pantalon.setTitle("Pantalon");
        pantalon.setDescription("D.Pantalon");

        chort.setTitle("Short");
        chort.setDescription("D. Short");

        falda.setTitle("Falda");
        falda.setDescription("D. Falda");

        if (tipe.matches("Prenda A")) {
            modelos.add(polo);
            modelos.add(blusa);
        } else {
            modelos.add(pantalon);
            modelos.add(chort);
            modelos.add(falda);
        }

        return modelos;
    }

    public ArrayList<Tipo_DTO> getTipos(String model) {

        ArrayList<Tipo_DTO> tipos = new ArrayList<>();

        Tipo_DTO polo_copa = new Tipo_DTO();
        Tipo_DTO polo_v = new Tipo_DTO();
        Tipo_DTO blusav = new Tipo_DTO();
        Tipo_DTO jean = new Tipo_DTO();
        Tipo_DTO bermuda = new Tipo_DTO();
        Tipo_DTO falda_larga = new Tipo_DTO();
        Tipo_DTO short_corto = new Tipo_DTO();

        polo_copa.setTitle("Polo Copa");
        polo_copa.setDescription("D. Polo Copa");
        ArrayList<Plantilla_DTO> plantilla_dtos_copa = new ArrayList<>();
        plantilla_dtos_copa.add(new Plantilla_DTO("h_a_polo_copa_1_slim_a",R.drawable.h_a_polo_copa_1_slim_a));
        plantilla_dtos_copa.add(new Plantilla_DTO("h_a_polo_copa_1_slim_b",R.drawable.h_a_polo_copa_1_slim_b));
        polo_copa.setPlantillas(plantilla_dtos_copa);

        polo_v.setTitle("Polo V");
        polo_v.setDescription("D. Polo V");
        ArrayList<Plantilla_DTO> plantilla_dtos_v = new ArrayList<>();
        plantilla_dtos_v.add(new Plantilla_DTO("h_a_polo_v_1_slim_a",R.drawable.h_a_polo_v_1_slim_a));
        plantilla_dtos_v.add(new Plantilla_DTO("h_a_polo_v_1_slim_b",R.drawable.h_a_polo_v_1_slim_b));
        polo_v.setPlantillas(plantilla_dtos_v);

        blusav.setTitle("Blusa V");
        blusav.setDescription("D. Blusa V");
        ArrayList<Plantilla_DTO> plantilla_dtos_blusa = new ArrayList<>();
        plantilla_dtos_blusa.add(new Plantilla_DTO("h_b_pantalon_jean_1_standard_a",R.drawable.h_b_pantalon_jean_1_standard_a));
        plantilla_dtos_blusa.add(new Plantilla_DTO("h_b_pantalon_jean_1_standard_a",R.drawable.h_b_pantalon_jean_1_standard_a));
        blusav.setPlantillas(plantilla_dtos_blusa);

        jean.setTitle("Jean");
        jean.setDescription("D. Jean");
        ArrayList<Plantilla_DTO> plantilla_dtos_jean = new ArrayList<>();
        plantilla_dtos_jean.add(new Plantilla_DTO("h_b_pantalon_jean_1_standard_a",R.drawable.h_b_pantalon_jean_1_standard_a));
        plantilla_dtos_jean.add(new Plantilla_DTO("h_b_pantalon_jean_1_standard_b",R.drawable.h_b_pantalon_jean_1_standard_b));
        jean.setPlantillas(plantilla_dtos_jean);

        bermuda.setTitle("Bermuda");
        bermuda.setDescription("D. Bermuda");
        ArrayList<Plantilla_DTO> plantilla_dtos_ber = new ArrayList<>();
        plantilla_dtos_ber.add(new Plantilla_DTO("h_b_short_bermuda_1_standard_a",R.drawable.h_b_short_bermuda_1_standard_a));
        plantilla_dtos_ber.add(new Plantilla_DTO("h_b_short_bermuda_1_standard_b",R.drawable.h_b_short_bermuda_1_standard_b));
        bermuda.setPlantillas(plantilla_dtos_ber);

        short_corto.setTitle("Short Corto");
        short_corto.setDescription("D. Short Corto");
        ArrayList<Plantilla_DTO> plantilla_s_corto = new ArrayList<>();
        plantilla_s_corto.add(new Plantilla_DTO("m_b_short_corto_1_standard_a",R.drawable.m_b_short_corto_1_standard_a));
        plantilla_s_corto.add(new Plantilla_DTO("m_b_short_corto_1_standard_b",R.drawable.m_b_short_corto_1_standard_b));
        short_corto.setPlantillas(plantilla_s_corto);

        falda_larga.setTitle("Falda Larga");
        falda_larga.setDescription("D. Falda Larga");
        ArrayList<Plantilla_DTO> plantilla_dtos_fal = new ArrayList<>();
        plantilla_dtos_fal.add(new Plantilla_DTO("m_b_falda_larga_1_standard_a",R.drawable.m_b_falda_larga_1_standard_a));
        plantilla_dtos_fal.add(new Plantilla_DTO("m_b_falda_larga_1_standard_b",R.drawable.m_b_falda_larga_1_standard_b));
        falda_larga.setPlantillas(plantilla_dtos_fal);

        if (model.equals("Polo")) {
            tipos.add(polo_v);
            tipos.add(polo_copa);
        } else if (model.equals("Blusa")) {
            tipos.add(blusav);
        } else if (model.equals("Pantalon")) {
            tipos.add(jean);
        } else if (model.equals("Short")) {
            tipos.add(bermuda);
            tipos.add(short_corto);
        } else if (model.equals("Falda")) {
            tipos.add(falda_larga);
        }

        return tipos;
    }


}
