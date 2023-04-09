package command;

import album.Canvas;
import shape.ICoordinate;

/**
 * The command that move shape in the canvas.
 */
public class MoveShape implements Command {

  private Canvas canvas;
  private String type;
  private String name;
  private ICoordinate newCoordinate;
  private ICoordinate previousCoordinate;


  /**
   * Instantiates a new MoveShape command.
   *
   * @param canvas        the canvas
   * @param type          the type of the shape
   * @param name          the name of the shape
   * @param newCoordinate the new coordinate
   */
  public MoveShape(Canvas canvas, String type, String name,
      ICoordinate newCoordinate) {
    this.canvas = canvas;
    this.type = type;
    this.name = name;
    this.newCoordinate = newCoordinate;
  }

  @Override
  public void execute() {
    this.previousCoordinate = this.canvas.getShape(name).getCoordinate();
    this.canvas.move(this.type, this.name, this.newCoordinate);
  }

  @Override
  public void undo() {
    this.canvas.move(this.type, this.name, this.previousCoordinate);
  }
}
