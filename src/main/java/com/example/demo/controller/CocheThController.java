package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.bussiness.ServicioCoche;
import com.example.demo.common.exceptions.ServicioException;
import com.example.demo.entities.Coche;

@Controller
@RequestMapping(value={"/","/cochesTh"})
public class CocheThController {
	
	@Autowired
	ServicioCoche servicio;
	
	@GetMapping
	public String paginaCoches(Model model) throws Exception {
		List<Coche> coches = servicio.listCoches();
		
		model.addAttribute("coches", coches);	
		return "coches";
	}
	
	@GetMapping("/crear")
	public String crearCoche(Model model) throws ServicioException {
		
		model.addAttribute("coche", new Coche());
		
		return "agregarNuevoCoche";
	}

	@PostMapping
	public String grabarCoche(@ModelAttribute Coche coche,Model model) throws Exception {
		
		if(coche.getEstado()==null) coche.setEstado(false);
		
		coche=servicio.grabarCoche(coche);
		
		List<Coche> coches = servicio.listCoches();
		
		model.addAttribute("coches", coches);
		return "redirect:/cochesTh";
	}
	
	@GetMapping("/modificar/{matricula}")
	public String modificarCoche(@PathVariable String matricula,Model model) throws Exception {
		
		Coche coche = servicio.conseguirCoche(matricula);
		
		model.addAttribute("coche", coche);	
		
		return "modificarCoche";
	}
	
	@GetMapping("/borrar/{matricula}")
	public String borrarCoche(@PathVariable String matricula,Model model) throws Exception {
		
		servicio.eliminarCoche(matricula);
		
		return "redirect:/cochesTh";
	}
	
	@GetMapping("/vendidos")
	public String cochesVendidos(Model model) throws Exception {
		List<Coche> coches = servicio.listCochesVendidos();
		
		model.addAttribute("coches", coches);	
		return "coches";
	}
	
	@GetMapping("/noVendidos")
	public String cochesEnVenta(Model model) throws Exception {
		List<Coche> coches = servicio.listCochesNoVendidos();
		
		model.addAttribute("coches", coches);	
		return "coches";
	}
	
	@GetMapping("/buscar")
	public String buscarMarcaModelo(@Param(value = "marca") String marca,@Param(value = "modelo") String modelo, Model model) throws Exception {
		if (marca.equals("")) marca=null;
		if (modelo.equals("")) modelo=null;
		List<Coche> coches = servicio.findByMarcaModelo(marca, modelo);
		model.addAttribute("coches", coches);	
		return "coches";
	}
}
