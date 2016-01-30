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
public class QueryClassDetails {
     public static ArrayList listofnames;
     public static ArrayList listofids;
    
     public static ArrayList getClassNames()
    {
        DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            ArrayList classnames = new ArrayList<String>();
            ArrayList staffids = new ArrayList<Integer>();
            classnames.add("Select Class Name");
            staffids.add(0);
            String Sql = "SELECT `ID`, `Class`, `Level` FROM `class` WHERE 1 ORDER BY `ID` ASC";
        try {            
            pstmt = conn.prepareStatement(Sql);
            rs = pstmt.executeQuery();
            while(rs.next())
            {
            staffids.add(rs.getInt("ID"));
            classnames.add(rs.getString("Level")+" "+rs.getString("Class"));
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
     
     
     
     public static ArrayList getAllClassID()
    {
        DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            ArrayList staffids = new ArrayList<Integer>();
            String Sql = "SELECT `ID` FROM `class` WHERE 1 ORDER BY `ID` ASC";
        try {            
            pstmt = conn.prepareStatement(Sql);
            rs = pstmt.executeQuery();
            while(rs.next())
            {
            staffids.add(rs.getInt("ID"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryStudentDetails.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                "Some unexpected error occured\n"+
                "Was not able to load Staff Names details\n"+
                "Please contact the systems administrator for assistance","Warning",JOptionPane.ERROR_MESSAGE);
        }
           
       
       return staffids;
    }
      
    
     public static int getClass_TeacherID(int classid, int teacherid)
    {
        DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            int id = 0;
           
            String Sql = "SELECT `ID` FROM `class_teacher` WHERE `classID`=? AND `teachersID`=? ORDER BY `ID` DESC ";
            
        try {            
            pstmt = conn.prepareStatement(Sql);
            pstmt.setInt(1, classid);
             pstmt.setInt(2, teacherid);
            rs = pstmt.executeQuery();
            
            if(rs.next())
              rs.getInt("ID");
           
            
        } catch (SQLException ex) {
            Logger.getLogger(QueryStudentDetails.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                "Some unexpected error occured\n"+
                "Was not able to load Staff details\n"+
                "Please contact the systems administrator for assistance","Warning",JOptionPane.ERROR_MESSAGE);
        }
           
        return id;
       
    }
     
     
    public static int getTeacherID(int classid)
    {
        DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            int id = 0;
           
            String Sql = "SELECT `teachersID` FROM `class_teacher` WHERE `classID`=?  ORDER BY `ID` DESC ";
            
        try {            
            pstmt = conn.prepareStatement(Sql);
            pstmt.setInt(1, classid);
            
            rs = pstmt.executeQuery();
            
            if(rs.next())
              id=rs.getInt("teachersID");
           
            
        } catch (SQLException ex) {
            Logger.getLogger(QueryStudentDetails.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                "Some unexpected error occured\n"+
                "Was not able to load Staff details\n"+
                "Please contact the systems administrator for assistance","Warning",JOptionPane.ERROR_MESSAGE);
        }
           
        return id;
       
    }

    
     public static int getclassIDsithTeaccherID(int staffid)
    {
        DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            int id = 0;
           
            String Sql = "SELECT `classID` FROM `class_teacher` WHERE `teachersID`=?  ORDER BY `ID` DESC ";
            
        try {            
            pstmt = conn.prepareStatement(Sql);
            pstmt.setInt(1, staffid);
            
            rs = pstmt.executeQuery();
            
            if(rs.next())
              id=rs.getInt("classID");
            else id=-1;
           
            
        } catch (SQLException ex) {
            Logger.getLogger(QueryStudentDetails.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                "Some unexpected error occured\n"+
                "Was not able to load Staff details\n"+
                "Please contact the systems administrator for assistance","Warning",JOptionPane.ERROR_MESSAGE);
        }
           
        return id;
       
    }
    
    
    
}
