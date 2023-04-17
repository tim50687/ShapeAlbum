package controller.command;

import java.security.NoSuchAlgorithmException;
import model.Canvas;

/**
 * The command that take snapshot of the canvas to record current status.
 */
public class SnapShotCanvas implements Command {

  private Canvas canvas;
  private String description;
  private String ID;

  /**
   * Instantiates a new Snap shot canvas.
   *
   * @param canvas      the canvas
   * @param description the description
   */
  public SnapShotCanvas(Canvas canvas, String description) {
    this.ID = "";
    this.canvas = canvas;
    this.description = description;
  }

  @Override
  public void execute() {
    try {
      this.canvas.takeSnapshot(this.description, this.canvas.getAllShape());
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void undo() {
    this.canvas.unTakeSnapshot();
  }

  @Override
  public String toString() {
    return "Take a snapshot!";
  }
}



