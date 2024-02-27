package com.example.demo.bussiness;

import java.util.List;

import com.example.demo.common.exceptions.ServicioException;
import com.example.demo.dto.VentaDTO;
import com.example.demo.entities.Coche;
import com.example.demo.entities.Venta;

public interface ServicioVenta {
	
	List<Venta> listVentas() throws ServicioException;

	Venta grabarVenta(VentaDTO ventaDTO) throws Exception;

	Double beneficios() throws ServicioException;

	List<Coche> listaCochesEmpleado(String nombre) throws ServicioException;

}
