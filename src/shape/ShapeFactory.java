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
   * @param name       the name
   * @param coordinate the coordinate
   * @param color      the color
   * @param width      the width
   * @param height     the height
   * @return the shape
   */
  public IShape getShape(String shapeType, String name, ICoordinate coordinate, Color color,
      int width, int height) {
    if (shapeType.equalsIgnoreCase("Oval")) {
      return new Oval(name, coordinate, color, width, height);
    }

    if (shapeType.equalsIgnoreCase("Rectangle")) {
      return new Rectangle(name, coordinate, color, width, height);
    }
    return null;
  }


}
