package com.br.devwebtecnologia.catalogo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.br.devwebtecnologia.catalogo.model.SegUsuario;
import com.br.devwebtecnologia.catalogo.service.UserService;

@Controller
public class LoginController {
	
	private final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private UserService userService;

	@RequestMapping(value={"/login"}, method = RequestMethod.GET)
	public ModelAndView login(@AuthenticationPrincipal SegUsuario user){
		ModelAndView modelAndView = new ModelAndView("/login");
		
		if (user != null) {
			modelAndView.setViewName("alimento");
			return modelAndView;
		} else {
			
			modelAndView.addObject("errorNovoUsuario", "Se é o primeiro acesso, realize o cadasto!");
			
		}
		
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	@GetMapping("/esqueceu-a-senha")
	public ModelAndView esqueceuSenha(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("esqueceu-a-senha");
		return modelAndView;
	}
	
	@GetMapping("/registration")
	public ModelAndView registration(SegUsuario user){
		ModelAndView modelAndView = new ModelAndView("/registration");
		modelAndView.addObject("user", user);
		return modelAndView;
	}
	
	@PostMapping("/registration")
	public ModelAndView createNewUser(@Valid SegUsuario user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView("/registration");
				
		SegUsuario userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			modelAndView.addObject("errorMessageEmail", "Já existe um usuário cadastrado com esse email: " 
									+ user.getEmail());
			modelAndView.setViewName("login");
			return modelAndView;
		}
		
		
		if( bindingResult.hasErrors() ){
			List<String> lista2 = new ArrayList<String>(); 
            for (FieldError lista : bindingResult.getFieldErrors()) {
            	lista.getDefaultMessage();
            	lista2.add(new String(lista.getDefaultMessage()));          	
            }
            
			modelAndView.addObject("errorMessage", lista2);
            modelAndView.addObject("user", user);
            
        } else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", user.getNome() + " cadastrado(a) com sucesso!");
			modelAndView.setViewName("login");
		}
		return modelAndView;
	}
	
	@GetMapping("/folderAlimento/cadastro-alimento")
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView("/folderAlimento/cadastro-alimento");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		SegUsuario user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", "Welcome " + user.getNome() + " " + user.getUltimoNome() + " (" + user.getEmail() + ")");
		modelAndView.addObject("adminMessage","Conteúdo disponível apenas para usuários com permissão de administrador!");
		modelAndView.setViewName("admin/home");
		return modelAndView;
	}
	

}
