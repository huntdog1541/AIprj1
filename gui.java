import javax.swing.*;
import java.awt.*;

/**
 * Created by David on 3/3/2015.
 */
public class gui extends JPanel {

    private Button bt1;
    private Button bt2;
    private JTextArea comments;
    private JScrollPane scrollbar;
    private int rowNumber;
    private int colNumber;
    private boolean hasAgents;
    private JLabel rowLabel;
    private JLabel columnLabel;
    private JTextField rowNumb;
    private JTextField colNumb;
    private JComboBox withAgents;
    private GridBagConstraints gb;

    public gui() {
        addComponents();
        rowNumber = 0;
        colNumber = 0;
        hasAgents = false;
    }

    public void addComponents() {
        layoutMang();
        bt1 = new Button("Button 1");
        bt2 = new Button("Button 2");
        comments = new JTextArea("JTextArea");
        comments.setPreferredSize(new Dimension(500, 500));
        scrollbar = new JScrollPane(comments, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        comments.setLineWrap(true);
        comments.setEditable(false);
        rowLabel = new JLabel("How many rows: ");
        columnLabel = new JLabel("How many columns: ");
        rowNumb = new JTextField();
        colNumb = new JTextField();
        withAgents = new JComboBox();
        gb.gridx = 1;
        gb.gridy = 1;
        this.add(bt1, gb);
        this.add(bt2);
        this.add(rowLabel);
        this.add(rowNumb);
        this.add(columnLabel);
        this.add(colNumb);
        this.add(comments);
        this.addText("\nAdding text to JTextArea");
    }

    public void addText(String txt) {
        comments.append(txt);
    }

    public void layoutMang() {
        this.setLayout(new GridBagLayout());
        gb = new GridBagConstraints();
        gb.fill = GridBagConstraints.HORIZONTAL;
        gb.gridx = 0;
        gb.gridy = 0;
        JButton bt3 = new JButton("Hi");
        this.add(bt3, gb);
    }

}
