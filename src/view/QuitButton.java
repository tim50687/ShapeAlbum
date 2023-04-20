package view;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * The QuitButton class represents a button used to exit the application when clicked.
 */
public class QuitButton extends JButton {

  // Dimension for next button
  private static final int BUTTONS_X = 770;
  private static final int BUTTONS_Y = 2;
  private static final int BUTTONS_WIDTH = 80;
  private static final int BUTTONS_HEIGHT = 80;

  // Image path
  private static final String path = "./src/assets/buttons/exit.png";

  /**
   * Instantiates a new Quit button.
   */
  public QuitButton() {
    this.setBounds(BUTTONS_X, BUTTONS_Y, BUTTONS_WIDTH, BUTTONS_HEIGHT);
    this.setBorder(BorderFactory.createEtchedBorder());
    ImageIcon quit = new ImageIcon(path);
    this.setIcon(quit);
    // Make action listener
    this.addActionListener(e -> System.exit(0));
  }
}
