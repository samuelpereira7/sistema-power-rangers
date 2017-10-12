/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.inatel.ac8.power_rangers.dao;

import br.inatel.ac8.power_rangers.entidade.Ranger;
import br.inatel.ac8.power_rangers.util.FabricaConexao;
import br.inatel.ac8.power_rangers.util.exception.ErroSistema;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author samuel
 */
public class RangerDAO {
    
    public void salvar(Ranger ranger) throws ErroSistema
    {
        try {       
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps;
            // id != null -> objeto vindo do banco (atualizacao)
            if(ranger.getId() == null)
            {
                ps = conexao.prepareStatement("INSERT INTO `Ranger`(`corRanger`, `habilidadeRanger`, `armaRanger`)VALUES(?, ?, ?);");
            } else {
                ps = conexao.prepareStatement("UPDATE `Ranger` SET `corRanger`=?, `habilidadeRanger`=?, `armaRanger`=? WHERE `idRanger`=?;");
                ps.setInt(4, ranger.getId());
            }
            
            ps.setString(1, ranger.getCor());
            ps.setInt(2, ranger.getHabilidade());
            ps.setString(3, ranger.getArma());
            ps.execute();
            
            FabricaConexao.fecharConexao();
            
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao salvar/atualizar Ranger!",ex);
        }
    }
    
    public List buscar() throws ErroSistema
    {
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("SELECT * FROM Ranger;");
            ResultSet resultSet = ps.executeQuery();
            
            List<Ranger> rangers = new ArrayList<>();
            while(resultSet.next())
            {
                Ranger ranger = new Ranger();
                ranger.setId(resultSet.getInt("idRanger"));
                ranger.setCor(resultSet.getString("corRanger"));
                ranger.setHabilidade(resultSet.getInt("habilidadeRanger"));
                ranger.setArma(resultSet.getString("armaRanger"));
                
                rangers.add(ranger);
            }
            FabricaConexao.fecharConexao();
            
            return rangers;
            
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao buscar Ranger!",ex);
        }
    }
    
    public void remover(Ranger ranger) throws ErroSistema
    {
        try {       
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps;
            if(ranger.getId() != null)
            {
                ps = conexao.prepareStatement("DELETE FROM `Ranger` WHERE `idRanger`=?;");
                ps.setInt(1, ranger.getId());
                ps.execute();
            } else {                
                // sei la
            }
            
            FabricaConexao.fecharConexao();
            
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao remover Ranger!",ex);
        }
    }
}
