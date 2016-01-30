/*
 * This class should only be used by a super adminin
 * contains inserts to the staff and staff related tables
 */
package sqlinserts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


/**
 *
 * @author franq
 */
public class NewStaff {
    
    private static DBConnection dbconn;
    
    
    
    /*
     * adds a staff contact details once they are entered
     * sample call
     * //staffContact("06069529","0720069529","githae.francis@gmail.com","00200-3148","City Square Nairobi");*/
    
    public static void staffContact2(String mobile, String email, Long address, String town) throws SQLException
    {
        PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "INSERT INTO `staff_contact`(`Mobile Number`, `Email`, `Physical Address`, `Town`)"
                + "VALUES(?,?,?,?)";
        
        
            stmt = conn.prepareStatement(insert);
            
         
            stmt.setString(1, mobile);
            stmt.setString(2, email);
            stmt.setLong(3, address);
            stmt.setString(4, town);
            
            
            //execute the statement
            stmt.executeUpdate();
            
            
            
       
 
			if (stmt != null) {
				stmt.close();
			}
 
			if (conn != null) {
				conn.close();
			}
 
		
    }
    
    
    
    public static int staffContact(String tel,String mobile, String email, String address, String town) throws SQLException
    {
        PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "INSERT INTO `staff_contact`(`Mobile Number`, `Email`, `Physical Address`, `Town`)"
                + "VALUES(?,?,?,?)";
        
        try {
            stmt = conn.prepareStatement(insert);
            
         
            stmt.setString(1, mobile);
            stmt.setString(2, email);
            stmt.setString(3, address);
            stmt.setString(4, town);
            
            
            //execute the statement
            stmt.executeUpdate();
            return 1;
            
            
        } catch (SQLException ex) {
            Logger.getLogger(NewStaff.class.getName()).log(Level.SEVERE, null, ex);
            
            
            if(ex.getErrorCode() == 1062)
            {
                JOptionPane.showMessageDialog(null, "A staff with these contact details already exists. \n" +
                        "Please check the details and try again", "Error "+ex.getErrorCode(), JOptionPane.INFORMATION_MESSAGE);
            }
            return 0;
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
    
       
 
    /*Method to update the stafftable... should be done after the contacts table is updated
     * `nationalID`, `Gender`, `Title`, `Surname`, `First Name`,`Last Name`, `DOB`, `ContactID`, `Job Title`, `Role
     * sample call:
     * registerStaff("29820933","Male","Mr","Mbui", "Francis","Githae","2013-12-11","Manager","Manages all the other department managers");*/
    
    public static int registerStaff(String nationalid, String gender, String prefix, String surname, String fname, String lname, String dob, String jobtitle,String role) throws SQLException
    { 
        int contactid;
        contactid = getContactID();
        PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        
        String insert = "INSERT INTO `staff`(`nationalID`, `Gender`, `Title`, `Surname`, `First Name`,"
                + "`Last Name`, `DOB`, `ContactID`, `Job Title`, `Role`) VALUES"
                +"(?,?,?,?,?,?,?,?,?,?)";
        
            stmt = conn.prepareStatement(insert);
            
           // stmt.setInt(1, null);
            stmt.setString(1, nationalid);
            stmt.setString(2, gender);
            stmt.setString(3, prefix);
            stmt.setString(4, surname);
            stmt.setString(5, fname);
            stmt.setString(6, lname);
            stmt.setString(7, dob);
            stmt.setInt(8, contactid);
            stmt.setString(9, jobtitle);
            stmt.setString(10, role);
            
            
            //execute the statement
            stmt.executeUpdate();
            
            
       
 
			if (stmt != null) {
				stmt.close();
			}
 
			if (conn != null) {
				conn.close();
			}
 
		
        return 1;
        
    }
    
    
    
    //method to set the staff salary details
     public static int setStaff_salary( double gross) throws SQLException 
     {
        PreparedStatement stmt;
        int staffid;
        staffid = getStaffID();
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        
         String insert = "INSERT INTO `staff_salary`(`staffID`,`Gross Pay`)"
                + "VALUES(?)";
        try {
            stmt = conn.prepareStatement(insert);
            stmt.setInt(1, staffid);
            stmt.setDouble(2, gross);
            
            stmt.executeUpdate();
            return 1;
        } catch (SQLException ex) {
            Logger.getLogger(NewStaff.class.getName()).log(Level.SEVERE, null, ex);
            if(ex.getErrorCode()==1062)
            {
            JOptionPane.showMessageDialog(null, "Salary Details have already been set. \n" +
                        "Click on the update to change salary details", "Error "+ex.getErrorCode(), JOptionPane.INFORMATION_MESSAGE);
            }
            return 0;
        }
        
         
     }
    
    
    
    
    //gets the last id from the contact ID table to fill the new contact details
    //returns integer (largestvalue+1)
    private static int getContactID() throws SQLException
    {
        PreparedStatement stmt = null;
        int contid = 0;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        
        
        String select = "SELECT  `ID` FROM  `staff_contact` ORDER BY ID DESC LIMIT 1";
        try {
            stmt = conn.prepareStatement(select);
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
 
				String userid = rs.getString("ID");
                                contid = Integer.parseInt(userid);
 
			}
            
            
        } catch (SQLException ex) {
            Logger.getLogger(NewStaff.class.getName()).log(Level.SEVERE, null, ex);
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
    
    
    
    //gets the last id from the contact ID table to fill the new contact details
    //returns integer (largestvalue+1)
    private static int getStaffID() throws SQLException
    {
        PreparedStatement stmt = null;
        int contid = 0;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        
        
        String select = "SELECT  `ID` FROM  `staff` ORDER BY ID DESC LIMIT 1";
        try {
            stmt = conn.prepareStatement(select);
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
 
				String userid = rs.getString("ID");
                                contid = Integer.parseInt(userid);
 
			}
            
            
        } catch (SQLException ex) {
            Logger.getLogger(NewStaff.class.getName()).log(Level.SEVERE, null, ex);
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
    
/*    
     public static void main(String args[]) {
        try {
            setStaff_salary(9000.00,499,4444.00);
            
        } catch (SQLException ex) {
            Logger.getLogger(NewStaff.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    
    
    
}

