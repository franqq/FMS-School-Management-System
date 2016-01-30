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
public class TransportDetails {
    private static DBConnection dbconn;
     public static void updatePickPoint(int termid, int classid, String name, Double fee,int transportid) throws SQLException
     {
         PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
              
        String update = "UPDATE `transport` SET `termID`=?,`classID`=?,`Name`=?,`Fee`=? WHERE `ID`=?";
       
            stmt = conn.prepareStatement(update);
            stmt.setInt(1, termid);
            stmt.setInt(2, classid);
            stmt.setString(3, name);
            stmt.setDouble(4, fee);
            stmt.setInt(5, transportid);         
            
            stmt.executeUpdate();
            if (stmt != null) {
            stmt.close();
            }
 
            if (conn != null) {
		conn.close();
            }
 
		
     }
}
