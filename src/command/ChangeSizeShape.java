package command;

import album.Canvas;
import shape.IShape;

/**
 * The command that change rectangle size.
 */
public class ChangeSizeShape implements Command {

  private Canvas canvas;
  private String name;

  /**
   * The New width.
   */
  int newWidth;
  /**
   * The Previous width.
   */
  int previousWidth;


  int newHeight;

  int previousHeight;


  /**
   * Instantiates a new Change rectangle size.
   *
   * @param canvas    the canvas
   * @param name      the name
   * @param newWidth  the new width
   * @param newHeight the new length
   */
  public ChangeSizeShape(Canvas canvas, String name, int newWidth,
      int newHeight) {
    this.canvas = canvas;
    this.name = name;
    this.newWidth = newWidth;
    this.newHeight = newHeight;
  }

  @Override
  public void execute() {
    IShape shape = this.canvas.getShape(name);
    this.previousWidth = shape.getWidth();
    this.previousHeight = shape.getHeight();
    this.canvas.changeShapeSize(this.name, this.newWidth, this.newHeight);
  }

  @Override
  public void undo() {
    this.canvas.changeShapeSize(this.name, this.previousWidth, this.previousHeight);
  }
}
