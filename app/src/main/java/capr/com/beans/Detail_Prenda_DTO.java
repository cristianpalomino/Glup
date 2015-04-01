package capr.com.beans;

/**
 * Created by S30839 on 28/02/2015.
 */
public class Detail_Prenda_DTO extends Prenda_DTO {

    private String name;
    private String marca;
    private String talla;
    private String genero;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
