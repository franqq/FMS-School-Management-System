/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlinserts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author franq
 */
public class NewReceiptNo {
    private static DBConnection dbconn;
    
    public NewReceiptNo(boolean activate) throws SQLException
    {
         PreparedStatement stmt = null;
        dbconn = new DBConnection();
        Connection conn = dbconn.vconnection;
        String insert = "INSERT INTO `receipt no`(`Activated`)"
                + "VALUES(?)";
        try {
            stmt = conn.prepareStatement(insert);
            stmt.setBoolean(1, activate);
            
             stmt.executeUpdate();
             
            
        } catch (SQLException ex) {
            Logger.getLogger(NewDepartment.class.getName()).log(Level.SEVERE, null, ex);
            
             if(ex.getErrorCode() == 1062)
            {
                JOptionPane.showMessageDialog(null, "Receipt No errors \n" +
                        "Contact the Administrator for further advice", "Error "+ex.getErrorCode(), JOptionPane.INFORMATION_MESSAGE);
            }
              else if(ex.getErrorCode() == 1452)
            {
                JOptionPane.showMessageDialog(null, "Fee Ammount Error 1452\n" +
                        "Contact the Administrator for further advice", "Error "+ex.getErrorCode(), JOptionPane.INFORMATION_MESSAGE);
            }
             
        }
         finally {
 
			if (stmt != null) {
				stmt.close();
			}
 
			if (conn != null) {
				conn.close();
			}
 
		}
    }
    
}
