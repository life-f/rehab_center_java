package org.rehabilitation.app.ui;

import org.rehabilitation.app.Application;
import org.rehabilitation.app.data.entity.NoteEntity;
import org.rehabilitation.app.data.entity.ScheduleEntity;
import org.rehabilitation.app.data.entity.SubjectEntity;
import org.rehabilitation.app.data.manager.NoteManager;
import org.rehabilitation.app.data.manager.ScheduleManager;
import org.rehabilitation.app.data.manager.SubjectManager;
import org.rehabilitation.app.util.BaseForm;
import org.rehabilitation.app.util.DialogUtil;
import org.rehabilitation.app.util.MysqlDatabase;

import javax.swing.*;
import java.util.List;

public class AddNoteForm extends BaseForm {
    private MysqlDatabase database = Application.getInstance().getDatabase();

    private final SubjectManager subjectManager = new SubjectManager(database);
    private final ScheduleManager scheduleManager = new ScheduleManager(database);
    private final NoteManager noteManager = new NoteManager(database);

    private JPanel mainPanel;
    private JButton exitButton;
    private JButton backButton;
    private JLabel title;
    private JComboBox dateComboBox;
    private JComboBox timeComboBox;
    private JButton addButton;

    private SubjectEntity subject;

    public AddNoteForm(String name) {
        setContentPane(mainPanel);
        subject = subjectManager.getByName(name);
        initElements();
        initButtons();

        setVisible(true);
    }

    private void initElements() {
        title.setText(subject.getName());

        List<String> scheduleDate = scheduleManager.getDatesBySubject(subject);
        if (!scheduleDate.isEmpty()) {
            scheduleDate.forEach(s -> {
                dateComboBox.addItem(s);
            });
            dateComboBox.addItemListener(e -> {
                initTimeComboBox();
            });
            initTimeComboBox();
        }
    }

    private void initTimeComboBox() {
        timeComboBox.removeAllItems();
        List<ScheduleEntity> scheduleTime = scheduleManager.getByDateSubject(
                String.valueOf(dateComboBox.getSelectedItem()),
                subject
        );
        if (!scheduleTime.isEmpty())
            scheduleTime.forEach(s -> {
                if (noteManager.getCountByIdSchedule(s.getIdSchedule()) < 6)
                    timeComboBox.addItem(s.getTime());
            });
    }

    private void initButtons() {
        backButton.addActionListener(e -> {
            dispose();
            setVisible(false);

            new SubjectsForm();
        });

        exitButton.addActionListener(e -> {
            dispose();
            setVisible(false);

            new AuthForm();
        });

        addButton.addActionListener(e -> {
            List<ScheduleEntity> schedules = scheduleManager.getByDateSubject(
                    String.valueOf(dateComboBox.getSelectedItem()),
                    subject
            );
            schedules.forEach(s -> {
                if (s.getTime().equals(String.valueOf(timeComboBox.getSelectedItem()))) {
                    NoteEntity note = new NoteEntity(
                            MainPageForm.getClient().getId(),
                            s.getIdSchedule()
                    );
                    if (noteManager.getByClientSchedule(MainPageForm.getClient().getId(), s.getIdSchedule()) == -1) {
                        noteManager.add(note);
                        DialogUtil.showInfo("Запись успешно добавлена");
                    } else {
                        DialogUtil.showWarn("Вы уже записаны на это занятие!");
                    }
                }
            });
        });
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
