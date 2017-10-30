/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.inatel.ac8.power_rangers.dao;

import br.inatel.ac8.power_rangers.entidade.Ranger;
import br.inatel.ac8.power_rangers.entidade.Zord;
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
public class ZordDAO {
    
    public void salvar(Zord zord) throws ErroSistema
    {
        try {       
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps;
            // id != null -> objeto vindo do banco (atualizacao)
            if(zord.getId() == null)
            {
                ps = conexao.prepareStatement("INSERT INTO `Zord`(`nomeZord`, `poderZord`) VALUES(?, ?);");
            } else {
                ps = conexao.prepareStatement("UPDATE `Zord` SET `nomeZord`=?, `poderZord`=? WHERE `idZord`=?;");
                ps.setInt(3, zord.getId());
            }
            
            ps.setString(1, zord.getNome());
            ps.setInt(2, zord.getPoder());
            ps.execute();
            
            FabricaConexao.fecharConexao();
            
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao salvar/atualizar Zord!",ex);
        }
    }
    
    public List buscar() throws ErroSistema
    {
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("SELECT * FROM Zord;");
            ResultSet resultSet = ps.executeQuery();
            
            List<Zord> zords = new ArrayList<>();
            while(resultSet.next())
            {
                Zord zord = new Zord();
                zord.setId(resultSet.getInt("idZord"));
                zord.setNome(resultSet.getString("nomeZord"));
                zord.setPoder(resultSet.getInt("poderZord"));
                
                zords.add(zord);
            }
            FabricaConexao.fecharConexao();
            
            return zords;
            
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao buscar Zord!",ex);
        }
    }
    
    public void remover(Zord zord) throws ErroSistema
    {
        try {       
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps;
            if(zord.getId() != null)
            {
                ps = conexao.prepareStatement("DELETE FROM `Zord` WHERE `idZord`=?;");
                ps.setInt(1, zord.getId());
                ps.execute();
            } else {                
                // sei la
            }
            
            FabricaConexao.fecharConexao();
            
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao remover Zord!",ex);
        }
    }
}
