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
public class NewLogin {
    private static DBConnection dbconn;
     public static void createNewUser(String nationalid,String password,String hint,String level) throws SQLException
     {
        Integer levelint = Integer.parseInt(level);
        PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "INSERT INTO `login`(`nationalID`, `password`, `hint`, `level`)"
                + "VALUES(?,?,?,?)";
      
            stmt = conn.prepareStatement(insert);
            stmt.setString(1, nationalid);
            stmt.setString(2, password);
            stmt.setString(3, hint);
            stmt.setInt(4, levelint);
            
            
            stmt.executeUpdate();
             
            
        
 
			if (stmt != null) {
				stmt.close();
			}
 
			if (conn != null) {
				conn.close();
			}
 
		
      }
}
