package com.br.devwebtecnologia.catalogo.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.br.devwebtecnologia.catalogo.model.TipoVinho;
import com.br.devwebtecnologia.catalogo.model.Vinho;
import com.br.devwebtecnologia.catalogo.repository.VinhoInterface;
import com.br.devwebtecnologia.catalogo.repository.filter.CatalogoFilter;

@Controller
@RequestMapping("/catalogo")
public class CatalogoController {

	@Autowired
	private VinhoInterface vinhoInterface;

	@GetMapping("/novo")
	public ModelAndView novo(Vinho vinho) {
		ModelAndView mv = new ModelAndView("vinho/cadastro-vinho");
		mv.addObject(vinho);
		mv.addObject("tipos", TipoVinho.values());
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Vinho vinho, BindingResult result, 
			RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(vinho);
		}
		
		vinhoInterface.save(vinho);
		attributes.addFlashAttribute("mensagem", "Vinho salvo com sucesso!");
		return new ModelAndView("redirect:/catalogo/novo");
	}
	
	@GetMapping
	public ModelAndView pesquisar(CatalogoFilter catalogoFilter) {
		ModelAndView mv = new ModelAndView("vinho/pesquisa-vinhos");
		mv.addObject("vinhos", vinhoInterface.findByNomeContainingIgnoreCase(
				Optional.ofNullable(catalogoFilter.getNome()).orElse("%")));
		return mv;
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo) {
		Vinho vinho = vinhoInterface.findOne(codigo);
		return novo(vinho);
	}
	
	@DeleteMapping("/{codigo}")
	public String apagar(@PathVariable Long codigo, RedirectAttributes attributes) {
		vinhoInterface.delete(codigo);
		attributes.addFlashAttribute("mensagem", "Vinho removido com sucesso");
		return "redirect:/catalogo";
	}
	
}







