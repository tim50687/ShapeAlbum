package command;

import album.Canvas;
import shape.IShape;

/**
 * The command that put shape on the canvas.
 */
public class PutShape implements Command {

  private Canvas canvas;
  private String type;
  private String name;
  private IShape shape;

  /**
   * Instantiates a new Put shape.
   *
   * @param canvas the canvas
   * @param type   the type of the shape
   * @param name   the name of the shape
   * @param shape  the shape
   */
  public PutShape(Canvas canvas, String type, String name, IShape shape) {
    this.canvas = canvas;
    this.type = type;
    this.name = name;
    this.shape = shape;
  }

  @Override
  public void execute() {
    this.canvas.putShape(this.type, this.name, this.shape);
  }

  @Override
  public void undo() {
    this.canvas.removeShape(this.name);
  }
}
