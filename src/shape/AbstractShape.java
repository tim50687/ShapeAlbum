package shape;

import java.awt.Color;

/**
 * The type Abstract shape. It implements the IShape interface and provides a common implementation
 * for the coordinate and color properties of all shapes.
 */
public abstract class AbstractShape implements IShape {

  protected String name;

  protected ICoordinate coordinate;

  protected Color color;

  /**
   * Instantiates a new AbstractShape with the specified coordinate and color.
   */
  public AbstractShape(String name, ICoordinate coordinate, Color color) {
    this.name = name;
    this.coordinate = coordinate;
    this.color = color;
  }

  @Override
  public ICoordinate getCoordinate() {
    return this.coordinate;
  }

  @Override
  public void move(ICoordinate coordinate) {
    this.coordinate = coordinate;
  }

  @Override
  public void setColor(Color color) {
    this.color = color;
  }

  @Override
  public Color getColor() {
    return this.color;
  }


}



