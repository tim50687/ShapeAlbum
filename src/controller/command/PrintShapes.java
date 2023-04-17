package controller.command;

import model.Canvas;

/**
 * The command that print shapes on the canvas.
 */
public class PrintShapes implements Command {

  private Canvas canvas;

  /**
   * Instantiates a new Print shapes.
   *
   * @param canvas the canvas
   */
  public PrintShapes(Canvas canvas) {
    this.canvas = canvas;
  }

  @Override
  public void execute() {
    this.canvas.printShapes();
  }

  @Override
  public void undo() {
    return;
  }

  @Override
  public String toString() {
    return "Print all shapes!";
  }
}
