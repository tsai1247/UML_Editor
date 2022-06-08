package Workspace.Mode;

import java.awt.event.ActionListener;

import javax.swing.JToggleButton;

import Workspace.Canvas.CanvasArea;
import Workspace.Mode.ModeArea.Mode;

public class ModeButton extends JToggleButton {
    public ModeButton(Mode mode) {
        super(mode.toString());
        this.setFocusable(false);
        this.addActionListener(new ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent e){
                ModeArea.getInstance().setCurrentMode(mode);
                CanvasArea.getInstance().ClearSelectedShape();
                CanvasArea.getInstance().ClearHighlightedLine();
                System.out.println(mode);
            }
        });
    }
    

}
