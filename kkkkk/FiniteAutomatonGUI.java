import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FiniteAutomatonGUI extends JFrame implements ActionListener {
    private JTextField statesField, transitionField, initialStateField, acceptingStateField, binaryField;
    private JButton runButton;
    private JTextArea outputArea;
    private Finite1 automaton;
    private int[][] transitionTable;

    public FiniteAutomatonGUI() {
        setTitle("Finite Automaton Simulator");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2));

        panel.add(new JLabel("Input states:"));
        statesField = new JTextField(10);
        panel.add(statesField);

        panel.add(new JLabel("Input transition:"));
        transitionField = new JTextField(10);
        panel.add(transitionField);

        panel.add(new JLabel("Enter initial state:"));
        initialStateField = new JTextField(10);
        panel.add(initialStateField);

        panel.add(new JLabel("Enter accepting state:"));
        acceptingStateField = new JTextField(10);
        panel.add(acceptingStateField);

        panel.add(new JLabel("Input binary:"));
        binaryField = new JTextField(10);
        panel.add(binaryField);

        runButton = new JButton("Run");
        runButton.addActionListener(this);
        panel.add(runButton);

        outputArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(outputArea);
        panel.add(scrollPane);

        add(panel);
        setVisible(true);

        automaton = new Finite1();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == runButton) {
            int st = Integer.parseInt(statesField.getText());
            int ts = Integer.parseInt(transitionField.getText());
            int initial = Integer.parseInt(initialStateField.getText());
            int fin = Integer.parseInt(acceptingStateField.getText());
            String s1 = binaryField.getText();
            String in[] = s1.split("");
            transitionTable = new int[st][ts];
            for (int i = 0; i < st; i++) {
                for (int j = 0; j < ts; j++) {
                    String input = JOptionPane.showInputDialog("Enter transition for state " + i + " and input " + j + ":");
                    transitionTable[i][j] = Integer.parseInt(input);
                }
            }
            String result = automaton.processInput(st, ts, transitionTable, initial, fin, in);
            outputArea.setText(result);
        }
    }
    public static void main(String[] args) {
        new FiniteAutomatonGUI();
    }
}