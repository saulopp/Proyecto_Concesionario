
package com.example.demo.bussiness;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.exceptions.CodeError;
import com.example.demo.common.exceptions.ServicioException;
import com.example.demo.entities.Empleado;
import com.example.demo.entities.Venta;
import com.example.demo.repositories.EmpleadoRepository;
import com.example.demo.repositories.VentaRepository;

@Service
public class ServicioEmpleadoImpl implements ServicioEmpleado {

	Logger log = LoggerFactory.getLogger(ServicioEmpleadoImpl.class);

	@Autowired
	EmpleadoRepository repository;
	
	@Autowired
	VentaRepository repositoryVenta;

	@Override
	public List<Empleado> listEmpleados() throws ServicioException {
		log.info("[listEmpleados]");

		List<Empleado> empleados;
		try {
			empleados = repository.findAll();

		} catch (Exception e) {
			log.error("Exception", e);
			throw new ServicioException(CodeError.ERROR_GENERAL, e);
		}
		return empleados;

	}

	@Override
	public Empleado grabarEmpleado(Empleado empleado) throws ServicioException {
		log.info("[grabarEmpleado]");
		log.info("[empleado: " + empleado.toString() + "]");

		try {
			empleado = repository.save(empleado);
		} catch (Exception e) {
			log.error("Exception", e);
			throw new ServicioException(CodeError.ERROR_GENERAL, e);
		}
		return empleado;
	}
	
	@Override
	public Empleado conseguirEmpleado(Integer id) throws ServicioException {
		log.info("[conseguirEmpleado]");
		log.debug("[idEmpleado: "+id+"]");
		
		Empleado empleado;
		
		try {
			Optional<Empleado> empleadoOp= repository.findById(id);
			if(!empleadoOp.isPresent()) throw new ServicioException(CodeError.EMPLEADO_NOT_FOUND);
			empleado= empleadoOp.get();
		}catch(ServicioException se) {
			log.error("ServicioException", se);
			throw se;
		}catch(Exception e) {
			log.error("Exception", e);
			throw new ServicioException(CodeError.ERROR_GENERAL,e);
		}
		return empleado;
	}
	
	@Override
	public void eliminarEmpleado(Integer id) throws Exception{
		log.info("[eliminarEmpleado]");
		log.debug("[id: "+id+"]");
		
		try {
			Optional<Venta> ventaOp;
			ventaOp = repositoryVenta.findEmpleadoConVentas(id);
			if(ventaOp.isPresent()) throw new ServicioException("No se puede borrar el empleado porque existe una venta asociada.");	
			repository.deleteById(id);
		}catch(ServicioException se) {
			log.error("ServicioException", se);
			throw new Exception(se.getCodigo());
		}catch(Exception e) {
			log.error("Exception", e);
			throw new Exception(CodeError.ERROR_GENERAL,e);
		}
	}

}
