package com.ibusiness.codegenerate.code.window;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.ibusiness.codegenerate.util.CreateActionListener;
import com.ibusiness.codegenerate.util.exitActionListener;
/**
 * 代码自动生成页面测试类
 * 
 * @author JiangBo
 *
 */
public class CodeWindow extends JFrame {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    // 行字段数目
    private static int rowNumberInt = 1;
    // 主键生成策略
    String[] keyTypes = {"uuid", "identity", "sequence" };

    /**
     * 构造函数
     */
    public CodeWindow() {
        JPanel localJPanel = new JPanel();
        setContentPane(localJPanel);
        localJPanel.setLayout(new GridLayout(14, 2));
        JLabel infolbl = new JLabel("提示:");
        JLabel showlbl = new JLabel();
        JLabel packagebl = new JLabel("包名（小写）：");
        JTextField packagefld = new JTextField("tx");
        JLabel entitylbl = new JLabel("实体类名（首字母大写）：");
        JTextField entityfld = new JTextField("TxTest");
        JLabel tablejbl = new JLabel("表名：");
        JTextField tablefld = new JTextField(20);
        
        JLabel keyType_Label = new JLabel("主键生成策略：");
        JComboBox keyType_ComboBox = new JComboBox(this.keyTypes);
        JLabel keySequence_Label = new JLabel("主键SEQUENCE：(oracle序列名)");
        JTextField keySequence_TextField = new JTextField(20);
        
        JLabel titlelbl = new JLabel("功能描述：");
        JTextField titlefld = new JTextField("tx_test1");
        
        JLabel fieldRowNumlbl = new JLabel("行字段数目：");
        JTextField fieldRowNumfld = new JTextField();
        fieldRowNumfld.setText(String.valueOf(rowNumberInt));
        
        ButtonGroup localButtonGroup = new ButtonGroup();
        JRadioButton jsp = new JRadioButton("Table风格(form)");
        jsp.setSelected(true);
        JRadioButton jsp_row = new JRadioButton("Div风格(form)");
        localButtonGroup.add(jsp);
        localButtonGroup.add(jsp_row);
        
        JCheckBox action_CheckBox = new JCheckBox("Action");
        action_CheckBox.setSelected(true);
        JCheckBox jsp_CheckBox = new JCheckBox("Jsp");
        jsp_CheckBox.setSelected(true);
        JCheckBox serviceI_CheckBox = new JCheckBox("ServiceI");
        serviceI_CheckBox.setSelected(true);
        JCheckBox serviceImpl_CheckBox = new JCheckBox("ServiceImpl");
        serviceImpl_CheckBox.setSelected(false);
        JCheckBox page_CheckBox = new JCheckBox("Page");
        page_CheckBox.setSelected(false);
        JCheckBox entityButton = new JCheckBox("Entity");
        entityButton.setSelected(true);
        
        localJPanel.add(infolbl);
        localJPanel.add(showlbl);
        localJPanel.add(packagebl);
        localJPanel.add(packagefld);
        localJPanel.add(entitylbl);
        localJPanel.add(entityfld);
        localJPanel.add(tablejbl);
        localJPanel.add(tablefld);
        localJPanel.add(keyType_Label);
        localJPanel.add(keyType_ComboBox);
        localJPanel.add(keySequence_Label);
        localJPanel.add(keySequence_TextField);
        localJPanel.add(titlelbl);
        localJPanel.add(titlefld);
        localJPanel.add(fieldRowNumlbl);
        localJPanel.add(fieldRowNumfld);
        localJPanel.add(action_CheckBox);
        localJPanel.add(jsp_CheckBox);
        localJPanel.add(serviceI_CheckBox);
        localJPanel.add(serviceImpl_CheckBox);
        localJPanel.add(page_CheckBox);
        localJPanel.add(entityButton);
        localJPanel.add(jsp);
        localJPanel.add(jsp_row);
        // 自动代码生成按钮
        JButton createButton = new JButton("生成");
        createButton.addActionListener(new CreateActionListener(this, packagefld, showlbl, entityfld, titlefld,
                tablefld, fieldRowNumfld, keyType_ComboBox, keySequence_TextField, jsp,
                jsp_row, action_CheckBox, jsp_CheckBox, serviceI_CheckBox, serviceImpl_CheckBox,
                page_CheckBox, entityButton));
        // 退出按钮
        JButton exitButton = new JButton("退出");
        exitButton.addActionListener(new exitActionListener(this));
        
        localJPanel.add(createButton);
        localJPanel.add(exitButton);
        setTitle("代码生成器[单表模型]");
        setVisible(true);
        setDefaultCloseOperation(3);
        setSize(new Dimension(600, 400));
        setResizable(false);
        setLocationRelativeTo(getOwner());
    }

    public static void main(String[] paramArrayOfString) {
        try {
            new CodeWindow().pack();
        } catch (Exception localException) {
            System.out.println(localException.getMessage());
        }
    }
}
