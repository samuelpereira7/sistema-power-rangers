/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.inatel.ac8.power_rangers.dao;

import br.inatel.ac8.power_rangers.entidade.Megazord;
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
public class MegazordDAO {
    
    public void salvar(Megazord megazord) throws ErroSistema
    {
        try {       
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps;
            // id != null -> objeto vindo do banco (atualizacao)
            if(megazord.getId() == null)
            {
                ps = conexao.prepareStatement("INSERT INTO `Megazord`(`nomeMegazord`, `poderMegazord`)VALUES(?, ?);");
            } else {
                ps = conexao.prepareStatement("UPDATE `Megazord` SET `nomeMegazord`=?, `poderMegazord`=? WHERE `idMegazord`=?;");
                ps.setInt(3, megazord.getId());
            }
            
            ps.setString(1, megazord.getNome());
            // TODO: set "poderMegazord" properly
            ps.setInt(2, megazord.getPoder());
            ps.execute();
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            //Logger.getLogger(MegazordDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new ErroSistema("Erro ao salvar/atualizar Megazord!",ex);
        }
    }
    
    public List buscar() throws ErroSistema
    {
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("SELECT * FROM Megazord;");
            ResultSet resultSet = ps.executeQuery();
            
            List<Megazord> megazords = new ArrayList<>();
            while(resultSet.next())
            {
                Megazord megazord = new Megazord();
                megazord.setId(resultSet.getInt("idMegazord"));
                megazord.setNome(resultSet.getString("nomeMegazord"));
                megazord.setPoder(resultSet.getInt("poderMegazord"));
                
                megazords.add(megazord);
            }
            FabricaConexao.fecharConexao();
            
            return megazords;
            
        } catch (SQLException ex) {
            //Logger.getLogger(MegazordDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new ErroSistema("Erro ao buscar Megazord!",ex);
            //return null;
        }
    }
    
    public void remover(Megazord megazord) throws ErroSistema
    {
        try {       
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps;
            if(megazord.getId() != null)
            {
                ps = conexao.prepareStatement("DELETE FROM `Megazord` WHERE `idMegazord`=?;");
                ps.setInt(1, megazord.getId());
                ps.execute();
            } else {                
                // sei la
            }
            
            FabricaConexao.fecharConexao();
            
        } catch (SQLException ex) {
            //Logger.getLogger(MegazordDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new ErroSistema("Erro ao remover Megazord!",ex);
        }
    }
            
    
}