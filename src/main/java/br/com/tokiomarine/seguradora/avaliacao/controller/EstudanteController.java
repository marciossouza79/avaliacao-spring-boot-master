package br.com.tokiomarine.seguradora.avaliacao.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;
import br.com.tokiomarine.seguradora.avaliacao.service.EstudandeService;

@Controller
@RequestMapping( value = {"/estudantes",""})
public class EstudanteController {

	// TODO efetue a correção dos problemas que existem na classe Estudante Controller
	@Autowired
	private EstudandeService service;

	@GetMapping("/criar")
	public String iniciarCastrado(Estudante estudante) {
		return "cadastrar-estudante";
	}

	@GetMapping( value = { "/" , "" })
	public String listar(Model model) {
		model.addAttribute("estudantes", service.buscarEstudantes());
		return "index";
	}
	
	@GetMapping("/listar")
	public String listarEstudantes(Model model) {
		model.addAttribute("estudantes", service.buscarEstudantes());
		return "index";
	}

	@PostMapping("/add")
	public String adicionarEstudante(@Valid Estudante estudante, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "cadastrar-estudante";
		}

		service.cadastrarEstudante(estudante);

		return "redirect:listar";
	}

	@GetMapping("/editar/{id}")
	public String exibirEdicaoEstudante(@PathVariable("id") Integer id, Model model) {
		Estudante estudante = service.buscarEstudante(id.longValue());
		model.addAttribute("estudante", estudante);
		return "atualizar-estudante";
	}

	@PostMapping("/atualizar/{id}")
	public String atualizarEstudante(@PathVariable("id") Integer id, @Valid Estudante estudante, BindingResult result, Model model) {
		if (result.hasErrors()) {
			// estudante.setId(id);
			return "atualizar-estudante";
		}

		service.atualizarEstudante(estudante);

		model.addAttribute("estudantes", service.buscarEstudantes());
		return "index";
	}

	@GetMapping("/apagar/{id}")
	public String apagarEstudante(@PathVariable("id") Integer id, Model model) {
		service.delete(id);
		model.addAttribute("estudantes", service.buscarEstudantes());
		return "index";
	}
}
