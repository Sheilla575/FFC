/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FFC;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class FuncConnect {
    Connection Con;
    Statement StatM;
    public void config(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Con = DriverManager.getConnection("jdbc:mysql://localhost/2sc3_ffc","root", "");
            StatM = Con.createStatement();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Connection Failed ! " + e.getMessage());
        }
    }
}
