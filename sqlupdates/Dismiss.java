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
public class Dismiss {
     private static DBConnection dbconn;
     public static void DismissStaff(int userid) throws SQLException
     {
         PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
              
        String update = "UPDATE `staff` SET `Whether Working`=FALSE WHERE `ID`=?";
       
            stmt = conn.prepareStatement(update);
            stmt.setInt(1, userid);
            
            stmt.executeUpdate();
            if (stmt != null) {
            stmt.close();
            }
 
            if (conn != null) {
		conn.close();
            }
 
		
     }
     
     public static void DismissSupportStaff(int userid) throws SQLException
     {
         PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
              
        String update = "UPDATE `support_staff` SET `Whether Working`=FALSE WHERE `ID`=?";
       
            stmt = conn.prepareStatement(update);
            stmt.setInt(1, userid);
            
            stmt.executeUpdate();
            if (stmt != null) {
            stmt.close();
            }
 
            if (conn != null) {
		conn.close();
            }
 
		
     }
}
