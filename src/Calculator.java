import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator implements ActionListener {

    JFrame frame;
    JTextField textField;
    JButton[] numbersButtons = new JButton[10];
    JButton[] functionButtons = new JButton[9];
    JButton addButton, subButton, mulButton, divButton,
            decButton, equButton, delButton, clrButton, negButton;
    JPanel panel;

    Font myFont = new Font("Menlo", Font.BOLD, 30);

    double num1 = 0, num2 = 0, result;
    char operator;

    Calculator() {
        try {
            javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            System.out.println(ex);
        }

        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 550);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        textField = new JTextField();
        textField.setBounds(50, 25, 300, 50);
        textField.setFont(myFont);
        textField.setEditable(false);

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("DEL");
        clrButton = new JButton("CLR");
        negButton = new JButton("+/-");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = negButton;

        for (JButton btn : functionButtons) {
            btn.addActionListener(this);
            btn.setFont(myFont);
            btn.setFocusable(false);
        }

        for (int i = 0; i < 10; ++i) {
            numbersButtons[i] = new JButton(String.valueOf(i));
            numbersButtons[i].addActionListener(this);
            numbersButtons[i].setFont(myFont);
            numbersButtons[i].setFocusable(false);

        }

        negButton.setBounds(50, 430, 100, 50);
        delButton.setBounds(150, 430, 100, 50);
        clrButton.setBounds(250, 430, 100, 50);

        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        panel.add(numbersButtons[7]);
        panel.add(numbersButtons[8]);
        panel.add(numbersButtons[9]);
        panel.add(addButton);
        panel.add(numbersButtons[4]);
        panel.add(numbersButtons[5]);
        panel.add(numbersButtons[6]);
        panel.add(subButton);
        panel.add(numbersButtons[1]);
        panel.add(numbersButtons[2]);
        panel.add(numbersButtons[3]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numbersButtons[0]);
        panel.add(equButton);
        panel.add(divButton);

        frame.add(panel);
        frame.add(negButton);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(textField);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; ++i) {
            if (e.getSource() == numbersButtons[i]) {
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }
        if (e.getSource() == decButton) {
            if (textField.getText().equals("") || textField.getText() == null)
                textField.setText("0.");
            else {
                if (!textField.getText().contains("."))
                    textField.setText(textField.getText().concat("."));
            }
        }
        if (e.getSource() == addButton) {
            if (!textField.getText().equals("") && textField.getText() != null)
                num1 = Double.parseDouble(textField.getText());
            else
                num1 = 0;
            operator = '+';
            textField.setText("");
        }
        if (e.getSource() == subButton) {
            if (!textField.getText().equals("") && textField.getText() != null)
                num1 = Double.parseDouble(textField.getText());
            else num1 = 0;
            operator = '-';
            textField.setText("");
        }
        if (e.getSource() == mulButton) {
            if (!textField.getText().equals("") && textField.getText() != null)
                num1 = Double.parseDouble(textField.getText());
            else
                num1 = 0;
            operator = '*';
            textField.setText("");
        }
        if (e.getSource() == divButton) {
            if (!textField.getText().equals("") && textField.getText() != null)
                num1 = Double.parseDouble(textField.getText());
            else num1 = 0;
            operator = '/';
            textField.setText("");
        }
        if (e.getSource() == equButton) {
            if (!textField.getText().equals("") && textField.getText() != null) {
                num2 = Double.parseDouble(textField.getText());
                switch (operator) {
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    case '*':
                        result = num1 * num2;
                        break;
                    case '/':
                        if (num2 == 0) result = 0;
                        else result = num1 / num2;
                        break;
                }
                num1 = result;
                if (result != Math.floor(result))
                    textField.setText(String.valueOf(result));
                else
                    textField.setText(String.valueOf((int) result));
            }
        }
        if (e.getSource() == clrButton) {
            textField.setText("");
            num1 = 0;
            num2 = 0;
            result = 0;
            operator = ' ';
        }
        if (e.getSource() == delButton) {
            String str = textField.getText();
            textField.setText("");
            for (int i = 0; i < str.length() - 1; ++i) {
                textField.setText(textField.getText() + str.charAt(i));
            }
        }
        if (e.getSource() == negButton) {
            if (textField.getText().equals("") || textField.getText() == null)
                textField.setText("-");
            else {
                double tmp = Double.parseDouble(textField.getText());
                tmp *= -1;
                if (tmp != Math.floor(tmp))
                    textField.setText(String.valueOf(tmp));
                else
                    textField.setText(String.valueOf((int) tmp));
            }
        }
    }
}
