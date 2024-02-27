package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.bussiness.ServicioEmpleado;
import com.example.demo.common.exceptions.ServicioException;
import com.example.demo.entities.Empleado;

@Controller
@RequestMapping(value={"/empleadosTh"})
public class EmpleadoThController {
	
	@Autowired
	ServicioEmpleado servicio;

	@GetMapping
	public String paginaEmpleados(Model model) throws Exception {
		List<Empleado> empleados = servicio.listEmpleados();
		
		model.addAttribute("empleados", empleados);	
		return "empleados";
	}
	
	@GetMapping("/crear")
	public String nuevoEmpleado(Model model) throws ServicioException {
		model.addAttribute("empleado", new Empleado());
				
		return "agregarNuevoEmpleado";
	}
	
	@PostMapping
	public String create(@ModelAttribute Empleado empleado,Model model) throws ServicioException {
		empleado = servicio.grabarEmpleado(empleado);
		model.addAttribute("empleado", empleado);
	
		return "redirect:/empleadosTh";

	}
	
	@GetMapping("/modificar/{id}")
	public String paginaEmpleado(@PathVariable Integer id,Model model) throws Exception {
		Empleado empleado = servicio.conseguirEmpleado(id);
		
		model.addAttribute("empleado", empleado);	
		
		return "modificarEmpleado";
	}
	
	@GetMapping("/d/{id}")
	public String eliminarEmpleado(@PathVariable Integer id,Model model) throws Exception {
		servicio.eliminarEmpleado(id);
		
		return "redirect:/empleadosTh";	
	}
}
