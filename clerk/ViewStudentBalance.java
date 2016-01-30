/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clerk;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import selects.QueryClassDetails;
import selects.QueryStudentDetails;


/**
 *
 * @author franq
 */
public class ViewStudentBalance extends javax.swing.JFrame {

    /**
     * Creates new form EditStudent
     */
    private static int selectedresidence;
    private static int searchid;
    private static String searchsurname,searchfname,searchlname;
    private String surname,fname,lname,gender,transport,dob,current_class,residence,meals,registeredBy,whetherStudent;
    private int contactID,studentNumber;
    private ArrayList<String> studentInfo;
    private ArrayList<String> classnamesinfo,residencenames,mealsinfo,transportinfo;
    private static int currentuserid;
    private java.util.Date today;
    private static int counter;
    private java.util.Date parsed = null;
    
    
    public ViewStudentBalance(int userid,int id,String surname,String fname,String lname) {
        /**/
        
          initComponents();
          today = new java.util.Date();
          currentuserid = userid;
          searchsurname = surname;
          searchfname = fname;
          searchlname = lname;
          searchid = id;
          counter = 0;
         
          
          
          classnamesinfo = QueryClassDetails.getClassNames();
          cmbclass.setModel(new DefaultComboBoxModel(classnamesinfo.toArray(new String[classnamesinfo.size()])));
          
          residencenames = QueryStudentDetails.getResidenceNames();
          cmbresidence.setModel(new DefaultComboBoxModel(residencenames.toArray(new String[residencenames.size()])));
          
          
          
          
           if(searchid != 0)
          {
                 studentInfo = selects.QuerySearchStudent.getStudentInfo(searchid);
          }
          else if(searchid == 0)
          {
                 studentInfo = selects.QuerySearchStudent.getStudentInfo(surname, fname, lname);
          }
           
        this.setTitle("Edit Student Details");
     
        surname = studentInfo.get(0);
        fname = studentInfo.get(1);
        lname = studentInfo.get(2);
        gender = studentInfo.get(3);
        dob=studentInfo.get(4);
         //date         
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
            parsed = format.parse(dob);
        } catch (ParseException ex) {
            Logger.getLogger(ViewStudentBalance.class.getName()).log(Level.SEVERE, null, ex);
        }
        contactID = Integer.parseInt(studentInfo.get(5));
        current_class = studentInfo.get(6);
        residence = studentInfo.get(7);
        meals =studentInfo.get(8);
        transport = studentInfo.get(9);
        registeredBy = studentInfo.get(10);
        whetherStudent = studentInfo.get(11);
        studentNumber = Integer.parseInt(studentInfo.get(12));
        
        
        //set the initial text on loading
        
        tfsurname.setText(surname);
        tffirstname.setText(fname);
        tflastname.setText(lname);
        cmbstaffgender1.setSelectedItem(gender);
        
        //jXDOB.setDate(utildate.);
        
        cmbresidence.setSelectedItem(residence);
        cmbmeals.setSelectedItem(meals);
        cmbclass.setSelectedItem(current_class);
        jXDOB.setDate(parsed);
        
       //get the classid
       selects.QueryClassDetails.getClassNames();
       int classidid = selects.QueryClassDetails.listofnames.indexOf(current_class);
       int classid = Integer.parseInt(selects.QueryClassDetails.listofids.get(classidid).toString());
        
        transportinfo = QueryStudentDetails.getTransportNames(classid);
        cmbtransport.setModel(new DefaultComboBoxModel(transportinfo.toArray(new String[transportinfo.size()])));
        
        
        mealsinfo = QueryStudentDetails.getMealsNames(classid);
        cmbmeals.setModel(new DefaultComboBoxModel(mealsinfo.toArray(new String[mealsinfo.size()])));
        
        cmbmeals.setSelectedItem(meals);
        cmbtransport.setSelectedItem(transport);
        
        selectedresidence = cmbresidence.getSelectedIndex();
        if(selectedresidence!=1)
        {
            //border
            ArrayList balancedel = selects.QueryBalance.getBordeBalanceTotal(NewStudent.getLastStudentID());
            lbltotal.setText(balancedel.get(0).toString());
            lblbal.setText(balancedel.get(1).toString());
        }
        else
        {
            //dayscholar
            ArrayList balancedel = selects.QueryBalance.getNonBordeBalanceTotal(NewStudent.getLastStudentID());
            lbltotal.setText(balancedel.get(0).toString());
            lblbal.setText(balancedel.get(1).toString());
        }
        
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        tfsurname = new javax.swing.JTextField();
        tffirstname = new javax.swing.JTextField();
        tflastname = new javax.swing.JTextField();
        cmbclass = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        jXDOB = new com.toedter.calendar.JDateChooser();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        cmbstaffgender1 = new javax.swing.JComboBox();
        cmbresidence = new javax.swing.JComboBox();
        cmbmeals = new javax.swing.JComboBox();
        cmbtransport = new javax.swing.JComboBox();
        jLabel18 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jrstudent = new javax.swing.JRadioButton();
        jralumni = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbltotal = new javax.swing.JLabel();
        lblbal = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(selects.QueryMainDetails.setIcon());
        setResizable(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Student Details"));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setForeground(new java.awt.Color(0, 0, 240));
        jLabel8.setText("Surname");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, -1, -1));

        jLabel10.setForeground(new java.awt.Color(0, 0, 240));
        jLabel10.setText("First Name");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, -1, -1));

        jLabel11.setForeground(new java.awt.Color(0, 0, 240));
        jLabel11.setText("Last Name");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, -1, -1));

        jLabel12.setForeground(new java.awt.Color(0, 0, 240));
        jLabel12.setText("Gender");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, -1, -1));
        jPanel2.add(tfsurname, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 193, -1));
        jPanel2.add(tffirstname, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 193, -1));
        jPanel2.add(tflastname, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 193, -1));

        cmbclass.setEnabled(true);
        cmbclass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbclassActionPerformed(evt);
            }
        });
        jPanel2.add(cmbclass, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 90, 208, -1));

        jLabel13.setForeground(new java.awt.Color(0, 0, 240));
        jLabel13.setText("Date of Birth");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 40, -1, -1));

        jXDOB.setEnabled(true);
        jPanel2.add(jXDOB, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 40, 208, -1));

        jLabel14.setForeground(new java.awt.Color(0, 0, 240));
        jLabel14.setText("Class");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 90, -1, -1));

        jLabel15.setForeground(new java.awt.Color(0, 0, 240));
        jLabel15.setText("Residence");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 150, -1, -1));

        jLabel16.setForeground(new java.awt.Color(0, 0, 240));
        jLabel16.setText("Meals");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 210, -1, -1));

        cmbstaffgender1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select", "MALE", "FEMALE" }));
        cmbstaffgender1.setEnabled(true);
        jPanel2.add(cmbstaffgender1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 210, 193, -1));

        cmbresidence.setEnabled(true);
        jPanel2.add(cmbresidence, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 150, 208, -1));

        cmbmeals.setEnabled(true);
        jPanel2.add(cmbmeals, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 210, 208, -1));

        cmbtransport.setEnabled(true);
        jPanel2.add(cmbtransport, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 280, 193, -1));

        jLabel18.setForeground(new java.awt.Color(0, 0, 240));
        jLabel18.setText("Transport");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, -1, -1));

        jLabel17.setForeground(new java.awt.Color(0, 0, 240));
        jLabel17.setText("More Details");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 280, -1, -1));

        jrstudent.setForeground(new java.awt.Color(0, 0, 240));
        jrstudent.setSelected(true);
        jrstudent.setText("Student");
        jrstudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrstudentActionPerformed(evt);
            }
        });
        jPanel2.add(jrstudent, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 280, -1, 20));

        jralumni.setForeground(new java.awt.Color(0, 0, 240));
        jralumni.setText("Alumni");
        jralumni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jralumniActionPerformed(evt);
            }
        });
        jPanel2.add(jralumni, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 310, 120, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Total Paid");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Pending Balance");

        lbltotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbltotal.setForeground(new java.awt.Color(255, 51, 51));
        lbltotal.setText("amt");

        lblbal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblbal.setForeground(new java.awt.Color(255, 51, 51));
        lblbal.setText("amt");

        jButton1.setText("Okay");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 871, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jLabel1)
                .addGap(56, 56, 56)
                .addComponent(lbltotal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(103, 103, 103)
                .addComponent(lblbal)
                .addGap(117, 117, 117))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(388, 388, 388)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbltotal)
                    .addComponent(jLabel2)
                    .addComponent(lblbal))
                .addGap(33, 33, 33)
                .addComponent(jButton1))
        );

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 240));
        jLabel6.setText("Student Fee Details");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(328, 328, 328)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jrstudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrstudentActionPerformed
        // TODO add your handling code here:
        if(jrstudent.isSelected() == true)
        {
            jralumni.setSelected(false);
        }
        else if(jrstudent.isSelected() == false)
        {
            jralumni.setSelected(true);
        }
    }//GEN-LAST:event_jrstudentActionPerformed

    private void jralumniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jralumniActionPerformed
        // TODO add your handling code here:
        if(jrstudent.isSelected() == false)
        {
            jralumni.setSelected(true);
        }
        else if(jrstudent.isSelected() == true)
        {
            jralumni.setSelected(false);
        }
    }//GEN-LAST:event_jralumniActionPerformed

    private void cmbclassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbclassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbclassActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewStudentBalance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewStudentBalance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewStudentBalance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewStudentBalance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewStudentBalance(currentuserid,searchid,searchsurname,searchfname,searchlname).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbclass;
    private javax.swing.JComboBox cmbmeals;
    private javax.swing.JComboBox cmbresidence;
    private javax.swing.JComboBox cmbstaffgender1;
    private javax.swing.JComboBox cmbtransport;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private com.toedter.calendar.JDateChooser jXDOB;
    private javax.swing.JRadioButton jralumni;
    private javax.swing.JRadioButton jrstudent;
    private javax.swing.JLabel lblbal;
    private javax.swing.JLabel lbltotal;
    private javax.swing.JTextField tffirstname;
    private javax.swing.JTextField tflastname;
    private javax.swing.JTextField tfsurname;
    // End of variables declaration//GEN-END:variables
}
