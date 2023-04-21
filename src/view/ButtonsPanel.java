package view;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JPanel;

/**
 * The type Buttons panel.
 */
public class ButtonsPanel extends JPanel {

  // Has the album to change its state
  private GraphicalAlbum graphicalAlbum;

  // Dimension for button panel
  private static final int BUTTONS_PANEL_X = 0;
  private static final int BUTTONS_PANEL_Y = 900;
  private static final int BUTTONS_PANEL_WIDTH = 1000;
  private static final int BUTTONS_PANEL_HEIGHT = 100;

  // Background color for panel
  private static final int BUTTONS_PANEL_R = 255;
  private static final int BUTTONS_PANEL_G = 228;
  private static final int BUTTONS_PANEL_B = 225;

  // Buttons
  private final NextButton nextButton;
  private final PrevButton prevButton;
  private final SearchButton searchButton;
  private final QuitButton quitButton;

  /**
   * Instantiates a new Buttons panel.
   *
   * @param graphicalAlbum the graphical album
   */
  public ButtonsPanel(GraphicalAlbum graphicalAlbum) {
    super(new FlowLayout(FlowLayout.CENTER, 10, 10));
    this.setBackground(new Color(BUTTONS_PANEL_R, BUTTONS_PANEL_G, BUTTONS_PANEL_B));
    this.graphicalAlbum = graphicalAlbum;

    // Add button on the panel
    nextButton = new NextButton(this.graphicalAlbum);
    prevButton = new PrevButton(this.graphicalAlbum);
    searchButton = new SearchButton(this.graphicalAlbum);
    quitButton = new QuitButton();

    this.add(this.prevButton);
    this.add(this.nextButton);
    this.add(this.searchButton);
    this.add(this.quitButton);
  }
}
