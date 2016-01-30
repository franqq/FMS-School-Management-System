/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frigfactor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import selects.QueryStudentDetails;

/**
 *
 * @author franq
 */
public class LoadFeeItems {
    public static ArrayList<String> bfinames;
    public static ArrayList<String> bfinamestables;
    public static ArrayList<Integer> bfids;
    public static ArrayList getBoardersFeeItems()
    {
            DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            ArrayList bnames = new ArrayList<String>();
            ArrayList bnamestables = new ArrayList<String>();
            ArrayList bids = new ArrayList<Integer>();
            bnames.add("Select");
            bids.add(0);
            String Sql = "SELECT `Name`,`ID` FROM `fee_items_borders` WHERE 1 ORDER BY `ID` ASC";
        try {            
            pstmt = conn.prepareStatement(Sql);
            rs = pstmt.executeQuery();
            while(rs.next())
            {
            bnames.add(rs.getString("Name"));
            bnamestables.add(rs.getString("Name").toLowerCase());
            bids.add(rs.getInt("ID"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryStudentDetails.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                "Some unexpected error occured\n"+
                "Was not able to load Fee Category Details\n"+
                "Please contact the systems administrator for assistance","Warning",JOptionPane.ERROR_MESSAGE);
        }
       bfinames = bnames;
       bfinamestables = bnamestables;
       bfids = bids;
       return bnamestables;
    } 

      
    
    public static ArrayList<String> dsnames;
    public static ArrayList<Integer> dsids;
    public static ArrayList<String> dsnamestables;
    public static ArrayList getDaySchoolFeeItems()
    {
            DBConnection dbconn =new DBConnection();
            Connection conn = dbconn.vconnection;
            PreparedStatement pstmt;
            ResultSet rs;
            ArrayList bnames = new ArrayList<String>();
            ArrayList bnamestables = new ArrayList<String>();
            ArrayList bids = new ArrayList<Integer>();
            bnames.add("Select");
            bids.add(0);
            String Sql = "SELECT `Name`,`ID` FROM `fee_items_non_borders` WHERE 1 ORDER BY `ID` ASC";
        try {            
            pstmt = conn.prepareStatement(Sql);
            rs = pstmt.executeQuery();
            while(rs.next())
            {
            bnames.add(rs.getString("Name"));
            bnamestables.add(rs.getString("Name").toLowerCase());
            bids.add(rs.getInt("ID"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueryStudentDetails.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                "Some unexpected error occured\n"+
                "Was not able to load Fee Category details\n"+
                "Please contact the systems administrator for assistance","Warning",JOptionPane.ERROR_MESSAGE);
        }
       dsnames = bnames;
       dsids = bids;
       return bnamestables;
    } 
}