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
public class ExamResultsInfo {
    private static DBConnection dbconn;
    
     public static void updateMark(int examid,int studentid,int classid,int subjectid,int subjectclassteacherid,double marks,int gradeid) throws SQLException
      {
          PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "UPDATE `exam_result` "
                + "SET `Marks`=?,`gradeID`=? "
                + "WHERE `examID`=? "
                + "AND`studentID`=?"
                + " AND `classID`=? "
                + "AND `subjectID`=? "
                + "AND`subject_teacher_classID`=? "
                + "AND`Activate`=FALSE";
          
              stmt = conn.prepareStatement(insert);
              
              stmt.setDouble(1, marks);
              stmt.setInt(2, gradeid);
              stmt.setInt(3, examid);
              stmt.setInt(4, studentid);
              stmt.setInt(5, classid);
              stmt.setInt(6, subjectid);
              stmt.setInt(7, subjectclassteacherid);
              
              
              stmt.executeUpdate();
             
        	if (stmt != null) {
			stmt.close();
			}
 
		if (conn != null) {
			conn.close();
			}
     
      }
}
