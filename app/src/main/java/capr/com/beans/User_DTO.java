package capr.com.beans;

/**
 * Created by S30839 on 13/03/2015.
 */
public class User_DTO {

    private String user;
    private String pass;
    private String indicador;
    private String codigo_empresa;
    private String nombre_empresa;


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getIndicador() {
        return indicador;
    }

    public void setIndicador(String indicador) {
        this.indicador = indicador;
    }

    public String getCodigo_empresa() {
        return codigo_empresa;
    }

    public void setCodigo_empresa(String codigo_empresa) {
        this.codigo_empresa = codigo_empresa;
    }

    public String getNombre_empresa() {
        return nombre_empresa;
    }

    public void setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
    }
}
