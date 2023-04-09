package shape;

import java.awt.Color;

/**
 * The ShapeFactory class is responsible for creating shapes based on the provided shape type and
 * parameters.
 */
public class ShapeFactory {

  /**
   * Gets shape.
   *
   * @param shapeType  the shape type
   * @param name       the name of the shape
   * @param coordinate the coordinate of the shape
   * @param color      the color of the shape
   * @param params     the params of the shape
   * @return the shape
   */
  public IShape getShape(String shapeType, String name, ICoordinate coordinate, Color color,
      double... params) {
    if (shapeType.equalsIgnoreCase("Circle")) {
      if (params.length != 1) {
        throw new IllegalArgumentException("Circle requires 1 parameter: radius");
      }
      double radius = params[0];
      return new Circle(name, coordinate, color, radius);
    }

    if (shapeType.equalsIgnoreCase("Square")) {
      if (params.length != 1) {
        throw new IllegalArgumentException("Square requires 1 parameter: side length");
      }
      double sideLength = params[0];
      return new Square(name, coordinate, color, sideLength);
    }

    if (shapeType.equalsIgnoreCase("Rectangle")) {
      if (params.length != 2) {
        throw new IllegalArgumentException("Rectangle requires 2 parameters: width, length");
      }
      double width;
      double length;
      if (params[0] > params[1]) {
        width = params[1];
        length = params[0];
      } else {
        width = params[0];
        length = params[1];
      }
      return new Rectangle(name, coordinate, color, length, width);
    }
    return null;
  }


}
