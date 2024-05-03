
package Modelo;

/**
 *
 * @author Rocio Castillo
 */
public class Vaca {
    
    private int idVaca;
    private int usuarioId;
    private String numPlaca;
    private String nombreVaca;
    private String fechanacimiento;
    private String madre;
    private String padre;    

    public int getIdVaca() {
        return idVaca;
    }

    public void setIdVaca(int idVaca) {
        this.idVaca = idVaca;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNumPlaca() {
        return numPlaca;
    }

    public void setNumPlaca(String numPlaca) {
        this.numPlaca = numPlaca;
    }

    public String getNombreVaca() {
        return nombreVaca;
    }

    public void setNombreVaca(String nombreVaca) {
        this.nombreVaca = nombreVaca;
    }

    public String getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(String fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public String getMadre() {
        return madre;
    }

    public void setMadre(String madre) {
        this.madre = madre;
    }

    public String getPadre() {
        return padre;
    }

    public void setPadre(String padre) {
        this.padre = padre;
    }
}
    