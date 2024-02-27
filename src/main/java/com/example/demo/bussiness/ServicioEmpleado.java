package com.example.demo.bussiness;

import java.util.List;

import com.example.demo.common.exceptions.ServicioException;
import com.example.demo.entities.Empleado;

public interface ServicioEmpleado {
	List<Empleado> listEmpleados() throws ServicioException;

	Empleado grabarEmpleado(Empleado empleado) throws ServicioException;
	
	Empleado conseguirEmpleado(Integer id) throws ServicioException;
	
	void eliminarEmpleado(Integer id) throws Exception;
	
}
