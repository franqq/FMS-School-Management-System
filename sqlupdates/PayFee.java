/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlupdates;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author franq
 */
public class PayFee {
      private static DBConnection dbconn;
     public static void updateBorderStatement(Double totalpaid,Double balance, Long studentid) throws SQLException
     {
         PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
              
        String update = "UPDATE `fee_statement_borders` SET `Total Paid`=?,`Balance`=? WHERE `studentID`=?";
       
            stmt = conn.prepareStatement(update);
            stmt.setDouble(1, totalpaid);
            stmt.setDouble(2, balance);
            stmt.setLong(3, studentid);
                      
            
            stmt.executeUpdate();
        
            if (stmt != null) {
            stmt.close();
            }
 
            if (conn != null) {
		conn.close();
            }
 
		
     }
      public static void updateNonBorderStatement(Double totalpaid,Double balance, Long studentid) throws SQLException
     {
         PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
              
        String update = "UPDATE `fee_statement_non_borders` SET ,`Total Paid`=?,`Balance`=? WHERE `studentID`=?";
       
            stmt = conn.prepareStatement(update);
            stmt.setDouble(1, totalpaid);
            stmt.setDouble(2, balance);
            stmt.setLong(3, studentid);
                      
            
            stmt.executeUpdate();
        
            if (stmt != null) {
            stmt.close();
            }
 
            if (conn != null) {
		conn.close();
            }
 
		
     }
}
