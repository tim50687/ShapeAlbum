package view;

import java.awt.Color;
import javax.swing.JPanel;

public class ButtonsPanel extends JPanel {

  // Dimension for button panel
  private static final int BUTTONS_PANEL_X = 0;
  private static final int BUTTONS_PANEL_Y = 700;
  private static final int BUTTONS_PANEL_WIDTH = 1600;
  private static final int BUTTONS_PANEL_HEIGHT = 200;

  // Background color for panel
  private static final int BUTTONS_PANEL_R = 255;
  private static final int BUTTONS_PANEL_G = 228;
  private static final int BUTTONS_PANEL_B = 225;

  // Buttons
  private final NextButton nextButton;
  private final PrevButton prevButton;
  private final SearchButton searchButton;
  private final QuitButton quitButton;

  public ButtonsPanel() {
    super();
    this.setBackground(new Color(BUTTONS_PANEL_R, BUTTONS_PANEL_G, BUTTONS_PANEL_B));
    this.setBounds(BUTTONS_PANEL_X, BUTTONS_PANEL_Y, BUTTONS_PANEL_WIDTH, BUTTONS_PANEL_HEIGHT);
    this.setLayout(null);

    // Add button on the panel
    nextButton = new NextButton();
    prevButton = new PrevButton();
    searchButton = new SearchButton();
    quitButton = new QuitButton();
    this.add(this.nextButton);
    this.add(this.prevButton);
    this.add(this.searchButton);
    this.add(this.quitButton);
  }
}
