package shape;

/**
 * The type 2D coordinate.
 */
public class twoDCoordinate implements ICoordinate {

  private String referencePoint;
  private double x;
  private double y;

  /**
   * Instantiates a new 2D coordinate.
   *
   * @param x the x
   * @param y the y
   */
  public twoDCoordinate(double x, double y) {
    this.referencePoint = "Center"; // reference point will always be center
    this.x = x;
    this.y = y;
  }

  @Override
  public double getX() {
    return x;
  }

  @Override
  public double getY() {
    return y;
  }

  @Override
  public void setX(double xCoordinate) {
    this.x = xCoordinate;
  }

  @Override
  public void setY(double yCoordinate) {
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

