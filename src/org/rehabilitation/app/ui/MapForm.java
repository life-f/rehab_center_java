package org.rehabilitation.app.ui;

import org.rehabilitation.app.util.BaseForm;

import javax.swing.*;

public class MapForm extends BaseForm {
    private JPanel mainPanel;
    private JButton socolButton;
    private JButton firstButton;
    private JButton secondButton;
    private JButton thirdButton;
    private JButton fourthButton;
    private JButton exitButton;
    private JButton backButton;
    private JPanel imageView;
    private JLabel titleField;

    public MapForm() {
        setContentPane(mainPanel);
        initButtons();
        initElements();

        setVisible(true);
    }

    private void initElements() {
        titleField.setText("Первый этаж");
        firstButton.setEnabled(false);

    }

    private void initButtons() {
        backButton.addActionListener(e -> {
            dispose();
            setVisible(false);

            new MainPageForm();
        });
        exitButton.addActionListener(e -> {
            dispose();
            setVisible(false);

            new AuthForm();
        });

        socolButton.addActionListener(e -> {
            titleField.setText("Цокольный этаж");
            setButtonsEnable();
            socolButton.setEnabled(false);
        });
        firstButton.addActionListener(e -> {
            titleField.setText("Первый этаж");
            setButtonsEnable();
            firstButton.setEnabled(false);
        });
        secondButton.addActionListener(e -> {
            titleField.setText("Второй этаж");
            setButtonsEnable();
            secondButton.setEnabled(false);
        });
        thirdButton.addActionListener(e -> {
            titleField.setText("Третий этаж");
            setButtonsEnable();
            thirdButton.setEnabled(false);
        });
        fourthButton.addActionListener(e -> {
            titleField.setText("Четвертый этаж");
            setButtonsEnable();
            fourthButton.setEnabled(false);
        });
    }

    private void setButtonsEnable() {
        socolButton.setEnabled(true);
        firstButton.setEnabled(true);
        secondButton.setEnabled(true);
        thirdButton.setEnabled(true);
        fourthButton.setEnabled(true);
    }

    @Override
    public int getFormWidth() {
        return 400;
    }

    @Override
    public int getFormHeight() {
        return 400;
    }
}
