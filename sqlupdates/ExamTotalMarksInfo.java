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
public class ExamTotalMarksInfo {
    private static DBConnection dbconn;
    
     public static void updateTotalMark(int examid,int studentid,int classid,double marks) throws SQLException
      {
          PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "UPDATE `exam_student_total` SET `Total`=? WHERE `examID`=? AND`studentID`=? AND `classID`=?;";
          
              stmt = conn.prepareStatement(insert);
              
              stmt.setDouble(1, marks);
              stmt.setInt(2, examid);
              stmt.setInt(3, studentid);
              stmt.setInt(4, classid);
              
              
              stmt.executeUpdate();
             
        	if (stmt != null) {
			stmt.close();
			}
 
		if (conn != null) {
			conn.close();
			}
     
      }
}
