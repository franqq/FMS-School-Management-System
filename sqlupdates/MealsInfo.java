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
public class MealsInfo {
    private static DBConnection dbconn;
     public static void updateMealsFee(Double mealsFee, int termid,int classid, String forstr) throws SQLException
     {
         PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
              
        String update = "UPDATE `meals` SET `Fee`=? WHERE `termID`=? AND `classID`=? AND`For`=?";
       
            stmt = conn.prepareStatement(update);
            stmt.setDouble(1, mealsFee);
            stmt.setInt(2, termid);
            stmt.setInt(3, classid);
            stmt.setString(4, forstr);
                                
            
            stmt.executeUpdate();
        
            if (stmt != null) {
            stmt.close();
            }
 
            if (conn != null) {
		conn.close();
            }
 
		
     }
     
      
}
