package controller.command;

import model.ICanvasModel;

/**
 * The command that print snapshots on the canvas.
 */
public class PrintSnapshots implements Command {

  private ICanvasModel canvas;

  /**
   * Instantiates a new Print snapshots.
   *
   * @param canvas the canvas
   */
  public PrintSnapshots(ICanvasModel canvas) {
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
