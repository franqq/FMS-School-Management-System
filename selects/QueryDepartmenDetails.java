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

/**
 *
 * @author franq
 */
public class QueryDepartmenDetails {
    public static int getDepartmentID(int staffid) throws SQLException
    {
        DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            int classtaughtid = 0;
            
            String Sql = "SELECT `departmentID` FROM `department_head` WHERE `staffID`=?";
                    
            pstmt = conn.prepareStatement(Sql);
            pstmt.setInt(1, staffid);
             rs = pstmt.executeQuery();
            if(rs.next())
            classtaughtid = rs.getInt("departmentID");
            else
                classtaughtid = -1;
           
            
       return classtaughtid;
    }
    
    public static ArrayList<String> getDepartmentDetails(int deptid) throws SQLException
    {
        DBConnection dbconn =new DBConnection();
        Connection conn = dbconn.vconnection;
        PreparedStatement pstmt;
        ResultSet rs;
        ArrayList<String> details;
        details = new ArrayList<String>();
        
        
         String Sql = "SELECT `Name`, `Description` FROM `department` WHERE `ID`=?";
                    
         pstmt = conn.prepareStatement(Sql);
         pstmt.setInt(1, deptid);
         rs = pstmt.executeQuery();
         if(rs.next())
         {
             details.add(rs.getString("Name"));
             details.add(rs.getString("Description"));
         }
         return details;
        
    }
    
    public static int getDepartmentHeadID(int deptid) throws SQLException
    {
        DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            int classtaughtid = 0;
            
            String Sql = "SELECT `staffID` FROM `department_head` WHERE `departmentID`=?";
                    
            pstmt = conn.prepareStatement(Sql);
            pstmt.setInt(1, deptid);
             rs = pstmt.executeQuery();
            boolean next = rs.next();
            
            classtaughtid = rs.getInt("staffID");
           
            
       return classtaughtid;
    }
}
