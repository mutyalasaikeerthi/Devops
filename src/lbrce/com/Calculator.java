package lbrce.com;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame {
    private JTextField display;
    private String currentInput = "";
    private String operator = "";
    private double firstOperand = 0;

    public Calculator() {
        // Set up the frame
        setTitle("Simple Calculator");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Create the display panel at the top
        display = new JTextField();
        display.setFont(new Font("Arial", Font.PLAIN, 30));
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        add(display, BorderLayout.NORTH);

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+"
        };

        // Create the buttons and add them to the panel
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.PLAIN, 24));
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("=")) {
                // Perform calculation when "=" is pressed
                calculate();
            } else if (command.equals("/") || command.equals("*") || command.equals("-") || command.equals("+")) {
                // Save the first operand and the operator
                if (!currentInput.isEmpty()) {
                    firstOperand = Double.parseDouble(currentInput);
                    currentInput = "";
                }
                operator = command;
            } else {
                // Append the number or decimal point to the current input
                currentInput += command;
            }

            // Update the display
            display.setText(currentInput);
        }
    }

    private void calculate() {
        double secondOperand = Double.parseDouble(currentInput);

        switch (operator) {
            case "+":
                currentInput = String.valueOf(firstOperand + secondOperand);
                break;
            case "-":
                currentInput = String.valueOf(firstOperand - secondOperand);
                break;
            case "*":
                currentInput = String.valueOf(firstOperand * secondOperand);
                break;
            case "/":
                if (secondOperand != 0) {
                    currentInput = String.valueOf(firstOperand / secondOperand);
                } else {
                    currentInput = "Error";  // Handle division by zero
                }
                break;
        }

        operator = "";
        firstOperand = 0;
        display.setText(currentInput);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculator calculator = new Calculator();
            calculator.setVisible(true);
        });
    }
}
