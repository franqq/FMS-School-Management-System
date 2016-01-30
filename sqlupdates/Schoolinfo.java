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
public class Schoolinfo {
    private static DBConnection dbconn;
     public static void updateSchool(String name,  String bording,String gender,String motto, String tel, String mob, String
             email, String add, String town) throws SQLException
     {
         PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
              
        String update = "UPDATE `school_details` SET `Name`=?,`BordingDetails`=?,`GenderDetails`=?,`Motto`=?,"
                + "`contact Telephone`=?,`Contact Mobile`=?,`email`=?,`Address`=?,`Town`=? WHERE 1";
       
            stmt = conn.prepareStatement(update);
            stmt.setString(1, name);
            stmt.setString(2, bording);
            stmt.setString(3, gender);
            stmt.setString(4, motto);
            stmt.setString(5, tel);
            stmt.setString(6, mob);
            stmt.setString(7, email);
            stmt.setString(8, add);
            stmt.setString(9, town);
                      
            
            stmt.executeUpdate();
        
            if (stmt != null) {
            stmt.close();
            }
 
            if (conn != null) {
		conn.close();
            }
 
		
     }
    
}
