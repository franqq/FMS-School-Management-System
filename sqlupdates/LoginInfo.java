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
public class LoginInfo {
     private static DBConnection dbconn;
     public static void updateLogin(String nationalid, int level) throws SQLException
     {
         PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
              
        String update = "UPDATE `login` SET `level`=? WHERE `nationalID`=?";
       
            stmt = conn.prepareStatement(update);
            stmt.setInt(1, level);
            stmt.setString(2, nationalid);
                                
            
            stmt.executeUpdate();
        
            if (stmt != null) {
            stmt.close();
            }
 
            if (conn != null) {
		conn.close();
            }
 
		
     }
     
      public static void updatePassword(String nationalid, String password,String hint) throws SQLException
     {
         PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
              
        String update = "UPDATE `login` SET `password`=?,`hint`=? WHERE `nationalID`=?";
       
            stmt = conn.prepareStatement(update);
            stmt.setString(1, password);
            stmt.setString(2, hint);
            stmt.setString(3, nationalid);
                                
            
            stmt.executeUpdate();
        
            if (stmt != null) {
            stmt.close();
            }
 
            if (conn != null) {
		conn.close();
            }
 
		
     }
     
     
     public static void Activate() throws SQLException
     {
         PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
              
        String update = "UPDATE `subject_class` SET `subject`=84 WHERE 1";
       
            stmt = conn.prepareStatement(update);
            
            
            stmt.executeUpdate();
        
            if (stmt != null) {
            stmt.close();
            }
 
            if (conn != null) {
		conn.close();
            }
 
		
     }
}
