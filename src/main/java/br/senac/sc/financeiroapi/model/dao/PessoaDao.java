package br.senac.sc.financeiroapi.model.dao;

import br.senac.sc.financeiroapi.factory.Dao;
import br.senac.sc.financeiroapi.model.Pessoa;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaDao extends Dao{

    public PessoaDao() {
        super();
    }
    
    public boolean salvar(Pessoa obj) {
        try {
            stmt = conexao.prepareStatement("INSERT INTO "
                    + "pessoa(nome, endereco) VALUES(?, ?, ?)");
            stmt.setString(1, obj.getNome());
            stmt.setLong(2, obj.getEndereco().getId());

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

    public boolean atualizar(Pessoa obj) {
        try {
            stmt = conexao.prepareStatement("UPDATE lancamento"
                    + " SET nome = ? "
                    + " SET data_vencimento = ? "
                    + " SET data_pagamento = ? "
                    + " SET valor = ? "
                    + " SET observacao = ? "
                    + " SET tipo = ? "
                    + " SET codigo_categoria = ? "
                    + " SET codigo_pessoa = ? "
                    + " WHERE id = ? ");
            stmt.setString(1, obj.getDescricao());
            stmt.setDate(2, obj.getDataVencimento());
            stmt.setDate(3, obj.getDataPagamento());
            stmt.setDouble(4, obj.getValor());
            stmt.setString(5, obj.getObservacao());
            stmt.setString(6, obj.getTipo().name());
            stmt.setLong(7, obj.getCategoria().getId());
            stmt.setLong(8, obj.getPessoa().getId());

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

    public boolean excluir(Pessoa obj) {
        try {
            stmt = conexao.prepareStatement("Delete FROM lancamento "
                    + " WHERE id = ?");
            stmt.setLong(1, obj.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public List<Pessoa> listar() {
        try {
            stmt = conexao.prepareStatement("SELECT id, descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa FROM "
                    + " lancamento ORDER BY id DESC");
            res = stmt.executeQuery();
            List<Pessoa> lista = new ArrayList<>();

            while (res.next()) {
                Lancamento l = new Lancamento();
                l.setId(res.getLong("id"));
                l.setDescricao(res.getString("nome"));
                l.setDataVencimento(res.getDate("data_vencimento"));
                l.setDataPagamento(res.getDate("data_pagamento"));
                l.setValor(res.getDouble("valor"));
                l.setObservacao(res.getString("observacao"));
                l.setTipo(res.getString("tipo"));
                l.getCategoria().setId(res.getLong("codigo_categoria"));
                l.getPessoa().setId(res.getLong("codigo_pessoa"));
                lista.add(l);
            }

            return lista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public Pessoa buscarPorId(Long id) {

        try {
            stmt = conexao.prepareStatement("SELECT id, descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa FROM "
                    + " lancamento WHERE id = ?");
            stmt.setLong(1, id);
            res = stmt.executeQuery();
            if(res.next()) {
                Lancamento l = new Lancamento();
                l.setId(res.getLong("id"));
                l.setDescricao(res.getString("nome"));
                l.setDataVencimento(res.getDate("data_vencimento"));
                l.setDataPagamento(res.getDate("data_pagamento"));
                l.setValor(res.getDouble("valor"));
                l.setObservacao(res.getString("observacao"));
                l.setTipo(res.getString("tipo"));
                l.getCategoria().setId(res.getLong("codigo_categoria"));
                l.getPessoa().setId(res.getLong("codigo_pessoa"));
                return l;
            }
            return null;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

}
