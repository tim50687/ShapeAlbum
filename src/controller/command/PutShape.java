package controller.command;

import model.ICanvasModel;
import model.shape.IShape;

/**
 * The command that put shape on the canvas.
 */
public class PutShape implements Command {

  private ICanvasModel canvas;
  private String name;
  private IShape shape;


  /**
   * Instantiates a new Put shape.
   *
   * @param canvas the canvas
   * @param name   the name
   * @param shape  the shape
   */
  public PutShape(ICanvasModel canvas, String name, IShape shape) {
    this.canvas = canvas;
    this.name = name;
    this.shape = shape;
  }

  @Override
  public void execute() {
    this.canvas.putShape(this.name, this.shape);
  }

  @Override
  public void undo() {
    this.canvas.removeShape(this.name);
  }

  @Override
  public String toString() {
    return String.format("Put %s on canvas.", this.name);
  }
}
