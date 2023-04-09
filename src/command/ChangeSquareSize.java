package command;

import album.Canvas;
import shape.Square;

/**
 * The command that change square size.
 */
public class ChangeSquareSize implements Command {

  private Canvas canvas;
  private String type;
  private String name;
  double newWidth;
  double previousWidth;

  /**
   * Instantiates a new Change square size command.
   *
   * @param canvas   the canvas
   * @param type     the type of the shape
   * @param name     the name of the shape
   * @param newWidth the new width
   */
  public ChangeSquareSize(Canvas canvas, String type, String name, double newWidth) {
    this.canvas = canvas;
    this.type = type;
    this.name = name;
    this.newWidth = newWidth;
  }

  @Override
  public void execute() {
    Square square = (Square) this.canvas.getShape(name);
    this.previousWidth = square.getWidth();
    this.canvas.changeShapeSize(this.type, this.name, this.newWidth);

  }

  @Override
  public void undo() {
    this.canvas.changeShapeSize(this.type, this.name, this.previousWidth);

  }
}
