package com.ifpe.factrack.model.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ifpe.factrack.model.entities.FuncFab;
import com.ifpe.factrack.model.entities.Setor;

public class SetorRepository implements Repository<Setor, Integer>{
	
protected SetorRepository() {
		
	}
	
	@Override
	public void create(Setor s) throws SQLException {
		// TODO Auto-generated method stub
		
		String sql = "insert into setor(nome_setor,"
				+ "descricao_setor) values (?,?)";
		
		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
		
		pstm.setString(1, s.getNome());
		pstm.setString(2, s.getDescricao());
		
		pstm.execute();
		
	}

	@Override
	public void update(Setor s) throws SQLException {
		// TODO Auto-generated method stub
		
		String sql = "update setor set nome_setor=?"
				+ ", descricao_setor=? "
				+ "where codigo_setor=?";
		
		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
		System.out.println("update setor repository");
		System.out.println(s.getCodigo());
		System.out.println(s.getNome());
		System.out.println(s.getDescricao());
		
		pstm.setString(1, s.getNome());
		pstm.setString(2, s.getDescricao());
		
		pstm.setInt(3, s.getCodigo());
		
		pstm.executeUpdate();
		
	}

	@Override
	public Setor read(Integer k) throws SQLException {
		// TODO Auto-generated method stub
		
		String sql = "select * from setor where codigo_setor = ?";
		
		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
		
		pstm.setInt(1, k);
		
		ResultSet result = pstm.executeQuery();
		
		if(result.next()) {
			
			Setor s =  new Setor();
			
			s.setCodigo(result.getInt("codigo_setor"));
			s.setNome(result.getString("nome_setor"));
			s.setDescricao(result.getString("descricao_setor"));
			
			return s;
		}
		
		return null;
	}
	
	public List<Setor> readAll() throws SQLException {
	    
	    String sqlSetores = "SELECT * FROM setor";
	    String sqlFuncionarios = "SELECT * FROM funcfab WHERE codigo_fk_setor = ?";
	    
	    PreparedStatement pstmSetores = ConnectionManager.getCurrentConnection().prepareStatement(sqlSetores);
	    PreparedStatement pstmFuncionarios = ConnectionManager.getCurrentConnection().prepareStatement(sqlFuncionarios);
	    
	    List<Setor> setores = new ArrayList<>();
	    
	    ResultSet resultSetores = pstmSetores.executeQuery();
	    
	    while (resultSetores.next()) {
	        // Criando o objeto Setor e preenchendo seus dados
	        Setor s = new Setor();
	        s.setCodigo(resultSetores.getInt("codigo_setor"));
	        s.setNome(resultSetores.getString("nome_setor"));
	        s.setDescricao(resultSetores.getString("descricao_setor"));
	        
	        // Buscando os funcionários que pertencem ao setor atual
	        pstmFuncionarios.setInt(1, s.getCodigo());
	        ResultSet resultFuncionarios = pstmFuncionarios.executeQuery();
	        
	        List<FuncFab> funcionarios = new ArrayList<>();
	        
	        while (resultFuncionarios.next()) {
	            // Criando o objeto FuncFab e preenchendo seus dados
	            FuncFab f = new FuncFab();
	            f.setCodigo(resultFuncionarios.getInt("codigo_funcfab"));
	            f.setNome(resultFuncionarios.getString("nome_funcfab"));
	            
	            // Associando o setor ao funcionário
	            f.setSetor(s);
	            
	            // Adicionando o funcionário à lista de funcionários do setor
	            funcionarios.add(f);
	        }
	        
	        // Adicionando a lista de funcionários ao setor
	        s.setFuncionarios(funcionarios);
	        
	        // Adicionando o setor completo à lista de setores
	        setores.add(s);
	    }
	    
	    return setores;
	}


	@Override
	public void delete(Integer k) throws SQLException {
		// TODO Auto-generated method stub
		
		String sql = "delete from setor where codigo_setor = " + k;
		
		ConnectionManager.getCurrentConnection().prepareStatement(sql).execute();
		
	}
	
	public static void main(String args[]) {
		
		Setor s = new Setor();
		
		s.setNome("Montagem");
		s.setDescricao("americo_aziatico@gmail.com");
		
		try {
			new SetorRepository().create(s);
			
			//Setor e = new SetorRepository().read(1);
			//System.out.println(e.getNome());
			
			new SetorRepository().delete(1);
		} catch (SQLException p1) {
			// TODO Auto-generated catch block
			p1.printStackTrace();
		}
		
	}

}
