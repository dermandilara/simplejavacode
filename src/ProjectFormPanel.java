import javax.swing.*;
        import java.awt.*;
        import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProjectFormPanel extends JPanel {
    private JTextField txtProjectName, txtTeamLeader, txtStartDate;
    private JComboBox<String> cbTeamSize, cbProjectType;
    private JButton btnSave, btnClear;

    public ProjectFormPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Form Alanları
        addComp("Project Name:", txtProjectName = new JTextField(20), 0, gbc);
        addComp("Team Leader:", txtTeamLeader = new JTextField(20), 1, gbc);

        String[] sizes = {"1-3", "4-6", "7-10", "10+"};
        addComp("Team Size:", cbTeamSize = new JComboBox<>(sizes), 2, gbc);

        String[] types = {"Web", "Mobile", "Desktop", "API"};
        addComp("Project Type:", cbProjectType = new JComboBox<>(types), 3, gbc);

        addComp("Start Date (DD/MM/YYYY):", txtStartDate = new JTextField(20), 4, gbc);


        btnSave = new JButton("Save");
        btnClear = new JButton("Clear");

        gbc.gridy = 5; gbc.gridx = 0; add(btnSave, gbc);
        gbc.gridx = 1; add(btnClear, gbc);


        btnSave.addActionListener(e -> saveAction());
        btnClear.addActionListener(e -> clearFields());
    }

    private void addComp(String label, JComponent comp, int row, GridBagConstraints gbc) {
        gbc.gridy = row; gbc.gridx = 0; add(new JLabel(label), gbc);
        gbc.gridx = 1; add(comp, gbc);
    }

    private void clearFields() {
        txtProjectName.setText("");
        txtTeamLeader.setText("");
        txtStartDate.setText("");
        cbTeamSize.setSelectedIndex(0);
        cbProjectType.setSelectedIndex(0);
    }

    private void saveAction() {
        if (txtProjectName.getText().isEmpty() || txtTeamLeader.getText().isEmpty() || txtStartDate.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }


        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("projects.txt", true))) {
            bw.write("=== Project Entry ===\n");
            bw.write("Project Name : " + txtProjectName.getText() + "\n");
            bw.write("Team Leader : " + txtTeamLeader.getText() + "\n");
            bw.write("Team Size : " + cbTeamSize.getSelectedItem() + "\n");
            bw.write("Project Type : " + cbProjectType.getSelectedItem() + "\n");
            bw.write("Start Date : " + txtStartDate.getText() + "\n");
            bw.write("Record Time : " + now + "\n");
            bw.write("======\n\n");

            JOptionPane.showMessageDialog(this, "Success! Saved to projects.txt");
            clearFields();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving file: " + ex.getMessage());
        }
    }
}