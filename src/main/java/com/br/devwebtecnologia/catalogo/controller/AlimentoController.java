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
import com.br.devwebtecnologia.catalogo.model.Alimento;
import com.br.devwebtecnologia.catalogo.model.AlimentoGrupo;
import com.br.devwebtecnologia.catalogo.repository.AlimentoInterface;
import com.br.devwebtecnologia.catalogo.repository.filter.AlimentoFilter;

@Controller
@RequestMapping("/alimento")
public class AlimentoController {

	@Autowired
	private AlimentoInterface alimentoInterface;
	
	@GetMapping("/novo")
	public ModelAndView novo(Alimento alimentoObj) {
		ModelAndView mv = new ModelAndView("folderAlimento/cadastro-alimento");
		mv.addObject(alimentoObj);
		mv.addObject("grupoSelecao", AlimentoGrupo.values());
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Alimento alimento, BindingResult result, 
			RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(alimento);
		}
		
		alimentoInterface.save(alimento);
		attributes.addFlashAttribute("mensagemCadastro", "Alimento salvo com sucesso!");
		return new ModelAndView("redirect:/alimento/novo");
	}
	
	@GetMapping
	public ModelAndView pesquisar(AlimentoFilter alimentoFilter) {
		ModelAndView mv = new ModelAndView("folderAlimento/pesquisa-alimento");
		mv.addObject("alimentosPesquisa", alimentoInterface.findByNomeContainingIgnoreCase(
				Optional.ofNullable(alimentoFilter.getNome()).orElse("%")));
		return mv;
	}
	
	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable Long id) {
		Alimento alimento = alimentoInterface.findOne(id);
		return novo(alimento);
	}
	
	@DeleteMapping("/{id}")
	public String apagar(@PathVariable Long id, RedirectAttributes attributes) {
		alimentoInterface.delete(id);
		attributes.addFlashAttribute("mensagemApagar", "Alimento removido com sucesso");
		return "redirect:/alimento";
	}
	
}







