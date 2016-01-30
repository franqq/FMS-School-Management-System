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
public class NewLibrary {
    private static DBConnection dbconn;
    
    
     public static void createNewLibrary(String name, String description) throws SQLException
     {
        PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "INSERT INTO `library`(`Name`, `Description`)"
                + "VALUES(?,?)";
      
            stmt = conn.prepareStatement(insert);
            stmt.setString(1, name);
            stmt.setString(2, description);
            
            
            stmt.executeUpdate();
             
            
        
 
			if (stmt != null) {
				stmt.close();
			}
 
			if (conn != null) {
				conn.close();
			}
 
		
      }
    
    
    
    
     public static void createNewLibraryAssistant(int libid,int staffid,String post) throws SQLException
    {
         PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "INSERT INTO `library_assistant`(`libraryID`, `staffID`, `Post`, `Start Date`)"
                + "VALUES(?,?,?,?)";
       
            stmt = conn.prepareStatement(insert);
            stmt.setInt(1, libid);
            stmt.setInt(2, staffid);
            stmt.setString(3, post);
            stmt.setString(4, selects.QueryMainDetails.getCurrentDate());
           
            
            
            stmt.executeUpdate();
                  
            //close previous connectiions
            if (stmt != null) {
		stmt.close();
            }
 
            if (conn != null) {
		conn.close();
            }
 
		
    }
    
    
    
   
    protected static void createnewLibraryStock(String generalid, String name, String type,String category, int ammount, double cost, double totalcost ) throws SQLException
    {
        PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "INSERT INTO `library_stock`(`General ID`, `Name`, `Type`, `Category`, `Ammount`, `Cost`, `Total Cost`)"
                + "VALUES(?,?,?,?,?,?,?)";
        try {
            stmt = conn.prepareStatement(insert);
            stmt.setString(1, generalid);
            stmt.setString(2, name);
            stmt.setString(3, type);
            stmt.setString(4, category);
            stmt.setInt(5,ammount);
            stmt.setDouble(6, cost);
            stmt.setDouble(7, totalcost);
            
            
            stmt.executeUpdate();
             
            
        } catch (SQLException ex) {
            Logger.getLogger(NewDepartment.class.getName()).log(Level.SEVERE, null, ex);
            
             if(ex.getErrorCode() == 1062)
            {
                JOptionPane.showMessageDialog(null, "This Item seems to be already in the current stock. \n" +
                        "Please check and update the number of items instead.", "Error "+ex.getErrorCode(), JOptionPane.INFORMATION_MESSAGE);
            }
              else if(ex.getErrorCode() == 1452)
            {
                JOptionPane.showMessageDialog(null, "Unexpected Error 1452\n\n" +
                        "Please contact the Administrator for further advice", "Error "+ex.getErrorCode(), JOptionPane.INFORMATION_MESSAGE);
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
    
    
     protected static void createnewLibraryItem(int librarystockid,int itemid, String name, String type,  double cost, boolean availability ) throws SQLException
    {
        PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "INSERT INTO `library_item`(`library_stockID`, `Item ID`, `Name`, `Type`, `cost`, `Availability`)"
                + "VALUES(?,?,?,?,?,?)";
        try {
            stmt = conn.prepareStatement(insert);
            stmt.setInt(1, librarystockid);
            stmt.setInt(2,itemid);
            stmt.setString(3, name);
            stmt.setString(4, type);
            stmt.setDouble(5, cost);
            stmt.setBoolean(6, availability);
            
            
            stmt.executeUpdate();
             
            
        } catch (SQLException ex) {
            Logger.getLogger(NewDepartment.class.getName()).log(Level.SEVERE, null, ex);
            
             if(ex.getErrorCode() == 1062)
            {
                JOptionPane.showMessageDialog(null, "This Item seems to have been registered already. \n" +
                        "Press Ok to continue.", "Error "+ex.getErrorCode(), JOptionPane.INFORMATION_MESSAGE);
            }
              else if(ex.getErrorCode() == 1452)
            {
                JOptionPane.showMessageDialog(null, "Unexpected Error 1452\n\n" +
                        "Please contact the Administrator for further advice", "Error "+ex.getErrorCode(), JOptionPane.INFORMATION_MESSAGE);
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
     
     
      
    protected static void createnewStaffBorrower(int itemid, int staffid, String returndate,String condition ) throws SQLException
    {
        PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "INSERT INTO   `library_staff_borrower` (`itemID`, `staffID`, `Borrow Date`, `Return Date`, `Condition`)"
                + "VALUES(?,?,?,?,?)";
        try {
            stmt = conn.prepareStatement(insert);
            
            stmt.setInt(1,itemid);
            stmt.setInt(2,staffid);
            stmt.setDate(3,getCurrentTimeStamp());
            stmt.setString(4, returndate);
            stmt.setString(5, condition);
            
             
            stmt.executeUpdate();
             
            
        } catch (SQLException ex) {
            Logger.getLogger(NewDepartment.class.getName()).log(Level.SEVERE, null, ex);
            
             if(ex.getErrorCode() == 1062)
            {
                JOptionPane.showMessageDialog(null, "This Item seems to be given out already. \n" +
                        "Press Ok to continue.", "Error "+ex.getErrorCode(), JOptionPane.INFORMATION_MESSAGE);
            } 
              else if(ex.getErrorCode() == 1452)
            {
                JOptionPane.showMessageDialog(null, "Unexpected Error 1452\nThis Item may not be in the library\n" +
                        "Please contact the Administrator for further advice", "Error "+ex.getErrorCode(), JOptionPane.INFORMATION_MESSAGE);
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
     
     
   
    
       
    protected static void createnewSupportStaffBorrower(int itemid, int supportstaffid, String returndate,String condition ) throws SQLException
    {
        PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "INSERT INTO   `library_support_staff_borrower` (`itemID`, `support_staffID`, `Borrow Date`, `Return Date`, `Condition`)"
                + "VALUES(?,?,?,?,?)";
        try {
            stmt = conn.prepareStatement(insert);
            
            stmt.setInt(1,itemid);
            stmt.setInt(2,supportstaffid);
            stmt.setDate(3,getCurrentTimeStamp());
            stmt.setString(4, returndate);
            stmt.setString(5, condition);
            
             
            stmt.executeUpdate();
             
            
        } catch (SQLException ex) {
            Logger.getLogger(NewDepartment.class.getName()).log(Level.SEVERE, null, ex);
            
             if(ex.getErrorCode() == 1062)
            {
                JOptionPane.showMessageDialog(null, "This Item seems to be given out already. \n" +
                        "Press Ok to continue.", "Error "+ex.getErrorCode(), JOptionPane.INFORMATION_MESSAGE);
            } 
              else if(ex.getErrorCode() == 1452)
            {
                JOptionPane.showMessageDialog(null, "Unexpected Error 1452\nThis Item may not be in the library\n" +
                        "Please contact the Administrator for further advice", "Error "+ex.getErrorCode(), JOptionPane.INFORMATION_MESSAGE);
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
     
    
     protected static void createnewStudentBorrower(int itemid, int studentid, String returndate,String condition ) throws SQLException
    {
        PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "INSERT INTO   `library_student_borrower` (`itemID`, `studentID`, `Borrow Date`, `Return Date`, `Condition`)"
                + "VALUES(?,?,?,?,?)";
        try {
            stmt = conn.prepareStatement(insert);
            
            stmt.setInt(1,itemid);
            stmt.setInt(2,studentid);
            stmt.setDate(3,getCurrentTimeStamp());
            stmt.setString(4, returndate);
            stmt.setString(5, condition);
            
             
            stmt.executeUpdate();
             
            
        } catch (SQLException ex) {
            Logger.getLogger(NewDepartment.class.getName()).log(Level.SEVERE, null, ex);
            
             if(ex.getErrorCode() == 1062)
            {
                JOptionPane.showMessageDialog(null, "This Item seems to be given out already. \n" +
                        "Press Ok to continue.", "Error "+ex.getErrorCode(), JOptionPane.INFORMATION_MESSAGE);
            } 
              else if(ex.getErrorCode() == 1452)
            {
                JOptionPane.showMessageDialog(null, "Unexpected Error 1452\nThis Item may not be in the library\n" +
                        "Please contact the Administrator for further advice", "Error "+ex.getErrorCode(), JOptionPane.INFORMATION_MESSAGE);
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
            //createNewLibrary("Chinua Achebe Memorial Library", "Has all books covering History Literature and General Sciences");
              //createnewLibraryStock("BKM", "Makmillan Mathematics", "Book","mathematics", 2, 1000, 2000);
            //createnewLibraryItem(1, 1, "Principles of Design", "Book", 4500.00, true);
             // createnewStaffBorrower(1, 1,"2014-01-01", "poor" );
             // createnewSupportStaffBorrower(1, 1, "2014-01-01", "poor");
              createnewStudentBorrower(1, 1, "2014-01-01", "poor");
              
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
