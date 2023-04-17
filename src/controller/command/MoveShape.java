package command;

import album.Canvas;
import shape.ICoordinate;

/**
 * The command that move shape in the canvas.
 */
public class MoveShape implements Command {

  private Canvas canvas;
  private String name;
  private ICoordinate newCoordinate;
  private ICoordinate previousCoordinate;


  /**
   * Instantiates a new Move shape.
   *
   * @param canvas        the canvas
   * @param name          the name
   * @param newCoordinate the new coordinate
   */
  public MoveShape(Canvas canvas, String name,
      ICoordinate newCoordinate) {
    this.canvas = canvas;
    this.name = name;
    this.newCoordinate = newCoordinate;
  }

  @Override
  public void execute() {
    this.previousCoordinate = this.canvas.getShape(name).getCoordinate();
    this.canvas.move(this.name, this.newCoordinate);
  }

  @Override
  public void undo() {
    this.canvas.move(this.name, this.previousCoordinate);
  }

  @Override
  public String toString() {
    return String.format("Move %s's coordinate to X: %d, Y: %d.", this.name,
        this.newCoordinate.getX(), this.newCoordinate.getY());
  }
}
