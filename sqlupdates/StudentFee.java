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
public class StudentFee {
     private static DBConnection dbconn;
     public static void updateBorderStudentFee(int studentid, double total) throws SQLException
     {
         PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
              
        String update = "UPDATE `fee_details_borders` SET `Total`=? WHERE `studentID`=?";
       
            stmt = conn.prepareStatement(update);
            stmt.setDouble(1, total);
            stmt.setInt(2, studentid);
            
            
            stmt.executeUpdate();
        
            if (stmt != null) {
            stmt.close();
            }
 
            if (conn != null) {
		conn.close();
            }
 
		
     }
     
     public static void updateDayStudentFee(int studentid, double total) throws SQLException
     {
         PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
              
        String update = "UPDATE `fee_details_non_borders` SET `Total`=? WHERE `studentID`=?";
       
            stmt = conn.prepareStatement(update);
            stmt.setDouble(1, total);
            stmt.setInt(2, studentid);
            
            
            stmt.executeUpdate();
        
            if (stmt != null) {
            stmt.close();
            }
 
            if (conn != null) {
		conn.close();
            }
 
		
     }
}
