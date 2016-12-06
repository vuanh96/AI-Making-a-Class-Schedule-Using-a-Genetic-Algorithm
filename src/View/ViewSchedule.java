/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import AI.CourseClass;
import AI.GA;
import AI.Room;
import Model.InputFromMySQL;
import java.awt.event.ItemEvent;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author KING
 */
public class ViewSchedule extends javax.swing.JFrame {

    /**
     * Creates new form ViewSchedule
     */
    private static GA ga;
    private static final int DAY_HOURS = 12;
    private static final int DAY_NUM = 5;
    private static int ROOM_NUM;

    public static int getDAY_HOURS() {
        return DAY_HOURS;
    }

    public static int getROOM_NUM() {
        return ROOM_NUM;
    }

    public static int getDAY_NUM() {
        return DAY_NUM;
    }

    public static GA getGA() {
        return ga;
    }
    
    

    public ViewSchedule() {
        initComponents();
        setLocationRelativeTo(null);
        
        cbPhongModel = new DefaultComboBoxModel();
        //---Ket noi va lay du lieu tu Database
        InputFromMySQL.getData();
        //---Khoi tao cbPhong
        ROOM_NUM = InputFromMySQL.getRoomList().size();
        initCbPhong();
        cbPhong.setEnabled(false);
    }
    private final DefaultComboBoxModel cbPhongModel;
    private void initCbPhong() {
        cbPhongModel.addElement("---Chọn phòng học---");
        for(Room i : InputFromMySQL.getRoomList()) {
            cbPhongModel.addElement(i.getName());
        }
        cbPhong.setModel(cbPhongModel);
    }        
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tab1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        lbFitness = new javax.swing.JLabel();
        tfFitness = new javax.swing.JTextField();
        tfGeneration = new javax.swing.JTextField();
        bRun = new javax.swing.JButton();
        lbCrossover = new javax.swing.JLabel();
        lbMutation = new javax.swing.JLabel();
        lbPopSize = new javax.swing.JLabel();
        bExcel = new javax.swing.JButton();
        bCheck = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        tfTime = new javax.swing.JTextField();
        cbPhong = new javax.swing.JComboBox<>();

        jRadioButton1.setText("jRadioButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Making a Class Schedule Using a Genetic Algorithm");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocation(new java.awt.Point(600, 150));

        tab1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tab1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"06h45 - 07h30", null, "", null, null, null},
                {"07h35 - 08h20", null, "", null, null, null},
                {"08h25 - 09h10", null, null, null, null, null},
                {"09h15 - 10h00", null, null, null, null, null},
                {"10h05 - 10h50", null, null, null, null, null},
                {"10h55 - 11h30", null, null, null, null, null},
                {"12h30 - 13h15", null, null, null, null, null},
                {"13h20 - 14h05", null, null, null, null, null},
                {"14h10 - 14h55", null, null, null, null, null},
                {"15h00 - 15h45  ", null, null, null, null, null},
                {"15h50 - 16h35", null, null, null, null, null},
                {"16h40 - 17h25", null, null, null, null, null}
            },
            new String [] {
                "ROOM", "MON", "TUE", "WED", "THU", "FRI"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tab1.setToolTipText("");
        tab1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tab1.setEnabled(false);
        tab1.setName(""); // NOI18N
        tab1.setRowHeight(25);
        jScrollPane4.setViewportView(tab1);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Generation :");

        lbFitness.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lbFitness.setText("Fitness :");

        tfFitness.setEditable(false);
        tfFitness.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        tfGeneration.setEditable(false);
        tfGeneration.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        bRun.setBackground(new java.awt.Color(255, 0, 0));
        bRun.setFont(new java.awt.Font("Arial", 1, 28)); // NOI18N
        bRun.setForeground(new java.awt.Color(255, 255, 255));
        bRun.setText("Run");
        bRun.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        bRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bRunActionPerformed(evt);
            }
        });

        lbCrossover.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lbCrossover.setText("Crossover           : ");

        lbMutation.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lbMutation.setText("Mutation              : ");

        lbPopSize.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lbPopSize.setText("Population Size  :");

        bExcel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        bExcel.setForeground(new java.awt.Color(0, 0, 255));
        bExcel.setText("Export to Excel");
        bExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bExcelActionPerformed(evt);
            }
        });

        bCheck.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        bCheck.setForeground(new java.awt.Color(0, 0, 255));
        bCheck.setText("Check  SoftContraints");
        bCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCheckActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("Time :");

        tfTime.setEditable(false);
        tfTime.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        cbPhong.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        cbPhong.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---Chọn phòng học---" }));
        cbPhong.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbPhongItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 902, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(tfGeneration, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(158, 158, 158))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(tfTime)
                                        .addGap(30, 30, 30)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbCrossover, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbMutation, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbPopSize, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbFitness, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(tfFitness, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10)
                        .addComponent(bRun, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(bCheck))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(bExcel)))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(59, 59, 59)
                            .addComponent(bRun, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(bExcel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(bCheck)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbFitness, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfFitness, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbCrossover))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfGeneration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbMutation))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(tfTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbPopSize))))
                .addGap(18, 18, 18)
                .addComponent(cbPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRunActionPerformed

        ga = new GA(80, 10, 1, 2000);
        long start = System.currentTimeMillis();
        int generation = ga.runGA();
        long end = System.currentTimeMillis();
        double time = (double)(end-start) / 1000;
        
        lbCrossover.setText("Crossover           : " + ga.getCrossoverProbability() + " %");
        lbMutation.setText("Mutation              : " + ga.getMutationProbability() + " %");
        lbPopSize.setText("Population Size  : " + ga.getPopSize());
               
        tfFitness.setText(Double.toString(ga.getBestOfBest().getFitness()));
        tfGeneration.setText(Integer.toString(generation));
        tfTime.setText(Double.toString(time));
        cbPhong.setEnabled(true);
    }//GEN-LAST:event_bRunActionPerformed

    private void bExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bExcelActionPerformed
        ViewExcel excel = new ViewExcel(ga.getBestOfBest());
        excel.writeFileExcel();
    }//GEN-LAST:event_bExcelActionPerformed

    private void bCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCheckActionPerformed
        ViewSoftContraints vsc = new ViewSoftContraints();
        vsc.setVisible(true);
    }//GEN-LAST:event_bCheckActionPerformed

    private void cbPhongItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbPhongItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            int stt_room = cbPhong.getSelectedIndex()-1;
            if ( stt_room >= 0) {
                loadTable(stt_room);
            }
        }
    }//GEN-LAST:event_cbPhongItemStateChanged
    private StringBuffer showLesson(int i) {
        StringBuffer s = new StringBuffer("");
        if (ga.getBestOfBest().getSlots().get(i) != null) {
            for (CourseClass cc : ga.getBestOfBest().getSlots().get(i)) {
                s.append(cc.getId());
                s.append("  ");
            }
        }
        return s;
    }

    private void loadTable(int i) { //i la STT phong hoc

        tab1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {"06h45 - 07h30", showLesson(i * DAY_HOURS + 0), showLesson(i * DAY_HOURS + 0 + DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 0 + 2 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 0 + 3 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 0 + 4 * DAY_HOURS * ROOM_NUM)},
                    {"07h35 - 08h20", showLesson(i * DAY_HOURS + 1), showLesson(i * DAY_HOURS + 1 + DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 1 + 2 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 1 + 3 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 1 + 4 * DAY_HOURS * ROOM_NUM)},
                    {"08h25 - 09h10", showLesson(i * DAY_HOURS + 2), showLesson(i * DAY_HOURS + 2 + DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 2 + 2 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 2 + 3 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 2 + 4 * DAY_HOURS * ROOM_NUM)},
                    {"09h15 - 10h00", showLesson(i * DAY_HOURS + 3), showLesson(i * DAY_HOURS + 3 + DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 3 + 2 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 3 + 3 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 3 + 4 * DAY_HOURS * ROOM_NUM)},
                    {"10h05 - 10h50", showLesson(i * DAY_HOURS + 4), showLesson(i * DAY_HOURS + 4 + DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 4 + 2 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 4 + 3 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 4 + 4 * DAY_HOURS * ROOM_NUM)},
                    {"10h55 - 11h30", showLesson(i * DAY_HOURS + 5), showLesson(i * DAY_HOURS + 5 + DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 5 + 2 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 5 + 3 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 5 + 4 * DAY_HOURS * ROOM_NUM)},
                    {"12h30 - 13h15", showLesson(i * DAY_HOURS + 6), showLesson(i * DAY_HOURS + 6 + DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 6 + 2 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 6 + 3 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 6 + 4 * DAY_HOURS * ROOM_NUM)},
                    {"13h20 - 14h05", showLesson(i * DAY_HOURS + 7), showLesson(i * DAY_HOURS + 7 + DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 7 + 2 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 7 + 3 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 7 + 4 * DAY_HOURS * ROOM_NUM)},
                    {"14h10 - 14h55", showLesson(i * DAY_HOURS + 8), showLesson(i * DAY_HOURS + 8 + DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 8 + 2 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 8 + 3 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 8 + 4 * DAY_HOURS * ROOM_NUM)},
                    {"15h00 - 15h45", showLesson(i * DAY_HOURS + 9), showLesson(i * DAY_HOURS + 9 + DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 9 + 2 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 9 + 3 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS + 9 + 4 * DAY_HOURS * ROOM_NUM)},
                    {"15h50 - 16h35", showLesson(i * DAY_HOURS +10), showLesson(i * DAY_HOURS +10 + DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS +10 + 2 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS +10 + 3 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS +10 + 4 * DAY_HOURS * ROOM_NUM)},
                    {"16h40 - 17h25", showLesson(i * DAY_HOURS +11), showLesson(i * DAY_HOURS +11 + DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS +11 + 2 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS +11 + 3 * DAY_HOURS * ROOM_NUM), showLesson(i * DAY_HOURS +11 + 4 * DAY_HOURS * ROOM_NUM)}
                },
                new String[]{
                    "ROOM : " + InputFromMySQL.getRoomList().get(i).getName(), "MON", "TUE", "WED", "THU", "FRI"
                }
        ));

    }

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewSchedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new ViewSchedule().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCheck;
    private javax.swing.JButton bExcel;
    private javax.swing.JButton bRun;
    private javax.swing.JComboBox<String> cbPhong;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lbCrossover;
    private javax.swing.JLabel lbFitness;
    private javax.swing.JLabel lbMutation;
    private javax.swing.JLabel lbPopSize;
    private javax.swing.JTable tab1;
    private javax.swing.JTextField tfFitness;
    private javax.swing.JTextField tfGeneration;
    private javax.swing.JTextField tfTime;
    // End of variables declaration//GEN-END:variables
}
