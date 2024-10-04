package com.ifpe.factrack.model.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ifpe.factrack.model.entities.FuncEsc;
import com.ifpe.factrack.model.entities.FuncFab;
import com.ifpe.factrack.model.entities.Setor;
import com.ifpe.factrack.model.entities.Problema;

public class RepositoryFacade {
	
	private static RepositoryFacade myself = null;
	
	private Repository<FuncFab, Integer> rFuncFab;
	private Repository<FuncEsc, Integer> rFuncEsc;
	private Repository<Problema, Integer> rProblema;
	private Repository<Setor, Integer> rSetor;
	
	private RepositoryFacade() {
		
		rFuncFab = new FuncFabRepository();
		rProblema = new ProblemaRepository();
		rSetor = new SetorRepository();
		
	}
	
	public static RepositoryFacade getInstance() {
		
		if(myself == null)
			myself = new RepositoryFacade();
		
		return myself;
		
	}
	
	public FuncFab readFuncFab(int codigo) throws SQLException {
		return this.rFuncFab.read(codigo);
	}
	
	public Setor readSetor(int codigo) throws SQLException {
		return this.rSetor.read(codigo);
	}
	
	public void createFuncFab(FuncFab funcfab) throws SQLException {
		rFuncFab.create(funcfab);
	}
	
	public void createFuncEsc(FuncEsc funcesc) throws SQLException {
		rFuncEsc.create(funcesc);
	}
	
	public void createSetor(Setor setor) throws SQLException {
		rSetor.create(setor);
	}
	
	public void updateSetor(Setor setor) throws SQLException {
		rSetor.update(setor);
	}
	
	public void createProblema(Problema problema) throws SQLException {
		rProblema.create(problema);
	}
	
	public List<FuncFab> readAllFuncsFab(){
		try {
			return ((FuncFabRepository)this.rFuncFab).readAll();
		} catch (SQLException e) {
			return new ArrayList<>();
		}
	}
	
	public List<Setor> readAllSetores(){
		try {
			return ((SetorRepository)this.rSetor).readAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return new ArrayList<>();
		}
	}
	
	public List<Problema> filterProblemaByDay(Date date) throws SQLException{
		return ((ProblemaRepository)this.rProblema).filterByDay(date);
	}
	
	public List<Problema> filterProblemaByCodigoSetor(int codigo) throws SQLException{
		return ((ProblemaRepository)this.rProblema).filterByCodigoSetor(codigo);
	}
	
	public List<Problema> filterFuncFabBySetor(int codigo) throws SQLException{
		return ((ProblemaRepository)this.rProblema).filterByCodigoSetor(codigo);
	}

	public List<FuncFab> filterFuncsFabByCodigoSetor(int codigoSetor) {
		return ((FuncFabRepository)this.rFuncFab).filterFuncsFabByCodigoSetor(codigoSetor);
	}

}
