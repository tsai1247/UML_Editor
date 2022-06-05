package Mode;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import java.awt.GridLayout;

public class ModeArea extends JPanel {
    enum Mode {
        SELECT,
        ASSOCIATION,
        GENERALIZATION,
        COMPOSITION,
        CLASS,
        USECASE
    }

    private ButtonGroup group = new ButtonGroup();
    private ModeButton[] buttons = new ModeButton[Mode.values().length];
    private Mode currentMode = Mode.SELECT;
    public Mode getCurrentMode() {
        return currentMode;
    }
    public void setCurrentMode(Mode mode) {
        currentMode = mode;
    }
    
    private static ModeArea instance = null;
    public static ModeArea getInstance() {
        if(instance == null) {
            instance = new ModeArea();
        }
        return instance;
    }

    private ModeArea() {
        super();
        this.setLayout(new GridLayout(Mode.values().length, 1));
        for (int i = 0; i < Mode.values().length; i++) {
            buttons[i] = new ModeButton(Mode.values()[i]);
            group.add(buttons[i]);
            add(buttons[i]);
        }
        buttons[0].setSelected(true);
    }


}
