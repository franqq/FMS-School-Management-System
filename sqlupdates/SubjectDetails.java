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
public class SubjectDetails {
    private static DBConnection dbconn;
     public static void updateSubject(String subj,String desc,int id) throws SQLException
     {
         PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
              
        String update = "UPDATE `subject` SET `Subject`=?,`description`=? WHERE `departmentID`=?";
       
            stmt = conn.prepareStatement(update);
            stmt.setString(1, subj);
            stmt.setString(2, desc);
            stmt.setInt(3, id);
           
            stmt.executeUpdate();
        
            if (stmt != null) {
            stmt.close();
            }
 
            if (conn != null) {
		conn.close();
            }
 
		
     }
     
     public static void updateSubjectGrade(int low,int high,String grade,String remarks,int subjectid) throws SQLException
     {
         PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
              
        String update = "UPDATE `subject_grade` SET `Low`=?,`High`=?,`Grade`=?,`Remarks`=? WHERE `subjectID`=?";
       
            stmt = conn.prepareStatement(update);
            stmt.setInt(1, low);
            stmt.setInt(2, high);
            stmt.setString(3, grade);
            stmt.setString(4, remarks);
            stmt.setInt(5, subjectid);
           
            stmt.executeUpdate();
        
            if (stmt != null) {
            stmt.close();
            }
 
            if (conn != null) {
		conn.close();
            }
 
		
     }
     
     public static void subjectTeacher(int subjectid,int staffid,int classid) throws SQLException
    {
        PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "UPDATE `subject_teacher_class` SET `staffID`=? WHERE `subjectID`=? AND `classID`= ?;";
        
            stmt = conn.prepareStatement(insert);
            stmt.setInt(1,staffid);
            stmt.setInt(2,subjectid);
            stmt.setInt(3, classid);
            
            
             stmt.executeUpdate();
       
			if (stmt != null) {
				stmt.close();
			}
 
			if (conn != null) {
				conn.close();
			}
 
		
    }
    
}
