package com.ifpe.factrack.controllers;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ifpe.factrack.model.entities.FuncFab;
import com.ifpe.factrack.model.entities.Setor;
import com.ifpe.factrack.model.entities.Problema;
import com.ifpe.factrack.model.repository.RepositoryFacade;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;

@Controller
@RequestMapping("/problemas")
public class ProblemaController {
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/{codigoSetor}")
	public String viewProblemas(Model m,@PathVariable("codigoSetor") int codigoSetor) {
		
		//FuncFab funcfab = RepositoryFacade.getInstance().readFuncFab(codigoSetor);
		
		try {
			Setor s = RepositoryFacade.getInstance().readSetor(codigoSetor);
			
			List<Problema> problemas = RepositoryFacade.getInstance().filterProblemaByCodigoSetor(codigoSetor);
		
			if(s != null) {
				m.addAttribute("problemas", problemas);
				//m.addAttribute("funcfab", funcfab);
				m.addAttribute("setor", s);
				m.addAttribute("problema", new Problema());
			}
			
			
			return "problemasSetor";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			session.setAttribute("msg", "O Setor com o codigo "+codigoSetor+" "
					+ "Não está cadastrado!");
			
			return "problemas";
			
		}
		
		
	}
	
	@GetMapping("/setor/{codigoSetor}")
	public String viewProblemasSetor(Model m,@PathVariable("codigoSetor") int codigoSetor) {
		
		List<FuncFab> funcsfab = RepositoryFacade.getInstance().readAllFuncsFab();
		
		m.addAttribute("funcsfab", funcsfab);
		
		try {
			Setor s = RepositoryFacade.getInstance().readSetor(codigoSetor); 
			
			List<Problema> problemas = RepositoryFacade.getInstance().filterProblemaByCodigoSetor(codigoSetor);
		
			if(s != null) {
				m.addAttribute("problemas", problemas);
				m.addAttribute("setor", s);
				m.addAttribute("problema", new Problema());
			}
			
			return "problemasSetor";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			session.setAttribute("msg", "O Setor com o codigo "+codigoSetor+" "
					+ "Não está cadastrado!");
			
			return "problemasSetor";
			
		}
		
		
	}
	
	@PostMapping("/save")
	public String saveProblema(Model m, Problema r,@RequestParam("codigoFuncFab") int codigoFuncFab, @RequestParam("codigoSetor") int codigoSetor) {
		
		FuncFab e;
		Setor p;
		try {
			e = RepositoryFacade.getInstance().readFuncFab(codigoFuncFab);
			p = e.getSetor();
			
			r.setFuncFab(e);
			r.setSetor(p);
			
			RepositoryFacade.getInstance().createProblema(r);
			session.setAttribute("msg", "Problema Cadastrado com sucesso!");
			
			return viewProblemas(m, codigoSetor);
			
		} catch (SQLException exp) {
			// TODO Auto-generated catch block
			session.setAttribute("msg", "erro ao cadastrar o problema!");
			
			return viewProblemas(m, codigoSetor);
		}
		
	}
}
