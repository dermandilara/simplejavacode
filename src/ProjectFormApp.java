import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProjectFormApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Software Project Registration Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 450);

        ProjectFormPanel panel = new ProjectFormPanel();
        frame.add(panel);

        frame.setVisible(true);
    }
}
