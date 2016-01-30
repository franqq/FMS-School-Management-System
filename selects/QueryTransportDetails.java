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
public class QueryTransportDetails {
    
     public static ArrayList listofnames;
     public static ArrayList listofids;
    
     public static ArrayList getTranportDetails(int classid)
    {
            DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            ArrayList classnames = new ArrayList<String>();
            ArrayList staffids = new ArrayList<Integer>();
            classnames.add("Select Pick Point");
            staffids.add(0);
            String Sql = "SELECT `ID`, `Name` FROM `transport` WHERE `classID`=?";
        try {            
            pstmt = conn.prepareStatement(Sql);
            pstmt.setInt(1, classid);
                    
            rs = pstmt.executeQuery();
            while(rs.next())
            {
            staffids.add(rs.getInt("ID"));
            classnames.add(rs.getString("Name"));
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
     
     public static ArrayList getTranportInfo(int transportid)
    {
            DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            ArrayList picknames = new ArrayList<String>();
           
            
            String Sql = "SELECT  `termID`, `classID`, `Name`, `Fee` FROM `transport` WHERE `ID`=?";
            try {            
            pstmt = conn.prepareStatement(Sql);
            pstmt.setInt(1, transportid);
                    
            rs = pstmt.executeQuery();
            while(rs.next())
            {
            
            picknames.add(rs.getString("termID"));
            picknames.add(rs.getString("classID"));
            picknames.add(rs.getString("Name"));
            picknames.add(rs.getString("Fee"));
           
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryStudentDetails.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                "Some unexpected error occured\n"+
                "Was not able to load Staff Names details\n"+
                "Please contact the systems administrator for assistance","Warning",JOptionPane.ERROR_MESSAGE);
        }
           
       
       return picknames;
    }
    
}
