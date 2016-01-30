/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package selects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author franq
 */
public class QueryPayFee {
     public static int getBordingDetails(long id)
    {
        DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            int residenceid =0;
            
            String Sql = "SELECT `residenceID` FROM `student` WHERE `Whether Student`=TRUE AND`ID`=?;";
        try {            
            pstmt = conn.prepareStatement(Sql);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if(rs.next())
            {
            residenceid = rs.getInt("residenceID");
            }
            else{
               residenceid = -1; 
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuerySearchClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                "Some unexpected error occured\n"+
                "Was not able to load Staff Names details\n"+
                "Please contact the systems administrator for assistance","Warning",JOptionPane.ERROR_MESSAGE);
        }
           
       
       return residenceid;
    }
     public static String getBorderBal(long id)
    {
        DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            String bal ="";
            
            String Sql = "SELECT `Balance` FROM `fee_statement_borders` WHERE `studentID`=?;";
        try {            
            pstmt = conn.prepareStatement(Sql);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if(rs.next())
            {
            bal = rs.getString("Balance");
            }
            else{
               bal = ""; 
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuerySearchClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                "Some unexpected error occured\n"+
                "Was not able to load Staff Names details\n"+
                "Please contact the systems administrator for assistance","Warning",JOptionPane.ERROR_MESSAGE);
        }
           
       
       return bal;
    }
     public static String getBorderTotal(long id)
    {
        DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            String bal ="";
            
            String Sql = "SELECT `Total Paid` FROM `fee_statement_borders` WHERE `studentID`=?       ;";
        try {            
            pstmt = conn.prepareStatement(Sql);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if(rs.next())
            {
            bal = rs.getString("Total Paid");
            }
            else{
               bal = ""; 
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuerySearchClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                "Some unexpected error occured\n"+
                "Was not able to load Staff Names details\n"+
                "Please contact the systems administrator for assistance","Warning",JOptionPane.ERROR_MESSAGE);
        }
           
       
       return bal;
    }
     public static String getNonBorderBal(long id)
    {
        DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            String bal ="";
            
            String Sql = "SELECT `Balance` FROM `fee_statement_non_borders` WHERE `studentID`=?;";
        try {            
            pstmt = conn.prepareStatement(Sql);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if(rs.next())
            {
            bal = rs.getString("Balance");
            }
            else{
               bal = ""; 
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuerySearchClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                "Some unexpected error occured\n"+
                "Was not able to load Staff Names details\n"+
                "Please contact the systems administrator for assistance","Warning",JOptionPane.ERROR_MESSAGE);
        }
           
       
       return bal;
    }
     public static String getNonBorderTotal(long id)
    {
        DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            String bal ="";
            
            String Sql = "SELECT `Total Paid` FROM `fee_statement_non_borders` WHERE `studentID`=?       ;";
        try {            
            pstmt = conn.prepareStatement(Sql);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if(rs.next())
            {
            bal = rs.getString("Total Paid");
            }
            else{
               bal = ""; 
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuerySearchClass.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                "Some unexpected error occured\n"+
                "Was not able to load Staff Names details\n"+
                "Please contact the systems administrator for assistance","Warning",JOptionPane.ERROR_MESSAGE);
        }
           
       
       return bal;
    }
}
