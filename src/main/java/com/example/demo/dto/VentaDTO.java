package com.example.demo.dto;

import java.util.Date;

public class VentaDTO{
	
	private Integer idCliente;

	private String idCoche;
	
	private Integer idEmpleado;
	
	private Date fecha;
	
	private Double monto;

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getIdCoche() {
		return idCoche;
	}

	public void setIdCoche(String idCoche) {
		this.idCoche = idCoche;
	}

	public Integer getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	@Override
	public String toString() {
		return "VentaDTO [idCliente=" + idCliente + ", idCoche=" + idCoche + ", idEmpleado=" + idEmpleado + ", fecha="
				+ fecha + ", monto=" + monto + "]";
	}
	
}

