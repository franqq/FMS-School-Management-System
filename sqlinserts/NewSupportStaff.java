/*
 * This class should only be used by a super admin
 * contains inserts to the supportstaff and supportstaff related tables
 */
package sqlinserts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author franq
 */
public class NewSupportStaff {
    
     private static DBConnection dbconn;
     
    
    
     
      /*
     * adds a staff contact details once they are entered
     * sample call
     * ***support_staffContact("06069529","0720069529","githae.francis@gmail.com","00200-3148","City Square Nairobi");****/
    
    public static void support_staffContact(String mobile, String email, Long address, String town) throws SQLException
    {
        PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "INSERT INTO `support_staff_contact`(`Mobile Number`, `Email`, `Physical Address`, `Town`)"
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
    
    /*Method to update the stafftable... should be done after the contacts table is updated
     * `nationalID`, `Gender`, `Title`, `Surname`, `First Name`,`Last Name`, `DOB`, `ContactID`, `Job Title`, `Role
     * sample call:
     * registerSupport_staff("29820933","Male","Mr","Mbui", "Francis","Githae","2013-12-11","Manager","Manages all the other department managers");*/
    
    public static void registerSupport_staff(String nationalid, String gender, String prefix, String surname, String fname, String lname, String dob, String jobtitle,String role) throws SQLException
    { 
        int contactid;
        contactid = getContactID();
        PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        
        String insert = "INSERT INTO `support_staff`(`nationalID`, `Gender`, `Title`, `Surname`, `First Name`,"
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
 
	
        
        
    }
    
    
    //method to set the staff salary details
     protected static void setSupport_staff_salary(double total,double tax, double gross) throws SQLException 
     {
        PreparedStatement stmt;
        int staffid;
        staffid = getSupport_staffID();
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        
         String insert = "INSERT INTO `support_staff_salary`(`Support_staffID`, `Total Salary`, `Tax`, `Gross Pay`)"
                + "VALUES(?,?,?,?)";
        try {
            stmt = conn.prepareStatement(insert);
            stmt.setInt(1, staffid);
            stmt.setDouble(2, total);
            stmt.setDouble(3, tax);
            stmt.setDouble(4, gross);
            
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(NewStaff.class.getName()).log(Level.SEVERE, null, ex);
            
            if(ex.getErrorCode()==1062)
            {
            JOptionPane.showMessageDialog(null, "Salary Details have already been set. \n" +
                        "Click on the update to change salary details", "Error "+ex.getErrorCode(), JOptionPane.INFORMATION_MESSAGE);
            }
        }
        
         
     }
    
    //method to add the staff passport photo
     //called from the insertStaffPic Class
     protected static void addSupport_staff_passport(File file) throws SQLException, FileNotFoundException
    {
        
        PreparedStatement stmt;
        int staffid;
        staffid = getSupport_staffID();
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        
         String insert = "INSERT INTO `support_staff_passport`(`support_staffID`, `passport`)"
                + "VALUES(?,?)";
        try {
            FileInputStream in = new FileInputStream(file);
            stmt = conn.prepareStatement(insert);
            stmt.setInt(1,staffid);
            stmt.setBinaryStream(2, (InputStream)in, (int)file.length());
            
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(NewStaff.class.getName()).log(Level.SEVERE, null, ex);
            
            
            if(ex.getErrorCode()==1062)
            {
            JOptionPane.showMessageDialog(null, "Staff passport photo already exists. \n" +
                        "Press Update to upload new image", "Error "+ex.getErrorCode(), JOptionPane.INFORMATION_MESSAGE);
            }
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
        
        
        String select = "SELECT  `ID` FROM  `support_staff_contact` ORDER BY ID DESC LIMIT 1";
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
    private static int getSupport_staffID() throws SQLException
    {
        PreparedStatement stmt = null;
        int contid = 0;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        
        
        String select = "SELECT  `ID` FROM  `support_staff` ORDER BY ID DESC LIMIT 1";
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
    
    public static void main(String args[]) {
       // try {
            ////InsertSupport_staffPic ins = new InsertSupport_staffPic();
            
        // catch (SQLException ex) {
          //  Logger.getLogger(NewStaff.class.getName()).log(Level.SEVERE, null, ex);
           // JOptionPane.showMessageDialog(null, "Unexpected Error Occured \n" +
           //             "Please Contact the System Administrator", "Error "+ex.getErrorCode(), JOptionPane.INFORMATION_MESSAGE);
        //} 
        //catch (FileNotFoundException ex) {
         //    Logger.getLogger(NewSupportStaff.class.getName()).log(Level.SEVERE, null, ex);
         //}
    }
    
    
}


