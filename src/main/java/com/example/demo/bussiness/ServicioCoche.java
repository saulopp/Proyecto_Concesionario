package com.example.demo.bussiness;

import java.util.List;

import com.example.demo.common.exceptions.ServicioException;
import com.example.demo.entities.Coche;

public interface ServicioCoche {

	List<Coche> listCoches() throws ServicioException;

	List<Coche> listCochesVendidos() throws ServicioException;

	List<Coche> listCochesNoVendidos() throws ServicioException;

	Coche grabarCoche(Coche coche) throws ServicioException;

	List<Coche> findByMarca(String marca) throws ServicioException;

	List<Coche> findByMarcaModelo(String marca, String modelo) throws ServicioException;

	void eliminarCoche(String matricula) throws Exception;

	Coche conseguirCoche(String matricula) throws ServicioException;

	
}