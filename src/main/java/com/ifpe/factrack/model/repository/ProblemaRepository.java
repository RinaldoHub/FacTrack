package com.ifpe.factrack.model.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ifpe.factrack.model.entities.FuncFab;
import com.ifpe.factrack.model.entities.Setor;
import com.ifpe.factrack.model.entities.Problema;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class ProblemaRepository implements Repository<Problema, Integer> {

	protected ProblemaRepository() {

	}

	@Override
	public void create(Problema p) throws SQLException {
		// TODO Auto-generated method stub

		String sql = "insert into problema(tipo_problema, codigo_fk_funcfab, codigo_fk_setor) values (?, ?, ?)";

		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

		pstm.setString(1, p.getTipo());
		pstm.setInt(2, p.getFuncFab().getCodigo());
		pstm.setInt(3, p.getSetor().getCodigo());

		pstm.execute();
	}

	@Override
	public void update(Problema p) {
		// TODO Auto-generated method stub

	}

	
	//TODO CONTINUAR IMPLEMENTANDO O PROFESSOR
	@Override
	public Problema read(Integer k) throws SQLException {
		// TODO Auto-generated method stub

		Problema p = null;

		String sql = "SELECT * FROM problema AS p "
		           + "JOIN funcfab AS f ON p.codigo_fk_funcfab = f.codigo_funcfab "
		           + "JOIN setor AS s ON p.codigo_fk_setor = s.codigo_setor "
		           + "WHERE p.codigo_problema = " + k;

		ResultSet result = ConnectionManager.getCurrentConnection().prepareStatement(sql).executeQuery();
		
		FuncFab f = null;
		Setor s = null;

		if (result.next()) {

			p = new Problema();

			p.setCodigo(k);
			p.setTipo(result.getString("descricao"));

			f = new FuncFab();
			f.setCodigo(result.getInt("codigo_funcfab"));
			f.setNome(result.getString("nome_funcfab"));

			p.setFuncFab(f);
			
			s = new Setor();
			s.setCodigo(result.getInt("codigo_setor"));
			s.setNome(result.getString("nome_setor"));
			
			p.setSetor(s);
		}

		return p;
	}

	@Override
	public void delete(Integer k) {
		// TODO Auto-generated method stub

	}

	public List<Problema> filterByCodigoFuncFab(int codigoFuncFab) throws SQLException {

		String sql = "select * from problema as p join funcfab as f on(p.codigo_fk_funcfab = f.codigo_funcfab) "
				+ "join setor as s on(p.codigo_fk_setor = s.codigo_setor) where p.codigo_fk_funcfab = "
				+ codigoFuncFab;

		ResultSet result = ConnectionManager.getCurrentConnection().prepareStatement(sql).executeQuery();

		List<Problema> problemas = new ArrayList<Problema>();

		FuncFab f = null;
		
		Setor s = null;

		while (result.next()) {

			if (f == null) {
				f = new FuncFab();
				f.setCodigo(result.getInt("codigo_funcfab"));
				f.setNome(result.getString("nome_funcfab"));

			}
			
			if (s == null) {
				s = new Setor();
				s.setCodigo(result.getInt("codigo_setor"));
				s.setNome(result.getString("nome_setor"));

			}

			Problema p = new Problema();

			p.setCodigo(result.getInt("codigo_problema"));
			p.setTipo(result.getString("descricao"));
			
			p.setFuncFab(f);
			p.setSetor(s);
			
			problemas.add(p);

		}
		return problemas;

	}
	
	public List<Problema> filterByCodigoSetor(int codigoSetor) throws SQLException {

		String sql = "select * from problema as r join funcfab as f on(r.codigo_fk_funcfab = f.codigo_funcfab) "
				+ "join setor as p on(r.codigo_fk_setor = p.codigo_setor) where r.codigo_fk_setor = "
				+ codigoSetor;

		ResultSet result = ConnectionManager.getCurrentConnection().prepareStatement(sql).executeQuery();

		List<Problema> problemas = new ArrayList<Problema>();

		FuncFab f = null;
		
		Setor s = null;

		while (result.next()) {

			if (f == null) {
				f = new FuncFab();
				f.setCodigo(result.getInt("codigo_funcfab"));
				f.setNome(result.getString("nome_funcfab"));

			}
			
			if (s == null) {
				s = new Setor();
				s.setCodigo(result.getInt("codigo_setor"));
				s.setNome(result.getString("nome_setor"));

			}

			Problema p = new Problema();

			p.setCodigo(result.getInt("codigo_problema"));
			p.setTipo(result.getString("descricao_problema"));
			
			p.setFuncFab(f);
			p.setSetor(s);
			
			problemas.add(p);

		}
		return problemas;

	}
	
	public List<Problema> filterByDay(Date date){
		//TODO 
		
		return null;
	}
	
	public static void main(String args[]) {
		
		Problema p = new Problema();
		p.setTipo("Equipamento não liga");
		FuncFab f =  new FuncFab();
		f.setCodigo(2);
		Setor s = new Setor();
		s.setCodigo(2);
		
		p.setFuncFab(f);
		p.setSetor(s);
		
		ProblemaRepository pr = new ProblemaRepository();
		
		try {
			pr.create(p);
			
			p = new Problema();
			p.setTipo("Máquina queimada");
			p.setFuncFab(f);
			
			pr.create(p);
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
