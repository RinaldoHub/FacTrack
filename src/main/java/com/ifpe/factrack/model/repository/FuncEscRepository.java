package com.ifpe.factrack.model.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ifpe.factrack.model.entities.FuncEsc;

public final class FuncEscRepository implements Repository<FuncEsc, Integer>{

	protected FuncEscRepository() {
		
	}
	
	@Override
	public void create(FuncEsc e) throws SQLException {
		// TODO Auto-generated method stub
		
		String sql = "insert into funcesc(nome_funcesc"
				+ ", email_funcesc, "
				+ "senha_funcesc) values (?,?,?)";
		
		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
		
		pstm.setString(1, e.getNome());
		pstm.setString(2, e.getEmail());
		pstm.setString(3, e.getSenha());
		
		pstm.execute();
		
	}

	@Override
	public void update(FuncEsc e) throws SQLException {
		// TODO Auto-generated method stub
		
		String sql = "update funcesc set nome_funcesc=?,"
				+ "email_funcesc=?,"
				+ "senha_funcesc=? where codigo_funcesc=?";
		
		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
		
		pstm.setString(1, e.getNome());
		pstm.setString(2, e.getEmail());
		pstm.setString(3, e.getSenha());
		
		pstm.setInt(4, e.getCodigo());
		
		pstm.executeUpdate();
		
	}

	@Override
	public FuncEsc read(Integer k) throws SQLException {
		// TODO Auto-generated method stub
		
		String sql = "select * from funcesc where codigo_funcesc = ?";
		
		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
		
		pstm.setInt(1, k);
		
		ResultSet result = pstm.executeQuery();
		
		if(result.next()) {
			
			FuncEsc e =  new FuncEsc();
			
			e.setCodigo(result.getInt("codigo_funcesc"));
			e.setNome(result.getString("nome_funcesc"));
			e.setEmail(result.getString("email_funcesc"));
			e.setSenha(result.getString("senha_funcesc"));
			
			return e;
		}
		
		return null;
	}
	
	public List<FuncEsc> readAll() throws SQLException{
		
		String sql = "select * from funcesc";
		
		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
		
		List<FuncEsc> funcescs = new ArrayList<>();
		
		ResultSet result = pstm.executeQuery();
		
		while(result.next()) {
			
			FuncEsc e =  new FuncEsc();
			
			e.setCodigo(result.getInt("codigo_funcesc"));
			e.setNome(result.getString("nome_funcesc"));
			e.setEmail(result.getString("email_funcesc"));
			e.setSenha(result.getString("senha_funcesc"));
			
			funcescs.add(e);
		}
		
		return funcescs;
		
	}

	@Override
	public void delete(Integer k) throws SQLException {
		// TODO Auto-generated method stub
		
		String sql = "delete from funcesc where codigo_funcesc = " + k;
		
		ConnectionManager.getCurrentConnection().prepareStatement(sql).execute();
		
	}
	
	public static void main(String args[]) {
		
		FuncEsc e = new FuncEsc();
		
		e.setNome("Juliao Monotono Pereira");
		e.setSenha("123456789");
		e.setEmail("juliao_pereira@gmail.com");
		e.setCodigo(1);
		
		try {
			new FuncEscRepository().create(e);
			
			//FuncEsc e = new FuncEscRepository().read(1);
			//System.out.println(e.getNome());
			
			new FuncEscRepository().delete(1);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
