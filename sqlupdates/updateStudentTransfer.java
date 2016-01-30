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
public class updateStudentTransfer {
    private static DBConnection dbconn;
     public static void updateTransferStudent (long id) throws SQLException
     {
         PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
              
        String update = "UPDATE `student` SET `Whether Student`=? WHERE `ID`=?";
       
            stmt = conn.prepareStatement(update);
            stmt.setBoolean(1,false);
            stmt.setLong(2, id);
            
           
            stmt.executeUpdate();
        
            if (stmt != null) {
            stmt.close();
            }
 
            if (conn != null) {
		conn.close();
            }
 
		
     }
}
