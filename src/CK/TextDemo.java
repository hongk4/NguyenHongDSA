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
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Color;
import javax.swing.JColorChooser;

public class TextDemo extends JPanel implements ActionListener {
    protected JTextField textField;
    protected JTextArea textArea;
    private JComboBox<Integer> fontSizeComboBox;
    private JCheckBox darkModeCheckBox;
    private final static String newline = "\n";
    private static Trie trie;
    private Color selectedTextColor = Color.RED;
    private static final String FILE_PATH = "in.txt";
    private JButton addWordButton;

    public void runRealTime(JTextField textField) {
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void removeUpdate(DocumentEvent e) {
                addTextToArea();
            }
            private void addTextToArea() {
                System.out.println(textField.getText());
                List<Pair<String, String>> lt = trie.suggest(textField.getText());
                textArea.setText("");
                for (Pair<String, String> i : lt) {
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

        JPanel inputPanel = new JPanel(new BorderLayout());
        textField = new JTextField(20);
        this.runRealTime(textField);

        inputPanel.add(textField, BorderLayout.CENTER);

        textArea = new JTextArea(5, 20);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        JButton clearButton = new JButton("X");
        JButton saveButton = new JButton("Lưu");
        darkModeCheckBox = new JCheckBox("Chế độ màn hình tối");
        JButton colorButton = new JButton("màu chữ");
        colorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectColor();
            }
        });
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
                textField.setText("");
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveToFile();
            }
        });
        addWordButton = new JButton("Thêm từ mới");
        addWordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewWordToFile();
            }
        });

        inputPanel.add(addWordButton, BorderLayout.NORTH);

        inputPanel.add(clearButton, BorderLayout.EAST);
        inputPanel.add(saveButton, BorderLayout.WEST);

        fontSizeComboBox = new JComboBox<>();
        for (int i = 10; i <= 30; i++) {
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
        c.insets = new Insets(0, 5, 0, 0);
        add(colorButton, c);

        c.gridwidth = GridBagConstraints.REMAINDER;
        add(darkModeCheckBox, c);

        add(inputPanel, c);

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        add(scrollPane, c);
    }

    private void selectColor() {
        Color selectedColor = JColorChooser.showDialog(this, "Choose Text Color", selectedTextColor);
        if (selectedColor != null) {
            selectedTextColor = selectedColor;
            textField.setForeground(selectedTextColor);
            textArea.setForeground(selectedTextColor);
        }
    }

    private void addNewWordToFile() {
        String word = JOptionPane.showInputDialog(this, "Nhập từ mới:");
        if (word != null && !word.isEmpty()) {
            try {
                FileWriter writer = new FileWriter(FILE_PATH, true);
                writer.write(word + newline);
                int numberOfDefinitions = Integer.parseInt(JOptionPane.showInputDialog(this, "Nhập số lượng nghĩa của từ:"));
                writer.write(String.valueOf(numberOfDefinitions) + newline);
                for (int i = 0; i < numberOfDefinitions; ++i) {
                    String definition = JOptionPane.showInputDialog(this, "Nhập nghĩa thứ " + (i + 1) + " của từ:");
                    writer.write(definition + newline);
                }
                writer.close();
                JOptionPane.showMessageDialog(this, "Từ mới đã được thêm vào từ điển của bạn.");
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi khi thêm từ mới.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void saveToFile() {
        JFileChooser fileChooser = new JFileChooser();

        FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("Text Files (*.txt)", "txt");

        fileChooser.addChoosableFileFilter(txtFilter);

        fileChooser.setFileFilter(txtFilter);

        int result = fileChooser.showSaveDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fileChooser.getSelectedFile();

                String filePath = file.getAbsolutePath();
                FileNameExtensionFilter selectedFilter = (FileNameExtensionFilter) fileChooser.getFileFilter();
                if (!filePath.endsWith("." + selectedFilter.getExtensions()[0])) {
                    file = new File(filePath + "." + selectedFilter.getExtensions()[0]);
                }

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
            textField.setBackground(Color.BLACK);
            textField.setForeground(Color.WHITE);
            textArea.setBackground(Color.BLACK);
            textArea.setForeground(Color.WHITE);
        } else {
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

   
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("TextDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(600, 400));
        frame.add(new TextDemo());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void addToTrie(Trie trie) {
        try {
            File myObj = new File(FILE_PATH);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                int a = Integer.parseInt(myReader.nextLine());
                for (int i = 0; i < a; ++i) {
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
