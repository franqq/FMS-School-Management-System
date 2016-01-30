/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlinserts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author franq
 */
public class NewTransport {
     private static DBConnection dbconn;
     
     public static void createNewTransport(int termid,int classid,String details, double fee) throws SQLException
     {
        PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "INSERT INTO `transport`(`termID`,`classID`, `Name`, `Fee`)"
                + "VALUES(?,?,?,?)";
     
            stmt = conn.prepareStatement(insert);
            stmt.setInt(1, termid);
            stmt.setInt(2, classid);
            stmt.setString(3, details);
            stmt.setDouble(4, fee);
            
            stmt.executeUpdate();
           
            
        
 
			if (stmt != null) {
				stmt.close();
			}
 
			if (conn != null) {
				conn.close();
			}
 
		
     }
     
     
      
}
