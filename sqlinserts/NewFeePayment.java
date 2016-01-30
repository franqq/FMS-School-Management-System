package sqlinserts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author franq
 */
public class NewFeePayment {
      private static DBConnection dbconn;
      
      protected static void createNewFeePayment(int studentid, int feedetailsid, double ammount, String paymentfor,int clerkid) throws SQLException
      {
           PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "INSERT INTO `fee_payment_detail`(`studentID`, `fee_detailsID`, `Ammount`, `Payment For`, `Date`, `clerkID`)"
                + "VALUES(?,?,?,?,?,?)";
        try {
            stmt = conn.prepareStatement(insert);
            stmt.setInt(1, studentid);
            stmt.setInt(2, feedetailsid);
            stmt.setDouble(3, ammount);
            stmt.setString(4, paymentfor);
            stmt.setDate(5, getCurrentTimeStamp());
            stmt.setInt(6, clerkid);
            
            
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
      
      
      //method to get todays date
      private static java.sql.Date getCurrentTimeStamp() {
 
		java.util.Date today = new java.util.Date();
		return new java.sql.Date(today.getTime());
 
	}
      
      
      public static void main(String args[]) {
          try {
             createNewFeePayment(2, 1, 37333.00, "School Fee", 1);
          } catch (SQLException ex) {
              Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
    
}
