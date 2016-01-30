/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlinserts;

import static sqlinserts.NewResidence.setResidenceMaster;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author franq
 */
public class NewMeals {
     private static DBConnection dbconn;
     
     public static void createNewMeals(int termid,int classid,String details, double fee) throws SQLException
     {
         PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "INSERT INTO `meals`(`termID`,`classID`, `For`, `Fee`)"
                + "VALUES(?,?,?,?)";
        try {
            stmt = conn.prepareStatement(insert);
            stmt.setInt(1, termid);
            stmt.setInt(2, classid);
            stmt.setString(3, details);
            stmt.setDouble(4,fee);
           
            
             stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(NewDepartment.class.getName()).log(Level.SEVERE, null, ex);
            
             if(ex.getErrorCode() == 1062)
            {
                JOptionPane.showMessageDialog(null, "Meals details have already been saved. \n" +
                        "Press Ok to continue", "Error "+ex.getErrorCode(), JOptionPane.INFORMATION_MESSAGE);
            }
        }
         finally {
 
			if (stmt != null) {
				stmt.close();
			}
 
			if (conn != null) {
				conn.close();
			}
 
		}
     }
     
      
}
