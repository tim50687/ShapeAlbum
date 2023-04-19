package view;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GraphicalAlbum extends JFrame implements IGraphicalAlbum {

  // Create button
  private JButton next;
  private JButton back;
  private JButton search;
  private JButton quit;

  // Create Panels
  private JPanel timestamp;
  private JPanel canvas;
  private JPanel buttonPanel;

  // Dimension for snapshot
  private static final int SNAPSHOT_WIDTH = 1600;
  private static final int SNAPSHOT_HEIGHT = 900;

  public GraphicalAlbum() {
    // Frame setUp
    super();
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
    this.setResizable(false); // Fixed size
    this.setLayout(null);
    this.setSize(SNAPSHOT_WIDTH, SNAPSHOT_HEIGHT);
    this.getContentPane().setBackground(new Color(200, 255, 255));

    // Panel Setup
    buttonPanel = new JPanel();
    buttonPanel.setBackground(new Color(255, 228, 225));
    buttonPanel.setBounds(0, 700, 1600, 200);
    buttonPanel.setLayout(null);

    canvas = new JPanel();
    canvas.setBackground(new Color(200, 255, 255));
    canvas.setBounds(0, 50, 1600, 800);
    canvas.setLayout(null);

    timestamp = new JPanel();
    timestamp.setBackground(new Color(255, 228, 225));
    timestamp.setBounds(0, 0, 1600, 50);
    timestamp.setLayout(null);

    // Button setUp
    this.next = new JButton();
    this.next.setBounds(590, 60, 70, 70);
    this.next.setBorder(BorderFactory.createEtchedBorder());
    ImageIcon next = new ImageIcon("./src/assets/buttons/next.png");
    this.next.setIcon(next);

    this.back = new JButton();
    this.back.setBounds(270, 60, 70, 70);
    this.back.setBorder(BorderFactory.createEtchedBorder());
    ImageIcon back = new ImageIcon("./src/assets/buttons/previous.png");
    this.back.setIcon(back);

    this.search = new JButton();
    this.search.setBounds(910, 60, 70, 70);
    this.search.setBorder(BorderFactory.createEtchedBorder());
    ImageIcon search = new ImageIcon("./src/assets/buttons/select.png");
    this.search.setIcon(search);

    this.quit = new JButton();
    this.quit.setBounds(1230, 60, 80, 80);
    this.quit.setBorder(BorderFactory.createEtchedBorder());
    ImageIcon quit = new ImageIcon("./src/assets/buttons/exit.png");
    this.quit.setIcon(quit);

    // Add button on buton panel
    buttonPanel.add(this.next);
    buttonPanel.add(this.back);
    buttonPanel.add(this.search);
    buttonPanel.add(this.quit);

    // Add panel on the frame
    this.add(buttonPanel);
    this.add(canvas);
    this.add(timestamp);
  }

  @Override
  public void displaySnapshot(String snapshot) {

  }

  @Override
  public void goNext() {

  }

  @Override
  public void goBack() {

  }

  @Override
  public void searchSnapshot() {

  }

  @Override
  public void displayError() {

  }

  @Override
  public void quit() {

  }
}
