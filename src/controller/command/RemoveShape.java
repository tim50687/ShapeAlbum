package controller.command;

import album.Canvas;
import shape.IShape;

/**
 * The command that Remove shape on the canvas.
 */
public class RemoveShape implements Command {

  private Canvas canvas;
  private String name;
  private IShape shape;

  /**
   * Instantiates a new Remove shape.
   *
   * @param canvas the canvas
   * @param name   the name of the shape
   */
  public RemoveShape(Canvas canvas, String name) {
    this.canvas = canvas;
    this.name = name;
    this.shape = this.canvas.getShape(this.name);
  }


  @Override
  public void execute() {
    this.canvas.removeShape(this.name);
  }

  @Override
  public void undo() {
    this.canvas.putShape(this.name, this.shape);
  }

  @Override
  public String toString() {
    return String.format("Remove %s from canvas.", this.name);
  }

}
