package Mode;

import java.awt.event.ActionListener;

import javax.swing.JToggleButton;

import Mode.ModeArea.Mode;

public class ModeButton extends JToggleButton {
    public ModeButton(Mode mode) {
        super(mode.toString());
        this.setFocusable(false);
        this.addActionListener(new ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent e){
                ModeArea.getInstance().setCurrentMode(mode);
                System.out.println(mode);
            }
        });
    }
    

}
