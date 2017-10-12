/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.inatel.ac8.power_rangers.util;

import br.inatel.ac8.power_rangers.util.exception.ErroSistema;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author samuel
 */
public class FabricaConexao {
    
    private static Connection conexao;
    private static final String URL_CONEXAO = "jdbc:mysql://Localhost:3306/sistema-powerrangers";
    private static final String USUARIO = "root";
    private static final String SENHA = "root";

    public static Connection getConexao() throws ErroSistema{
        if(conexao == null)
        {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conexao = DriverManager.getConnection(URL_CONEXAO, USUARIO, SENHA);
            } catch (SQLException ex) {
                //Logger.getLogger(FabricaConexao.class.getName()).log(Level.SEVERE, null, ex);
                throw new ErroSistema("Nao foi possivel conectar ao banco de dados!",ex);
            } catch (ClassNotFoundException ex) {
                //Logger.getLogger(FabricaConexao.class.getName()).log(Level.SEVERE, null, ex);
                throw new ErroSistema("O driver do banco de dados nao foi encontrado!",ex);
            }
        }
        
        return conexao;
    }
    
    public static void fecharConexao() throws ErroSistema{
        if(conexao != null)
        {
            try {
                conexao.close();
                conexao = null;
            } catch (SQLException ex) {
                //Logger.getLogger(FabricaConexao.class.getName()).log(Level.SEVERE, null, ex);
                throw new ErroSistema("Erro ao fechar conexao com o banco de dados!",ex);
            }
        }
    }
    
    
    
}
