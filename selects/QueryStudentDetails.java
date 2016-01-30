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
public class QueryStudentDetails {
    
    public static ArrayList studentsids;
    public static ArrayList classids;
    
    public static void getAllStudentsIDandClassID()
    {
        DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            ArrayList sid = new ArrayList<Integer>();
            ArrayList cid = new ArrayList<Integer>();
            
            String Sql = "SELECT  `ID`,`classID` FROM `student` WHERE `Whether Student`=TRUE ORDER BY `ID` ASC;";
        try {            
            pstmt = conn.prepareStatement(Sql);
            rs = pstmt.executeQuery();
            while(rs.next())
            {
            sid.add(rs.getInt("ID"));
            cid.add(rs.getInt("classID"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryStudentDetails.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                "Some unexpected error occured\n"+
                "Was not able to load Staff Names details\n"+
                "Please contact the systems administrator for assistance","Warning",JOptionPane.ERROR_MESSAGE);
        }
           
       studentsids = sid;
       classids = cid;
       
    }
    
    public static ArrayList getClassNames()
    {
        DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            ArrayList classnames = new ArrayList<String>();
            classnames.add("Select");
            String Sql = "SELECT `Class` FROM `class` WHERE 1 ORDER BY `ID` ASC";
        try {            
            pstmt = conn.prepareStatement(Sql);
             rs = pstmt.executeQuery();
            while(rs.next())
            {
            classnames.add(rs.getString("Class"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryStudentDetails.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                "Some unexpected error occured\n"+
                "Was not able to load class name details\n"+
                "Please contact the systems administrator for assistance","Warning",JOptionPane.ERROR_MESSAGE);
        }
           
            
       return classnames;
    }
    public static ArrayList<String> resdnames;
    public static ArrayList<Integer> resids;
    public static ArrayList getResidenceNames()
    {
        DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            ArrayList residencenames = new ArrayList<String>();
            ArrayList residenceids = new ArrayList<Integer>();
            residencenames.add("Select");
            residenceids.add(0);
            String Sql = "SELECT `Name`,`ID` FROM `residence` WHERE `termID`=? ORDER BY `ID` ASC";
        try {            
            pstmt = conn.prepareStatement(Sql);
            pstmt.setInt(1, frigfactor.AccomodationFee.getCurrentTerm());
            rs = pstmt.executeQuery();
            while(rs.next())
            {
            residencenames.add(rs.getString("Name"));
            residenceids.add(rs.getInt("ID"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryStudentDetails.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                "Some unexpected error occured\n"+
                "Was not able to load class name details\n"+
                "Please contact the systems administrator for assistance","Warning",JOptionPane.ERROR_MESSAGE);
        }
        resdnames = residencenames;
        resids = residenceids;
        return residencenames;
    }
         
public static ArrayList getMealsNames(int classid)
    {
        DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            ArrayList classnames = new ArrayList<String>();
            classnames.add("Select");
            String Sql = "SELECT `For` FROM `meals` WHERE `classID`=? AND `termID`=? ORDER BY `ID` ASC";
            
        try {            
            pstmt = conn.prepareStatement(Sql);
             pstmt.setInt(1, classid);
             pstmt.setInt(2, frigfactor.AccomodationFee.getCurrentTerm());
             rs = pstmt.executeQuery();
            while(rs.next())
            {
            classnames.add(rs.getString("For"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryStudentDetails.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                "Some unexpected error occured\n"+
                "Was not able to load class name details\n"+
                "Please contact the systems administrator for assistance","Warning",JOptionPane.ERROR_MESSAGE);
        }
           
            
       return classnames;
    }

public static ArrayList<String> transnames;
public static ArrayList<Integer> transids;
public static ArrayList getTransportNames(int classid)
    {
        DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            ArrayList transportnames = new ArrayList<String>();
            ArrayList transportids = new ArrayList<Integer>();
            transportnames.add("Select");
            transportids.add(0);
            String Sql = "SELECT `Name`,`ID` FROM `transport` WHERE `classID`=? AND `termID`=? ORDER BY `ID` ASC";
        try {            
            pstmt = conn.prepareStatement(Sql);
            pstmt.setInt(1, classid);
            pstmt.setInt(2, frigfactor.AccomodationFee.getCurrentTerm());
            rs = pstmt.executeQuery();
            while(rs.next())
            {
            transportnames.add(rs.getString("Name"));
            transportids.add(rs.getInt("ID"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryStudentDetails.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                "Some unexpected error occured\n"+
                "Was not able to load class name details\n"+
                "Please contact the systems administrator for assistance","Warning",JOptionPane.ERROR_MESSAGE);
        }
           
       transnames = transportnames;
       transids = transportids;
       return transportnames;
    } 
public static int getLastContact()
{
            DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            int lastcontid=0;
           
            String Sql = "SELECT  `ID` \n" +
                         "FROM  `student_contact` \n" +
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
           
            String Sql = "SELECT  `student_contactID` \n" +
                         "FROM  `student` \n" +
                         "WHERE 1 \n" +
                         "ORDER BY  `ID` DESC";
        try {            
            pstmt = conn.prepareStatement(Sql);
             rs = pstmt.executeQuery();
             rs.next();
             lastcontid = rs.getInt("student_contactID");
        } catch (SQLException ex) {
            Logger.getLogger(QueryStudentDetails.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                "Some unexpected error occured\n"+
                "Was not able to load class name details\n"+
                "Please contact the systems administrator for assistance","Warning",JOptionPane.ERROR_MESSAGE);
        }
                     
       return lastcontid;
}
   
}
