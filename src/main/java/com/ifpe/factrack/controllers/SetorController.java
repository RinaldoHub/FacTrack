package com.ifpe.factrack.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ifpe.factrack.model.entities.FuncFab;
import com.ifpe.factrack.model.entities.Setor;
import com.ifpe.factrack.model.repository.RepositoryFacade;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/setor")
public class SetorController {

	@Autowired
	private HttpSession session;
	
	@GetMapping("/{codigoSetor}")
	public String inicial(Model m, HttpServletRequest request, Setor p, HttpSession session, @PathVariable("codigoSetor") int codigoSetor) {
		
		try {
			Setor s = RepositoryFacade.getInstance().readSetor(codigoSetor);
			
			List<FuncFab> funcsfab = RepositoryFacade.getInstance().filterFuncsFabByCodigoSetor(codigoSetor);
		
			if(s != null) {
				m.addAttribute("funcsfab", funcsfab);
				m.addAttribute("setor", s);
				m.addAttribute("funcfab", new FuncFab());
			}
			
			session.setAttribute("setor", s);
			
			return "funcsfab";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			session.setAttribute("msg", "O Setor com o codigo "+codigoSetor+" "
					+ "Não está cadastrado!");
			
			return "redirect:/funcesc/";
			
		}
	}

	@PostMapping("/cadastro")
	public String inserir(Model m, HttpServletRequest request, Setor p, HttpSession session) {

		/*
		 * o request é uma variável de contexto que o spring prepara para nós
		 */

		// String nome = request.getParameter("nome");
		// String matricula = request.getParameter("matricula");

		try {
			RepositoryFacade.getInstance().createSetor(p);

			session.setAttribute("msg", "Setor Cadastrado com Sucesso!");

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			session.setAttribute("msg", "Erro ao cadastrar o setor!");
		}

		return "redirect:/funcesc/";

	}

	/*@RequestMapping("/login")
	public String login(Model m, HttpServletRequest request, Setor p, HttpSession session) {

		this.session = request.getSession();

		m.addAttribute("session", session);

		return "loginSetor";
	}

	@PostMapping("/realizarLogin")
	public String realizarLogin(Model m, HttpServletRequest request, Setor p, HttpSession session) {

		List<Setor> setores = new ArrayList<>();

		setores = RepositoryFacade.getInstance().readAllSetores();

		for (Setor prof : setores) {

			if (prof.getEmail().equals(p.getEmail()) && prof.getSenha().equals(p.getSenha())) {

				session.setAttribute("pLogado", prof);

				session.setAttribute("msg", "Setor logado com sucesso!");

				return "redirect:/setor/areadosetor";

			}

		}

		session.setAttribute("msg", "Credenciais incorretas!");

		return "redirect:/setor/login";
	}
	
	@RequestMapping("/editar")
	public String editar(Model m, HttpServletRequest request, Setor p, HttpSession session) {
		
		System.out.println(p.getNome());
		
		try {
			RepositoryFacade.getInstance().updateSetor(p);

			session.setAttribute("msg", "Setor editado com Sucesso!");

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			session.setAttribute("msg", "Erro ao editar o setor!");
		}

		return "redirect:/setor/areadosetor";
	}
	
	@RequestMapping("/deslogar")
	public String deslogar(Model m, HttpServletRequest request, Setor p, HttpSession session){
		session.removeAttribute("pLogado");
		
		session.setAttribute("msg", "Deslogado com sucesso!");
		
		return "redirect:/";
	}*/
	
	@RequestMapping("/{codigo}")
	public String mostrarRelatos(Model m, HttpServletRequest request, Setor p, HttpSession session, @PathVariable int codigo){
		
		
		
		return "redirect:/setor/areadosetor";
	}
	
	@RequestMapping("/{codigoSetor}")
	public String relatosSetor(Model m, HttpServletRequest request, Setor p, HttpSession session) {
		
		return "relatos";
	}

}
