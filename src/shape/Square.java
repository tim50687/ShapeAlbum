package shape;

import java.awt.Color;

/**
 * The type Square.
 */
public class Square extends AbstractShape {

  private double width;


  /**
   * Instantiates a new Square.
   *
   * @param name       the name of square
   * @param coordinate the coordinate of square
   * @param color      the color of square
   * @param width      the width of square
   */
  public Square(String name, ICoordinate coordinate, Color color, double width) {
    super(name, coordinate, color);
    this.width = width;
  }

  /**
   * Sets width.
   *
   * @param width the width
   * @throws IllegalArgumentException the illegal argument exception
   */
  public void setWidth(double width) throws IllegalArgumentException {
    if (width < 0) {
      throw new IllegalArgumentException("Width cannot be negative.");
    }
    this.width = width;
  }

  /**
   * Gets width.
   *
   * @return the width
   */
  public double getWidth() {
    return this.width;
  }

  @Override
  public double getArea() {
    return Math.pow(this.width, 2);
  }

  @Override
  public double getPerimeter() {
    return this.width * 4;
  }

  @Override
  public String toString() {
    return String.format("Name: %s\nType: %s\n%s, Width: %.1f, Color: (%d, %d, %d)",
        this.name,
        this.getClass().getSimpleName(), this.coordinate,
        this.width, this.color.getRed(), this.color.getGreen(), this.color.getBlue());
  }

}
