package capr.com.beans;

import java.util.ArrayList;

/**
 * Created by S30839 on 28/02/2015.
 */
public class Tipo_DTO extends Modelo_DTO {

    private ArrayList<Plantilla_DTO> plantillas = new ArrayList<>();

    public void setPlantillas(ArrayList<Plantilla_DTO> plantillas) {
        this.plantillas = plantillas;
    }

    public ArrayList<Plantilla_DTO> getPlantillas() {
        return plantillas;
    }

    @Override
    public String getTitle() {
        return super.getTitle();
    }

    @Override
    public void setTitle(String title) {
        super.setTitle(title);
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }

    @Override
    public void setDescription(String description) {
        super.setDescription(description);
    }
}
