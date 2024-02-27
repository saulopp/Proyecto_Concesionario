package com.example.demo.bussiness;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.exceptions.CodeError;
import com.example.demo.common.exceptions.ServicioException;
import com.example.demo.entities.Cliente;
import com.example.demo.repositories.ClienteRepository;

@Service
public class ServicioClienteImpl implements ServicioCliente {
	
	Logger log = LoggerFactory.getLogger(ServicioClienteImpl.class);
	
	@Autowired
	ClienteRepository repository;
	
	@Override
	public List<Cliente> listClientes() throws ServicioException{
		log.info("[listClientes]");
		
		List<Cliente> clientes;
		
		try {
			clientes= repository.findAll();
			
		}catch(Exception e) {
			log.error("Exception", e);
			throw new ServicioException(CodeError.ERROR_GENERAL,e);
		}
		return clientes;
		
	}
	@Override
	public Cliente grabarCliente(Cliente cliente) throws ServicioException {
		log.info("[grabarCliente]");
		log.info("[empleado: "+cliente.toString()+"]");
		
		try{
			cliente =repository.save(cliente);
		}catch(Exception e) {
			log.error("Exception", e);
			throw new ServicioException(CodeError.ERROR_GENERAL,e);
		}
		return cliente;
		
	}
	@Override
	public Cliente conseguirCliente(Integer id) throws ServicioException {
		log.info("[conseguirCliente]");
		log.debug("[idCliente: "+id+"]");
		
		Cliente cliente;
		
		try {
			Optional<Cliente> clienteOp= repository.findById(id);
			if(!clienteOp.isPresent()) throw new ServicioException(CodeError.CLIENTE_NOT_FOUND);
			cliente= clienteOp.get();
		}catch(ServicioException se) {
			log.error("ServicioException", se);
			throw se;
		}catch(Exception e) {
			log.error("Exception", e);
			throw new ServicioException(CodeError.ERROR_GENERAL,e);
		}
		return cliente;
	}
	@Override
	public List<Cliente> listEstadoAlta() throws ServicioException {
		log.info("[listEstadoAlta]");
		
		List<Cliente> clientes;
		
		try {
			clientes = repository.findByEstadoAltaTrue();
			
		}catch(Exception e) {
			log.error("Exception", e);
			throw new ServicioException(CodeError.ERROR_GENERAL,e);
		}
		return clientes;
	}
	@Override
	public List<Cliente> listEstadoBaja() throws ServicioException {
		log.info("[listEstadoAlta]");
		
		List<Cliente> clientes;
		
		try {
			clientes = repository.findByEstadoAltaFalse();
			
		}catch(Exception e) {
			log.error("Exception", e);
			throw new ServicioException(CodeError.ERROR_GENERAL,e);
		}
		return clientes;
	}
	
	@Override
	public void eliminarCliente(Integer id) throws Exception{
		log.info("[eliminarCliente]");
		log.debug("[id: "+id+"]");
	
		try {
			Optional<Cliente> clienteOp;
			clienteOp = repository.findClienteSinVentas(id);
			if(!clienteOp.isPresent()) throw new ServicioException("No se puede borrar el cliente porque existe una venta asociada.");	
			repository.deleteById(id);
			
		}catch(ServicioException se) {
			log.error("ServicioException", se);
			throw new Exception(se.getCodigo());
		}catch(Exception e) {
			log.error("Exception", e);
			throw new Exception(CodeError.ERROR_GENERAL,e);
		}
		
	}

	@Override
	public List<Cliente> findByDniNombre(String nif, String nombre) throws ServicioException {
		log.info("[findByDniNombre]");

		List<Cliente> clientes;
		try {
			if (nif == null) {
				clientes = repository.findByNombre(nombre);
			} else if (nombre == null) {
				clientes = repository.findByNif(nif);
			} else {
				clientes = repository.findByNifAndNombre(nif, nombre);
			}

		} catch (Exception e) {
			log.error("Exception", e);
			throw new ServicioException(CodeError.ERROR_GENERAL, e);
		}
		return clientes;
	}
}
