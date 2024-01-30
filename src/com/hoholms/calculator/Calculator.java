package com.hoholms.calculator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator implements ActionListener {

  public static final String FRAME_TITLE       = "Java Calculator";
  public static final String FONT_MENLO        = "Menlo";
  public static final String EMPTY_STRING      = "";
  public static final String DECIMAL_SEPARATOR = ".";
  public static final String EQUALS_SIGN       = "=";
  public static final String DEL_BUTTON_TEXT   = "DEL";
  public static final String CLR_BUTTON_TEXT   = "CLR";
  public static final String NEG_BUTTON_TEXT   = "+/-";
  public static final String ZERO_DECIMAL      = "0.";

  public static final char ADD      = '+';
  public static final char SUBTRACT = '-';
  public static final char MULTIPLY = '*';
  public static final char DIVIDE   = '/';

  JFrame     frame;
  JTextField textField;
  JButton[]  numbersButtons  = new JButton[10];
  JButton[]  functionButtons = new JButton[9];
  JButton    addButton;
  JButton    subButton;
  JButton    mulButton;
  JButton    divButton;
  JButton    decButton;
  JButton    equButton;
  JButton    delButton;
  JButton    clrButton;
  JButton    negButton;
  JPanel     panel;

  Font myFont = new Font(FONT_MENLO, Font.BOLD, 30);

  double num1 = 0;
  double num2 = 0;
  double result;
  char   operator;

  Calculator() {
    try {
      javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception ex) {
      System.err.println(ex.getMessage());
    }

    frame = new JFrame(FRAME_TITLE);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setSize(420, 550);
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);
    frame.setLayout(null);

    textField = new JTextField();
    textField.setBounds(50, 25, 300, 50);
    textField.setFont(myFont);
    textField.setEditable(false);

    addButton = new JButton(String.valueOf(ADD));
    subButton = new JButton(String.valueOf(SUBTRACT));
    mulButton = new JButton(String.valueOf(MULTIPLY));
    divButton = new JButton(String.valueOf(DIVIDE));
    decButton = new JButton(DECIMAL_SEPARATOR);
    equButton = new JButton(EQUALS_SIGN);
    delButton = new JButton(DEL_BUTTON_TEXT);
    clrButton = new JButton(CLR_BUTTON_TEXT);
    negButton = new JButton(NEG_BUTTON_TEXT);

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
    performInputModification(e);
    chooseCalcOperation(e);
    performCalculation(e);
    performClearActions(e);
  }

  private void performClearActions(final ActionEvent e) {
    if (e.getSource() == clrButton) {
      clearInput();
    }

    if (e.getSource() == delButton) {
      deleteLastChar();
    }
  }

  private void performInputModification(final ActionEvent e) {
    for (int i = 0; i < 10; ++i) {
      if (e.getSource() == numbersButtons[i]) {
        textField.setText(textField.getText().concat(String.valueOf(i)));
      }
    }

    if (e.getSource() == decButton) {
      setDecimal();
    }

    if (e.getSource() == negButton) {
      negateValue();
    }
  }

  private void chooseCalcOperation(final ActionEvent e) {
    if (e.getSource() == addButton) {
      setOperation(ADD);
    }

    if (e.getSource() == subButton) {
      setOperation(SUBTRACT);
    }

    if (e.getSource() == mulButton) {
      setOperation(MULTIPLY);
    }

    if (e.getSource() == divButton) {
      setOperation(DIVIDE);
    }
  }

  private void setDecimal() {
    if (textField.getText().isEmpty() || textField.getText() == null) {
      textField.setText(ZERO_DECIMAL);
    } else {
      if (!textField.getText().contains(DECIMAL_SEPARATOR)) {
        textField.setText(textField.getText().concat(DECIMAL_SEPARATOR));
      }
    }
  }

  private void negateValue() {
    if (textField.getText().isEmpty() || textField.getText() == null) {
      textField.setText(String.valueOf(SUBTRACT));
    } else {
      double tmp = Double.parseDouble(textField.getText());
      tmp *= -1;
      if (tmp != Math.floor(tmp)) {
        textField.setText(String.valueOf(tmp));
      } else {
        textField.setText(String.valueOf((int) tmp));
      }
    }
  }

  private void deleteLastChar() {
    String str = textField.getText();
    textField.setText(EMPTY_STRING);
    for (int i = 0; i < str.length() - 1; ++i) {
      textField.setText(textField.getText() + str.charAt(i));
    }
  }

  private void clearInput() {
    textField.setText(EMPTY_STRING);
    num1 = 0;
    num2 = 0;
    result = 0;
    operator = ' ';
  }

  private void performCalculation(final ActionEvent e) {
    if (e.getSource() == equButton && (!textField.getText().isEmpty() && textField.getText() != null)) {
      num2 = Double.parseDouble(textField.getText());
      switch (operator) {
        case ADD:
          result = num1 + num2;
          break;
        case SUBTRACT:
          result = num1 - num2;
          break;
        case MULTIPLY:
          result = num1 * num2;
          break;
        case DIVIDE:
          if (num2 == 0) {
            result = 0;
          } else {
            result = num1 / num2;
          }
          break;
        default:
      }
      num1 = result;
      if (result != Math.floor(result)) {
        textField.setText(String.valueOf(result));
      } else {
        textField.setText(String.valueOf((int) result));
      }
    }
  }

  private void setOperation(final char newOperator) {
    if (!textField.getText().isEmpty() && textField.getText() != null) {
      num1 = Double.parseDouble(textField.getText());
    } else {
      num1 = 0;
    }

    operator = newOperator;
    textField.setText(EMPTY_STRING);
  }

}
