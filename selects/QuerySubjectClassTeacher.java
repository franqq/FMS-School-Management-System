/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package selects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author franq
 */
public class QuerySubjectClassTeacher {
    public static int getClass_TeacherID( int subjectid, int teacherid, int classid)
    {
        DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            int id = 0;
           
            String Sql = "SELECT `ID` FROM `subject_teacher_class` WHERE `subjectID`=? AND `staffID`=? AND `classID`=? ORDER BY `ID` DESC";
            
        try {            
            pstmt = conn.prepareStatement(Sql);
            pstmt.setInt(1, subjectid);
            pstmt.setInt(2, teacherid);
            pstmt.setInt(3, classid);
            
           
            
            rs = pstmt.executeQuery();
            
            if(rs.next())
              id = rs.getInt("ID");
           
            
        } catch (SQLException ex) {
            Logger.getLogger(QueryStudentDetails.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                "Some unexpected error occured\n"+
                "Was not able to load Staff details\n"+
                "Please contact the systems administrator for assistance","Warning",JOptionPane.ERROR_MESSAGE);
        }
           
        return id;
       
    }
}
