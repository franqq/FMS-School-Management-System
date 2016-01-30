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
public class ClassInfo {
    private static DBConnection dbconn;
     public static void updateClass(String classl,  String level,String capacity, int classid) throws SQLException
     {
         PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
              
        String update = "UPDATE `class` SET `Class`=?,`Level`=?,`Capacity`=? WHERE `ID`=?";
       
            stmt = conn.prepareStatement(update);
            stmt.setString(1, classl);
            stmt.setString(2, level);
            stmt.setString(3, capacity);
            stmt.setInt(4, classid);
           
            stmt.executeUpdate();
        
            if (stmt != null) {
            stmt.close();
            }
 
            if (conn != null) {
		conn.close();
            }
 
		
     }
     
     public void stopClassTeacher(int classid) throws SQLException
     {
          PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
              
        String update = "UPDATE `class` SET `Class`=?,`Level`=?,`Capacity`=? WHERE `ID`=?";
       
            stmt = conn.prepareStatement(update);
           // stmt.setString(1, classl);
           // stmt.setString(2, level);
           // stmt.setString(3, capacity);
           // stmt.setInt(4, deptid);
           
            stmt.executeUpdate();
        
            if (stmt != null) {
            stmt.close();
            }
 
            if (conn != null) {
		conn.close();
            }
     }

}
