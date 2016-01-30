/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlinserts;

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
public class NewFeeBreakdown {
    
     private static DBConnection dbconn;
     
     private static void createNewTermFeeBreakDown(String name, double ammount, int termid, int classid, boolean residencestate) throws SQLException
     {
         PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "INSERT INTO `fee_breakdown`(`Name`, `Ammount`, `termID`, `classID`, `Residence State`)"
                + "VALUES(?,?,?,?,?)";
        try {
            stmt = conn.prepareStatement(insert);
            stmt.setString(1, name);
            stmt.setDouble(2, ammount);
            stmt.setInt(3, termid);
            stmt.setInt(4, classid);
            stmt.setBoolean(5, residencestate);
           
            
             stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(NewDepartment.class.getName()).log(Level.SEVERE, null, ex);
            
             if(ex.getErrorCode() == 1062)
            {
                JOptionPane.showMessageDialog(null, "Fee details have already been saved. \n" +
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
     
     
     
      public static void main(String args[]) {
          try {
             createNewTermFeeBreakDown("Tuition",4500.00,1,1,true);
          } catch (SQLException ex) {
              Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
}
