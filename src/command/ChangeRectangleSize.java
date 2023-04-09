package command;

import album.Canvas;
import shape.Rectangle;

/**
 * The command that change rectangle size.
 */
public class ChangeRectangleSize implements Command {

  private Canvas canvas;
  private String type;
  private String name;

  double newWidth;
  double previousWidth;

  double newLength;
  double previousLength;

  /**
   * Instantiates a new Change rectangle size.
   *
   * @param canvas    the canvas
   * @param type      the type of the shape
   * @param name      the name of the shape
   * @param newWidth  the new width
   * @param newLength the new length
   */
  public ChangeRectangleSize(Canvas canvas, String type, String name, double newWidth,
      double newLength) {
    this.canvas = canvas;
    this.type = type;
    this.name = name;
    this.newWidth = newWidth;
    this.newLength = newLength;
  }

  @Override
  public void execute() {
    Rectangle rectangle = (Rectangle) this.canvas.getShape(name);
    this.previousWidth = rectangle.getWidth();
    this.previousLength = rectangle.getLength();
    this.canvas.changeShapeSize(this.type, this.name, this.newLength, this.newWidth);
  }

  @Override
  public void undo() {
    this.canvas.changeShapeSize(this.type, this.name, this.previousLength, this.previousWidth);

  }
}
