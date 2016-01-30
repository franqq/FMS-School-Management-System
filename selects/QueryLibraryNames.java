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
public class QueryLibraryNames {
    public static ArrayList listofnames;
    public static ArrayList listofids;
    
     public static ArrayList getLibraryNames()
    {
        DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            ArrayList classnames = new ArrayList<String>();
            ArrayList staffids = new ArrayList<Integer>();
            classnames.add("Select Library");
            staffids.add(0);
            String Sql = "SELECT `ID`, `Name` FROM `library` WHERE 1";
        try {            
            pstmt = conn.prepareStatement(Sql);
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
}
