package com.back.gestaogasto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back.gestaogasto.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	public List<Categoria> findByDescricaoContainingIgnoreCase(String searchCategoriaGasto);
}
