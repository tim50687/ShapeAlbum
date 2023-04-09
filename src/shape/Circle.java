package shape;

import java.awt.Color;

/**
 * The type Circle.
 */
public class Circle extends AbstractShape {

  private double radius;

  /**
   * Instantiates a new Circle.
   *
   * @param name       the name of circle
   * @param coordinate the coordinate of circle
   * @param color      the color of circle
   * @param radius     the radius of circle
   */
  public Circle(String name, ICoordinate coordinate, Color color, double radius) {
    super(name, coordinate, color);
    this.radius = radius;
  }

  /**
   * Sets size.
   *
   * @param radius the radius
   * @throws IllegalArgumentException the illegal argument exception
   */
  public void setSize(double radius) throws IllegalArgumentException {
    if (radius < 0) {
      throw new IllegalArgumentException("Radius cannot be negative.");
    }
    this.radius = radius;
  }

  /**
   * Gets radius.
   *
   * @return the radius
   */
  public double getRadius() {
    return this.radius;
  }

  @Override
  public double getArea() {
    return Math.PI * Math.pow(this.radius, 2);
  }

  @Override
  public double getPerimeter() {
    return Math.PI * 2 * this.radius;
  }


  @Override
  public String toString() {
    return String.format("Name: %s\nType: %s\n%s, Radius: %.1f, Color: (%d, %d, %d)",
        this.name,
        this.getClass().getSimpleName(),
        this.coordinate,
        this.radius, this.color.getRed(), this.color.getGreen(), this.color.getBlue());
  }
}