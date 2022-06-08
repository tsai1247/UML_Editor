package Workspace.Menu;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import Workspace.Canvas.CanvasArea;

public class RenameWindow {
    JFrame frame = new JFrame("Rename");
    JTextField textField = new JTextField();
    JButton okButton = new JButton("OK");
    JButton cancelButton = new JButton("Cancel");

    public RenameWindow(String originalName) {
        frame.setLayout(null);

        // set text field
        textField.setText(originalName);
        textField.setBounds(10, 10, 200, 30);
        textField.setAlignmentY(JTextField.CENTER_ALIGNMENT);
        frame.add(textField);

        // set ok button
        okButton.setBounds(10, 50, 80, 30);
        frame.add(okButton);

        // set cancel button
        cancelButton.setBounds(100, 50, 80, 30);
        frame.add(cancelButton);

        // set the window size
        frame.setSize(300, 150);

        // add action listener to the OK button
        okButton.addActionListener(e->{
            OK_Clicked();
        });

        // add action listener to the Cancel button
        cancelButton.addActionListener(e->{
            Cancel_Clicked();
        });
    }

    public void ShowWindow() {
        frame.setVisible(true);
    }

    private void OK_Clicked()
    {
        String newName = textField.getText();
        CanvasArea.getInstance().getSelectedShapes().get(0).setName(newName);
        frame.dispose();
    }
    private void Cancel_Clicked()
    {
        frame.dispose();
    }
}
