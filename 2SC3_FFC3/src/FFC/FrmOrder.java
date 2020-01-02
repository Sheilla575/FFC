/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FFC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author ASUS
 */
public class FrmOrder extends javax.swing.JFrame {
    String SQL;
    Connection Con;
    Statement Stat;
    ResultSet ReS, ReST, ReSF;
    /**
     * Creates new form FrmCrud
     */
    void FillCBType() throws SQLException{
        PreparedStatement PreStat = Con.prepareStatement("SELECT `fur_type` FROM table_furniture");
        ReS = PreStat.executeQuery();
        while(ReS.next()){
            String Type = ReS.getString("fur_type");
            CBType.addItem(Type);
        }
    }
    void FillCBMaterial() throws SQLException{
        PreparedStatement PreStat = Con.prepareStatement("SELECT `mat_name` FROM table_material");
        ReS = PreStat.executeQuery();
        while(ReS.next()){
            String Material = ReS.getString("mat_name");
            CBMaterial.addItem(Material);
        }
    }
    void FillYourBalls(){
        try {
            DefaultTableModel Model = (DefaultTableModel) Table.getModel();
            Model.setRowCount(0);
            PreparedStatement PreStat = Con.prepareStatement("SELECT * FROM table_order");
            ReS = PreStat.executeQuery();
            while(ReS.next()){
                Object Row[] = new Object[9];
                Row[0] = ReS.getInt(1);
                Row[1] = ReS.getString(2);
                Row[2] = ReS.getString(3);
                Row[3] = ReS.getInt(4);
                Row[4] = ReS.getInt(5);
                Row[5] = ReS.getInt(6);
                Row[6] = ReS.getString(7);
                Row[7] = ReS.getInt(8);
                Row[8] = ReS.getString(9);
                Model.addRow(Row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FrmOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public FrmOrder() {
        initComponents();
        FuncConnect DB = new FuncConnect();
        DB.config();
        Con = DB.Con;
        Stat = DB.StatM;
        setLocationRelativeTo(null);
        try {
            FillCBType();
            FillCBMaterial();
            FillYourBalls();
        } catch (SQLException ex) {
            Logger.getLogger(FrmOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    FuncBasic Func = new FuncBasic();
    String GetName, GetDesc;
    int GetID, GetType = 1, GetLength, GetWidth, GetHeight, GetMaterial = 1, GetPrice, Length, Width, Height, WoW = 0, Clicked;
   
    void SetType(){
        try {
            PreparedStatement PreStat = Con.prepareStatement("SELECT * FROM table_furniture WHERE fur_id = "+ GetType +"");
            ReST = PreStat.executeQuery();
            if(ReST.next()){
                TFLength.setText(Integer.toString(ReST.getInt("fur_length")));
                TFLengthL.setText(Integer.toString(ReST.getInt("fur_lengthl")));
                TFWidth.setText(Integer.toString(ReST.getInt("fur_width")));
                TFWidthL.setText(Integer.toString(ReST.getInt("fur_widthl")));
                TFHeight.setText(Integer.toString(ReST.getInt("fur_height")));
                TFHeightL.setText(Integer.toString(ReST.getInt("fur_heightl")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FrmOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void SetMaterial(){
        TFPrice.setText(Integer.toString(GetPrice));
    }
    void Recount(){
        try {
            PreparedStatement PreStat = Con.prepareStatement("SELECT * FROM table_furniture WHERE fur_id = "+ GetType +"");
            ReST = PreStat.executeQuery();
            if(ReST.next()){
                Length = Integer.parseInt(TFLength.getText());
                Width = Integer.parseInt(TFWidth.getText());
                Height = Integer.parseInt(TFHeight.getText());
                int LengthPrice, WidthPrice, HeightPrice, MatPrice;
                if(Length > ReST.getInt("fur_lengthl") || Width > ReST.getInt("fur_widthl") || Height > ReST.getInt("fur_heightl")){
                    if(Length > ReST.getInt("fur_lengthl")){
                        Length = ReST.getInt("fur_lengthl");
                    }
                    if(Width > ReST.getInt("fur_widthl")){
                        Width = ReST.getInt("fur_widthl");
                    }
                    if(Height > ReST.getInt("fur_heightl")){
                        Height = ReST.getInt("fur_heightl");
                    }
                }   
                PreparedStatement PreStatMat = Con.prepareStatement("SELECT * FROM table_material WHERE mat_id = "+ GetMaterial +"");
                ResultSet ReSTMat = PreStatMat.executeQuery();
                if(ReSTMat.next()){
                    LengthPrice = Length * ReST.getInt("fur_length") / 1000;
                    WidthPrice = Width * ReST.getInt("fur_width") / 1000;
                    HeightPrice = Height * ReST.getInt("fur_height") / 1000;
                    MatPrice = ReST.getInt("fur_price") * ReSTMat.getInt("mat_price") / 100;
                    GetPrice = MatPrice + LengthPrice + WidthPrice + HeightPrice + ReST.getInt("fur_price");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FrmOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void Clear(){
        TFID.setText("");
        TFName.setText("");
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        TFID = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        TFName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        TFLength = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        TFWidth = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        TFHeight = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        TFPrice = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        TFDate = new javax.swing.JTextField();
        CBType = new javax.swing.JComboBox();
        CBMaterial = new javax.swing.JComboBox();
        TFHeightL = new javax.swing.JTextField();
        TFWidthL = new javax.swing.JTextField();
        TFLengthL = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TFDesc = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        ButAdd = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        ButUpdate = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        ButDelete = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        ButBack = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        ButClear = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        TFSearch = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel4.setBackground(new java.awt.Color(35, 39, 42));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Wodalypse");

        jPanel1.setBackground(new java.awt.Color(35, 39, 42));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        TFID.setEditable(false);
        TFID.setBackground(new java.awt.Color(35, 39, 42));
        TFID.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TFID.setForeground(new java.awt.Color(255, 255, 255));
        TFID.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        TFID.setPreferredSize(new java.awt.Dimension(2, 24));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Order ID");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Customer Name");

        TFName.setBackground(new java.awt.Color(35, 39, 42));
        TFName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TFName.setForeground(new java.awt.Color(255, 255, 255));
        TFName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Furniture Type");

        TFLength.setBackground(new java.awt.Color(35, 39, 42));
        TFLength.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TFLength.setForeground(new java.awt.Color(255, 255, 255));
        TFLength.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFLength.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Length");

        TFWidth.setBackground(new java.awt.Color(35, 39, 42));
        TFWidth.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TFWidth.setForeground(new java.awt.Color(255, 255, 255));
        TFWidth.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFWidth.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Width");

        TFHeight.setBackground(new java.awt.Color(35, 39, 42));
        TFHeight.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TFHeight.setForeground(new java.awt.Color(255, 255, 255));
        TFHeight.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFHeight.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Height");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Material");

        TFPrice.setEditable(false);
        TFPrice.setBackground(new java.awt.Color(35, 39, 42));
        TFPrice.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TFPrice.setForeground(new java.awt.Color(255, 255, 255));
        TFPrice.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFPrice.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Price");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Date Ordered");

        TFDate.setEditable(false);
        TFDate.setBackground(new java.awt.Color(35, 39, 42));
        TFDate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TFDate.setForeground(new java.awt.Color(255, 255, 255));
        TFDate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFDate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        CBType.setBackground(new java.awt.Color(39, 35, 42));
        CBType.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        CBType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBTypeActionPerformed(evt);
            }
        });

        CBMaterial.setBackground(new java.awt.Color(39, 35, 42));
        CBMaterial.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        CBMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBMaterialActionPerformed(evt);
            }
        });

        TFHeightL.setEditable(false);
        TFHeightL.setBackground(new java.awt.Color(35, 39, 42));
        TFHeightL.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TFHeightL.setForeground(new java.awt.Color(255, 0, 0));
        TFHeightL.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFHeightL.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        TFWidthL.setEditable(false);
        TFWidthL.setBackground(new java.awt.Color(35, 39, 42));
        TFWidthL.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TFWidthL.setForeground(new java.awt.Color(255, 0, 0));
        TFWidthL.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFWidthL.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        TFLengthL.setEditable(false);
        TFLengthL.setBackground(new java.awt.Color(35, 39, 42));
        TFLengthL.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TFLengthL.setForeground(new java.awt.Color(255, 0, 0));
        TFLengthL.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFLengthL.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Description");

        TFDesc.setBackground(new java.awt.Color(35, 39, 42));
        TFDesc.setColumns(20);
        TFDesc.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        TFDesc.setForeground(new java.awt.Color(255, 255, 255));
        TFDesc.setRows(5);
        jScrollPane2.setViewportView(TFDesc);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TFID, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TFName, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(CBType, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(CBMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TFPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(TFHeightL, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(TFWidthL, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(TFLengthL, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TFHeight, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                            .addComponent(TFWidth)
                            .addComponent(TFLength)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TFDate)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TFID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(TFName))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CBType, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(TFLength)
                    .addComponent(TFLengthL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(TFWidthL)
                    .addComponent(TFWidth))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TFHeight, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TFHeightL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CBMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TFPrice)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TFDate)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );

        Table.setBackground(new java.awt.Color(35, 39, 42));
        Table.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        Table.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        Table.setForeground(new java.awt.Color(255, 255, 255));
        Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Type", "Length", "Width", "Height", "Material", "Price", "Date"
            }
        ));
        Table.setGridColor(new java.awt.Color(255, 255, 255));
        Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Table);

        jPanel2.setBackground(new java.awt.Color(35, 39, 42));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        ButAdd.setBackground(new java.awt.Color(9, 164, 0));
        ButAdd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        ButAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButAddMouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("ADD");

        javax.swing.GroupLayout ButAddLayout = new javax.swing.GroupLayout(ButAdd);
        ButAdd.setLayout(ButAddLayout);
        ButAddLayout.setHorizontalGroup(
            ButAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ButAddLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        ButAddLayout.setVerticalGroup(
            ButAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ButAddLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        ButUpdate.setBackground(new java.awt.Color(226, 160, 28));
        ButUpdate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        ButUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButUpdateMouseClicked(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("UPDATE");

        javax.swing.GroupLayout ButUpdateLayout = new javax.swing.GroupLayout(ButUpdate);
        ButUpdate.setLayout(ButUpdateLayout);
        ButUpdateLayout.setHorizontalGroup(
            ButUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ButUpdateLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                .addContainerGap())
        );
        ButUpdateLayout.setVerticalGroup(
            ButUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ButUpdateLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        ButDelete.setBackground(new java.awt.Color(204, 0, 0));
        ButDelete.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        ButDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButDeleteMouseClicked(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("DELETE");

        javax.swing.GroupLayout ButDeleteLayout = new javax.swing.GroupLayout(ButDelete);
        ButDelete.setLayout(ButDeleteLayout);
        ButDeleteLayout.setHorizontalGroup(
            ButDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ButDeleteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        ButDeleteLayout.setVerticalGroup(
            ButDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ButDeleteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        ButBack.setBackground(new java.awt.Color(35, 39, 42));
        ButBack.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        ButBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButBackMouseClicked(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("BACK");

        javax.swing.GroupLayout ButBackLayout = new javax.swing.GroupLayout(ButBack);
        ButBack.setLayout(ButBackLayout);
        ButBackLayout.setHorizontalGroup(
            ButBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ButBackLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        ButBackLayout.setVerticalGroup(
            ButBackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ButBackLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        ButClear.setBackground(new java.awt.Color(35, 39, 42));
        ButClear.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        ButClear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButClearMouseClicked(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("CLEAR");

        javax.swing.GroupLayout ButClearLayout = new javax.swing.GroupLayout(ButClear);
        ButClear.setLayout(ButClearLayout);
        ButClearLayout.setHorizontalGroup(
            ButClearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ButClearLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        ButClearLayout.setVerticalGroup(
            ButClearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ButClearLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        TFSearch.setBackground(new java.awt.Color(35, 39, 42));
        TFSearch.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TFSearch.setForeground(new java.awt.Color(255, 255, 255));
        TFSearch.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFSearch.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        TFSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TFSearchKeyReleased(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Search");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ButUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ButDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ButBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ButClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ButAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TFSearch)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ButAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ButUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ButDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TFSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ButClear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ButBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 883, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CBTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBTypeActionPerformed
        try {
            PreparedStatement PreStatA = Con.prepareStatement("SELECT * FROM table_furniture WHERE fur_type = '"+ CBType.getSelectedItem() +"'");
            ResultSet ReSA = PreStatA.executeQuery();
            if(ReSA.next()){
                GetType = ReSA.getInt("fur_id");
            }
            SetType();
            Recount();
            SetMaterial();
        } catch (SQLException ex) {
            Logger.getLogger(FrmOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_CBTypeActionPerformed

    private void ButAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButAddMouseClicked
        if(TFName.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Please fill the Customer name field !");
        }else{
            GetName = TFName.getText();
            GetDesc = TFDesc.getText();
            GetLength = Integer.parseInt(TFLength.getText());
            GetWidth = Integer.parseInt(TFWidth.getText());
            GetHeight = Integer.parseInt(TFHeight.getText());
            Recount();
            GetPrice = Integer.parseInt(TFPrice.getText());
            Func.Add(GetName, GetType, GetLength, GetWidth, GetHeight, GetMaterial, GetPrice, GetDesc);
            FillYourBalls();
        }
    }//GEN-LAST:event_ButAddMouseClicked

    private void CBMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBMaterialActionPerformed
        try {
            PreparedStatement PreStatA = Con.prepareStatement("SELECT * FROM table_material WHERE mat_name = '"+ CBMaterial.getSelectedItem() +"'");
            ResultSet ReSA = PreStatA.executeQuery();
            if(ReSA.next()){
                GetMaterial = ReSA.getInt("mat_id");
            }
            Recount();
            SetMaterial();
        } catch (SQLException ex) {
            Logger.getLogger(FrmOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_CBMaterialActionPerformed

    private void ButUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButUpdateMouseClicked
        if(TFID.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Please choose one of the available row !");
        }else{
            GetName = TFName.getText();
            GetID = Integer.parseInt(TFID.getText());
            GetLength = Integer.parseInt(TFLength.getText());
            GetWidth = Integer.parseInt(TFWidth.getText());
            GetHeight = Integer.parseInt(TFHeight.getText());
            GetDesc = TFDesc.getText();
            Recount();
            SetMaterial();
            GetPrice = Integer.parseInt(TFPrice.getText());
            Func.Update(GetID, GetName, GetType, GetLength, GetWidth, GetHeight, GetMaterial, GetPrice, GetDesc);
            FillYourBalls();
        }
    }//GEN-LAST:event_ButUpdateMouseClicked

    private void ButBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButBackMouseClicked
        new FrmOrder().setVisible(false);
        new FrmMenu().setVisible(true);
        dispose();
    }//GEN-LAST:event_ButBackMouseClicked

    private void TableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableMouseClicked
        try {
            DefaultTableModel Model = (DefaultTableModel) Table.getModel();
            PreparedStatement PreStat = Con.prepareStatement("SELECT * FROM table_order WHERE order_id = "+ Model.getValueAt(Table.getSelectedRow(), 0) +"");
            ResultSet ReSA = PreStat.executeQuery();
            if(ReSA.next()){
                CBType.setSelectedItem(ReSA.getString("order_type"));
                CBMaterial.setSelectedItem(ReSA.getString("order_mat"));
                TFID.setText(Integer.toString(ReSA.getInt("order_id")));
                TFName.setText(ReSA.getString("order_name"));
                TFLength.setText(Integer.toString(ReSA.getInt("order_length")));
                TFWidth.setText(Integer.toString(ReSA.getInt("order_width")));
                TFHeight.setText(Integer.toString(ReSA.getInt("order_height")));
                TFDate.setText(Integer.toString(ReSA.getInt("order_date")));
                TFDesc.setText(ReSA.getString("order_desc"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FrmOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_TableMouseClicked

    private void ButDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButDeleteMouseClicked
        if(TFID.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Please choose one of the available row !");
        }else{
            GetID = Integer.parseInt(TFID.getText());
            Func.Delete(GetID);
            Clear();
            FillYourBalls();
        }
    }//GEN-LAST:event_ButDeleteMouseClicked

    private void ButClearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButClearMouseClicked
        Clear();
    }//GEN-LAST:event_ButClearMouseClicked

    private void TFSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TFSearchKeyReleased
        try {
            if(TFSearch.getText().equals("")){
                FillYourBalls();
            }else{
                String Query = "SELECT order_id AS ID, order_name AS Name, order_type AS Type, order_length AS Length, order_width AS Width, order_height AS Height, order_mat AS Material, order_price AS Price, order_date AS Date FROM table_order WHERE order_name LIKE '%"+ TFSearch.getText() +"%'";
                PreparedStatement PreStat = Con.prepareStatement(Query);
                ReS = PreStat.executeQuery();
                Table.setModel(DbUtils.resultSetToTableModel(ReS));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FrmOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_TFSearchKeyReleased

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
            java.util.logging.Logger.getLogger(FrmOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmOrder().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ButAdd;
    private javax.swing.JPanel ButBack;
    private javax.swing.JPanel ButClear;
    private javax.swing.JPanel ButDelete;
    private javax.swing.JPanel ButUpdate;
    private javax.swing.JComboBox CBMaterial;
    private javax.swing.JComboBox CBType;
    private javax.swing.JTextField TFDate;
    private javax.swing.JTextArea TFDesc;
    private javax.swing.JTextField TFHeight;
    private javax.swing.JTextField TFHeightL;
    private javax.swing.JTextField TFID;
    private javax.swing.JTextField TFLength;
    private javax.swing.JTextField TFLengthL;
    private javax.swing.JTextField TFName;
    private javax.swing.JTextField TFPrice;
    private javax.swing.JTextField TFSearch;
    private javax.swing.JTextField TFWidth;
    private javax.swing.JTextField TFWidthL;
    public javax.swing.JTable Table;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
