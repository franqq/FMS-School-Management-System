/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package selects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author franq
 */
public class QueryExamResults {
     public static double getOldTotal(int examid,int studentid,int classid) throws SQLException
    {
        DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            double total = 0;
            
            String Sql = "SELECT `Total` FROM `exam_student_total` WHERE `examID`=? AND `studentID`=? AND `classID`=?";
                    
            pstmt = conn.prepareStatement(Sql);
            pstmt.setInt(1, examid);
            pstmt.setInt(2, studentid);
            pstmt.setInt(3, classid);
            
            rs = pstmt.executeQuery();
            boolean next = rs.next();
            
            total = rs.getDouble("Total");
           
            
       return total;
    }
     
     public static float getOldMark(int examid,int studentid,int classid,int subjectid,int subjectteacherclassid) throws SQLException
    {
        DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            float total = 0;
            
            String Sql = "SELECT  `Marks` FROM `exam_result` "
                    + "WHERE `examID`=? "
                    + "AND `studentID`=? "
                    + "AND `classID`=? "
                    + "AND `subjectID`=? "
                    + "AND `subject_teacher_classID`=? "
                    + "AND `Activate`=FALSE";
                    
            pstmt = conn.prepareStatement(Sql);
            pstmt.setInt(1, examid);
            pstmt.setInt(2, studentid);
            pstmt.setInt(3, classid);
            pstmt.setInt(4, subjectid);
            pstmt.setInt(5, subjectteacherclassid);
            
            rs = pstmt.executeQuery();
            boolean next = rs.next();
            
            total = rs.getFloat("Marks");
           
            
       return total;
    }
}
