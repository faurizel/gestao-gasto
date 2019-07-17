package com.back.gestaogasto.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.gestaogasto.entity.Categoria;
import com.back.gestaogasto.repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	private CategoriaRepository repository;
	
	CategoriaController(CategoriaRepository categoriaRepository) {
		this.repository = categoriaRepository;
	}
	
	
	@PostMapping("/adicionar")
	public Categoria adicionaCategoriaGasto(@RequestBody Categoria categoriaGasto){
	    return repository.save(categoriaGasto);
	}
	
	
	
	@GetMapping
	@RequestMapping("/listar/{searchCategoriaGasto}")
	public ResponseEntity<List<Categoria>> listaCategoriaGasto(@PathVariable String searchCategoriaGasto) {
		
		List<Categoria> categoriaGastos = (List<Categoria>) repository.findByDescricaoContainingIgnoreCase(searchCategoriaGasto);
		return ResponseEntity.status(HttpStatus.OK).body(categoriaGastos);        
	} 

}
