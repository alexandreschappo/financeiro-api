package br.senac.sc.financeiroapi.factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Dao {
    protected Connection conexao;
    private static final String DATABASE_NAME = "financeiroapi";
    protected PreparedStatement stmt;
    protected String sql = "";
    protected ResultSet res;
    protected boolean debug = true;

    public Dao() {
        conexao = Conexao.getConexao();
        openOrCreateDatabase();
    }
    
    private void openOrCreateDatabase(){ 
        try {
            PreparedStatement stmt;
            stmt = conexao.prepareStatement("CREATE DATABASE IF NOT EXISTS "+DATABASE_NAME);
            stmt.execute();
            
            stmt = conexao.prepareStatement("USE "+DATABASE_NAME);
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
