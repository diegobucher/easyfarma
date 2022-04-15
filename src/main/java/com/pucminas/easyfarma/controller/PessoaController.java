package com.pucminas.easyfarma.controller;

import com.pucminas.easyfarma.domain.Pessoa;
import com.pucminas.easyfarma.domain.enums.TipoPessoa;
import com.pucminas.easyfarma.dto.RequisicaoFormPessoa;
import com.pucminas.easyfarma.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/pessoas")
public class PessoaController {

	private final String ROUTER = "/pessoas";
	private final String TIPO_PESSOA_FIELD = "listaTipoPessoas";

	@Autowired
	PessoaService service;

//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	public ResponseEntity<?> find(@PathVariable Integer id) {
//		Pessoa obj = service.find(id);
//		return ResponseEntity.ok().body(obj);
//	}

	@GetMapping("")
	public ModelAndView findAll() {
		List<Pessoa> obj = service.findAll();

		ModelAndView mv = new ModelAndView("pessoas/index");
		mv.addObject("pessoas", obj);
		return mv;
	}

	@GetMapping(value = "/new")
	public ModelAndView insert(RequisicaoFormPessoa requisicao) {
		ModelAndView mv = new ModelAndView("pessoas/new");
		mv.addObject(TIPO_PESSOA_FIELD, TipoPessoa.values());
		return mv;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Pessoa obj, @PathVariable Integer id) {
		obj.setId(id);
		service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ModelAndView show(@PathVariable Integer id) {
		Pessoa pessoa = this.service.find(id);

		if (pessoa != null) {
			ModelAndView mv = new ModelAndView("pessoas/show");
			mv.addObject("pessoa", pessoa);

			return mv;
		}
		// não achou um registro na tabela pessoa com o id informado
		else {
			System.out.println("$$$$$$$$$$$ NÃO ACHOU A PESSOA DE ID "+ id + " $$$$$$$$$$$");
			return this.retornaErroPessoa("SHOW ERROR: pessoa #" + id + " não encontrado no banco!");
		}
	}

	@PostMapping("")
	public ModelAndView create(@Valid RequisicaoFormPessoa requisicao, BindingResult bindingResult) {
		System.out.println(requisicao);
		if (bindingResult.hasErrors()) {
			System.out.println("\n************* TEM ERROS ***************\n");

			ModelAndView mv = new ModelAndView("pessoas/new");
			mv.addObject(TIPO_PESSOA_FIELD, TipoPessoa.values());
			return mv;
		}
		else {
			Pessoa pessoa = requisicao.toPessoa();
			this.service.insert(pessoa);

			return new ModelAndView("redirect:/pessoas/" + pessoa.getId());
		}
	}

	@GetMapping("/{id}/edit")
	public ModelAndView edit(@PathVariable Integer id, RequisicaoFormPessoa requisicao) {
		Pessoa pessoa = service.find(id);

		if (pessoa != null) {

			requisicao.fromPessoa(pessoa);

			ModelAndView mv = new ModelAndView("pessoas/edit");
			mv.addObject("pessoaId", pessoa.getId());
			mv.addObject(TIPO_PESSOA_FIELD, TipoPessoa.values());

			return mv;
		} else {
			System.out.println("$$$$$$$$$$$ NÃO ACHOU A PESSOA DE ID "+ id + " $$$$$$$$$$$");
			return this.retornaErroPessoa("SHOW ERROR: pessoa #" + id + " não encontrado no banco!");
		}
	}

	@PostMapping("/{id}")
	public ModelAndView update(@PathVariable Integer id, @Valid RequisicaoFormPessoa requisicao, BindingResult bindingResult) {
		System.out.println("requisicao: " + requisicao.toString());

		if (bindingResult.hasErrors()) {
			ModelAndView mv = new ModelAndView("pessoas/edit");
			mv.addObject("pessoaId", id);
			mv.addObject(TIPO_PESSOA_FIELD, TipoPessoa.values());
			return mv;
		}
		else {
			Pessoa pessoa = service.find(id);

			if (pessoa != null) {
				Pessoa toUpdate = requisicao.toPessoa();
				toUpdate.setId(pessoa.getId());
				service.update(toUpdate);

				return new ModelAndView("redirect:/pessoas/" + pessoa.getId());
			}
			// não achou um registro na tabela Pessoa com o id informado
			else {
				System.out.println("############ NÃO ACHOU A PESSOA DE ID "+ id + " ############");
				return this.retornaErroPessoa("UPDATE ERROR: Pessoa #" + id + " não encontrado no banco!");
			}
		}
	}

	@GetMapping("/{id}/delete")
	public ModelAndView delete(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView("redirect:/pessoas");

		try {
			service.delete(id);
			mv.addObject("mensagem", "Pessoa #" + id + " deletado com sucesso!");
			mv.addObject("erro", false);
		}
		catch (Exception e) {
			System.out.println(e);
			mv = this.retornaErroPessoa("DELETE ERROR: Pessoa #" + id + " não encontrado no banco!");
		}

		return mv;
	}

	private ModelAndView retornaErroPessoa(String msg) {
		ModelAndView mv = new ModelAndView("redirect:/pessoas");
		mv.addObject("mensagem", msg);
		mv.addObject("erro", true);
		return mv;
	}
}
