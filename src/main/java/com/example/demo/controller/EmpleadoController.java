package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bussiness.ServicioEmpleado;
import com.example.demo.common.exceptions.ServicioException;
import com.example.demo.entities.Empleado;

@RestController
@RequestMapping("/empleado")

public class EmpleadoController {
	
	@Autowired
	ServicioEmpleado servicio;
	
	@GetMapping
	public List<Empleado> list() throws ServicioException{
		
		return servicio.listEmpleados();
		
	}
	
	@PostMapping
	public Empleado create(@RequestBody Empleado empleado) throws ServicioException {
		return servicio.grabarEmpleado(empleado);
	}
}
