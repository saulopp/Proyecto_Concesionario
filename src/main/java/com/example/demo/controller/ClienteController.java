package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bussiness.ServicioCliente;
import com.example.demo.common.exceptions.ServicioException;
import com.example.demo.entities.Cliente;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	ServicioCliente servicio;
	
	@GetMapping
	public List<Cliente> list() throws ServicioException{
		
		return servicio.listClientes();
	}
	
	@PostMapping
	public Cliente create(@RequestBody Cliente cliente) throws ServicioException {
		return servicio.grabarCliente(cliente);
	}
	
	@PutMapping
	public Cliente save(@RequestBody Cliente cliente) throws ServicioException{
		return servicio.grabarCliente(cliente);
	}
	
	@GetMapping(value="/buscarPorId")
	public Cliente find(@Param(value = "id") Integer id) throws ServicioException{
		return servicio.conseguirCliente(id);
	}
	
	@GetMapping(value="/buscarEstadoAlta")
	public List<Cliente> listAlta() throws ServicioException{
		return servicio.listEstadoAlta();
	}
	
	@GetMapping(value="/buscarEstadoBaja")
	public List<Cliente> listBaja() throws ServicioException{
		return servicio.listEstadoBaja();
	}
	
	@DeleteMapping 
	public String delete(@Param(value = "id") Integer id) throws Exception{
		servicio.eliminarCliente(id);
		return "Cliente eliminado correctamente";
	}
}
