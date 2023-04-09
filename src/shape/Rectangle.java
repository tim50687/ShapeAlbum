package shape;

import java.awt.Color;

/**
 * The type Rectangle.
 */
public class Rectangle extends AbstractShape {

  private double width;
  private double length;

  /**
   * Instantiates a new Rectangle.
   *
   * @param name       the name of rectangle
   * @param coordinate the coordinate of rectangle
   * @param color      the color of rectangle
   * @param length     the length of rectangle
   * @param width      the width of rectangle
   */
  public Rectangle(String name, ICoordinate coordinate, Color color, double length, double width) {
    super(name, coordinate, color);
    this.length = length;
    this.width = width;
  }

  /**
   * Sets size.
   *
   * @param length the length
   * @param width  the width
   * @throws IllegalArgumentException the illegal argument exception
   */
  public void setSize(double length, double width) throws IllegalArgumentException {
    if (length < 0 || width < 0) {
      throw new IllegalArgumentException("Side length cannot be negative.");
    }
    this.length = length;
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

  /**
   * Gets length.
   *
   * @return the length
   */
  public double getLength() {
    return this.length;
  }

  @Override
  public double getArea() {
    return this.length * this.width;
  }

  @Override
  public double getPerimeter() {
    return (this.length + this.width) * 2;
  }

  @Override
  public String toString() {
    return String.format(
        "Name: %s\nType: %s\n%s, Length: %.1f, Width: %.1f, Color: (%d, %d, %d)",
        this.name, this.getClass().getSimpleName(),
        this.coordinate, this.length,
        this.width, this.color.getRed(), this.color.getGreen(), this.color.getBlue());
  }
}
