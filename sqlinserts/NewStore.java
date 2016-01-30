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
public class NewStore {
    
    private static DBConnection dbconn;
    
    
    
    
    public static void createNewStoreAssistant(int staffid) throws SQLException
    {
         PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "INSERT INTO `item_store_assistant` (`staffID`,`Start Date`)"
                + "VALUES(?,?)";
        
            stmt = conn.prepareStatement(insert);
            stmt.setInt(1, staffid);
            stmt.setString(2, selects.QueryMainDetails.getCurrentDate());
            
            
            stmt.executeUpdate();
             
         
 
            if (stmt != null) {
		stmt.close();
            }
 
            if (conn != null) {
		conn.close();
            }
 
		
    }
    
    
    
    ////createNewItemStoreCurrent("BKM", "Macmillan Mathematics Book 2", "Book", 4,1200.00, 4800.00);
    
    private static void createNewItemStoreCurrent(String generalid, String name,String type, int ammount,double cost, double totalcost) throws SQLException
    {
        PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "INSERT INTO `item_store_current`(`General ID`, `Name`, `Type`, `Ammount`, `Cost`, `Total cost`)"
                + "VALUES(?,?,?,?,?,?)";
        
            stmt = conn.prepareStatement(insert);
            stmt.setString(1, generalid);
            stmt.setString(2, name);
            stmt.setString(3, type);
            stmt.setInt(4, ammount);
            stmt.setDouble(5, cost);
            stmt.setDouble(6, totalcost);
            
            stmt.executeUpdate();
             
        
 
	if (stmt != null) {
		stmt.close();
	}
 
	if (conn != null) {
		conn.close();
	}
 
		
    }
    
    
  ////createNewItemStoreCurrent("BKM", "Macmillan Mathematics Book 2", "Book", 4,1200.00, 4800.00);
    
    private static void createNewItemStoreAction(int itemgroupcurrentid, String name,String type, int ammount,double cost, 
            double totalcost,String action,int confirmedby, int teachersid) throws SQLException
    {
        PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "INSERT INTO `item_store_action`(`item_group_curentID`, `Name`, `Type`, `Ammount`, `Cost`, `Total Cost`, `Action`, `ConfirmedBy`, `TeachersID`)"
                + "VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            stmt = conn.prepareStatement(insert);
            stmt.setInt(1, itemgroupcurrentid);
            stmt.setString(2, name);
            stmt.setString(3, type);
            stmt.setInt(4, ammount);
            stmt.setDouble(5, cost);
            stmt.setDouble(6, totalcost);
            stmt.setString(7, action);
            stmt.setInt(8, confirmedby);
            stmt.setInt(9, teachersid);
            
            stmt.executeUpdate();
             
            
        } catch (SQLException ex) {
            Logger.getLogger(NewDepartment.class.getName()).log(Level.SEVERE, null, ex);
            
             if(ex.getErrorCode() == 1062)
            {
                JOptionPane.showMessageDialog(null, "Item details already saved \n" +
                        "Press Ok to continue", "Error "+ex.getErrorCode(), JOptionPane.INFORMATION_MESSAGE);
            }
              else if(ex.getErrorCode() == 1452)
            {
                JOptionPane.showMessageDialog(null, "Unexpected error occured\n" +
                        "Contact the Administrator for further advice", "Error "+ex.getErrorCode(), JOptionPane.INFORMATION_MESSAGE);
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
       
    
    
    
    private static void createnewItem(int itemstorecurrentid,String itemcode, String name, String type, double cost, boolean availability) throws SQLException
    {
         PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "INSERT INTO `items`(`item_store_currentID`, `Item ID`, `Item Name`, `Type`, `Cost`, `Availability`)"
                + "VALUES(?,?,?,?,?,?)";
        try {
            stmt = conn.prepareStatement(insert);
            stmt.setInt(1, itemstorecurrentid);
            stmt.setString(2, itemcode);
            stmt.setString(3, name);
            stmt.setString(4, type);
            stmt.setDouble(5, cost);
            stmt.setBoolean(6, availability);
            
            
            stmt.executeUpdate();
             
            
        } catch (SQLException ex) {
            Logger.getLogger(NewDepartment.class.getName()).log(Level.SEVERE, null, ex);
            
             if(ex.getErrorCode() == 1062)
            {
                JOptionPane.showMessageDialog(null, "Item details already saved \n" +
                        "Press Ok to continue", "Error "+ex.getErrorCode(), JOptionPane.INFORMATION_MESSAGE);
            }
              else if(ex.getErrorCode() == 1452)
            {
                JOptionPane.showMessageDialog(null, "Unexpected error occured\n" +
                        "Contact the Administrator for further advice", "Error "+ex.getErrorCode(), JOptionPane.INFORMATION_MESSAGE);
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
            //createNewItemStoreCurrent("BKM", "Macmillan Mathematics Book 2", "Book", 4,1200.00, 4800.00);
            //createNewStoreAssistant(1);
            //createNewItemStoreAction(1, "Makmilan Mathematics","Books", 3 ,900.00, 2700.00,"Given to Librarian",1, 1);
             createnewItem(1, "BJKK", "Understanding Mathematics", "Mathematics", 1400.00, true);
          } catch (SQLException ex) {
              Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
    
    
     //method to get todays date
      private static java.sql.Date getCurrentTimeStamp() {
 
		java.util.Date today = new java.util.Date();
		return new java.sql.Date(today.getTime());
 
	}
}
