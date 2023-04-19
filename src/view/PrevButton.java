package view;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class PrevButton extends JButton {

  // Has the album to change its state
  private GraphicalAlbum graphicalAlbum;

  // Dimension for next button
  private static final int BUTTONS_X = 270;
  private static final int BUTTONS_Y = 2;
  private static final int BUTTONS_WIDTH = 70;
  private static final int BUTTONS_HEIGHT = 70;

  // Image path
  private static final String path = "./src/assets/buttons/previous.png";

  public PrevButton(GraphicalAlbum graphicalAlbum) {
    this.setBounds(BUTTONS_X, BUTTONS_Y, BUTTONS_WIDTH, BUTTONS_HEIGHT);
    this.setBorder(BorderFactory.createEtchedBorder());
    ImageIcon prev = new ImageIcon(path);
    this.setIcon(prev);
    this.graphicalAlbum = graphicalAlbum;
    // Make action listener
    this.addActionListener(e -> this.graphicalAlbum.goBack());
  }
}
