package com.ifpe.factrack.controllers;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ifpe.factrack.model.entities.FuncFab;
import com.ifpe.factrack.model.entities.Setor;
import com.ifpe.factrack.model.repository.RepositoryFacade;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/funcfab")
public class FuncFabController {

	@PostMapping("/cadastro")
	public String inserir(Model m, HttpServletRequest request, FuncFab f, HttpSession session) {
		
		try {
			//Pega o codigo do setor, busca por ele, e atribui ele completo ao FuncFab
			Setor s = RepositoryFacade.getInstance().readSetor(f.getSetor().getCodigo());
		    f.setSetor(s);
			
			RepositoryFacade.getInstance().createFuncFab(f);
			
			session.setAttribute("msg","Funcionário Cadastrado com Sucesso!");
			
			return "redirect:/setor/" + s.getCodigo();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			session.setAttribute("msg","Erro ao cadastrar o funcionário!");
			
			System.out.println(e1);
		}
		
		return "index";
		
	}
	
}
