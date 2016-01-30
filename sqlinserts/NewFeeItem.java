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
public class NewFeeItem {
     private static DBConnection dbconn;
      
      public static void createBorderFeeItem(String itemname) throws SQLException
      {
           PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "INSERT INTO `fee_items_borders`(`Name`) VALUES (?)";
       
            stmt = conn.prepareStatement(insert);
            stmt.setString(1, itemname);
          
            
            
             stmt.executeUpdate();
        
         
 
			if (stmt != null) {
				stmt.close();
			}
 
			if (conn != null) {
				conn.close();
			}
 
		
      }
      
     public static void createNonBorderFeeItem(String itemname) throws SQLException
      {
        PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "INSERT INTO `fee_items_non_borders`(`Name`) VALUES (?)";
       
            stmt = conn.prepareStatement(insert);
            stmt.setString(1, itemname);
           
            
            
             stmt.executeUpdate();
        
         
 
			if (stmt != null) {
				stmt.close();
			}
 
			if (conn != null) {
				conn.close();
			}
 
		
      }
}
