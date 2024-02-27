package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer>{

}
