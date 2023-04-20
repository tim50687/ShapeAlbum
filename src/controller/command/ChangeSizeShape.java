package controller.command;

import model.ICanvasModel;
import model.shape.IShape;

/**
 * The command that change rectangle size.
 */
public class ChangeSizeShape implements Command {

  private ICanvasModel canvas;
  private String name;
  int newWidth;
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
  public ChangeSizeShape(ICanvasModel canvas, String name, int newWidth,
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

  @Override
  public String toString() {
    return String.format("Change %s's size to Width: %d, Height: %d.", this.name, this.newWidth,
        this.newHeight);
  }
}
