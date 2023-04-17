package model.shape;

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
   * Sets the color of the shape.
   *
   * @param color the color
   */
  void setColor(Color color);

  /**
   * Gets the color of the shape.
   *
   * @return the color
   */
  Color getColor();


  /**
   * Gets width.
   *
   * @return the width
   */
  int getWidth();

  /**
   * Gets height.
   *
   * @return the height
   */
  int getHeight();


  /**
   * Sets width.
   *
   * @return the width
   */
  void setWidth(int width);

  /**
   * Sets height.
   *
   * @return the height
   */
  void setHeight(int height);

}
