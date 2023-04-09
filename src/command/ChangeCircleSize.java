package command;

import album.Canvas;
import shape.Circle;

/**
 * The command that change circle size.
 */
public class ChangeCircleSize implements Command {

  private Canvas canvas;
  private String type;
  private String name;
  private double newRadius;
  private double previousRadius;

  /**
   * Instantiates a new Change circle size command.
   *
   * @param canvas    the canvas
   * @param type      the type of the shape
   * @param name      the name of the shape
   * @param newRadius the new radius
   */
  public ChangeCircleSize(Canvas canvas, String type, String name, double newRadius) {
    this.canvas = canvas;
    this.type = type;
    this.name = name;
    this.newRadius = newRadius;
  }

  @Override
  public void execute() {
    Circle circle = (Circle) this.canvas.getShape(name);
    this.previousRadius = circle.getRadius();
    this.canvas.changeShapeSize(this.type, this.name, this.newRadius);
  }

  @Override
  public void undo() {
    this.canvas.changeShapeSize(this.type, this.name, this.previousRadius);
  }
}
