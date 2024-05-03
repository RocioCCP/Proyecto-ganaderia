/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Rocio Castillo
 */
public class Produccion {
    
    private int idProduccion;
    private int vacaId;
    private String fecha;
    private Double producido1;
     private Double producido2;
     private Double totalproduc;
     private String observaciones;

    public int getIdProduccion() {
        return idProduccion;
    }

    public void setIdProduccion(int idProduccion) {
        this.idProduccion = idProduccion;
    }

    public int getVacaId() {
        return vacaId;
    }

    public void setVacaId(int vacaId) {
        this.vacaId = vacaId;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Double getProducido1() {
        return producido1;
    }

    public void setProducido1(Double producido1) {
        this.producido1 = producido1;
    }

    public Double getProducido2() {
        return producido2;
    }

    public void setProducido2(Double producido2) {
        this.producido2 = producido2;
    }

    public Double getTotalproduc() {
        return totalproduc;
    }

    public void setTotalproduc(Double totalproduc) {
        this.totalproduc = totalproduc;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}

  