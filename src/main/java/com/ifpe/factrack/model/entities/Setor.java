package com.ifpe.factrack.model.entities;

import java.util.ArrayList;
import java.util.List;

public class Setor {
	
	private int codigo;
	private String nome;
	private String descricao;
	private List<FuncFab> funcionarios = new ArrayList<>(); 
	private List<Problema> problemas = new ArrayList<>();
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public List<FuncFab> getFuncionarios() {
		return funcionarios;
	}
	public void setFuncionarios(List<FuncFab> funcionarios) {
		this.funcionarios = funcionarios;
	}
	public List<Problema> getProblemas() {
		return problemas;
	}
	public void setProblemas(List<Problema> problemas) {
		this.problemas = problemas;
	}
}
