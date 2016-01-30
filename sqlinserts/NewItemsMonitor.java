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
public class NewItemsMonitor {
    private static DBConnection dbconn;
    
    private static void createnewItemCurrentlyWith(int itemid, int studentsid) throws SQLException
    {
        PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "INSERT INTO `items_currently_with` (`itemID`, `studentsID`, `Start Date`)"
                + "VALUES(?,?,?)";
        try {
            stmt = conn.prepareStatement(insert);
            stmt.setInt(1, itemid);
            stmt.setInt(2, studentsid);
            stmt.setDate(3, getCurrentTimeStamp());
            
            
            stmt.executeUpdate();
             
            
        } catch (SQLException ex) {
            Logger.getLogger(NewDepartment.class.getName()).log(Level.SEVERE, null, ex);
            
             if(ex.getErrorCode() == 1062)
            {
                JOptionPane.showMessageDialog(null, "This Item is already declared lost \n" +
                        "Press Ok to continue", "Error "+ex.getErrorCode(), JOptionPane.INFORMATION_MESSAGE);
            }
              else if(ex.getErrorCode() == 1452)
            {
                JOptionPane.showMessageDialog(null, "Unexpected Error 1452\n\n" +
                        "This item does not exist in the system. \nContact the Administrator for further advice", "Error "+ex.getErrorCode(), JOptionPane.INFORMATION_MESSAGE);
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
    
    private static void enterLostItem(int itemstorecurrentid, int itemid, int studentid, int staffid, String name, String type, double cost) throws SQLException
    {
        PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "INSERT INTO `items_lost` (`item_store_curentID`, `itemsID`, `studentID`, `StaffID`, `Item Name`, `Type`, `Cost`)"
                + "VALUES(?,?,?,?,?,?,?)";
        try {
            stmt = conn.prepareStatement(insert);
            stmt.setInt(1, itemstorecurrentid);
            stmt.setInt(2, itemid);
            stmt.setInt(3, studentid);
            stmt.setInt(4, staffid);
            stmt.setString(5, name);
            stmt.setString(6, type);
            stmt.setDouble(7, cost);
            
            
            stmt.executeUpdate();
             
            
        } catch (SQLException ex) {
            Logger.getLogger(NewDepartment.class.getName()).log(Level.SEVERE, null, ex);
            
             if(ex.getErrorCode() == 1062)
            {
                JOptionPane.showMessageDialog(null, "This Item is currently with this student \n" +
                        "Press Ok to continue", "Error "+ex.getErrorCode(), JOptionPane.INFORMATION_MESSAGE);
            }
              else if(ex.getErrorCode() == 1452)
            {
                JOptionPane.showMessageDialog(null, "Unexpected Error 1452\n\n" +
                        "This item does not exist in the system. \nContact the Administrator for further advice", "Error "+ex.getErrorCode(), JOptionPane.INFORMATION_MESSAGE);
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
    
    
    
    private static void enterPayedItem(int itemstorecurrentid, int itemid, int studentid, int staffid, String name, String type, double cost) throws SQLException
    {
        PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "INSERT INTO `items_paid` (`item_group_currentID`, `itemsID`, `studentID`, `StaffID`, `Item Name`, `Type`, `Cost`)"
                + "VALUES(?,?,?,?,?,?,?)";
        try {
            stmt = conn.prepareStatement(insert);
            stmt.setInt(1, itemstorecurrentid);
            stmt.setInt(2, itemid);
            stmt.setInt(3, studentid);
            stmt.setInt(4, staffid);
            stmt.setString(5, name);
            stmt.setString(6, type);
            stmt.setDouble(7, cost);
            
            
            stmt.executeUpdate();
             
            
        } catch (SQLException ex) {
            Logger.getLogger(NewDepartment.class.getName()).log(Level.SEVERE, null, ex);
            
             if(ex.getErrorCode() == 1062)
            {
                JOptionPane.showMessageDialog(null, "This Item seems to be already in the current stock. \n" +
                        "Press Ok to continue", "Error "+ex.getErrorCode(), JOptionPane.INFORMATION_MESSAGE);
            }
              else if(ex.getErrorCode() == 1452)
            {
                JOptionPane.showMessageDialog(null, "Unexpected Error 1452\n\n" +
                        "This item does not exist in the system. \nContact the Administrator for further advice", "Error "+ex.getErrorCode(), JOptionPane.INFORMATION_MESSAGE);
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
              //createnewItemCurrentlyWith(1, 1);
             // enterLostItem(1, 1, 1, 1, "Makmillan Mathematics", "Book", 500.00);
              enterPayedItem(1, 1, 1, 1, "Makmillan Mathematics", "Book", 500.00);
          } catch (SQLException ex) {
              Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
}
