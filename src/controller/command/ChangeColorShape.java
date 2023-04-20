package controller.command;

import java.awt.Color;
import model.ICanvasModel;

/**
 * The command that change color shape.
 */
public class ChangeColorShape implements Command {

  private ICanvasModel canvas;
  private String type;
  private String name;

  Color previousColor;
  Color newColor;

  /**
   * Instantiates a new Change color shape command.
   *
   * @param canvas   the canvas
   * @param name     the name of the shape
   * @param newColor the new color
   */
  public ChangeColorShape(ICanvasModel canvas, String name, Color newColor) {
    this.canvas = canvas;
    this.name = name;
    this.newColor = newColor;
  }

  @Override
  public void execute() {
    this.previousColor = this.canvas.getShape(name).getColor();
    this.canvas.changeColor(this.name, this.newColor);
  }

  @Override
  public void undo() {
    this.canvas.changeColor(this.name, this.previousColor);
  }

  @Override
  public String toString() {
    return String.format("Change %s's color to %s.", this.name,
        "(" + this.newColor.getRed() + ", " + this.newColor.getGreen() + ", "
            + this.newColor.getBlue() + ")");
  }
}
