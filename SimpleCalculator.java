import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleCalculator extends JFrame implements ActionListener {

    private JTextField display;
    private JPanel panel;
    private String[] buttonLabels = {
        "1", "2", "3", "4", "5", "6",
        "7", "8", "9", "0", "+", "-",
        "*", "/", "=", "%", "Mod", "Exit"
    };
    private JButton[] buttons;

    public SimpleCalculator() {
        setTitle("Simple Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10)); 

        display = new JTextField();
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setPreferredSize(new Dimension(0, 50)); 
        add(display, BorderLayout.NORTH);

        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 6, 5, 5));

        buttons = new JButton[buttonLabels.length];
        for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].setPreferredSize(new Dimension(50, 50)); 
            buttons[i].addActionListener(this);
            panel.add(buttons[i]);
        }

        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Exit")) {
            System.exit(0);
        } else if (command.equals("=")) {
            try {
                display.setText(evaluate(display.getText()));
            } catch (Exception ex) {
                display.setText("Error");
            }
        } else {
            display.setText(display.getText() + command);
        }
    }

    private String evaluate(String expression) {
        String[] tokens = expression.split(" ");
        double result = 0;
        String operator = "";

        for (String token : tokens) {
            if (token.matches("[0-9]+")) {
                if (operator.isEmpty()) {
                    result = Double.parseDouble(token);
                } else {
                    switch (operator) {
                        case "+":
                            result += Double.parseDouble(token);
                            break;
                        case "-":
                            result -= Double.parseDouble(token);
                            break;
                        case "*":
                            result *= Double.parseDouble(token);
                            break;
                        case "/":
                            result /= Double.parseDouble(token);
                            break;
                        case "%":
                            result %= Double.parseDouble(token);
                            break;
                        case "Mod":
                            result %= Double.parseDouble(token);
                            break;
                    }
                }
            } else {
                operator = token;
            }
        }

        return String.valueOf(result);
    }

    public static void main(String[] args) {
        new SimpleCalculator();
    }
}
