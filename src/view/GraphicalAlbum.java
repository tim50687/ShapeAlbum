package view;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.swing.JFrame;

public class GraphicalAlbum extends JFrame implements IGraphicalAlbum {

  // Panels
  private CanvasPanel canvasPanel;
  private ButtonsPanel buttonPanel;

  // Dimension for snapshot
  private static final int SNAPSHOT_WIDTH = 1600;
  private static final int SNAPSHOT_HEIGHT = 900;

  // Snapshots, make it to a list
  private final List<String> snapshots;

  public GraphicalAlbum(LinkedHashMap<String, String> snapshots) {
    // Frame setUp
    super();
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false); // Fixed size
    this.setLayout(null);
    this.setSize(SNAPSHOT_WIDTH, SNAPSHOT_HEIGHT);
    this.setLocationRelativeTo(null);
    this.getContentPane().setBackground(new Color(200, 255, 255));

    // Add snapshots to Graphical album
    this.snapshots = new ArrayList<>(snapshots.values());

    // Add panel on the frame
    this.buttonPanel = new ButtonsPanel();
    this.add(buttonPanel);
    // By default, put the fist snapshot on the canvas
    this.canvasPanel = new CanvasPanel(this.snapshots.get(0));
    this.add(canvasPanel);

    // Set frame visible to true
    this.setVisible(true);
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

}
