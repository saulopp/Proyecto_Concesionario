package com.example.demo.bussiness;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.Constantes;
import com.example.demo.common.exceptions.CodeError;
import com.example.demo.common.exceptions.ServicioException;
import com.example.demo.dto.VentaDTO;
import com.example.demo.entities.Cliente;
import com.example.demo.entities.Coche;
import com.example.demo.entities.Empleado;
import com.example.demo.entities.Venta;
import com.example.demo.repositories.ClienteRepository;
import com.example.demo.repositories.CocheRepository;
import com.example.demo.repositories.EmpleadoRepository;
import com.example.demo.repositories.VentaRepository;

@Service
public class ServicioVentaImpl implements ServicioVenta{
	
	Logger log = LoggerFactory.getLogger(ServicioEmpleadoImpl.class);
		
	@Autowired
	VentaRepository ventaRepository;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	EmpleadoRepository empleadoRepository;
	
	@Autowired
	CocheRepository cocheRepository;
	
	@Override
	public List<Venta> listVentas() throws ServicioException{
		log.info("[listVentas]");
		
		List<Venta> ventas;
		try {
			ventas= ventaRepository.findAll();
			
		}catch(Exception e) {
			log.error("Exception", e);
			throw new ServicioException(CodeError.ERROR_GENERAL,e);
		}
		return ventas;
		
	}

	@Override
	public Venta grabarVenta(VentaDTO ventaDTO) throws Exception {
		log.info("[grabarVenta]");
		Venta venta = new Venta();
		
		try {
			
			Optional<Cliente> clienteOp;
			clienteOp = clienteRepository.findById(ventaDTO.getIdCliente());
			if(!clienteOp.isPresent()) throw new ServicioException(CodeError.CLIENTE_NOT_FOUND);
			venta.setCliente(clienteOp.get());
			int nVentas = clienteOp.get().getnVentas()+1;
			clienteOp.get().setnVentas(nVentas);
			if (nVentas>1 && clienteOp.get().getCategoria().equals(Constantes.PLATA))
				clienteOp.get().setCategoria(Constantes.ORO);
				
			clienteRepository.save(clienteOp.get());
			
			Optional<Empleado> empleadoOp;
			empleadoOp = empleadoRepository.findById(ventaDTO.getIdEmpleado());
			if(!empleadoOp.isPresent()) throw new ServicioException(CodeError.EMPLEADO_NOT_FOUND);
			venta.setEmpleado(empleadoOp.get());
			
			Optional<Coche> cocheOp;
			cocheOp = cocheRepository.findById(ventaDTO.getIdCoche());
			if(!cocheOp.isPresent()) throw new ServicioException(CodeError.COCHE_NOT_FOUND);
			if (cocheOp.get().getEstado()) throw new ServicioException(CodeError.COCHE_ALREADY_SOLD);
			venta.setCoche(cocheOp.get());
			
			venta.setMonto(ventaDTO.getMonto());
			venta.setFecha(java.sql.Date.valueOf(LocalDate.now()));
			cocheOp.get().setEstado(true);
			cocheRepository.save(cocheOp.get());
			
			ventaRepository.save(venta);
			log.info("[venta: "+venta.toString()+"]");
		}catch(ServicioException se) {
			log.error("ServicioException", se);
			throw new Exception(se.getCodigo());
		}catch(Exception e) {
			log.error("Exception", e);
			throw new Exception(CodeError.ERROR_GENERAL,e);
		}
		Optional<Venta> ventaAlmacenada =ventaRepository.findById(venta.getId());
		
		return ventaAlmacenada.get();
	}
	
	@Override
	public Double beneficios() throws ServicioException{
		log.info("[beneficios]");
		List<Venta> ventas = listVentas();
		Double beneficios=0D;
		for (Venta venta : ventas) {
			beneficios += venta.getMonto()-venta.getCoche().getPrecio();
		}
		return beneficios;
	}

	//Filtro coches por empleado
	@Override
	public List<Coche> listaCochesEmpleado(String nombre) throws ServicioException {
		log.info("[listCochesEmpleado]");
		
		List<Coche> coches;
		
		try {
			coches= ventaRepository.listaCochesEmpleado(nombre);
		}catch(Exception e) {
			log.error("Exception", e);
			throw new ServicioException(CodeError.ERROR_GENERAL,e);
		}
		return coches;
	}
}
