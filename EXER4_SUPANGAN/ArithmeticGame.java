import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

class WelcomeScreen extends JFrame {
    private JTextField nameField;

    public WelcomeScreen() {
        setTitle("Arithmetic Game");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(255, 239, 213)); 
        setLayout(new BorderLayout(10, 10));

        // Title Label
        JLabel titleLabel = new JLabel(" Arithmetic Game ", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 32)); // smaller so it fits nicely
        titleLabel.setForeground(new Color(139, 69, 19));
        add(titleLabel, BorderLayout.NORTH);

        // Center Panel for Name Entry (smaller and centered)
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(255, 239, 213));

        JLabel nameLabel = new JLabel("Enter your name:");
        nameLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        nameField = new JTextField();
        nameField.setMaximumSize(new Dimension(250, 35)); // smaller input box
        nameField.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        nameField.setHorizontalAlignment(JTextField.CENTER);
        nameField.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(Box.createVerticalStrut(30)); // spacing above
        centerPanel.add(nameLabel);
        centerPanel.add(Box.createVerticalStrut(10)); // small gap
        centerPanel.add(nameField);
        centerPanel.add(Box.createVerticalStrut(30)); // spacing below

        add(centerPanel, BorderLayout.CENTER);

        // Start Button
        JButton startButton = new JButton("Start Game");
        startButton.setBackground(new Color(255, 204, 153));
        startButton.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String playerName = nameField.getText().trim();
                if (playerName.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter your name before starting!", "Missing Name", JOptionPane.WARNING_MESSAGE);
                } else {
                    new ArithmeticGame(playerName);
                    dispose();
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(255, 239, 213));
        buttonPanel.add(startButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new WelcomeScreen());
    }
}


// ==================== ARITHMETIC GAME ====================
class ArithmeticGame extends JFrame {
    private JLabel num1Label, num2Label, operatorLabel, resultLabel, correctLabel, incorrectLabel;
    private JTextField answerField;
    private JButton submitButton;
    private JRadioButton level1, level2, level3;
    private JCheckBox addBox, subBox, mulBox, divBox, modBox;
    private int correct = 0, incorrect = 0, questionCount = 0;
    private final int TOTAL_QUESTIONS = 10;
    private int num1, num2, correctAnswer;
    private String currentOperator = "+";
    private Random rand = new Random();
    private String playerName;

    public ArithmeticGame(String playerName) {
        this.playerName = playerName;

        setTitle("Arithmetic Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 550);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(255, 250, 205));

        // Title
        JLabel titleLabel = new JLabel(" Let's Play, " + playerName + "! ", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 26));
        titleLabel.setForeground(new Color(139, 69, 19));
        add(titleLabel, BorderLayout.NORTH);

        // Equation Section
        JPanel equationPanel = new JPanel(new GridBagLayout());
        equationPanel.setBackground(new Color(255, 250, 205));
        GridBagConstraints eqGbc = new GridBagConstraints();
        eqGbc.gridx = 0;
        eqGbc.gridy = 0;
        eqGbc.anchor = GridBagConstraints.CENTER;

        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        innerPanel.setBackground(new Color(255, 250, 205));

        num1Label = new JLabel("0");
        operatorLabel = new JLabel("+");
        num2Label = new JLabel("0");
        resultLabel = new JLabel(" = ");
        answerField = new JTextField(6);

        Font eqFont = new Font("Comic Sans MS", Font.BOLD, 40);
        num1Label.setFont(eqFont);
        num2Label.setFont(eqFont);
        operatorLabel.setFont(eqFont);
        resultLabel.setFont(eqFont);
        answerField.setFont(new Font("Arial", Font.BOLD, 28));
        answerField.setHorizontalAlignment(JTextField.CENTER);

        innerPanel.add(num1Label);
        innerPanel.add(operatorLabel);
        innerPanel.add(num2Label);
        innerPanel.add(resultLabel);
        innerPanel.add(answerField);

        equationPanel.add(innerPanel, eqGbc);
        add(equationPanel, BorderLayout.CENTER);

        // Bottom Area
        JPanel bottomPanel = new JPanel(new GridLayout(1, 3, 15, 15));
        bottomPanel.setBackground(new Color(255, 250, 205));
        add(bottomPanel, BorderLayout.SOUTH);

        // Operators Panel
        JPanel operatorPanel = new JPanel(new GridLayout(6, 1));
        operatorPanel.setBackground(new Color(255, 250, 205));
        operatorPanel.setBorder(BorderFactory.createTitledBorder("⚙️ Choose Operators"));

        addBox = new JCheckBox("Addition (+)");
        subBox = new JCheckBox("Subtraction (-)");
        mulBox = new JCheckBox("Multiplication (×)");
        divBox = new JCheckBox("Division (÷)");
        modBox = new JCheckBox("Modulo (%)");

        for (JCheckBox box : new JCheckBox[]{addBox, subBox, mulBox, divBox, modBox}) {
            box.setBackground(new Color(255, 250, 205));
            box.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
            operatorPanel.add(box);
        }

        bottomPanel.add(operatorPanel);

        // Level Panel
        JPanel levelPanel = new JPanel(new GridLayout(3, 1));
        levelPanel.setBackground(new Color(255, 250, 205));
        levelPanel.setBorder(BorderFactory.createTitledBorder("🎚️ Select Level"));

        level1 = new JRadioButton("Level 1 (1–50)");
        level2 = new JRadioButton("Level 2 (51–100)");
        level3 = new JRadioButton("Level 3 (101–300)");
        ButtonGroup group = new ButtonGroup();
        group.add(level1);
        group.add(level2);
        group.add(level3);
        level1.setSelected(true);

        for (JRadioButton rb : new JRadioButton[]{level1, level2, level3}) {
            rb.setBackground(new Color(255, 250, 205));
            rb.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
            levelPanel.add(rb);
        }

        bottomPanel.add(levelPanel);

        // Right Panel (Submit + Score)
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(new Color(255, 250, 205));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        submitButton = new JButton("SUBMIT");
        submitButton.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        submitButton.setForeground(Color.BLACK);
        submitButton.setBackground(new Color(255, 218, 185));
        submitButton.setPreferredSize(new Dimension(140, 40));
        gbc.gridy = 0;
        rightPanel.add(submitButton, gbc);

        correctLabel = new JLabel(" Correct: 0", SwingConstants.CENTER);
        incorrectLabel = new JLabel(" Incorrect: 0", SwingConstants.CENTER);
        correctLabel.setFont(new Font("Arial", Font.BOLD, 16));
        incorrectLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridy = 1;
        rightPanel.add(correctLabel, gbc);
        gbc.gridy = 2;
        rightPanel.add(incorrectLabel, gbc);

        bottomPanel.add(rightPanel);

        generateQuestion();
        submitButton.addActionListener(e -> checkAnswer());

        setVisible(true);
    }

    private void generateQuestion() {
        if (questionCount >= TOTAL_QUESTIONS) {
            endGame();
            return;
        }

        int min = 1, max = 50;
        if (level2.isSelected()) { min = 51; max = 100; }
        else if (level3.isSelected()) { min = 101; max = 300; }

        num1 = rand.nextInt(max - min + 1) + min;
        num2 = rand.nextInt(max - min + 1) + min;

        java.util.List<String> ops = new java.util.ArrayList<>();
        if (addBox.isSelected()) ops.add("+");
        if (subBox.isSelected()) ops.add("-");
        if (mulBox.isSelected()) ops.add("*");
        if (divBox.isSelected()) ops.add("/");
        if (modBox.isSelected()) ops.add("%");
        if (ops.isEmpty()) ops.add("+");

        currentOperator = ops.get(rand.nextInt(ops.size()));

        switch (currentOperator) {
            case "+": correctAnswer = num1 + num2; break;
            case "-": correctAnswer = num1 - num2; break;
            case "*": correctAnswer = num1 * num2; break;
            case "/":
                while (num2 == 0) num2 = rand.nextInt(max - min + 1) + min;
                correctAnswer = num1 / num2;
                break;
            case "%":
                while (num2 == 0) num2 = rand.nextInt(max - min + 1) + min;
                correctAnswer = num1 % num2;
                break;
        }

        num1Label.setText(String.valueOf(num1));
        num2Label.setText(String.valueOf(num2));
        operatorLabel.setText(currentOperator);
        answerField.setText("");
        questionCount++;
    }

    private void checkAnswer() {
        try {
            int ans = Integer.parseInt(answerField.getText());
            if (ans == correctAnswer) {
                correct++;
                JOptionPane.showMessageDialog(this, "🎉 Great job, " + playerName + "! That's correct!", "Result", JOptionPane.INFORMATION_MESSAGE);
            } else {
                incorrect++;
                JOptionPane.showMessageDialog(this, "❌ Oops, " + playerName + "! The correct answer was " + correctAnswer, "Result", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number!", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        correctLabel.setText(" Correct: " + correct);
        incorrectLabel.setText(" Incorrect: " + incorrect);
        generateQuestion();
    }

    private void endGame() {
        String message;
        if (correct > incorrect) {
            message = "🏆 Congratulations, " + playerName + "! You finished the Arithmetic Game!\n"
                    + "Your Score: " + correct + "/" + TOTAL_QUESTIONS + "\nGreat job!";
        } else {
            message = "😢 Better luck next time, " + playerName + "!\n"
                    + "Your Score: " + correct + "/" + TOTAL_QUESTIONS;
        }
        JOptionPane.showMessageDialog(this, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);
        dispose();
        new WelcomeScreen(); 
    }
}