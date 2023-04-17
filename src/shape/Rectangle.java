package shape;

import java.awt.Color;

/**
 * The type Rectangle.
 */
public class Rectangle extends AbstractShape {


  /**
   * Instantiates a new Rectangle.
   *
   * @param name       the name
   * @param coordinate the coordinate
   * @param color      the color
   * @param width      the width
   * @param height     the height
   */
  public Rectangle(String name, ICoordinate coordinate, Color color, int width, int height) {
    super(name, coordinate, color, width, height);
  }

  @Override
  public String toString() {
    return String.format(
        "Name: %s\nType: %s\n%s, Width: %d, Height: %d, Color: (%d, %d, %d)",
        this.name, this.getClass().getSimpleName(),
        this.coordinate, this.width,
        this.height, this.color.getRed(), this.color.getGreen(), this.color.getBlue());
  }
}
