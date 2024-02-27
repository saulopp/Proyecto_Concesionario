package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.bussiness.ServicioCliente;
import com.example.demo.bussiness.ServicioCoche;
import com.example.demo.bussiness.ServicioEmpleado;
import com.example.demo.bussiness.ServicioVenta;
import com.example.demo.common.exceptions.ServicioException;
import com.example.demo.dto.VentaDTO;
import com.example.demo.entities.Cliente;
import com.example.demo.entities.Coche;
import com.example.demo.entities.Empleado;
import com.example.demo.entities.Venta;

@Controller
@RequestMapping(value={"/","/ventasTh"})
public class VentaThController {
	
	
	@Autowired
	ServicioCoche servicioCoche;
	
	@Autowired
	ServicioCliente servicioCliente;
	
	@Autowired
	ServicioEmpleado servicioEmpleado;
	
	@Autowired
	ServicioVenta servicioVenta;
	
	@GetMapping
	public String paginaVentas(Model model) throws Exception {
		
		List<Coche> cochesNoVendidos = servicioCoche.listCochesNoVendidos();
		
		model.addAttribute("cochesNoVendidos", cochesNoVendidos);
		
		List<Cliente> clientesAlta = servicioCliente.listEstadoAlta();
		
		model.addAttribute("clientesAlta", clientesAlta);
		
		List<Empleado> empleados = servicioEmpleado.listEmpleados();
		
		model.addAttribute("empleados", empleados);
		
		Double beneficios = servicioVenta.beneficios();
		
		model.addAttribute("beneficios",beneficios);
		
		List<Venta> ventas = servicioVenta.listVentas();
		
		model.addAttribute("ventas",ventas);
		
		return "ventas";
	
	}
	
	@PostMapping
	public String create(@Param(value = "matricula") String matricula, @Param(value = "idCliente") Integer idCliente,
			@Param(value = "idEmpleado") Integer idEmpleado, @Param(value = "monto") Double monto) throws Exception {
		
		VentaDTO venta = new VentaDTO();
		venta.setIdCoche(matricula);
		venta.setIdCliente(idCliente);
		venta.setIdEmpleado(idEmpleado);
		venta.setMonto(monto);
		servicioVenta.grabarVenta(venta);
		
		return "redirect:/ventasTh";
	}
	
	@GetMapping(value="/buscar")
	public String listaCochesEmpleado(@Param(value= "nombre") String nombre, Model model) throws ServicioException{
		
		List<Coche> cochesFiltrados = servicioVenta.listaCochesEmpleado(nombre);
		
		model.addAttribute("cochesFiltrados",cochesFiltrados);
		
		
		//Generamos la pagina inicial de ventas
		
		List<Coche> cochesNoVendidos = servicioCoche.listCochesNoVendidos();
		
		model.addAttribute("cochesNoVendidos", cochesNoVendidos);
		
		List<Cliente> clientesAlta = servicioCliente.listEstadoAlta();
		
		model.addAttribute("clientesAlta", clientesAlta);
		
		List<Empleado> empleados = servicioEmpleado.listEmpleados();
		
		model.addAttribute("empleados", empleados);
		
		Double beneficios = servicioVenta.beneficios();
		
		model.addAttribute("beneficios",beneficios);
		
		List<Venta> ventas = servicioVenta.listVentas();
		
		model.addAttribute("ventas",ventas);
		
		return "ventas";
	}
}
