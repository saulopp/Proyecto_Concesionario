package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Coche;
public interface CocheRepository extends JpaRepository<Coche, String>{
	
	List<Coche> findByEstadoTrue();
	
	Optional<Coche> findByMatriculaAndEstadoTrue(String matricula);
		
	List<Coche> findByEstadoFalse();

	List<Coche> findByMarca(String marca);
	
	List<Coche> findByModelo(String modelo);
	
	List<Coche> findByMarcaAndModelo(String marca, String modelo);

	Optional<Coche> findByMatricula(String matricula);
	
}
