package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * The type Graphical album.
 */
public class GraphicalAlbum extends JFrame implements IGraphicalAlbum {

  // Panels
  private CanvasPanel canvasPanel;
  private ButtonsPanel buttonPanel;

  // Dimension for snapshot
  private final int snapshot_width;
  private final int snapshot_height;

  // Background color for graphical album
  private static final int GRAPHICAL_ALBUM_PANEL_R = 200;
  private static final int GRAPHICAL_ALBUM_PANEL_G = 255;
  private static final int GRAPHICAL_ALBUM_PANEL_B = 255;


  // Snapshots, make it to a list
  private int currentSnapshotIndex = 0;
  private List<String> snapshotTimestamp; // for search button
  private List<String> snapshots;

  /**
   * Gets a list of timestamps from a list of snapshot strings.
   *
   * @param snapshots the list of snapshot strings to extract timestamps from
   * @return a list of timestamps extracted from the snapshot strings
   */
  private List<String> getSnapshotTimestamp(List<String> snapshots) {
    List<String> timestamps = new ArrayList<>();
    for (String snapshot : snapshots) {
      String[] lines = snapshot.split("\n");
      for (String line : lines) {
        // Get the timestamp
        if (line.startsWith("Timestamp")) {
          timestamps.add(line.split(": ")[1]);
        }
      }
    }
    return timestamps;
  }

  /**
   * Instantiates a new Graphical album.
   */
  public GraphicalAlbum(int width, int height) {
    // Frame setUp
    super();
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false); // Fixed size
    this.snapshot_width = width;
    this.snapshot_height = height;
    this.setPreferredSize(new Dimension(this.snapshot_width, this.snapshot_height));
    this.setLocationRelativeTo(null);
    this.getContentPane().setBackground(
        new Color(GRAPHICAL_ALBUM_PANEL_R, GRAPHICAL_ALBUM_PANEL_G, GRAPHICAL_ALBUM_PANEL_B));
    // Add button panel on the frame
    this.buttonPanel = new ButtonsPanel(this);
    this.add(buttonPanel, BorderLayout.SOUTH);

    this.setVisible(false);
    this.pack();
  }

  @Override
  public void setSnapshots(LinkedHashMap<String, String> snapshots) {
    // Add snapshots to Graphical album
    this.snapshots = new ArrayList<>(snapshots.values());
    this.snapshotTimestamp = getSnapshotTimestamp(this.snapshots);

    // Add canvas panel on the frame
    // By default, put the fist snapshot on the canvas
    this.canvasPanel = new CanvasPanel(this.snapshots.get(currentSnapshotIndex));
    this.add(canvasPanel, BorderLayout.CENTER);

    // Frame can be seen after add on the canvas panel
    this.setVisible(true);
    this.pack();
  }

  @Override
  public void goNext() {
    if (this.currentSnapshotIndex + 1 == this.snapshots.size()) {
      JOptionPane.showMessageDialog(this, "There is no next snapshot!", "Error Message",
          JOptionPane.ERROR_MESSAGE);
    } else {
      this.currentSnapshotIndex += 1;
      this.canvasPanel.setSnapshot(this.snapshots.get(currentSnapshotIndex));
      this.canvasPanel.repaint();
    }
  }

  @Override
  public void goBack() {
    if (this.currentSnapshotIndex - 1 < 0) {
      JOptionPane.showMessageDialog(this, "There is no previous snapshot!", "Error Message",
          JOptionPane.ERROR_MESSAGE);
    } else {
      this.currentSnapshotIndex -= 1;
      this.canvasPanel.setSnapshot(this.snapshots.get(currentSnapshotIndex));
      this.canvasPanel.repaint();
    }
  }

  @Override
  public void searchSnapshot() {
    // Make selections available
    Object[] possibilities = new Object[this.snapshots.size()];
    for (int i = 0; i < this.snapshots.size(); i++) {
      possibilities[i] = String.format("No.%d snapshot", i + 1);
    }
    // Get user's selection
    String answer = (String) JOptionPane.showInputDialog(
        this,
        "Pick one snapshot from it's timestamp!",
        "Snapshots selection",
        JOptionPane.PLAIN_MESSAGE,
        null,
        possibilities,
        possibilities[this.currentSnapshotIndex]);
    // Set the currentSnapshotIndex to the snapshot chosen by the user
    int index = -1;
    for (int i = 0; i < possibilities.length; i++) {
      if (possibilities[i] == answer) {
        index = i;
        break;
      }
    }
    this.currentSnapshotIndex = index;
    this.canvasPanel.setSnapshot(this.snapshots.get(currentSnapshotIndex));
    this.canvasPanel.repaint();
  }


}
