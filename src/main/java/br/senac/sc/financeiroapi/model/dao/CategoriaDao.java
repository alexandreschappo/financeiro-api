package br.senac.sc.financeiroapi.model.dao;

import br.senac.sc.financeiroapi.factory.Dao;
import br.senac.sc.financeiroapi.model.Categoria;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDao extends Dao {

    public CategoriaDao() {
        super();
    }

    public boolean salvar(Categoria obj) {
        try {
            stmt = conexao.prepareStatement("INSERT INTO "
                    + "categoria(nome) VALUES(?)");
            stmt.setString(1, obj.getNome());

            //Quantidade de linhas afetadas
            int res = stmt.executeUpdate();
            if (res > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }

    }

    public boolean atualizar(Categoria obj) {
        try {
            stmt = conexao.prepareStatement("UPDATE categoria"
                    + " SET nome = ? "
                    + " WHERE id = ? ");
            stmt.setString(1, obj.getNome());
            stmt.setLong(2, obj.getId());

            if (stmt.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean excluir(Categoria obj) {
        try {
            stmt = conexao.prepareStatement("Delete FROM categoria "
                    + " WHERE id = ?");
            stmt.setLong(1, obj.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public List<Categoria> listar() {
        try {
            stmt = conexao.prepareStatement("SELECT id, nome FROM "
                    + " categoria ORDER BY id DESC");
            res = stmt.executeQuery();
            List<Categoria> lista = new ArrayList<>();

            while (res.next()) {
                Categoria c = new Categoria();
                c.setId(res.getLong("id"));
                c.setNome(res.getString("nome"));
                lista.add(c);
            }

            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public Categoria buscarPorId(Long id) {

        try {
            stmt = conexao.prepareStatement("SELECT id, nome FROM "
                    + " categoria WHERE id = ?");
            stmt.setLong(1, id);
            res = stmt.executeQuery();
            if(res.next()) {
                Categoria c = new Categoria();
                c.setId(res.getLong("id"));
                c.setNome(res.getString("nome"));
                return c;
            }
            return null;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

}
