import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.Timer;

class WelcomeScreen extends JFrame {
    private JTextField nameField;

    public WelcomeScreen() {
        setTitle("Arithmetic Game");
        setSize(500, 300);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(255, 239, 213));
        setLayout(new BorderLayout(10, 10));

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showConfirmDialog(
                        WelcomeScreen.this,
                        "Are you sure you want to exit?",
                        "Exit Confirmation",
                        JOptionPane.YES_NO_OPTION
                );
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        JLabel titleLabel = new JLabel(" Arithmetic Game ", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 32));
        titleLabel.setForeground(new Color(139, 69, 19));
        add(titleLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(255, 239, 213));

        JLabel nameLabel = new JLabel("Enter your name:");
        nameLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        nameField = new JTextField();
        nameField.setMaximumSize(new Dimension(250, 35));
        nameField.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        nameField.setHorizontalAlignment(JTextField.CENTER);
        nameField.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(Box.createVerticalStrut(30));
        centerPanel.add(nameLabel);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(nameField);
        centerPanel.add(Box.createVerticalStrut(30));
        add(centerPanel, BorderLayout.CENTER);

        JButton startButton = new JButton("Start Game");
        startButton.setBackground(new Color(255, 204, 153));
        startButton.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        startButton.addActionListener(e -> {
            String playerName = nameField.getText().trim();
            if (playerName.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter your name before starting!", "Missing Name", JOptionPane.WARNING_MESSAGE);
            } else {
                new ArithmeticGame(playerName);
                dispose();
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

class ArithmeticGame extends JFrame {
    private JLabel num1Label, num2Label, operatorLabel, resultLabel, correctLabel, incorrectLabel;
    private JTextField answerField;
    private JButton submitButton, exitButton;
    private JRadioButton level1, level2, level3;
    private JRadioButton addBox, subBox, mulBox, divBox, modBox;
    private int correct = 0, incorrect = 0, questionCount = 0;
    private final int TOTAL_QUESTIONS = 10;
    private int num1, num2, correctAnswer;
    private String currentOperator = "?";
    private Random rand = new Random();
    private String playerName;
    private boolean timerStarted = false;
    private Timer timer;
    private int timeLeft = 10;
    private JLabel timerLabel;

    public ArithmeticGame(String playerName) {
        this.playerName = playerName;
        setTitle("Arithmetic Game");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(1000, 550);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(255, 250, 205));

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                confirmExit();
            }
        });

        JLabel titleLabel = new JLabel(" Let's Play, " + playerName + "! ", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 26));
        titleLabel.setForeground(new Color(139, 69, 19));
        add(titleLabel, BorderLayout.NORTH);

        JPanel equationPanel = new JPanel(new GridBagLayout());
        equationPanel.setBackground(new Color(255, 250, 205));

        num1Label = new JLabel("0");
        operatorLabel = new JLabel("?");
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

        JPanel innerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        innerPanel.setBackground(new Color(255, 250, 205));
        innerPanel.add(num1Label);
        innerPanel.add(operatorLabel);
        innerPanel.add(num2Label);
        innerPanel.add(resultLabel);
        innerPanel.add(answerField);
        equationPanel.add(innerPanel);
        add(equationPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new GridLayout(1, 3, 15, 15));
        bottomPanel.setBackground(new Color(255, 250, 205));
        add(bottomPanel, BorderLayout.SOUTH);

        // Operator Panel
        JPanel operatorPanel = new JPanel(new GridLayout(6, 1));
        operatorPanel.setBackground(new Color(255, 250, 205));
        operatorPanel.setBorder(BorderFactory.createTitledBorder("⚙️ Choose Operators"));

        addBox = new JRadioButton("Addition (+)");
        subBox = new JRadioButton("Subtraction (-)");
        mulBox = new JRadioButton("Multiplication (×)");
        divBox = new JRadioButton("Division (÷)");
        modBox = new JRadioButton("Modulo (%)");

        ButtonGroup operatorGroup = new ButtonGroup();
        for (JRadioButton box : new JRadioButton[]{addBox, subBox, mulBox, divBox, modBox}) {
            box.setBackground(new Color(255, 250, 205));
            box.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
            operatorGroup.add(box);
            operatorPanel.add(box);
        }
        bottomPanel.add(operatorPanel);

        ActionListener opListener = e -> {
            JRadioButton src = (JRadioButton) e.getSource();
            if (src == addBox) currentOperator = "+";
            else if (src == subBox) currentOperator = "-";
            else if (src == mulBox) currentOperator = "*";
            else if (src == divBox) currentOperator = "/";
            else if (src == modBox) currentOperator = "%";
            operatorLabel.setText(currentOperator);
            if (questionCount < TOTAL_QUESTIONS) generateQuestion();
        };

        addBox.addActionListener(opListener);
        subBox.addActionListener(opListener);
        mulBox.addActionListener(opListener);
        divBox.addActionListener(opListener);
        modBox.addActionListener(opListener);

        // Level Panel
        JPanel levelPanel = new JPanel(new GridLayout(4, 1));
        levelPanel.setBackground(new Color(255, 250, 205));
        levelPanel.setBorder(BorderFactory.createTitledBorder("🎚️ Select Level"));

        level1 = new JRadioButton("Level 1 (1–50)");
        level2 = new JRadioButton("Level 2 (51–100)");
        level3 = new JRadioButton("Level 3 (101–500)");
        ButtonGroup group = new ButtonGroup();
        group.add(level1);
        group.add(level2);
        group.add(level3);
        level1.setSelected(true);

        for (JRadioButton rb : new JRadioButton[]{level1, level2, level3}) {
            rb.setBackground(new Color(255, 250, 205));
            rb.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
            levelPanel.add(rb);
        }

        ActionListener levelListener = e -> {
            if (anyOperatorSelected()) {
                if (questionCount < TOTAL_QUESTIONS) generateQuestion();
            } else showDefaultEquation();
        };

        level1.addActionListener(levelListener);
        level2.addActionListener(levelListener);
        level3.addActionListener(levelListener);

        timerLabel = new JLabel(" Time left: 10s", SwingConstants.CENTER);
        timerLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
        levelPanel.add(timerLabel);
        bottomPanel.add(levelPanel);

        // Right Panel
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(new Color(255, 250, 205));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        submitButton = new JButton("SUBMIT");
        submitButton.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
        submitButton.setBackground(new Color(255, 218, 185));
        submitButton.setPreferredSize(new Dimension(140, 40));
        gbc.gridy = 0;
        rightPanel.add(submitButton, gbc);

        correctLabel = new JLabel(" Correct: 0", SwingConstants.CENTER);
        incorrectLabel = new JLabel(" Incorrect: 0", SwingConstants.CENTER);
        correctLabel.setFont(new Font("Arial", Font.BOLD, 25));
        incorrectLabel.setFont(new Font("Arial", Font.BOLD, 25));
        gbc.gridy = 1;
        rightPanel.add(correctLabel, gbc);
        gbc.gridy = 2;
        rightPanel.add(incorrectLabel, gbc);

        exitButton = new JButton("EXIT");
        exitButton.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
        exitButton.setBackground(new Color(255, 160, 122));
        exitButton.setPreferredSize(new Dimension(140, 40));
        exitButton.addActionListener(e -> confirmExit());
        gbc.gridy = 3;
        rightPanel.add(exitButton, gbc);
        bottomPanel.add(rightPanel);

        answerField.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (!timerStarted && anyOperatorSelected() && questionCount < TOTAL_QUESTIONS) {
                    startTimer();
                    timerStarted = true;
                }
            }
        });

        submitButton.addActionListener(e -> {
            if (!anyOperatorSelected()) {
                JOptionPane.showMessageDialog(this, "Please select an operator first!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (questionCount >= TOTAL_QUESTIONS) {
                endGame();
                return;
            }
            stopTimer();
            checkAnswer();
        });

        showDefaultEquation();
        setVisible(true);
    }

    private void confirmExit() {
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to exit?",
                "Exit Confirmation",
                JOptionPane.YES_NO_OPTION
        );
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void stopTimer() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
        timerStarted = false;
    }

    private void showDefaultEquation() {
        num1Label.setText("0");
        operatorLabel.setText("?");
        num2Label.setText("0");
    }

    private boolean anyOperatorSelected() {
        return addBox.isSelected() || subBox.isSelected() || mulBox.isSelected() || divBox.isSelected() || modBox.isSelected();
    }

    private void startTimer() {
        if (timer != null && timer.isRunning()) timer.stop();
        timeLeft = 10;
        timerLabel.setText(" Time left: 10s");
        timer = new Timer(1000, e -> {
            timeLeft--;
            timerLabel.setText(" Time left: " + timeLeft + "s");
            if (timeLeft <= 0) {
                ((Timer) e.getSource()).stop();
                questionCount++;
                incorrect++;
                incorrectLabel.setText(" Incorrect: " + incorrect);
                JOptionPane.showMessageDialog(this, "⏰ Time's up!", "Timeout", JOptionPane.WARNING_MESSAGE);
                if (questionCount >= TOTAL_QUESTIONS) {
                    endGame();
                } else {
                    generateQuestion();
                }
            }
        });
        timer.start();
    }

    private void generateQuestion() {
        if (!anyOperatorSelected()) {
            showDefaultEquation();
            return;
        }
        if (questionCount >= TOTAL_QUESTIONS) {
            endGame();
            return;
        }

        int min = 1, max = 50;
        if (level2.isSelected()) {
            min = 51;
            max = 100;
        } else if (level3.isSelected()) {
            min = 101;
            max = 500;
        }

        num1 = rand.nextInt(max - min + 1) + min;
        num2 = rand.nextInt(max - min + 1) + min;

        switch (currentOperator) {
            case "+": correctAnswer = num1 + num2; break;
            case "-": correctAnswer = num1 - num2; break;
            case "*": correctAnswer = num1 * num2; break;
            case "/": while (num2 == 0) num2 = rand.nextInt(max - min + 1) + min; correctAnswer = num1 / num2; break;
            case "%": while (num2 == 0) num2 = rand.nextInt(max - min + 1) + min; correctAnswer = num1 % num2; break;
            default: correctAnswer = 0; break;
        }

        num1Label.setText(String.valueOf(num1));
        operatorLabel.setText(currentOperator);
        num2Label.setText(String.valueOf(num2));
        answerField.setText("");
        timerStarted = false;
    }

    private void checkAnswer() {
        try {
            double ans = Double.parseDouble(answerField.getText());
            if ((int) ans == correctAnswer) {
                correct++;
                JOptionPane.showMessageDialog(this, "🎉 Correct!", "Result", JOptionPane.INFORMATION_MESSAGE);
            } else {
                incorrect++;
                JOptionPane.showMessageDialog(this, "❌ Incorrect! The correct answer was " + correctAnswer, "Result", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number!", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        correctLabel.setText(" Correct: " + correct);
        incorrectLabel.setText(" Incorrect: " + incorrect);

        questionCount++;

        if (questionCount >= TOTAL_QUESTIONS) {
            endGame();
        } else {
            generateQuestion();
        }
    }

    // ✅ Modified endGame() — starts a new round if player got 10/10 correct
    private void endGame() {
        if (timer != null) timer.stop();

        if (correct == TOTAL_QUESTIONS) {
            JOptionPane.showMessageDialog(this,
                    "🎉 Excellent job, " + playerName + "! You got all 10 correct!\nStarting a new game...",
                    "Level Complete", JOptionPane.INFORMATION_MESSAGE);

            correct = 0;
            incorrect = 0;
            questionCount = 0;
            correctLabel.setText("Correct: 0");
            incorrectLabel.setText("Incorrect: 0");
            generateQuestion();
            answerField.setEditable(true);
            submitButton.setEnabled(true);
            addBox.setEnabled(true);
            subBox.setEnabled(true);
            mulBox.setEnabled(true);
            divBox.setEnabled(true);
            modBox.setEnabled(true);
            level1.setEnabled(true);
            level2.setEnabled(true);
            level3.setEnabled(true);
            timer.start();
            return;
        }

        String msg = (correct > incorrect)
                ? "🏆 Congratulations, " + playerName + "! You finished!\nScore: " + correct + "/" + TOTAL_QUESTIONS
                : "😢 Better luck next time, " + playerName + "!\nScore: " + correct + "/" + TOTAL_QUESTIONS;

        JOptionPane.showMessageDialog(this, msg, "Game Over", JOptionPane.INFORMATION_MESSAGE);

        submitButton.setEnabled(false);
        answerField.setEditable(false);
        addBox.setEnabled(false);
        subBox.setEnabled(false);
        mulBox.setEnabled(false);
        divBox.setEnabled(false);
        modBox.setEnabled(false);
        level1.setEnabled(false);
        level2.setEnabled(false);
        level3.setEnabled(false);
        exitButton.setEnabled(true);
    }
}
