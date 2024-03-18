package org.rehabilitation.app.ui;

import org.rehabilitation.app.Application;
import org.rehabilitation.app.data.entity.LogDataEntity;
import org.rehabilitation.app.data.manager.LogDataManager;
import org.rehabilitation.app.util.BaseForm;
import org.rehabilitation.app.util.DialogUtil;

import javax.swing.*;

public class AuthForm extends BaseForm {

    private final LogDataManager logDataManager = Application.getInstance().getLogDataManager();

    private JPanel mainPanel;
    private JTextField loginField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public AuthForm() {
        setContentPane(mainPanel);
        setResizable(false);
        initButtons();

        setVisible(true);
    }

    private void initButtons() {
        loginButton.addActionListener(e -> {
            LogDataEntity logDataEntity = logDataManager.getByLoginPassword(loginField.getText(), new String(passwordField.getPassword()));
            if (logDataEntity != null) {
                dispose();
                setVisible(false);
                new MainPageForm(logDataEntity.getIdClient());
            } else {
                DialogUtil.showError("Неверный логин или пароль");
            }
        });
    }

    @Override
    public int getFormWidth() {
        return 400;
    }

    @Override
    public int getFormHeight() {
        return 200;
    }
}
