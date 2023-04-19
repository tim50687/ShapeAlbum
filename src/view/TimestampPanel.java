package view;

import java.awt.Color;
import javax.swing.JPanel;

public class TimestampPanel extends JPanel {

  // Dimension for canvas panel
  private static final int TIMESTAMP_PANEL_X = 0;
  private static final int TIMESTAMP_PANEL_Y = 0;
  private static final int TIMESTAMP_PANEL_WIDTH = 1600;
  private static final int TIMESTAMP_PANEL_HEIGHT = 50;

  // Background color for panel
  private static final int TIMESTAMP_PANEL_R = 255;
  private static final int TIMESTAMP_PANEL_G = 228;
  private static final int TIMESTAMP_PANEL_B = 225;

  public TimestampPanel() {
    super();
    this.setBackground(new Color(TIMESTAMP_PANEL_R, TIMESTAMP_PANEL_G, TIMESTAMP_PANEL_B));
    this.setBounds(TIMESTAMP_PANEL_X, TIMESTAMP_PANEL_Y, TIMESTAMP_PANEL_WIDTH,
        TIMESTAMP_PANEL_HEIGHT);
    this.setLayout(null);
  }

}
