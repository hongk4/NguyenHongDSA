package CK;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TextDemo extends JPanel implements ActionListener {
    protected JTextField textField;
    protected JTextArea textArea;
    private JComboBox<Integer> fontSizeComboBox;
    private JCheckBox darkModeCheckBox;
    private final static String newline = "\n";
    private static Trie trie;
    public void runRealTime(JTextField textField){
        textField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void removeUpdate(DocumentEvent e) {
                addTextToArea();

            }
            private void addTextToArea() {
                System.out.println(textField.getText());
                List<Pair<String, String>> lt = trie.suggest(textField.getText());
                textArea.setText("");
                for(Pair<String, String> i : lt){
                    textArea.append(i.getFirst() + ": " + i.getSecond() + "\n");
                }

            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                addTextToArea();

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                addTextToArea();
            }
        });
    }

    public TextDemo() {
        super(new GridBagLayout());

        // Thêm clearButton và textField vào panel
        JPanel inputPanel = new JPanel(new BorderLayout());
        textField = new JTextField(20);
        this.runRealTime(textField);

        inputPanel.add(textField, BorderLayout.CENTER);

        textArea = new JTextArea(5, 20);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        JButton clearButton = new JButton("X");
        JButton saveButton = new JButton("Save"); // Thêm nút Save
        darkModeCheckBox = new JCheckBox("Dark Mode");
        darkModeCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTheme();
            }
        });
        updateTheme();

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Xóa nội dung của textField khi nút được nhấn
                textField.setText("");
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveToFile();
            }
        });

        inputPanel.add(clearButton, BorderLayout.EAST);
        inputPanel.add(saveButton, BorderLayout.WEST); // Thêm nút Save

        // Thêm JComboBox để chọn kích thước chữ và Dark Mode checkbox
        fontSizeComboBox = new JComboBox<>();
        for (int i = 10; i <= 24; i++) {
            fontSizeComboBox.addItem(i);
        }
        fontSizeComboBox.setSelectedItem(16);

        fontSizeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int fontSize = (Integer) fontSizeComboBox.getSelectedItem();
                Font currentFont = textField.getFont();
                textField.setFont(new Font(currentFont.getFontName(), currentFont.getStyle(), fontSize));

                Font currentTextAreaFont = textArea.getFont();
                textArea.setFont(new Font(currentTextAreaFont.getFontName(), currentTextAreaFont.getStyle(), fontSize));
            }
        });


        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;
        add(fontSizeComboBox, c);

        c.gridwidth = GridBagConstraints.REMAINDER;
        add(darkModeCheckBox, c);

        add(inputPanel, c);

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        add(scrollPane, c);
    }

    private void saveToFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fileChooser.getSelectedFile();
                FileWriter writer = new FileWriter(file);
                writer.write(textArea.getText());
                writer.close();
                JOptionPane.showMessageDialog(this, "File saved successfully.");
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error saving file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    private void updateTheme() {
        if (darkModeCheckBox.isSelected()) {
            // Chế độ tối
            textField.setBackground(Color.BLACK);
            textField.setForeground(Color.WHITE);
            textArea.setBackground(Color.BLACK);
            textArea.setForeground(Color.WHITE);
        } else {
            // Chế độ sáng
            textField.setBackground(Color.WHITE);
            textField.setForeground(Color.BLACK);
            textArea.setBackground(Color.WHITE);
            textArea.setForeground(Color.BLACK);
        }
    }



    public void actionPerformed(ActionEvent evt) {
        String text = textField.getText();
        textArea.append(text + newline);
        textField.selectAll();
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("TextDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(600, 400));
        frame.add(new TextDemo());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    public static void addToTrie(Trie trie){
        try {
            File myObj = new File("D:\\IdeaProjects\\Nguyenhong.oop\\src\\CK\\in.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                int a = Integer.parseInt(myReader.nextLine());
                for(int i = 0; i < a; ++i){
                    String t = myReader.nextLine();
                    trie.insert(data, t);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        TextDemo.trie = new Trie();
        addToTrie(trie);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}