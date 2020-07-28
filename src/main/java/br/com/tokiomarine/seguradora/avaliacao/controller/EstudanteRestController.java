package br.com.tokiomarine.seguradora.avaliacao.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;
import br.com.tokiomarine.seguradora.avaliacao.service.EstudandeService;

// TODO não esquecer de usar as anotações para criação do restcontroller

@RestController
@RequestMapping(value="api/estudantes")
public class EstudanteRestController {

	// TODO caso você não conheça THEMELEAF faça a implementação dos métodos em forma de RESTCONTROLLER (seguindo o padrão RESTFUL)
	@Autowired
	private EstudandeService estudandeService;

	// TODO IMPLEMENTAR A LISTAGEM DE ESTUDANTES (GET)
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Estudante>> findAll() {
		List<Estudante> list = estudandeService.buscarEstudantes();
		return ResponseEntity.ok().body(list);
	}
	
	// TODO IMPLEMENTAR CADASTRO DE ESTUDANTES (POST)
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Estudante estudante){
		estudante = estudandeService.cadastrarEstudanteRest(estudante);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(estudante.getId()).toUri();			
		return ResponseEntity.created(uri).build();
	}
	
	// TODO IMPLEMENTAR ATUALIZACAO DE ESTUDANTES (PUT)
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid  @RequestBody Estudante estudante, @PathVariable Integer id){
		estudandeService.atualizarEstudante(estudante);
		return ResponseEntity.noContent().build();
	}
	
	// TODO IMPLEMENTAR A EXCLUSÃO DE ESTUDANTES (DELETE)
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		estudandeService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
