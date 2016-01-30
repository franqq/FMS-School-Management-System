/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlinserts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author franq
 */
public class SetFeeBalance {
      private static DBConnection dbconn;
     public static void setBorderStatement(Double totalpaid,Double balance, Long studentid) throws SQLException
     {
         PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
              
        String update = "INSERT INTO `fee_statement_borders`(`studentID`, `Total Paid`, `Balance`) VALUES (?,?,?)";
       
            stmt = conn.prepareStatement(update);
            stmt.setLong(1, studentid);
            stmt.setDouble(2, totalpaid);
            stmt.setDouble(3, balance);
            
                      
            
            stmt.executeUpdate();
        
            if (stmt != null) {
            stmt.close();
            }
 
            if (conn != null) {
		conn.close();
            }
 
		
     }
      public static void setNonBorderStatement(Double totalpaid,Double balance, Long studentid) throws SQLException
     {
         PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
              
        String update = "INSERT INTO `fee_statement_non_borders`(`studentID`, `Total Paid`, `Balance`) VALUES (?,?,?)";
       
            stmt = conn.prepareStatement(update);
            stmt.setLong(1, studentid);
            stmt.setDouble(2, totalpaid);
            stmt.setDouble(3, balance);
                      
            
            stmt.executeUpdate();
        
            if (stmt != null) {
            stmt.close();
            }
 
            if (conn != null) {
		conn.close();
            }
 
		
     }
}
