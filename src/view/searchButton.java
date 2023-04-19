package view;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class searchButton extends JButton {

  // Dimension for next button
  private static final int BUTTONS_X = 910;
  private static final int BUTTONS_Y = 60;
  private static final int BUTTONS_WIDTH = 70;
  private static final int BUTTONS_HEIGHT = 70;

  // Image path
  private static final String path = "./src/assets/buttons/select.png";

  public searchButton() {
    this.setBounds(BUTTONS_X, BUTTONS_Y, BUTTONS_WIDTH, BUTTONS_HEIGHT);
    this.setBorder(BorderFactory.createEtchedBorder());
    ImageIcon next = new ImageIcon(path);
    this.setIcon(next);
  }
}
