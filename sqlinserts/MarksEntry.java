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
public class MarksEntry {
    private static DBConnection dbconn;
    //method to enter new class details
      public static void EnterMarks(int examid,int studentid,int classid, int subjectid,
              int subjectteacherclassid, double marks, int gradeid) throws SQLException
      {
          PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "INSERT INTO `exam_result`(`examID`, `studentID`, `classID`, `subjectID`, `subject_teacher_classID`, "
                      + "`Marks`, `gradeID`, `Activate`) "
                      + "VALUES (?,?,?,?,?,?,?,?)";
          
              stmt = conn.prepareStatement(insert);
              
              stmt.setInt(1, examid);
              stmt.setInt(2, studentid);
              stmt.setInt(3, classid);
              stmt.setInt(4, subjectid);
              stmt.setInt(5, subjectteacherclassid);
              stmt.setDouble(6, marks);
              stmt.setInt(7, gradeid);
              stmt.setBoolean(8, false);
              
              stmt.executeUpdate();
              
           
			if (stmt != null) {
				stmt.close();
			}
 
			if (conn != null) {
				conn.close();
			}
 
      }
      
      public static void ActivateExam(int examid,int classid) throws SQLException
      {
          PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "INSERT INTO `exam_result`(`examID`,`classID`,`Activate`) "
                      + "VALUES (?,?,?)";
          
              stmt = conn.prepareStatement(insert);
              
              stmt.setInt(1, examid);
              stmt.setInt(2, classid);
              stmt.setBoolean(3, true);
              
              stmt.executeUpdate();
              
           
			if (stmt != null) {
				stmt.close();
			}
 
			if (conn != null) {
				conn.close();
			}
 
      }
      
      
      
      public static void SetTotalToZero(int examid,int studentid,int classid) throws SQLException
      {
          PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "INSERT INTO `exam_student_total`(`examID`, `studentID`, `classID`, `Total`)"
                + " VALUES (?,?,?,?)";
          
              stmt = conn.prepareStatement(insert);
              
              stmt.setInt(1, examid);
              stmt.setInt(2, studentid);
              stmt.setInt(3, classid);
              stmt.setFloat(4, 0);
              
              stmt.executeUpdate();
             
        	if (stmt != null) {
			stmt.close();
			}
 
		if (conn != null) {
			conn.close();
			}
     
      }
      
}
