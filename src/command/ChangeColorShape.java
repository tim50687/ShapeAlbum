package command;

import album.Canvas;
import java.awt.Color;

/**
 * The command that change color shape.
 */
public class ChangeColorShape implements Command {

  private Canvas canvas;
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
  public ChangeColorShape(Canvas canvas, String name, Color newColor) {
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
}