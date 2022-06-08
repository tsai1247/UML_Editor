package Workspace.Mode;
import javax.swing.JToggleButton;
import Workspace.Canvas.CanvasArea;
import Workspace.Mode.ModeArea.Mode;

public class ModeButton extends JToggleButton {
    public ModeButton(Mode mode) {
        super(mode.toString());
        this.setFocusable(false);
        this.addActionListener(e->{
                ButtonClicked(mode);
        });
    }

    private void ButtonClicked(Mode mode) {
        ModeArea.getInstance().setCurrentMode(mode);
        CanvasArea.getInstance().ClearSelectedShape();
        CanvasArea.getInstance().ClearHighlightedLine();
    }
}
