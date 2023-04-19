package view;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GraphicalAlbum extends JFrame implements IGraphicalAlbum {

  // Panels
  private final CanvasPanel canvasPanel;
  private final ButtonsPanel buttonPanel;

  // Dimension for snapshot
  private static final int SNAPSHOT_WIDTH = 1600;
  private static final int SNAPSHOT_HEIGHT = 900;

  // Background color for graphical album
  private static final int GRAPHICAL_ALBUM_PANEL_R = 200;
  private static final int GRAPHICAL_ALBUM_PANEL_G = 255;
  private static final int GRAPHICAL_ALBUM_PANEL_B = 255;


  // Snapshots, make it to a list
  private int currentSnapshotIndex = 0;
  private final List<String> snapshotTimestamp; // for search button
  private final List<String> snapshots;

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

  public GraphicalAlbum(LinkedHashMap<String, String> snapshots) {
    // Frame setUp
    super();
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false); // Fixed size
    this.setLayout(null);
    this.setSize(SNAPSHOT_WIDTH, SNAPSHOT_HEIGHT);
    this.setLocationRelativeTo(null);
    this.getContentPane().setBackground(
        new Color(GRAPHICAL_ALBUM_PANEL_R, GRAPHICAL_ALBUM_PANEL_G, GRAPHICAL_ALBUM_PANEL_B));

    // Add snapshots to Graphical album
    this.snapshots = new ArrayList<>(snapshots.values());
    this.snapshotTimestamp = getSnapshotTimestamp(this.snapshots);

    // Add panel on the frame
    this.buttonPanel = new ButtonsPanel(this);
    this.add(buttonPanel);
    // By default, put the fist snapshot on the canvas
    this.canvasPanel = new CanvasPanel(this.snapshots.get(currentSnapshotIndex));
    this.add(canvasPanel);

    // Set frame visible to true
    this.setVisible(true);
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
      possibilities[i] = this.snapshotTimestamp.get(i);
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
    this.currentSnapshotIndex = this.snapshotTimestamp.indexOf(answer);
    this.canvasPanel.setSnapshot(this.snapshots.get(currentSnapshotIndex));
    this.canvasPanel.repaint();
  }


}
