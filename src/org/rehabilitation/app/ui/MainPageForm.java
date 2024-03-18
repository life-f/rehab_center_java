package org.rehabilitation.app.ui;

import org.rehabilitation.app.Application;
import org.rehabilitation.app.data.entity.ClientEntity;
import org.rehabilitation.app.data.manager.ClientManager;
import org.rehabilitation.app.util.BaseForm;
import org.rehabilitation.app.util.MysqlDatabase;

import javax.swing.*;

public class MainPageForm extends BaseForm {
    private MysqlDatabase database = Application.getInstance().getDatabase();
    private final ClientManager clientManager = new ClientManager(database);
    private static ClientEntity client = null;

    private JPanel mainPanel;
    private JButton exitButton;
    private JButton scheduleButton;
    private JButton addNoteButton;
    private JButton mapButton;
    private JLabel lastNameField;
    private JLabel firstNameField;
    private JLabel middleNameField;

    public MainPageForm(int id) {
        setContentPane(mainPanel);
        initButtons();
        initElements(id);

        setVisible(true);
    }

    public MainPageForm() {
        this(client.getId());
    }

    private void initElements(int id) {
        client = clientManager.getById(id);
        lastNameField.setText(client.getLastName());
        firstNameField.setText(client.getFirstName());
        middleNameField.setText(client.getMiddleName());
    }

    private void initButtons() {
        exitButton.addActionListener(e -> {
            dispose();
            setVisible(false);
            new AuthForm();
        });

        scheduleButton.addActionListener(e -> {
            dispose();
            setVisible(false);

            new ScheduleForm(client.getId());
        });

        addNoteButton.addActionListener(e -> {
            dispose();
            setVisible(false);

            new SubjectsForm();
        });

        mapButton.addActionListener(e -> {
            dispose();
            setVisible(false);

            new MapForm();
        });
    }

    public static ClientEntity getClient() {
        return client;
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
