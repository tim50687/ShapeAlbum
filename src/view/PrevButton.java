package view;

import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * The type Previous button.
 */
public class PrevButton extends JButton {

  // Has the album to change its state
  private GraphicalAlbum graphicalAlbum;

  // Dimension for next button
  private static final int BUTTONS_X = 170;
  private static final int BUTTONS_Y = 2;
  private static final int BUTTONS_WIDTH = 70;
  private static final int BUTTONS_HEIGHT = 70;

  // Image path
  private static final String path = "../src/assets/buttons/previous.png";

  /**
   * Instantiates a new Prev button.
   *
   * @param graphicalAlbum the graphical album
   */
  public PrevButton(GraphicalAlbum graphicalAlbum) {
    this.setPreferredSize(new Dimension(BUTTONS_WIDTH, BUTTONS_HEIGHT)); // set preferred size
    this.setBorder(BorderFactory.createEtchedBorder());
    ImageIcon prev = new ImageIcon(path);
    this.setIcon(prev);
    this.graphicalAlbum = graphicalAlbum;
    // Make action listener
    this.addActionListener(e -> this.graphicalAlbum.goBack());
  }
}
