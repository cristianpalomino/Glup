package capr.com.beans;

/**
 * Created by S30839 on 28/02/2015.
 */
public class Prenda_DTO {

    private String title;
    private String description;

    /**
     * Atributos de Prenda to Servidor
     */
    private String lado;
    private String datoMarca;
    private String datoTalla;
    private String datoGenero;
    private String tipoPrenda;
    private String modeloPrenda;
    private String tipoModelo;
    private String codUser;
    private String estilo;
    private String correPrenda;

    public Prenda_DTO() {

    }

    public Prenda_DTO(String lado, String datoMarca, String datoTalla, String datoGenero, String tipoPrenda, String modeloPrenda, String tipoModelo, String codUser, String estilo, String correPrenda) {
        this.lado = lado;
        this.datoMarca = datoMarca;
        this.datoTalla = datoTalla;
        this.datoGenero = datoGenero;
        this.tipoPrenda = tipoPrenda;
        this.modeloPrenda = modeloPrenda;
        this.tipoModelo = tipoModelo;
        this.codUser = codUser;
        this.estilo = estilo;
        this.correPrenda = correPrenda;
    }

    public void setCorrePrenda(String correPrenda) {
        this.correPrenda = correPrenda;
    }

    public String getCorrePrenda() {
        return correPrenda;
    }

    public String getLado() {
        return lado;
    }

    public void setLado(String lado) {
        this.lado = lado;
    }

    public String getDatoMarca() {
        return datoMarca;
    }

    public void setDatoMarca(String datoMarca) {
        this.datoMarca = datoMarca;
    }

    public String getDatoTalla() {
        return datoTalla;
    }

    public void setDatoTalla(String datoTalla) {
        this.datoTalla = datoTalla;
    }

    public String getDatoGenero() {
        return datoGenero;
    }

    public void setDatoGenero(String datoGenero) {
        this.datoGenero = datoGenero;
    }

    public String getTipoPrenda() {
        return tipoPrenda;
    }

    public void setTipoPrenda(String tipoPrenda) {
        this.tipoPrenda = tipoPrenda;
    }

    public String getModeloPrenda() {
        return modeloPrenda;
    }

    public void setModeloPrenda(String modeloPrenda) {
        this.modeloPrenda = modeloPrenda;
    }

    public String getTipoModelo() {
        return tipoModelo;
    }

    public void setTipoModelo(String tipoModelo) {
        this.tipoModelo = tipoModelo;
    }

    public String getCodUser() {
        return codUser;
    }

    public void setCodUser(String codUser) {
        this.codUser = codUser;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



}
