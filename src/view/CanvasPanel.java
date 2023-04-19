package view;

import java.awt.Color;
import javax.swing.JPanel;

public class CanvasPanel extends JPanel {

  // Dimension for canvas panel
  private static final int CANVAS_PANEL_X = 0;
  private static final int CANVAS_PANEL_Y = 50;
  private static final int CANVAS_PANEL_WIDTH = 1600;
  private static final int CANVAS_PANEL_HEIGHT = 650;

  // Background color for panel
  private static final int CANVAS_PANEL_R = 200;
  private static final int CANVAS_PANEL_G = 255;
  private static final int CANVAS_PANEL_B = 255;

  public CanvasPanel() {
    super();
    this.setBackground(new Color(CANVAS_PANEL_R, CANVAS_PANEL_G, CANVAS_PANEL_B));
    this.setBounds(CANVAS_PANEL_X, CANVAS_PANEL_Y, CANVAS_PANEL_WIDTH, CANVAS_PANEL_HEIGHT);
    this.setLayout(null);
  }
}
