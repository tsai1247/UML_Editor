package Workspace.Menu;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.event.*;

import Workspace.Canvas.CanvasArea;

import java.awt.event.*;

public class RenameWindow {
    
    JFrame frame = new JFrame("Rename");
    public RenameWindow(String originalName) {
        frame.setLayout(null);

        // create a text area
        JTextField textArea = new JTextField();
        textArea.setText(originalName);
        textArea.setBounds(10, 10, 200, 30);
        textArea.setAlignmentY(JTextField.CENTER_ALIGNMENT);
        frame.add(textArea);

        // create an OK button
        JButton okButton = new JButton("OK");
        okButton.setBounds(10, 50, 80, 30);
        frame.add(okButton);

        // create a cancel button
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(100, 50, 80, 30);
        frame.add(cancelButton);

        // set the window size
        frame.setSize(300, 150);

        // add action listener to the OK button
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newName = textArea.getText();
                CanvasArea.getInstance().getSelectedShapes().get(0).setName(newName);
                frame.dispose();
            }
        });

        // add action listener to the Cancel button
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }

    public void ShowWindow() {
        frame.setVisible(true);
    }

}
