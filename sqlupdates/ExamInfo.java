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
public class ExamInfo {
     private static DBConnection dbconn;
     public static void updateExam(String name,  String type, String startdate,int termid,String year,int examid ) throws SQLException
     {
         PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
              
        String update = "UPDATE `exam` SET `Exam Name`=?,`Exam Type`=?,`Start Date`=?,`termID`=?,`Year`=? WHERE `ID`=?";
       
            stmt = conn.prepareStatement(update);
            stmt.setString(1, name);
            stmt.setString(2, type);
            stmt.setString(3,startdate);
            stmt.setInt(4, termid);
            stmt.setString(5, year);
            stmt.setInt(6, examid);
           
            stmt.executeUpdate();
        
            if (stmt != null) {
            stmt.close();
            }
 
            if (conn != null) {
		conn.close();
            }
 
		
     }
}
