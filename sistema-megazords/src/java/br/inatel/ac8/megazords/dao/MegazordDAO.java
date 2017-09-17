/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.inatel.ac8.megazords.dao;

import br.inatel.ac8.megazords.entidade.Megazord;
import br.inatel.ac8.megazords.util.FabricaConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author samuel
 */
public class MegazordDAO {
    
    public void salvar(Megazord megazord)
    {
        try {       
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps;
            if(megazord.getId() == null)
            {
                ps = conexao.prepareStatement("INSERT INTO `megazord`(`nomeMegazord`)VALUES(?);");
            } else {
                ps = conexao.prepareStatement("UPDATE `megazord` SET `nomeMegazord`=? WHERE `idMegazord`=?;");
                ps.setInt(2, megazord.getId());
            }
            
            ps.setString(1, megazord.getNome());
            ps.execute();
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(MegazordDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List buscar()
    {
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("SELECT * FROM megazord;");
            ResultSet resultSet = ps.executeQuery();
            
            List<Megazord> megazords = new ArrayList<>();
            while(resultSet.next())
            {
                Megazord megazord = new Megazord();
                megazord.setId(resultSet.getInt("idMegazord"));
                megazord.setNome(resultSet.getString("nomeMegazord"));
                
                megazords.add(megazord);
            }
            
            return megazords;
            
        } catch (SQLException ex) {
            Logger.getLogger(MegazordDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public void remover(Megazord megazord)
    {
        try {       
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps;
            if(megazord.getId() != null)
            {
                ps = conexao.prepareStatement("DELETE FROM `megazord` WHERE `idMegazord`=?;");
                ps.setInt(1, megazord.getId());
                ps.execute();
            } else {                
                // sei la
            }
            
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(MegazordDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            
    
}