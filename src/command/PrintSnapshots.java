package command;

import album.Canvas;

/**
 * The command that print snapshots on the canvas.
 */
public class PrintSnapshots implements Command {

  private Canvas canvas;

  /**
   * Instantiates a new Print snapshots.
   *
   * @param canvas the canvas
   */
  public PrintSnapshots(Canvas canvas) {
    this.canvas = canvas;
  }

  @Override
  public void execute() {
    this.canvas.printSnapshots();
  }

  @Override
  public void undo() {
    return;
  }

  @Override
  public String toString() {
    return "Print all snapshots!";
  }
}
