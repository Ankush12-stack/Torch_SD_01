import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class temperature extends JFrame {
    private JComboBox<String> fromComboBox;
    private JComboBox<String> toComboBox;
    private JTextField temperatureField;
    private JLabel resultLabel;

    public temperature() {
        setTitle("Temperature Converter");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        String[] temperatureScales = {"Celsius", "Fahrenheit", "Kelvin"};
        JLabel fromLabel = new JLabel("From:");
        JLabel toLabel = new JLabel("To:");
        JLabel temperatureLabel = new JLabel("Temperature:");
        fromComboBox = new JComboBox<>(temperatureScales);
        toComboBox = new JComboBox<>(temperatureScales);
        temperatureField = new JTextField(10);
        JButton convertButton = new JButton("Convert");
        resultLabel = new JLabel("");

        // Set layout
        setLayout(new GridLayout(4, 2, 5, 5));

        // Add components to the frame
        add(fromLabel);
        add(fromComboBox);
        add(toLabel);
        add(toComboBox);
        add(temperatureLabel);
        add(temperatureField);
        add(convertButton);
        add(resultLabel);

        // Add action listener to the convert button
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertTemperature();
            }
        });
    }

    private void convertTemperature() {
        String fromScale = (String) fromComboBox.getSelectedItem();
        String toScale = (String) toComboBox.getSelectedItem();
        double temperature;

        try {
            temperature = Double.parseDouble(temperatureField.getText());
        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid input!");
            return;
        }

        double result;

        if (fromScale.equals("Celsius") && toScale.equals("Fahrenheit")) {
            result = (temperature * 9 / 5) + 32;
        } else if (fromScale.equals("Fahrenheit") && toScale.equals("Celsius")) {
            result = (temperature - 32) * 5 / 9;
        } else if (fromScale.equals("Celsius") && toScale.equals("Kelvin")) {
            result = temperature + 273.15;
        } else if (fromScale.equals("Kelvin") && toScale.equals("Celsius")) {
            result = temperature - 273.15;
        } else if (fromScale.equals("Fahrenheit") && toScale.equals("Kelvin")) {
            result = (temperature - 32) * 5 / 9 + 273.15;
        } else if (fromScale.equals("Kelvin") && toScale.equals("Fahrenheit")) {
            result = (temperature - 273.15) * 9 / 5 + 32;
        } else {
            result = temperature;
        }

        resultLabel.setText(String.format("%.2f %s", result, toScale));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new temperature().setVisible(true);
            }
        });
    }
}
