/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlupdates;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sqlinserts.NewDepartment;

/**
 *
 * @author franq
 */
public class StudentInfo {
     private static DBConnection dbconn;
    public static void createNewStudent(String surname, String firstname, String lastname, String gender, String dob,
             int classid, int residenceid, int mealsid, int transportid, int whetherstudent,int studentid) throws SQLException
     {
         PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
              
        String update = "UPDATE `student` SET `surname`=?,"
                + "`First Name`=?,`Last Name`=?,`Gender`=?,`DOB`=?,`classID`=?,"
                + "`residenceID`=?,`mealsID`=?,`transportID`=?,`Whether Student`=? "
                + "WHERE `ID`=?";
       
            stmt = conn.prepareStatement(update);
            stmt.setString(1, surname);
            stmt.setString(2, firstname);
            stmt.setString(3, lastname);
            stmt.setString(4, gender);
            stmt.setString(5, dob);
            
            stmt.setInt(6,classid);
            stmt.setInt(7,residenceid);
            stmt.setInt(8,mealsid);
            stmt.setInt(9,transportid);
            stmt.setInt(10,whetherstudent);
            
            stmt.setInt(11, studentid);
            
            stmt.executeUpdate();
        
			if (stmt != null) {
				stmt.close();
			}
 
			if (conn != null) {
				conn.close();
			}
 
		
     }
    
    
      public static void updateStudentContacts(String mobile, String email, String physical, String town,int contid) throws SQLException
     {
         PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "UPDATE `student_contact` SET `Mobile Number`=?,`Email`=?,`Physical Address`=?,`Town`=? "
                + "WHERE `ID`=?";
        
            stmt = conn.prepareStatement(insert);
            stmt.setString(1,mobile);
            stmt.setString(2, email);
            stmt.setString(3, physical);
            stmt.setString(4, town);
            stmt.setInt(5, contid);
            
           
            
             stmt.executeUpdate();
             
        
 
			if (stmt != null) {
				stmt.close();
			}
 
			if (conn != null) {
				conn.close();
			}
 
		
     }
     
}
