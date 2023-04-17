package model.shape;

/**
 * The type 2D coordinate.
 */
public class twoDCoordinate implements ICoordinate {

  private String referencePoint;
  private int x;
  private int y;

  /**
   * Instantiates a new 2D coordinate.
   *
   * @param x the x
   * @param y the y
   */
  public twoDCoordinate(int x, int y) {
    this.referencePoint = "Upper Left"; // reference point will always be center
    this.x = x;
    this.y = y;
  }

  @Override
  public int getX() {
    return x;
  }

  @Override
  public int getY() {
    return y;
  }

  @Override
  public void setX(int xCoordinate) {
    this.x = xCoordinate;
  }

  @Override
  public void setY(int yCoordinate) {
    this.y = yCoordinate;
  }

  @Override
  public void move(ICoordinate coordinate) {
    this.x = coordinate.getX();
    this.y = coordinate.getY();
  }

  @Override
  public String toString() {
    return this.referencePoint + ": (" + this.x + ", " + this.y + ")";
  }
}

