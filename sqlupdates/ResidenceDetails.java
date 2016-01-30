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
public class ResidenceDetails {
    private static DBConnection dbconn;
     public static void updateResidence(int termid, String name, String capacity, String gender,Double fee,int residenceid) throws SQLException
     {
         PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
              
        String update = "UPDATE `residence` SET `termID`=?,`Name`=?,`Capacity`=?,`Gender`=?,`Fee`=? WHERE `ID`=?";
       
            stmt = conn.prepareStatement(update);
            stmt.setInt(1, termid);
            stmt.setString(2, name);
            stmt.setString(3, capacity);
            stmt.setString(4, gender);
            stmt.setDouble(5, fee);
            stmt.setInt(6, residenceid);
                      
            
            stmt.executeUpdate();
        
            if (stmt != null) {
            stmt.close();
            }
 
            if (conn != null) {
		conn.close();
            }
 
		
     }
}
