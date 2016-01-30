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
public class NewStudentLeader {
    
     private static DBConnection dbconn;
     
    public static void createNewStudentLeader(String postname, String studentid, String role) throws SQLException
     {
         PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "INSERT INTO `student_leaders`( `Post Name`, `studentID`, `Role`, `Start Date` ) VALUES (?,?,?,?)";
       
            stmt = conn.prepareStatement(insert);
            stmt.setString(1, postname);
            stmt.setString(2, studentid);
            stmt.setString(3, role);
            stmt.setString(4, selects.QueryMainDetails.getCurrentDate());
            
            
            stmt.executeUpdate();
        
			if (stmt != null) {
				stmt.close();
			}
 
			if (conn != null) {
				conn.close();
			}
 
		
     }
}
