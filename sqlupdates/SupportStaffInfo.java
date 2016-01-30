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
public class SupportStaffInfo {
    
     private static DBConnection dbconn;
    public static void updateStaffInfo(String nationalid,String gender,String title,String surname,String fname, String lname,
            String dob,String jobtitle,String role, int whetherworking, String oldnationalid) throws SQLException
     {
         PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
              
        String update = "UPDATE `support_staff` SET `nationalID`=?,`Gender`=?,`Title`=?,`Surname`=?,`First Name`=?,"
                + "`Last Name`=?,`DOB`=?,`Job Title`=?,`Role`=?,`Whether Working`=? "
                + "WHERE `nationalID`=?";
       
            stmt = conn.prepareStatement(update);
            stmt.setString(1, nationalid);
            stmt.setString(2, gender);
            stmt.setString(3,title);
            stmt.setString(4, surname);
            stmt.setString(5, fname);
            stmt.setString(6,lname);
            stmt.setString(7,dob);
            stmt.setString(8,jobtitle);
            stmt.setString(9,role);
            stmt.setInt(10,whetherworking);
            stmt.setString(11, oldnationalid);
            
            stmt.executeUpdate();
        
			if (stmt != null) {
				stmt.close();
			}
 
			if (conn != null) {
				conn.close();
			}
 
		
     }
    
    
     public static void updateSupportStaffContacts(String mobile, String email, Long physical, String town,int contid) throws SQLException
     {
         PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "UPDATE `support_staff_contact` SET `Mobile Number`=?,`Email`=?,`Physical address`=?,`Town`=?"
                + " WHERE `ID`=?";
        
        
            stmt = conn.prepareStatement(insert);
            stmt.setString(1,mobile);
            stmt.setString(2, email);
            stmt.setLong(3, physical);
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
