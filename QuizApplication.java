import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizApplication {
    private String[][] questions = {
            {"What is the capital of France?", "Berlin", "Madrid", "Paris", "Rome", "3"},
            {"Which language is used for Android development?", "Swift", "Kotlin", "Python", "C++", "2"},
            {"Which planet is known as the Red Planet?", "Earth", "Mars", "Jupiter", "Venus", "2"},
            {"Which is the largest ocean?", "Atlantic", "Indian", "Arctic", "Pacific", "4"}
    };

    private int currentQuestionIndex = 0;
    private int score = 0;
    private int timeLimit = 10;  
    private int timeLeft;
    private Timer timer;

    private JFrame frame;
    private JLabel questionLabel;
    private JRadioButton[] options = new JRadioButton[4];
    private ButtonGroup optionsGroup;
    private JButton submitButton;
    private JLabel timerLabel;

    public QuizApplication() {
        setupUI();
        loadQuestion();
        startTimer();
    }

    private void setupUI() {
        frame = new JFrame("Quiz Application");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        questionLabel = new JLabel();
        questionLabel.setBounds(50, 30, 300, 20);
        frame.add(questionLabel);

        optionsGroup = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            options[i].setBounds(50, 60 + i * 30, 300, 20);
            optionsGroup.add(options[i]);
            frame.add(options[i]);
        }

        submitButton = new JButton("Submit");
        submitButton.setBounds(50, 200, 100, 30);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
            }
        });
        frame.add(submitButton);

        timerLabel = new JLabel("Time left: " + timeLimit + "s");
        timerLabel.setBounds(250, 200, 100, 30);
        frame.add(timerLabel);

        frame.setVisible(true);
    }

    private void loadQuestion() {
        if (currentQuestionIndex < questions.length) {
            String[] currentQuestion = questions[currentQuestionIndex];
            questionLabel.setText(currentQuestion[0]);
            for (int i = 0; i < 4; i++) {
                options[i].setText(currentQuestion[i + 1]);
            }
            optionsGroup.clearSelection();
        } else {
            showResult();
        }
    }

    private void checkAnswer() {
        String correctAnswer = questions[currentQuestionIndex][5];
        int selectedOption = -1;
        for (int i = 0; i < 4; i++) {
            if (options[i].isSelected()) {
                selectedOption = i + 1;
            }
        }

        if (String.valueOf(selectedOption).equals(correctAnswer)) {
            score++;
        }

        currentQuestionIndex++;
        loadQuestion();
        resetTimer();
    }

    private void startTimer() {
        timeLeft = timeLimit;
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeLeft--;
                timerLabel.setText("Time left: " + timeLeft + "s");
                if (timeLeft == 0) {
                    checkAnswer();
                }
            }
        });
        timer.start();
    }

    private void resetTimer() {
        timer.stop();
        timeLeft = timeLimit;
        timer.start();
    }

    private void showResult() {
        timer.stop();
        JOptionPane.showMessageDialog(frame, "Quiz finished!\nYour score: " + score + "/" + questions.length);
        frame.dispose();
    }

    public static void main(String[] args) {
        new QuizApplication();
    }
}

