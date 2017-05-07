package com.br.devwebtecnologia.catalogo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.br.devwebtecnologia.catalogo.model.SegUsuario;
import com.br.devwebtecnologia.catalogo.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value={"/login"}, method = RequestMethod.GET)
	public ModelAndView login(@AuthenticationPrincipal SegUsuario user){
		ModelAndView modelAndView = new ModelAndView();
		
		if (user != null) {
			modelAndView.setViewName("alimento");
			return modelAndView;
		}
		
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	@RequestMapping(value={"/esqueceu-a-senha"}, method = RequestMethod.GET)
	public ModelAndView esqueceuSenha(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("esqueceu-a-senha");
		return modelAndView;
	}
	
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		SegUsuario user = new SegUsuario();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid SegUsuario user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		SegUsuario userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult
					.rejectValue("email", "error.user",
							"Já existe um usuário cadastrado com esse email!");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "Usuário cadastrado com sucesso!");
			modelAndView.addObject("user", new SegUsuario());
			modelAndView.setViewName("registration");
			
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/folderAlimento/cadastro-alimento", method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		SegUsuario user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", "Welcome " + user.getNome() + " " + user.getUltimoNome() + " (" + user.getEmail() + ")");
		modelAndView.addObject("adminMessage","Conteúdo disponível apenas para usuários com permissão de administrador!");
		modelAndView.setViewName("admin/home");
		return modelAndView;
	}
	

}
