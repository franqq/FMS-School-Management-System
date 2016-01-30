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

/**
 *
 * @author franq
 */
public class QueryFeeDetails {
    private static DBConnection dbconn;
      
      public static ArrayList getBorderFeeDet(String receiptno) throws SQLException
      {
        PreparedStatement stmt = null;
        ArrayList<String> details = new ArrayList<String>();
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "SELECT  `studentID`, `Ammount`, `Payment For` FROM `fee_payment_borders` WHERE `Receipt No`=?";
        
            stmt = conn.prepareStatement(insert);
            stmt.setString(1, receiptno);
          
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
                details.add(rs.getString("studentID"));
                details.add(rs.getString("Ammount"));
                details.add(rs.getString("Payment For"));
            
            if (stmt != null) {
		stmt.close();
		}
 
            if (conn != null) {
		conn.close();
		}
       	 return details;
      }
      
      public static ArrayList getNonBorderFeeDet(String receiptno) throws SQLException
      {
        PreparedStatement stmt = null;
        ArrayList<String> details = new ArrayList<String>();
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "SELECT  `studentID`, `Ammount`, `Payment For` FROM `fee_payment_non_borders` WHERE `Receipt No`=?";
        
            stmt = conn.prepareStatement(insert);
            stmt.setString(1, receiptno);
          
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
                details.add(rs.getString("studentID"));
                details.add(rs.getString("Ammount"));
                details.add(rs.getString("Payment For"));
            
            if (stmt != null) {
		stmt.close();
		}
 
            if (conn != null) {
		conn.close();
		}
       	 return details;
      }
}
