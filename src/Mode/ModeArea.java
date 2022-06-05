package Mode;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;

public class ModeArea extends JPanel {
    private static int buttonSize = 80;
    public static int getButtonSize() {
        return buttonSize;
    }

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

        this.setLayout(null);
        this.setBounds(0, 0, buttonSize, buttonSize * Mode.values().length);
        for (int i = 0; i < Mode.values().length; i++) {
            buttons[i] = new ModeButton(Mode.values()[i]);
            group.add(buttons[i]);
            add(buttons[i]);
        }
        buttons[0].setSelected(true);
    }


}
