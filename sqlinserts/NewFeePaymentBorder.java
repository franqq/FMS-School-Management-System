/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlinserts;

import static sqlinserts.NewFeeDetailsNonBorders.createNewFeeDetails_NonBorders;
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
public class NewFeePaymentBorder {
    private static DBConnection dbconn;
    
    public static void payBorderFee(long studentid, double ammount, String paymentfor,int clerkid) throws SQLException
    {
        NewReceiptNo receiptupdate = new NewReceiptNo(true);
         PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "INSERT INTO `fee_payment_borders`(`Receipt No`, `studentID`, `Ammount`, `Payment For`, `Date`, `clerkID`)"
                + "VALUES(?,?,?,?,?,?)";
     
            int executeUpdate=0;
            stmt = conn.prepareStatement(insert);
            stmt.setLong(1, getReceiptNo());
            stmt.setLong(2, studentid);
            stmt.setDouble(3, ammount);
            stmt.setString(4, paymentfor);
            stmt.setString(5, selects.QueryMainDetails.getCurrentDate());
            stmt.setInt(6, clerkid);
            executeUpdate = stmt.executeUpdate();
             if(executeUpdate!=0)
             
       
 
			if (stmt != null) {
				stmt.close();
			}
 
			if (conn != null) {
				conn.close();
			}
 
		
    }
    
    
    private static Long getReceiptNo() throws SQLException
    {
        PreparedStatement stmt = null;
        long contid = 0;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        
        
        String select = "SELECT  `receipt` FROM  `receipt no` ORDER BY `receipt` DESC LIMIT 1";
        try {
            stmt = conn.prepareStatement(select);
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
 
		 contid = rs.getLong("receipt");
 
			}
            
            
        } catch (SQLException ex) {
            Logger.getLogger(NewStudent.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Unexpected Error Occured \n" +
                        "Please Contact the System Administrator", "Error "+ex.getErrorCode(), JOptionPane.INFORMATION_MESSAGE);
        }
        finally {
 
			if (stmt != null) {
				stmt.close();
			}
 
			if (conn != null) {
				conn.close();
			}
 
		}
        return contid;
    }
    
     //method to get todays date
      private static java.sql.Date getCurrentTimeStamp() {
 
		java.util.Date today = new java.util.Date();
		return new java.sql.Date(today.getTime());
 
	}
      
      
       
}
