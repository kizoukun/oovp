package GUI01;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MyTable {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Table Example");
        String[] columnNames = {"Name", "Age", "Gender"};
        Object[][] data = {
                {"John", 30, "Male"},
                {"Mary", 25, "Female"},
                {"Peter", 40, "Male"},
                {"Jane", 20, "Female"}
        };
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane);
        frame.pack();
        frame.setVisible(true);
    }
}
