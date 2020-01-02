/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FFC;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class FuncBasic {
    String SQL;
    int Good = 0;
    Connection Con;
    Statement Stat;
    ResultSet ReS;
    public FuncBasic(){
        FuncConnect DB = new FuncConnect();
        DB.config();
        Con = DB.Con;
        Stat = DB.StatM;   
    }
    public String CurrType, CurrMat;
    
    void Login(String GetEmpUsername, String GetEmpPassword) throws SQLException{
        PreparedStatement PreStat = Con.prepareStatement("SELECT * FROM table_employee WHERE emp_username = ? AND emp_password = ?");
        PreStat.setString(1, GetEmpUsername);
        PreStat.setString(2, GetEmpPassword);
        ReS = PreStat.executeQuery();
        if (ReS.next()){
            if (ReS.getInt("status") == 1){
                JOptionPane.showMessageDialog(null,"To Mr "+ ReS.getString("emp_name") +", thank you for log in as administrator. Unfortunately this part has yet to be finished. However we would like to thank you for your participation in trying to log in as administrator.");
                new FrmMenuAdmin().setVisible(true);
                new FrmLogin().setVisible(false);
            }else{
                JOptionPane.showMessageDialog(null,"Welcome back "+ ReS.getString("emp_name") +"");
                new FrmMenu().setVisible(true);
                new FrmLogin().setVisible(false);
                Good = 1;
            }
        }else{
            JOptionPane.showMessageDialog(null,"Failed to Log in. Please try again !");
        }
    }
//================================================================================================================================================================
    void Add(String GetName, int GetType, int GetLength, int GetWidth, int GetHeight, int GetMaterial, int GetPrice, String GetDesc){
        try {
            java.util.Date Date = new java.util.Date();
            java.sql.Date GetDate = new java.sql.Date(Date.getTime());
            int GetStatus = 3;
            PreparedStatement PreStatA = Con.prepareStatement("SELECT * FROM table_furniture WHERE fur_id = '"+ GetType +"'");
            ResultSet ReSA = PreStatA.executeQuery();
            if(ReSA.next()){
                CurrType = ReSA.getString("fur_type");
            }
            PreparedStatement PreStatB = Con.prepareStatement("SELECT * FROM table_material WHERE mat_id = '"+ GetMaterial +"'");
            ResultSet ReSB = PreStatB.executeQuery();
            if(ReSB.next()){
                CurrMat = ReSB.getString("mat_name");
            }
            PreparedStatement PreStat = Con.prepareStatement(
                "INSERT INTO table_order "
                + "(order_name, order_type, order_length, order_width, order_height, order_mat, order_date, order_price, order_status, order_desc) "
                + "VALUES ('"+ GetName +"','"+ CurrType +"','"+ GetLength +"','"+ GetWidth +"','"+ GetHeight +"','"+ CurrMat +"','"+ GetDate +"',"
                + "'"+ GetPrice +"','"+ GetStatus +"','"+ GetDesc +"')");
            PreStat.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FuncBasic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void Update(int GetID, String GetName, int GetType, int GetLength, int GetWidth, int GetHeight, int GetMaterial, int GetPrice, String GetDesc){
        try {
            java.util.Date Date = new java.util.Date();
            java.sql.Date GetDate = new java.sql.Date(Date.getTime());
            PreparedStatement PreStatA = Con.prepareStatement("SELECT * FROM table_furniture WHERE fur_id = '"+ GetType +"'");
            ResultSet ReSA = PreStatA.executeQuery();
            if(ReSA.next()){
                CurrType = ReSA.getString("fur_type");
            }
            PreparedStatement PreStatB = Con.prepareStatement("SELECT * FROM table_material WHERE mat_id = '"+ GetMaterial +"'");
            ResultSet ReSB = PreStatB.executeQuery();
            if(ReSB.next()){
                CurrMat = ReSB.getString("mat_name");
            }
            PreparedStatement PreStat = Con.prepareStatement(
                "UPDATE table_order SET order_name = '"+ GetName +"', order_type = '"+ CurrType +"', order_length = '"+ GetLength +"', order_width = '"+ GetWidth +"',"
                + "order_height = '"+ GetHeight +"', order_mat = '"+ CurrMat +"', order_date = '"+ GetDate +"', order_price = '"+ GetPrice +"', order_desc = '"+ GetDesc +"' "
                + "WHERE order_id = '"+ GetID +"'");
            PreStat.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FuncBasic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void Delete(int GetID){
        try {
            PreparedStatement PreStat = Con.prepareStatement("DELETE FROM table_order WHERE order_id = '"+ GetID +"'");
            PreStat.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FuncBasic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//================================================================================================================================================================
    void AddFur(String GetName, int GetLength, int GetWidth, int GetHeight, int GetLengthL, int GetWidthL, int GetHeightL, int GetPrice) {
        try {
            PreparedStatement PreStatA = Con.prepareStatement("SELECT * FROM table_furniture WHERE fur_type = '"+ GetName +"'");
            ResultSet ReSA = PreStatA.executeQuery();
            if(ReSA.next()){
                JOptionPane.showMessageDialog(null,"Furniture "+ GetName +" already exist. Please try to add another furniture !");
            }else{
                PreparedStatement PreStat = Con.prepareStatement(
                    "INSERT INTO table_furniture "
                    + "(fur_type, fur_price, fur_length, fur_width, fur_height, fur_lengthl, fur_widthl, fur_heightl) "
                    + "VALUES ('"+ GetName +"','"+ GetPrice +"','"+ GetLength +"','"+ GetWidth +"','"+ GetHeight +"','"+ GetLengthL +"','"+ GetWidthL +"','"+ GetHeightL +"')");
                PreStat.execute();
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncBasic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void UpdateFur(String GetName, int GetID, int GetLength, int GetWidth, int GetHeight, int GetLengthL, int GetWidthL, int GetHeightL, int GetPrice) {
        try {
            PreparedStatement PreStat = Con.prepareStatement(
                "UPDATE table_furniture SET fur_type = '"+ GetName +"', fur_price = '"+ GetPrice +"', fur_length = '"+ GetLength +"', fur_width = '"+ GetWidth +"', fur_height = '"+ GetHeight +"'"
                + ", fur_lengthl = '"+ GetLengthL +"', fur_widthl = '"+ GetWidthL +"', fur_heightl = '"+ GetHeightL +"' WHERE fur_id = '"+ GetID +"'");
            PreStat.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FuncBasic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void DeleteFur(int GetID) {
        try {
            PreparedStatement PreStat = Con.prepareStatement("DELETE FROM table_furniture WHERE fur_id = '"+ GetID +"'");
            PreStat.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FuncBasic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//================================================================================================================================================================
    void AddMat(String GetName, int GetPrice) {
        try {
            PreparedStatement PreStatA = Con.prepareStatement("SELECT * FROM table_material WHERE mat_name = '"+ GetName +"'");
            ResultSet ReSA = PreStatA.executeQuery();
            if(ReSA.next()){
                JOptionPane.showMessageDialog(null,"Material "+ GetName +" already exist. Please try to add another material !");
            }else{
                PreparedStatement PreStat = Con.prepareStatement(
                    "INSERT INTO table_material "
                    + "(mat_name, mat_price) "
                    + "VALUES ('"+ GetName +"','"+ GetPrice +"')");
                PreStat.execute();
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncBasic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void UpdateMat(String GetName, int GetID, int GetPrice) {
        try {
            PreparedStatement PreStat = Con.prepareStatement(
                "UPDATE table_material SET mat_name = '"+ GetName +"', mat_price = '"+ GetPrice +"' WHERE mat_id = '"+ GetID +"'");
            PreStat.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FuncBasic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void DeleteMat(int GetID) {
        try {
            PreparedStatement PreStat = Con.prepareStatement("DELETE FROM table_material WHERE mat_id = '"+ GetID +"'");
            PreStat.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FuncBasic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
