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
import static selects.QueryLibraryNames.listofids;
import static selects.QueryLibraryNames.listofnames;

/**
 *
 * @author franq
 */
public class QueryLoginLevels {
     public static ArrayList listofnames;
    public static ArrayList listofids;
    
     public static ArrayList getLoginNames()
    {
        DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            ArrayList classnames = new ArrayList<String>();
            ArrayList staffids = new ArrayList<Integer>();
            classnames.add("SELECT");
            staffids.add(0);
            String Sql = "SELECT `ID`,`Login Level` FROM `login_levels` WHERE 1 ORDER BY `ID` ASC;";
        try {            
            pstmt = conn.prepareStatement(Sql);
            rs = pstmt.executeQuery();
            while(rs.next())
            {
            staffids.add(rs.getInt("ID"));
            classnames.add(rs.getString("Login Level"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryLoginLevels.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                "Some unexpected error occured\n"+
                "Was not able to load Staff Names details\n"+
                "Please contact the systems administrator for assistance","Warning",JOptionPane.ERROR_MESSAGE);
        }
           
       listofnames = classnames;
       listofids = staffids;
       return classnames;
    }
     
     public static int getLevelID(String nationalid)
{
     DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            int levelid=0;
           
            String Sql = "SELECT `level` FROM `login` WHERE `nationalID`=?";
        try {            
            pstmt = conn.prepareStatement(Sql);
            pstmt.setString(1, nationalid);
             rs = pstmt.executeQuery();
             rs.next();
             levelid = rs.getInt("level");
        } catch (SQLException ex) {
            Logger.getLogger(QueryLoginLevels.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                "Some unexpected error occured\n"+
                "Was not able to load class name details\n"+
                "Please contact the systems administrator for assistance","Warning",JOptionPane.ERROR_MESSAGE);
        }
                     
       return levelid;
}
}
