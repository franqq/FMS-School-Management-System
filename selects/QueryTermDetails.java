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
import static selects.QuerySubject.listofids;
import static selects.QuerySubject.listofnames;
import static selects.QuerySubject.listofsubjectids;
import static selects.QuerySubject.listofsubjectnames;

/**
 *
 * @author franq
 */
public class QueryTermDetails {
    
public static ArrayList<String> termdet;    
    
public static ArrayList getTermDetails(String name, String year)
{
            DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            ArrayList<String> details = new ArrayList<String>();
            
           
            String Sql = "SELECT `Name`, `Start Date`, `Close Date`, `Year`, `Remarks` FROM `term` WHERE `Name`=? AND `Year`=?";
        try {            
            pstmt = conn.prepareStatement(Sql);
            pstmt.setString(1, name);
            pstmt.setString(2, year);
             rs = pstmt.executeQuery();
             if(rs.next())
             {
             details.add(rs.getString("Name"));
             details.add(rs.getString("Start Date"));
             details.add(rs.getString("Close Date"));
             details.add(rs.getString("Remarks"));
             }
             else{
                 JOptionPane.showMessageDialog(null, "Term Details Not found. \n" +
                        "", "Warning", JOptionPane.WARNING_MESSAGE);
             }
             
        } catch (SQLException ex) {
            Logger.getLogger(QueryStudentDetails.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                "Some unexpected error occured\n"+
                "Was not able to load details\n"+
                "Please contact the systems administrator for assistance","Warning",JOptionPane.ERROR_MESSAGE);
        }
       termdet = details;            
       return details;
    
}
    
public static int getTermID(String name, String year)
{
            DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            int termid=0;
           
            String Sql = "SELECT `ID` FROM `term` WHERE `Name`=? AND `Year`=?";
        try {            
            pstmt = conn.prepareStatement(Sql);
            pstmt.setString(1, name);
            pstmt.setString(2, year);
             rs = pstmt.executeQuery();
             if(rs.next())
             termid = rs.getInt("ID");
             
        } catch (SQLException ex) {
            Logger.getLogger(QueryStudentDetails.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                "Some unexpected error occured\n"+
                "Was not able to load class name details\n"+
                "Please contact the systems administrator for assistance","Warning",JOptionPane.ERROR_MESSAGE);
        }
                     
       return termid;
    
}
public static String getTermName(int id)
{
            DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            String termname = null;
           
            String Sql = "SELECT `Name` FROM `term` WHERE `ID`=?";
        try {            
            pstmt = conn.prepareStatement(Sql);
            pstmt.setInt(1, id);
            
             rs = pstmt.executeQuery();
             if(rs.next())
             termname = rs.getString("Name");
        } catch (SQLException ex) {
            Logger.getLogger(QueryStudentDetails.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                "Some unexpected error occured\n"+
                "Was not able to load class name details\n"+
                "Please contact the systems administrator for assistance","Warning",JOptionPane.ERROR_MESSAGE);
        }
                     
       return termname;
    
}

public static ArrayList getYears()
    {
        DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            ArrayList classnames = new ArrayList<String>();
                       
            classnames.add("Select");
            
            String Sql = "SELECT DISTINCT `Year` FROM `term` WHERE 1 ORDER BY `ID` DESC";
        try {            
            pstmt = conn.prepareStatement(Sql);
            rs = pstmt.executeQuery();
            while(rs.next())
            {
            
            classnames.add(rs.getString("Year"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryStudentDetails.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                "Some unexpected error occured\n"+
                "Was not able to load Staff Names details\n"+
                "Please contact the systems administrator for assistance","Warning",JOptionPane.ERROR_MESSAGE);
        }
           
        return classnames;
    }
    


}
