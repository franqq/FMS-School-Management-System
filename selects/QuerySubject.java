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
public class QuerySubject {
     public static ArrayList listofnames;
     public static ArrayList listofids;
    
     public static ArrayList getSubject(int deptid)
    {
        DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            ArrayList classnames = new ArrayList<String>();
            ArrayList ids = new ArrayList<Integer>();
           
            classnames.add("Select Subject");
            ids.add(0);
            String Sql = "SELECT `ID`, `Subject` FROM `subject` WHERE `departmentID`=? ORDER BY `Subject` ASC;";
        try {            
            pstmt = conn.prepareStatement(Sql);
            pstmt.setInt(1, deptid);
            rs = pstmt.executeQuery();
            while(rs.next())
            {
            
            classnames.add(rs.getString("Subject"));
            ids.add(rs.getInt("ID"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryStudentDetails.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                "Some unexpected error occured\n"+
                "Was not able to load Staff Names details\n"+
                "Please contact the systems administrator for assistance","Warning",JOptionPane.ERROR_MESSAGE);
        }
           
       listofnames = classnames;
       listofids = ids;
       return classnames;
    }
     
     public static ArrayList listofsubjectnames;
     public static ArrayList listofsubjectids;
    
     public static ArrayList getSubjectNames()
    {
        DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            ArrayList classnames = new ArrayList<String>();
            ArrayList ids = new ArrayList<Integer>();
           
            classnames.add("Select Subject");
            ids.add(0);
            String Sql = "SELECT `ID`,`Subject` FROM `subject` WHERE 1 ORDER BY `ID` ASC;";
        try {            
            pstmt = conn.prepareStatement(Sql);
            rs = pstmt.executeQuery();
            while(rs.next())
            {
            
            classnames.add(rs.getString("Subject"));
            ids.add(rs.getInt("ID"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryStudentDetails.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                "Some unexpected error occured\n"+
                "Was not able to load Staff Names details\n"+
                "Please contact the systems administrator for assistance","Warning",JOptionPane.ERROR_MESSAGE);
        }
           
       listofsubjectnames = classnames;
       listofsubjectids = ids;
       return classnames;
    }
}
