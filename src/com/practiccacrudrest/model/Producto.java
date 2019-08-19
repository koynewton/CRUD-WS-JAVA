package com.practiccacrudrest.model;

public class Producto {
	
	int idProducto;
	String nombreProducto;
	Double precioProducto;
	int existenciasProducto;
	
	
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	
	public int getIdProducto() {
		return this.idProducto;
	} 
	
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	
	public String getNombreProducto() {
		return this.nombreProducto;
	}
	
	public void setPrecioProducto(Double precioProducto) {
		this.precioProducto = precioProducto;
	}
	
	public Double getPrecioProducto() {
		return this.precioProducto;
	}
	
	public void setExistenciasProducto(int existenciasProducto) {
		this.existenciasProducto = existenciasProducto;
	}
	
	public int getExistenciasProducto() {
		return this.existenciasProducto;
	}
	

}
