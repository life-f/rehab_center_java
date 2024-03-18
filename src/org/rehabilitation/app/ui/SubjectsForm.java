package org.rehabilitation.app.ui;

import org.rehabilitation.app.Application;
import org.rehabilitation.app.data.entity.SubjectEntity;
import org.rehabilitation.app.data.manager.SubjectManager;
import org.rehabilitation.app.util.BaseForm;
import org.rehabilitation.app.util.MysqlDatabase;

import javax.swing.*;
import javax.swing.BoxLayout;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectsForm extends BaseForm {
    private MysqlDatabase database = Application.getInstance().getDatabase();
    private final SubjectManager subjectManager = new SubjectManager(database);
    private final List<SubjectEntity> subjects = subjectManager.getAll();

    private JPanel mainPanel;
    private JButton exitButton;
    private JButton backButton;
    private JPanel buttonsPanel;

    private List<JButton> buttons = new ArrayList<>();

    public SubjectsForm() {
        setContentPane(mainPanel);
        initButtons();

        setVisible(true);
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

        Container container = buttonsPanel;
        BoxLayout boxLayout = new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS);
        container.setLayout(boxLayout);
        subjects.forEach(subject -> {
            JButton button = new JButton(subject.getName());
            container.add(button);

            button.setMinimumSize(new Dimension(300, -1));
            button.setMaximumSize(new Dimension(300, -1));
            button.setHorizontalAlignment(SwingConstants.CENTER);
            button.addActionListener(e -> {
                dispose();
                setVisible(false);

                new AddNoteForm(subject.getName());
            });
        });
    }

    @Override
    public int getFormWidth() {
        return 500;
    }

    @Override
    public int getFormHeight() {
        return 400;
    }
}
