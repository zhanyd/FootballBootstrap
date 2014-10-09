package com.ibusiness.codegenerate.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.ibusiness.codegenerate.code.window.CodeWindow;

/**
 * 自动代码生成器windows页面退出按钮,退出事件监听器
 * 
 * @author Administrator
 *
 */
public final class exitActionListener implements ActionListener {
    public exitActionListener(CodeWindow paramCodeWindow) {
    }

    public void actionPerformed(ActionEvent paramActionEvent) {
        System.exit(0);
    }
}
