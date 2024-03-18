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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ScheduleForm extends BaseForm {
    private MysqlDatabase database = Application.getInstance().getDatabase();
    private final NoteManager noteManager = new NoteManager(database);
    private final ScheduleManager scheduleManager = new ScheduleManager(database);
    private final SubjectManager subjectManager = new SubjectManager(database);

    private DefaultTableModel model;

    private JPanel mainPanel;
    private JTable table;
    private JButton exitButton;
    private JButton backButton;
    private JButton prevButton;
    private JButton cancelButton;
    private JButton nextButton;

    private final int idClient;

    public ScheduleForm(int idClient) {
        setContentPane(mainPanel);
        initButtons();
        this.idClient = idClient;
        initTable();

        loadFutureData();

        setVisible(true);
    }

    private void initButtons() {
        nextButton.setEnabled(false);

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

        prevButton.addActionListener(e -> {
            initTable();
            loadPreviousData();
            nextButton.setEnabled(true);
            prevButton.setEnabled(false);
            cancelButton.setEnabled(false);
        });

        nextButton.addActionListener(e -> {
            initTable();
            loadFutureData();
            nextButton.setEnabled(false);
            prevButton.setEnabled(true);
            cancelButton.setEnabled(true);
        });

        cancelButton.addActionListener(e -> {
            int idNote = Integer.parseInt(String.valueOf(table.getValueAt(
                    table.getSelectedRow(), 0
            )));
            if (DialogUtil.showConfirm("Вы действительно хотите отменить эту запись?"))
                if (noteManager.deleteById(idNote) != 0) {
                    DialogUtil.showInfo("Запись успешно отменена");
                }
            initTable();
            loadFutureData();
        });
    }

    private void initTable() {
        table.getTableHeader().setReorderingAllowed(false);

        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setModel(model);

        model.addColumn("id");
        model.addColumn("Дата");
        model.addColumn("Время");
        model.addColumn("Занятие");
        model.addColumn("Кабинет");

        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        table.getColumnModel().getColumn(0).setPreferredWidth(0);
        table.getColumnModel().getColumn(0).setResizable(false);
    }

    private void loadFutureData() {
        List<NoteEntity> notes = noteManager.getByIdClient(idClient);
        notes.forEach(noteEntity -> {
            ScheduleEntity scheduleEntity = scheduleManager.getById(noteEntity.getIdSchedule());
            SubjectEntity subjectEntity = subjectManager.getById(scheduleEntity.getIdSubject());
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.now();
            if (dtf.format(localDate).compareTo(scheduleEntity.getDate()) < 0)
                model.addRow(new Object[]{
                        noteEntity.getIdNote(),
                        scheduleEntity.getDate(),
                        scheduleEntity.getTime(),
                        subjectEntity.getName(),
                        subjectEntity.getClassroom()
                });
            sortTable();
        });
    }

    private void loadPreviousData() {
        List<NoteEntity> notes = noteManager.getByIdClient(idClient);
        notes.forEach(noteEntity -> {
            ScheduleEntity scheduleEntity = scheduleManager.getById(noteEntity.getIdSchedule());
            SubjectEntity subjectEntity = subjectManager.getById(scheduleEntity.getIdSubject());
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.now();
            if (dtf.format(localDate).compareTo(scheduleEntity.getDate()) >= 0)
                model.addRow(new Object[]{
                        noteEntity.getIdNote(),
                        scheduleEntity.getDate(),
                        scheduleEntity.getTime(),
                        subjectEntity.getName(),
                        subjectEntity.getClassroom()
                });
            sortTable();
        });
    }

    private void sortTable() {
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
        sorter.sort();
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
