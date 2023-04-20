package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 * The type Canvas panel.
 */
public class CanvasPanel extends JPanel {

  // Dimension for canvas panel
  private static final int CANVAS_PANEL_X = 0;
  private static final int CANVAS_PANEL_Y = 0;
  private static final int CANVAS_PANEL_WIDTH = 1600;
  private static final int CANVAS_PANEL_HEIGHT = 800;

  // Background color for panel
  private static final int CANVAS_PANEL_R = 200;
  private static final int CANVAS_PANEL_G = 255;
  private static final int CANVAS_PANEL_B = 255;

  // Timestamp
  private static final int TIMESTAMP_X = 1200;
  private static final int TIMESTAMP_Y = 30;

  // Description
  private static final int DESCRIPTION_X = 0;
  private static final int DESCRIPTION_Y = 30;

  // Font size
  private static final int FONT_SIZE = 30;

  // Snapshot
  private String snapshot;

  /**
   * Instantiates a new Canvas panel.
   *
   * @param snapshot the snapshot
   */
  public CanvasPanel(String snapshot) {
    super();
    this.setBackground(new Color(CANVAS_PANEL_R, CANVAS_PANEL_G, CANVAS_PANEL_B));
    this.setBounds(CANVAS_PANEL_X, CANVAS_PANEL_Y, CANVAS_PANEL_WIDTH, CANVAS_PANEL_HEIGHT);
    this.setLayout(null);

    // Get the snapshot
    this.snapshot = snapshot;
  }

  /**
   * Adds the command to the graphical album by parsing the given snapshots and drawing shapes on
   * the provided Graphics2D object.
   *
   * @param snapshots  A string representation of the snapshots to be parsed and drawn on the
   *                   graphical album.
   * @param graphics2D The Graphics2D object on which to draw the parsed shapes.
   */
  private void addCommandToGraphical(String snapshots, Graphics2D graphics2D) {
    // Do the command
    int count = 0;
    String timestamp = "";
    String description = "";
    String type = "";
    int x = 0;
    int y = 0;
    int width = 0;
    int height = 0;
    int r = 0;
    int g = 0;
    int b = 0;

    // Put the shape in snapshot to the web album
    String[] lines = snapshots.split("\n");
    for (String line : lines) {
      // Get the timestamp
      if (line.startsWith("Timestamp")) {
        timestamp = line.split(": ")[1];
        // Get the type of the shape
      } else if (line.startsWith("Description")) {
        // Check if it is an empty description
        if (line.split(": ").length == 1) {
          description = "";
        } else {
          description = line.split(": ")[1];
        }
        // Draw timestamp
        graphics2D.setPaint(Color.black);
        graphics2D.setFont(new Font("Garamond", Font.PLAIN, FONT_SIZE));
        graphics2D.drawString(timestamp, TIMESTAMP_X, TIMESTAMP_Y);
      } else if (line.startsWith("Type")) {
        type = line.split(": ")[1];
        // Get the information of the shape
      } else if (line.startsWith("Upper Left")) {
        String[] parts = line.split(": ")[1].replace("(", "").replace(")", "").split(", ");
        x = Integer.parseInt(parts[0]);
        y = Integer.parseInt(parts[1]);
        width = Integer.parseInt(line.split(": ")[2].split(",")[0]);
        height = Integer.parseInt(line.split(": ")[3].split(",")[0]);
        r = Integer.parseInt(
            line.split(": ")[4].replace("(", "").replace(")", "").split(", ")[0]);
        g = Integer.parseInt(
            line.split(": ")[4].replace("(", "").replace(")", "").split(", ")[1]);
        b = Integer.parseInt(
            line.split(": ")[4].replace("(", "").replace(")", "").split(", ")[2]);
      } else if (line.startsWith("---")) {
        // if reach "--------------------", put a shape on web
        graphics2D.setPaint(new Color(r, g, b));
        String shape = "";
        if (type.equalsIgnoreCase("rectangle")) {
          graphics2D.fillRect(x, y, width, height);
        } else if (type.equalsIgnoreCase("Oval")) {
          graphics2D.fillOval(x, y, width, height);
        }
      }
      // Draw Description
      graphics2D.setPaint(Color.black);
      graphics2D.setFont(new Font("Garamond", Font.PLAIN, FONT_SIZE));
      graphics2D.drawString(description, DESCRIPTION_X, DESCRIPTION_Y);
    }
  }


  /**
   * Sets snapshot.
   *
   * @param snapshot the snapshot
   */
  public void setSnapshot(String snapshot) {
    this.snapshot = snapshot;
  }

  @Override
  public void paint(Graphics graphics) {
    super.paint(graphics);
    Graphics2D graphics2D = (Graphics2D) graphics;
    addCommandToGraphical(this.snapshot, graphics2D);
  }

}
