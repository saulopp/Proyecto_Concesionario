package com.example.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="CLIENTE")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	@Column(name="NIF")
	private String nif;
	@Column(name="NOMBRE")
	private String nombre;
	@Column(name="DIRECCION")
	private String direccion;
	@Column(name="CIUDAD")
	private String ciudad;
	@Column(name="TELEFONO")
	private String telefono;
	@Column(name="CATEGORIA")
	private String categoria;
	@Column(name="NVENTAS")
	private Integer nVentas;
	@Column(name="ESTADOALTA")
	private Boolean estadoAlta;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNif() {
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public Integer getnVentas() {
		return nVentas;
	}
	public void setnVentas(Integer nVentas) {
		this.nVentas = nVentas;
	}
	public Boolean getEstadoAlta() {
		return estadoAlta;
	}
	public void setEstadoAlta(Boolean estadoAlta) {
		this.estadoAlta = estadoAlta;
	}
	
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nif=" + nif + ", nombre=" + nombre + ", direccion=" + direccion + ", ciudad="
				+ ciudad + ", telefono=" + telefono + ", categoria=" + categoria + ", nVentas=" + nVentas
				+ ", estadoAlta=" + estadoAlta + "]";
	}
}