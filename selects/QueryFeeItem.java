/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package selects;

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
public class QueryFeeItem {
     private static DBConnection dbconn;
      
      public static int getBorderFeeItem(String itemname) throws SQLException
      {
        PreparedStatement stmt = null;
        int id = -1;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "SELECT `ID` FROM `fee_items_borders` WHERE `Name`=?";
       
            stmt = conn.prepareStatement(insert);
            stmt.setString(1, itemname);
          
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
                id=rs.getInt("ID");
            
            if (stmt != null) {
		stmt.close();
		}
 
            if (conn != null) {
		conn.close();
		}
       	 return id;
      }
      
     public static int getNonBorderFeeItem(String itemname) throws SQLException
      {
        PreparedStatement stmt = null;
        int id = -1;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "SELECT `ID` FROM `fee_items_non_borders` WHERE `Name`=?";
       
            stmt = conn.prepareStatement(insert);
            stmt.setString(1, itemname);
           
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
                id=rs.getInt("ID");
        	if (stmt != null) {
                    stmt.close();
		}
 
		if (conn != null) {
                    conn.close();
		}
         return id;
                
      }
}
