package com.back.gestaogasto.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.gestaogasto.entity.Gasto;
import com.back.gestaogasto.repository.GastoRepository;

@RestController
@RequestMapping("/gasto")
public class GastoController {

	@Autowired
	GastoRepository repository;

	public GastoController(GastoRepository gastoRepository) {
		this.repository = gastoRepository;
	}

	@PostMapping("/adicionar")
	public Gasto adicionaGasto(@RequestBody Gasto gasto) {
		return repository.save(gasto);
	}

	@GetMapping
	@RequestMapping("/listar/{codigoUsuario}")
	public ResponseEntity<List<Gasto>> listaGastoUsuario(
			@PathVariable Long codigoUsuario) {
		List<Gasto> gastos = (List<Gasto>) repository.findByCodigoUsuarioOrderByDataDesc(codigoUsuario);
		return ResponseEntity.status(HttpStatus.OK).body(gastos);
	}

	@GetMapping
	@RequestMapping("/filtrar/{codigoUsuario}/{data}")
	public ResponseEntity<List<Gasto>> filtraGastoUsuario(
			@PathVariable Long codigoUsuario, @PathVariable String data)
			throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(data);

		List<Gasto> gastos = (List<Gasto>) repository
				.findByCodigoUsuarioAndData(codigoUsuario, date);
		return ResponseEntity.status(HttpStatus.OK).body(gastos);
	}

	@PutMapping
	@RequestMapping("/alterar")
	public ResponseEntity<Gasto> alteraGastoUsuario(@RequestBody Gasto gasto) {

		return repository.findById(gasto.getId()).map(recordGasto -> {
			recordGasto.setCategoria(gasto.getCategoria());

			Gasto updatedGasto = repository.save(recordGasto);

			return ResponseEntity.ok().body(updatedGasto);
		}).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping("/adicionarCategoriaAutomatico")
	public Gasto adicionaGastoAutomatico(@RequestBody Gasto gasto) {

		Long codigoCategoria = repository.findByCodigoUsuario(gasto.getCodigoUsuario(), gasto.getCategoria().getDescricao());

		if (!codigoCategoria.toString().contentEquals("")) {
			gasto.getCategoria().setId(codigoCategoria);
		}
		return repository.save(gasto);
	}

}
