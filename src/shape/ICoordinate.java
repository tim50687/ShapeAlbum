package shape;

/**
 * The ICoordinate interface represents a coordinate in a two-dimensional plane.
 */
public interface ICoordinate {

  /**
   * Moves this coordinate to a new location by setting its x and y coordinates to the x and y
   * coordinates of the specified coordinate.
   *
   * @param coordinate the new coordinate to move to
   */
  void move(ICoordinate coordinate);

  /**
   * Sets the x coordinate of this coordinate to the specified value.
   *
   * @param xCoordinate the new x coordinate
   */
  void setX(double xCoordinate);

  /**
   * Sets the y coordinate of this coordinate to the specified value.
   *
   * @param yCoordinate the new y coordinate
   */
  void setY(double yCoordinate);

  /**
   * Gets the x coordinate of this coordinate.
   *
   * @return the x coordinate
   */
  double getX();

  /**
   * Gets the x coordinate of this coordinate.
   *
   * @return the y coordinate
   */
  double getY();

}
