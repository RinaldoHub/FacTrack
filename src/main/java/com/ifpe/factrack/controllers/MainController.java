package com.ifpe.factrack.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ifpe.factrack.model.entities.FuncFab;
import com.ifpe.factrack.model.entities.Setor;
import com.ifpe.factrack.model.repository.RepositoryFacade;

@Controller
public class MainController {
	
	@RequestMapping({"/*","/setor/*","/problemas/*"})
	public String inicial(Model m) {
		
		List<FuncFab> funcsFab = RepositoryFacade.getInstance().readAllFuncsFab();
		
		m.addAttribute("funcsfab", funcsFab);
		
		return "index";
		
	}

}
