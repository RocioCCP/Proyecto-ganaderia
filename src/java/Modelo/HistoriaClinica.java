/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Rocio Castillo
 */
public class HistoriaClinica {
    private int idHistoriaClinica;
    private int vacaId;
    private int medicamentosId;
    private int procedimientosId;
    private String fechaproceso;
    private String recomendaciones;

    public int getIdHistoriaClinica() {
        return idHistoriaClinica;
    }

    public void setIdHistoriaClinica(int idHistoriaClinica) {
        this.idHistoriaClinica = idHistoriaClinica;
    }

    public int getVacaId() {
        return vacaId;
    }

    public void setVacaId(int vacaId) {
        this.vacaId = vacaId;
    }

    public int getMedicamentosId() {
        return medicamentosId;
    }

    public void setMedicamentosId(int medicamentosId) {
        this.medicamentosId = medicamentosId;
    }

    public int getProcedimientosId() {
        return procedimientosId;
    }

    public void setProcedimientosId(int procedimientosId) {
        this.procedimientosId = procedimientosId;
    }

    public String getFechaproceso() {
        return fechaproceso;
    }

    public void setFechaproceso(String fechaproceso) {
        this.fechaproceso = fechaproceso;
    }

    public String getRecomendaciones() {
        return recomendaciones;
    }

    public void setRecomendaciones(String recomendaciones) {
        this.recomendaciones = recomendaciones;
    }
    
}
