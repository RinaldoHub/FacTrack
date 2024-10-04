package com.ifpe.factrack.controllers;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ifpe.factrack.model.entities.FuncEsc;
import com.ifpe.factrack.model.entities.FuncFab;
import com.ifpe.factrack.model.entities.Setor;
import com.ifpe.factrack.model.repository.RepositoryFacade;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/funcesc")
public class FuncEscController {
	
	@GetMapping("/")
	public String inicial(Model m, HttpServletRequest request, FuncEsc f, HttpSession session){
		
		List<FuncFab> funcsFab = RepositoryFacade.getInstance().readAllFuncsFab();
		
		List<Setor> setores = RepositoryFacade.getInstance().readAllSetores();
		
		m.addAttribute("funcsfab", funcsFab);
		
		m.addAttribute("setores", setores);
		
		return "indexEscritorio";
	}

	@PostMapping("/cadastro")
	public String inserir(Model m, HttpServletRequest request, FuncEsc f, HttpSession session) {
		
		try {
			RepositoryFacade.getInstance().createFuncEsc(f);
			
			session.setAttribute("msg","Funcionário Cadastrado com Sucesso!");
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			session.setAttribute("msg","Erro ao cadastrar o funcionário!");
		}
		
		return "redirect:/";
		
		
	}
	
}
