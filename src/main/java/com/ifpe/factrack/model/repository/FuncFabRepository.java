package com.ifpe.factrack.model.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ifpe.factrack.model.entities.FuncFab;
import com.ifpe.factrack.model.entities.Problema;
import com.ifpe.factrack.model.entities.Setor;

public final class FuncFabRepository implements Repository<FuncFab, Integer>{

	protected FuncFabRepository() {
		
	}
	
	@Override
	public void create(FuncFab f) throws SQLException {
		// TODO Auto-generated method stub
		
		String sql = "insert into funcfab(nome_funcfab, codigo_fk_setor)"
				+ " values (?, ?)";
		
		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
		
		pstm.setString(1, f.getNome());
		pstm.setInt(2, f.getSetor().getCodigo());
		
		pstm.execute();
		
	}

	@Override
	public void update(FuncFab f) throws SQLException {
		// TODO Auto-generated method stub
		
		/*String sql = "update funcfab set matricula_funcfab=?,"
				+ "nome_funcfab=?, endereco_funcfab = ?,"
				+ "telefone_funcfab=?, email_funcfab=?,"
				+ "ano_entrada=? where codigo_funcfab=?";
		
		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
		
		pstm.setString(1, f.getNome());
		
		pstm.setInt(2, f.getCodigo());
		
		pstm.executeUpdate();*/
		
	}

	@Override
	public FuncFab read(Integer k) throws SQLException {
		// TODO Auto-generated method stub
		
		String sql = "select * from funcfab where codigo_funcfab = ?";
		
		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
		
		pstm.setInt(1, k);
		
		ResultSet result = pstm.executeQuery();
		
		if(result.next()) {
			
			FuncFab f =  new FuncFab();
			
			f.setCodigo(result.getInt("codigo_funcfab"));
			f.setNome(result.getString("nome_funcfab"));
			
			Setor s = new Setor();
			s.setCodigo(result.getInt("codigo_setor"));
			s.setNome(result.getString("nome_setor"));
			
			f.setSetor(s);
			
			return f;
		}
		
		return null;
	}
	
	public List<FuncFab> readAll() throws SQLException{
		
		String sql = "SELECT f.codigo_funcfab, f.nome_funcfab, s.codigo_setor, s.nome_setor " +
                "FROM funcfab f " +
                "JOIN setor s ON f.codigo_fk_setor = s.codigo_setor";
		
		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
		
		List<FuncFab> funcfabs = new ArrayList<>();
		
		ResultSet result = pstm.executeQuery();
		
		while(result.next()) {
			
			FuncFab f =  new FuncFab();
			
			f.setCodigo(result.getInt("codigo_funcfab"));
			f.setNome(result.getString("nome_funcfab"));
			
			Setor s = new Setor();
			s.setCodigo(result.getInt("codigo_setor"));
			s.setNome(result.getString("nome_setor"));
			
			f.setSetor(s);
			
			funcfabs.add(f);
		}
		
		return funcfabs;
		
	}

	@Override
	public void delete(Integer k) throws SQLException {
		// TODO Auto-generated method stub
		
		String sql = "delete from funcfab where codigo_funcfab = "+k;
		
		ConnectionManager.getCurrentConnection().prepareStatement(sql).execute();
		
	}

	public List<FuncFab> filterFuncsFabByCodigoSetor(int codigoSetor) {
		
		String sql = "SELECT * FROM funcfab "
				+ "WHERE codigo_fk_setor = "
				+ codigoSetor;
		
		List<FuncFab> funcsfab = new ArrayList<FuncFab>();

		try {
			ResultSet result = ConnectionManager.getCurrentConnection().prepareStatement(sql).executeQuery();
	
			Setor s = RepositoryFacade.getInstance().readSetor(codigoSetor);
	
			while (result.next()) {
				
				FuncFab f = null;
	
				if (f == null) {
					f = new FuncFab();
					f.setCodigo(result.getInt("codigo_funcfab"));
					f.setNome(result.getString("nome_funcfab"));
	
				}
				
				funcsfab.add(f);
			}
		}
		
		catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return funcsfab;
	}
	
	public static void main(String args[]) {
		
		FuncFab f = new FuncFab();
		
		f.setNome("Manoel da SIlva");
		f.setCodigo(1);
		
		try {
			new FuncFabRepository().create(f);
			
			//FuncFab e = new FuncFabRepository().read(1);
			//System.out.println(f.getNome());
			
			new FuncFabRepository().delete(1);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
