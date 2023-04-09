package shape;

import java.awt.Color;

/**
 * The interface IShape represents a geometric shape with a coordinate, size and color that can be
 * moved, resized and recolored.
 */
public interface IShape {


  /**
   * Gets the coordinate of the shape.
   *
   * @return the coordinate
   */
  ICoordinate getCoordinate();

  /**
   * Moves the shape to a new coordinate.
   *
   * @param coordinate the coordinate
   */
  void move(ICoordinate coordinate);


  /**
   * Gets area of the shape.
   *
   * @return the area
   */
  double getArea();

  /**
   * Gets perimeter of the shape.
   *
   * @return the perimeter
   */
  double getPerimeter();


  /**
   * Sets the color of the shape.
   */
  void setColor(Color color);

  /**
   * Gets the color of the shape.
   *
   * @return the color
   */
  Color getColor();

}
