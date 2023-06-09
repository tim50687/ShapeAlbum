package view;

import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * The type Search button.
 */
public class SearchButton extends JButton {

  // Has the album to change its state
  private GraphicalAlbum graphicalAlbum;

  // Dimension for next button
  private static final int BUTTONS_X = 570;
  private static final int BUTTONS_Y = 2;
  private static final int BUTTONS_WIDTH = 70;
  private static final int BUTTONS_HEIGHT = 70;

  // Image path
  private static final String path = "../src/assets/buttons/select.png";

  /**
   * Instantiates a new Search button.
   *
   * @param graphicalAlbum the graphical album
   */
  public SearchButton(GraphicalAlbum graphicalAlbum) {
    this.setPreferredSize(new Dimension(BUTTONS_WIDTH, BUTTONS_HEIGHT)); // set preferred size
    this.setBorder(BorderFactory.createEtchedBorder());
    ImageIcon search = new ImageIcon(path);
    this.setIcon(search);
    this.graphicalAlbum = graphicalAlbum;
    // Make action listener
    this.addActionListener(e -> this.graphicalAlbum.searchSnapshot());
  }
}
