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
public class DepartmentInfo {
    private static DBConnection dbconn;
     public static void updateDepartment(String name,  String desc, int deptid) throws SQLException
     {
         PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
              
        String update = "UPDATE `department` SET `Name`=?,`Description`=? WHERE `ID`=?";
       
            stmt = conn.prepareStatement(update);
            stmt.setString(1, name);
            stmt.setString(2, desc);
            stmt.setInt(3, deptid);
           
            stmt.executeUpdate();
        
            if (stmt != null) {
            stmt.close();
            }
 
            if (conn != null) {
		conn.close();
            }
 
		
     }

          public static void updateDepartmentHead(int staffid, int deptid) throws SQLException
     {
         PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
              
        String update = "UPDATE `department_head` SET `staffID`=? WHERE `departmentID`=?;";
       
            stmt = conn.prepareStatement(update);
            stmt.setInt(1, staffid);
            stmt.setInt(2, deptid);
            
           
            stmt.executeUpdate();
        
            if (stmt != null) {
            stmt.close();
            }
 
            if (conn != null) {
		conn.close();
            }
 
		
     }
}
