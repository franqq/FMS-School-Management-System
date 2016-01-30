/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package selects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author franq
 */
public class QueryBalance {
     private static ArrayList<String> borderbalancetotal;
     public static ArrayList getBordeBalanceTotal(long studentid)
    {
        DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ArrayList<String> bbt = new ArrayList<String>();
            ResultSet rs;
                       
            String Sql = "SELECT   `Total Paid`, `Balance` FROM `fee_statement_borders` WHERE `studentID`=?";
            
        try {            
            pstmt = conn.prepareStatement(Sql);
            pstmt.setLong(1, studentid);
            rs = pstmt.executeQuery();
            
             rs.next();
             bbt.add(rs.getString("Total Paid"));
             bbt.add(rs.getString("Balance"));
            
           
            
        } catch (SQLException ex) {
            Logger.getLogger(QueryStudentDetails.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                "Some unexpected error occured\n"+
                "Was not able to load Fee details\n"+
                "Please contact the systems administrator for assistance","Warning",JOptionPane.ERROR_MESSAGE);
        }
         
        borderbalancetotal = bbt;
        return bbt;
       
    }
     
     private static ArrayList<String> dsbalancetotal;
     public static ArrayList getNonBordeBalanceTotal(long studentid)
    {
        DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ArrayList<String> bbt = new ArrayList<String>();
            ResultSet rs;
                       
            String Sql = "SELECT   `Total Paid`, `Balance` FROM `fee_statement_non_borders` WHERE `studentID`=?";
            
        try {            
            pstmt = conn.prepareStatement(Sql);
            pstmt.setLong(1, studentid);
            rs = pstmt.executeQuery();
            
             rs.next();
             bbt.add(rs.getString("Total Paid"));
             bbt.add(rs.getString("Balance"));
            
           
            
        } catch (SQLException ex) {
            Logger.getLogger(QueryStudentDetails.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                "Some unexpected error occured\n"+
                "Was not able to load Fee details\n"+
                "Please contact the systems administrator for assistance","Warning",JOptionPane.ERROR_MESSAGE);
        }
         
        dsbalancetotal = bbt;
        return bbt;
       
    }
}
