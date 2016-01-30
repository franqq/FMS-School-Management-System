/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package selects;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.Calendar;

import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author franq
 */
public class QueryExamDetails {
    
    
     public static int getLastExamID() throws SQLException
    {
        DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            int classtaughtid = 0;
            
            String Sql = "SELECT `ID` FROM `exam` WHERE 1 ORDER BY `ID` DESC";
                    
            pstmt = conn.prepareStatement(Sql);
            rs = pstmt.executeQuery();
            boolean next = rs.next();
            
            classtaughtid = rs.getInt("ID");
           
            
       return classtaughtid;
    }
    
    
    public static ArrayList getClassesTaught(int userid)
    {
        DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            ArrayList classnames = new ArrayList<String>();
            classnames.add("Select");
            String Sql = "SELECT  `class`.`Class` ,`class`.`Level`" +
                         "FROM  `class` " +
                         "INNER JOIN  `subject_teacher_class` ON 1 " +
                         "WHERE  `subject_teacher_class`.`staffID` =? " +
                         "AND  `subject_teacher_class`.`classID` =  `class`.`ID` " +
                         "ORDER BY `class`.`ID` ASC";
        try {            
            pstmt = conn.prepareStatement(Sql);
            pstmt.setInt(1, userid);
            rs = pstmt.executeQuery();
            while(rs.next())
            {
            classnames.add(rs.getString("Level") + " " + rs.getString("Class"));
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
    
     public static int getClassTaughtID(String classtaughtname) throws SQLException
    {
        DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            int classtaughtid = 0;
            
            String Sql = "SELECT `ID` FROM `class` WHERE `Class`=?";
                    
            pstmt = conn.prepareStatement(Sql);
            pstmt.setString(1, classtaughtname);
             rs = pstmt.executeQuery();
            boolean next = rs.next();
            
            classtaughtid = rs.getInt("ID");
           
            
       return classtaughtid;
    }
    
    public static ArrayList subjecttaughtnames;
    public static ArrayList subjecttaughtids;
    
    public static ArrayList getSubjectTaught(int userid,int classid)
    {
            DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            ArrayList classnames = new ArrayList<String>();
            ArrayList ids = new ArrayList<Integer>();
            
            classnames.add("SELECT");
            ids.add(0);
            String Sql = "SELECT `subject`.`Subject`,`subject`.`ID` " +
                         "FROM  `subject` \n" +
                         "INNER JOIN `subject_teacher_class` " +
                         "ON `subject_teacher_class`.`subjectID` =  `subject`.`ID` " +
                         "INNER JOIN `class`\n" +
                         "ON `subject_teacher_class`.`classID` =  `class`.`ID` " +
                         "WHERE \n" +
                         "`subject_teacher_class`.`staffID` =? " +
                         "AND\n" +
                         "`class`.`ID`=?";
        try {            
            pstmt = conn.prepareStatement(Sql);
            pstmt.setInt(1, userid);
            pstmt.setInt(2, classid);
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
                "Was not able to load class name details\n"+
                "Please contact the systems administrator for assistance","Error Message",JOptionPane.ERROR_MESSAGE);
        }
           
        subjecttaughtnames = classnames;
        subjecttaughtids  = ids;
       return classnames;
    }
    
    
    public static int getSubjectTaughtID(String classtaughtname) throws SQLException
    {
        DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            int classtaughtid = 0;
            
            String Sql = "SELECT `ID` FROM `subject` WHERE `Subject`=?";
                    
            pstmt = conn.prepareStatement(Sql);
            pstmt.setString(1, classtaughtname);
             rs = pstmt.executeQuery();
            boolean next = rs.next();
            
            classtaughtid = rs.getInt("ID");
           
            
       return classtaughtid;
    }
    
    
    public static ArrayList<String> names;
    public static ArrayList<Integer> ids;
    public static ArrayList getExamDetails()
    {
        DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            ArrayList classnames = new ArrayList<String>();
            ArrayList classids = new ArrayList<Integer>();
            
            classnames.add("SELECT EXAM");
            classids.add(0);
            
            String Sql = "SELECT `ID`, `Exam Name` ,  `Exam Type` " +
                            "FROM  `exam` " +
                            "WHERE `Year` =  ?" +
                            "ORDER BY  `ID` ASC ";
        try {            
            pstmt = conn.prepareStatement(Sql);
            pstmt.setString(1, selects.QueryMainDetails.getCurrentYear());
            
            rs = pstmt.executeQuery();
            while(rs.next())
            {
            classnames.add(rs.getString("Exam Name")+ "," + rs.getString("Exam Type"));
            classids.add(rs.getInt("ID"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryStudentDetails.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                "Some unexpected error occured\n"+
                "Was not able to load class name details\n"+
                "Please contact the systems administrator for assistance","Error Message",JOptionPane.ERROR_MESSAGE);
        }
           
        ids = classids;
        names = classnames;
       return classnames;
    }
     
     
     public static int getExamID(String examname) throws SQLException
    {
        DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            int examID = 0;
           // ArrayList<String> arl = new ArrayList<String>();
            //arl.addAll(Arrays.asList(examname.split("|")));
            ArrayList<String> arl;
            arl = new ArrayList<String>();
            for(String retval: examname.split(","))
            {
               arl.add(retval);
            }
            
            String examnm,examtp;
            examnm = arl.get(0);
            examtp = arl.get(1);
            
            String Sql = "SELECT `ID` FROM `exam` WHERE `Exam Name`=? AND `Exam Type`=? AND`Year` =  ? ";
                    
            pstmt = conn.prepareStatement(Sql);
            pstmt.setString(1, examnm);
            pstmt.setString(2, examtp);
            pstmt.setString(3, getCurrentYear());
            
             rs = pstmt.executeQuery();
            boolean next = rs.next();
            
            examID = rs.getInt("ID");
           
            
       return examID;
    }
     
     
     
     public static ArrayList<String> getExamdetails(int examid) throws SQLException
    {
        DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ArrayList<String> details = new ArrayList<String>();
            ResultSet rs;
            
           
           
            String Sql = "SELECT  `Exam Name`, `Exam Type`, `Start Date`, `termID`, `Year` FROM `exam` WHERE `ID`=?";
                    
            pstmt = conn.prepareStatement(Sql);
            pstmt.setInt(1, examid);
           
            
             rs = pstmt.executeQuery();
             while(rs.next()){
                 details.add(rs.getString("Exam Name"));
                 details.add(rs.getString("Exam Type"));
                 details.add(rs.getString("Start Date"));
                 details.add(rs.getString("termID"));
                  details.add(rs.getString("Year"));
                 
             }
            
           return details;
           
            
       
    }
     
     
      //method to get todays date
      private static String getCurrentYear() {
 
		GregorianCalendar calendar = new GregorianCalendar();
                return Integer.toString(calendar.get(Calendar.YEAR));

	}

   
}
