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
public class NewFeeDetailsNonBorders {
      private static DBConnection dbconn;
      
      protected static void createNewFeeDetails_NonBorders(int classid,int termid,double lostitem,double trans,double meals, double total) throws SQLException
      {
          PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "INSERT INTO `fee_details_non_borders`(`classID`, `termID`, `Lost School Item Fee`, `Transport Fee`, `Meals Fee`, `Total`)"
                + "VALUES(?,?,?,?,?,?)";
        try {
            stmt = conn.prepareStatement(insert);
            stmt.setInt(1, classid);
            stmt.setInt(2, termid);
            stmt.setDouble(3, lostitem);
            stmt.setDouble(4, trans);
            stmt.setDouble(5, meals);
            stmt.setDouble(6, total);
           
            
             stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(NewDepartment.class.getName()).log(Level.SEVERE, null, ex);
            
             if(ex.getErrorCode() == 1062)
            {
                JOptionPane.showMessageDialog(null, "Fee details have already been saved. \n" +
                        "Press Ok to continue", "Error "+ex.getErrorCode(), JOptionPane.INFORMATION_MESSAGE);
            }
              else if(ex.getErrorCode() == 1452)
            {
                JOptionPane.showMessageDialog(null, "Fee Ammount Error 1041. \n" +
                        "Press Contact the Administrator for further advice", "Error "+ex.getErrorCode(), JOptionPane.INFORMATION_MESSAGE);
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
            createNewFeeDetails_NonBorders(1, 1, 455.00, 432.00, 3455.55, 0);
          } catch (SQLException ex) {
              Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
}
