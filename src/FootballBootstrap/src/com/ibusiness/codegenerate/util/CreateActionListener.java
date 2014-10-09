package com.ibusiness.codegenerate.util;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.apache.commons.lang3.StringUtils;

import com.ibusiness.codegenerate.code.DbEntity.DbFiledToJspUtil;
import com.ibusiness.codegenerate.code.generate.CodeGenerate;
import com.ibusiness.codegenerate.code.window.CodeWindow;
import com.ibusiness.codegenerate.code.window.CreateFileProperty;

/**
 * 自动代码生成器windows页面生成按钮,生成事件监听器
 * 
 * @author JiangBo
 * 
 */
public final class CreateActionListener implements ActionListener {
    private JLabel titleInfo_JLabel;
    private JTextField packageName_TextField;
    private JTextField entity_TextField;
    private JTextField tableNameRemark_TextField;
    private JTextField tableName_TextField;
    private JTextField rowNumber_TextField;
    private JTextField keySequence_TextField;
    private JComboBox keyType_ComboBox;
    private JRadioButton jspDetail_RadioButton;
    private JRadioButton jspRow_RadioButton;
    private JCheckBox action_CheckBox;
    private JCheckBox jsp_CheckBox;
    private JCheckBox serviceI_CheckBox;
    private JCheckBox serviceImpl_CheckBox;
    private JCheckBox page_CheckBox;
    private JCheckBox entity_CheckBox;

    /**
     * 构造函数
     */
    public CreateActionListener(CodeWindow paramCodeWindow, JTextField packageName_TextField, JLabel titleInfo_JLabel,
            JTextField entity_TextField, JTextField tableNameRemark_TextField, JTextField tableName_TextField,
            JTextField rowNumber_TextField, JComboBox keyType_ComboBox, JTextField keySequence_TextField,
            JRadioButton jspDetail_RadioButton, JRadioButton jspRow_RadioButton, JCheckBox action_CheckBox,
            JCheckBox jsp_CheckBox, JCheckBox serviceI_CheckBox, JCheckBox serviceImpl_CheckBox,
            JCheckBox page_CheckBox, JCheckBox entity_CheckBox) {
        this.titleInfo_JLabel = titleInfo_JLabel;
        this.packageName_TextField = packageName_TextField;
        this.entity_TextField = entity_TextField;
        this.tableNameRemark_TextField = tableNameRemark_TextField;
        this.tableName_TextField = tableName_TextField;
        this.rowNumber_TextField = rowNumber_TextField;
        this.keySequence_TextField = keySequence_TextField;
        this.keyType_ComboBox = keyType_ComboBox;
        this.jspDetail_RadioButton = jspDetail_RadioButton;
        this.jspRow_RadioButton = jspRow_RadioButton;
        this.action_CheckBox = action_CheckBox;
        this.jsp_CheckBox = jsp_CheckBox;
        this.serviceI_CheckBox = serviceI_CheckBox;
        this.serviceImpl_CheckBox = serviceImpl_CheckBox;
        this.page_CheckBox = page_CheckBox;
        this.entity_CheckBox = entity_CheckBox;
    }

    /**
     * Action输入验证证
     */
    public void actionPerformed(ActionEvent actionEvent) {
        if (StringUtils.isBlank(this.packageName_TextField.getText())) {
            this.titleInfo_JLabel.setForeground(Color.red);
            this.titleInfo_JLabel.setText("包名不能为空！");
            return;
        }
        if (StringUtils.isBlank(this.entity_TextField.getText())) {
            this.titleInfo_JLabel.setForeground(Color.red);
            this.titleInfo_JLabel.setText("实体类名不能为空！");
            return;
        }
        if (StringUtils.isBlank(this.tableNameRemark_TextField.getText())) {
            this.titleInfo_JLabel.setForeground(Color.red);
            this.titleInfo_JLabel.setText("描述不能为空！");
            return;
        }
        if (StringUtils.isBlank(this.tableName_TextField.getText())) {
            this.titleInfo_JLabel.setForeground(Color.red);
            this.titleInfo_JLabel.setText("表名不能为空！");
            return;
        }
        if ("sequence".equals(this.keyType_ComboBox.getSelectedItem().toString())
                && StringUtils.isBlank(this.keySequence_TextField.getText())) {
            this.titleInfo_JLabel.setForeground(Color.red);
            this.titleInfo_JLabel.setText("主键生成策略为sequence时，序列号不能为空！");
            return;
        }
        CreateFileProperty localCreateFileProperty = new CreateFileProperty();
        if (this.jspDetail_RadioButton.isSelected())
            localCreateFileProperty.setJspMode("01");
        if (this.jspRow_RadioButton.isSelected())
            localCreateFileProperty.setJspMode("02");
        if (this.action_CheckBox.isSelected())
            localCreateFileProperty.setActionFlag(true);
        if (this.jsp_CheckBox.isSelected())
            localCreateFileProperty.setJspFlag(true);
        if (this.serviceI_CheckBox.isSelected())
            localCreateFileProperty.setServiceIFlag(true);
        if (this.serviceImpl_CheckBox.isSelected())
            localCreateFileProperty.setServiceImplFlag(true);
        if (this.page_CheckBox.isSelected())
            localCreateFileProperty.setPageFlag(true);
        if (this.entity_CheckBox.isSelected())
            localCreateFileProperty.setEntityFlag(true);
        try {
            // 如果表中有输入表名的表存在
            boolean bool = new DbFiledToJspUtil().checkTableExist(this.tableName_TextField.getText());
            if (bool) {
                // 自动生成Java，JSP代码
                new CodeGenerate(this.packageName_TextField.getText(), "", this.entity_TextField.getText(),
                        this.tableName_TextField.getText(), this.tableNameRemark_TextField.getText(),
                        localCreateFileProperty, Integer.parseInt(this.rowNumber_TextField.getText()),
                        this.keyType_ComboBox.getSelectedItem().toString(), this.keySequence_TextField.getText())
                        .generateToFile();
                this.titleInfo_JLabel.setForeground(Color.red);
                this.titleInfo_JLabel.setText("成功生成增删改查->功能：" + this.tableNameRemark_TextField.getText());
            } else {
                this.titleInfo_JLabel.setForeground(Color.red);
                this.titleInfo_JLabel.setText("表[" + this.tableName_TextField.getText() + "] 在数据库中，不存在");
            }
        } catch (Exception localException) {
            this.titleInfo_JLabel.setForeground(Color.red);
            this.titleInfo_JLabel.setText(localException.getMessage());
        }
    }
}
