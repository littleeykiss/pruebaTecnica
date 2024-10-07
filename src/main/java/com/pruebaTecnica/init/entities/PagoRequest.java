package com.pruebaTecnica.init.entities;

public class PagoRequest {
    private String fechaActual;
    private Double tasaInteres;
    private Integer diasAnioComercial;
    
	public String getFechaActual() {
		return fechaActual;
	}
	public void setFechaActual(String fechaActual) {
		this.fechaActual = fechaActual;
	}
	public Double getTasaInteres() {
		return tasaInteres;
	}
	public void setTasaInteres(Double tasaInteres) {
		this.tasaInteres = tasaInteres;
	}
	public Integer getDiasAnioComercial() {
		return diasAnioComercial;
	}
	public void setDiasAnioComercial(Integer diasAnioComercial) {
		this.diasAnioComercial = diasAnioComercial;
	}
	public PagoRequest(String fechaActual, Double tasaInteres, Integer diasAnioComercial) {
		super();
		this.fechaActual = fechaActual;
		this.tasaInteres = tasaInteres;
		this.diasAnioComercial = diasAnioComercial;
	}

	public PagoRequest() {
		
	}
    // Getters y Setters
    
    
}