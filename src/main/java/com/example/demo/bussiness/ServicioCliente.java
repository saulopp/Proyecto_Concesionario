package com.example.demo.bussiness;

import java.util.List;

import com.example.demo.common.exceptions.ServicioException;
import com.example.demo.entities.Cliente;

public interface ServicioCliente {

	List<Cliente> listClientes() throws ServicioException;

	Cliente grabarCliente(Cliente cliente) throws ServicioException;

	Cliente conseguirCliente(Integer id) throws ServicioException;

	List<Cliente> listEstadoAlta() throws ServicioException;

	List<Cliente> listEstadoBaja() throws ServicioException;
	
	void eliminarCliente(Integer id) throws Exception;
	
	List<Cliente> findByDniNombre(String nif, String nombre) throws ServicioException;

}