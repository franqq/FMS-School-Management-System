/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package selects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author franq
 */
public class QueryStafftDetails {
    public static ArrayList listofnames;
    public static ArrayList listofids;
    
     public static ArrayList getStaffNames()
    {
        DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            ArrayList classnames = new ArrayList<String>();
            ArrayList staffids = new ArrayList<Integer>();
            classnames.add("Select Member Of Staff");
            staffids.add(0);
            String Sql = "SELECT `ID`,`Title`, `Surname`, `First Name`, `Last Name` FROM `staff` WHERE `Whether Working`=1 ORDER BY `Title` ASC";
        try {            
            pstmt = conn.prepareStatement(Sql);
            rs = pstmt.executeQuery();
            while(rs.next())
            {
            staffids.add(rs.getInt("ID"));
            classnames.add(rs.getString("Title")+" "+rs.getString("Surname")+" "+rs.getString("First Name")+" "+rs.getString("Last Name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryStudentDetails.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                "Some unexpected error occured\n"+
                "Was not able to load Staff Names details\n"+
                "Please contact the systems administrator for assistance","Warning",JOptionPane.ERROR_MESSAGE);
        }
           
       listofnames = classnames;
       listofids = staffids;
       return classnames;
    }
     
     
public static int getLastContact()
{
            DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            int lastcontid=0;
           
            String Sql = "SELECT  `ID` \n" +
                         "FROM  `staff_contact` \n" +
                         "WHERE 1 \n" +
                         "ORDER BY  `ID` DESC ";
        try {            
            pstmt = conn.prepareStatement(Sql);
             rs = pstmt.executeQuery();
             rs.next();
             lastcontid = rs.getInt("ID");
        } catch (SQLException ex) {
            Logger.getLogger(QueryStudentDetails.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                "Some unexpected error occured\n"+
                "Was not able to load class name details\n"+
                "Please contact the systems administrator for assistance","Warning",JOptionPane.ERROR_MESSAGE);
        }
                     
       return lastcontid;
    
}

public static int getCurrentContactID()
{
     DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            int lastcontid=0;
           
            String Sql = "SELECT  `ContactID` \n" +
                         "FROM  `staff` \n" +
                         "WHERE 1 \n" +
                         "ORDER BY  `ID` DESC";
            
        try {            
            pstmt = conn.prepareStatement(Sql);
             rs = pstmt.executeQuery();
             rs.next();
             lastcontid = rs.getInt("ContactID");
        } catch (SQLException ex) {
            Logger.getLogger(QueryStudentDetails.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                "Some unexpected error occured\n"+
                "Was not able to load class name details\n"+
                "Please contact the systems administrator for assistance","Warning",JOptionPane.ERROR_MESSAGE);
        }
                     
       return lastcontid;
}

public static int getuserIDwithNationalID(String nationalid)
{
     DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            int lastcontid=0;
           
            String Sql = "SELECT `ID` FROM `staff` WHERE `nationalID`=?";
            
        try {            
            pstmt = conn.prepareStatement(Sql);
            pstmt.setString(1, nationalid);
             rs = pstmt.executeQuery();
             rs.next();
             lastcontid = rs.getInt("ID");
        } catch (SQLException ex) {
            Logger.getLogger(QueryStudentDetails.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                "Some unexpected error occured\n"+
                "Was not able to load class name details\n"+
                "Please contact the systems administrator for assistance","Warning",JOptionPane.ERROR_MESSAGE);
        }
                     
       return lastcontid;
}

public static String getNationalIDwithUserID(int userid)
{
     DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            String nationalid="";
           
            String Sql = "SELECT `nationalID` FROM `staff` WHERE `ID`=?";
            
        try {            
            pstmt = conn.prepareStatement(Sql);
            pstmt.setInt(1, userid);
             rs = pstmt.executeQuery();
             rs.next();
             nationalid = rs.getString("nationalID");
        } catch (SQLException ex) {
            Logger.getLogger(QueryStudentDetails.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                "Some unexpected error occured\n"+
                "Was not able to load class name details\n"+
                "Please contact the systems administrator for assistance","Warning",JOptionPane.ERROR_MESSAGE);
        }
                     
       return nationalid;
}

     
}
