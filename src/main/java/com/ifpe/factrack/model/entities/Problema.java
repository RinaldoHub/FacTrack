package com.ifpe.factrack.model.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Problema {
	
	private int codigo;
	private Date data;
	private String tipo;
	
	private Setor setor;
	private FuncFab funcfab;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public FuncFab getFuncFab() {
		return funcfab;
	}

	public void setFuncFab(FuncFab funcfab) {
		this.funcfab = funcfab;
	}
	
	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}
	
	public String getDataFormatada() {
		if(this.data != null)
			return new SimpleDateFormat("dd/MM/yyyy").format(this.data);
		
		return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
	}
	
	public void setDataFormatada(String dataString) {
		
		try {
			this.data = new SimpleDateFormat("dd/MM/yyyy").parse(dataString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
