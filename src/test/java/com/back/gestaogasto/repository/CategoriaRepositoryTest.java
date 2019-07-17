package com.back.gestaogasto.repository;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.back.gestaogasto.entity.Categoria;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoriaRepositoryTest {

	@Autowired
	private CategoriaRepository repository;
	
	@Before
	public void setUp(){
		Categoria categoria =  new Categoria();
		categoria.setDescricao("Descricao Categoria");
		repository.save(categoria);
		}
	@After
	public final void tearDown(){
		this.repository.deleteAll();
	}
	
	@Test
	public void testBuscaCategoria(){
		List<Categoria> categoria = repository.findByDescricaoContainingIgnoreCase("Descricao");
		assertNotNull(categoria);
		
	}
}
