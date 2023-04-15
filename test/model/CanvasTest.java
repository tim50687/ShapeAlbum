import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import album.Canvas;
import command.ChangeCircleSize;
import command.ChangeColorShape;
import command.ChangeRectangleSize;
import command.ChangeSquareSize;
import command.Command;
import command.PrintShapes;
import command.Invoker;
import command.MoveShape;
import command.PrintSnapshots;
import command.PutShape;
import command.SnapShotCanvas;
import java.awt.Color;
import org.junit.Before;
import org.junit.Test;
import shape.ICoordinate;
import shape.IShape;
import shape.ShapeFactory;
import shape.twoDCoordinate;

/**
 * The JUnit test for shape photo album.
 */
public class CanvasTest {

  // Create canvas
  private Canvas canvas1;
  private Canvas canvas2;
  // Create shape
  private ShapeFactory factory;
  private IShape circle;
  private IShape square;
  private IShape rectangle;
  private ICoordinate coordinate;
  private Color red;
  private Color green;
  private Color blue;
  // Create command
  private Command changeCircleSize;
  private Command changeSquareSize;
  private Command changeRectangleSize;
  private Command changeColorShape;
  private Command moveShape;
  private Command takeSnapshot;
  private Command putShape;
  private Command printShapes;
  private Command printSnapshots;
  // Create invoker
  private Invoker invoker;

  /**
   * Sets up.
   */
  @Before
  public void setUp() {
    // Make shape factory
    factory = new ShapeFactory();
    // Create color
    red = Color.RED;
    green = Color.GREEN;
    blue = Color.BLUE;

  }

  /**
   * Test create canvas.
   */
  @Test
  public void testCreateCanvas() {
    canvas1 = new Canvas(300, 200);
    assertEquals("Canvas:\nLength: 300.0, Height: 200.0", canvas1.toString());
    canvas2 = new Canvas(100, 500);
    assertEquals("Canvas:\nLength: 100.0, Height: 500.0", canvas2.toString());
  }

  /**
   * Test negative length canvas.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeLengthCanvas() {
    canvas1 = new Canvas(-300, 200);
  }

  /**
   * Test negative width canvas.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeWidthCanvas() {
    canvas1 = new Canvas(300, -200);
  }

  /**
   * Test put shape on the canvas.
   */
  @Test
  public void testPutShape() {
    // Create canvas
    canvas1 = new Canvas(300, 200);

    // Put Circle on canvas
    coordinate = new twoDCoordinate(100, 100);
    circle = factory.getShape("circle", "C", coordinate, red, 10);
    // Make command
    putShape = new PutShape(canvas1, "circle", "C", circle);
    // Invoke Command
    invoker = new Invoker(putShape);
    // Do the command
    invoker.press();
    // Test shape is on the canvas
    assertEquals("Name: C\nType: Circle\nCenter: (100.0, 100.0), Radius: 10.0, Color: (255, 0, 0)",
        canvas1.getShape("C").toString());

    // Put Square on canvas
    coordinate = new twoDCoordinate(50, 100);
    square = factory.getShape("square", "S", coordinate, green, 10);
    // Make command
    putShape = new PutShape(canvas1, "square", "S", square);
    // Invoke Command
    invoker = new Invoker(putShape);
    // Do the command
    invoker.press();
    // Test shape is on the canvas
    assertEquals("Name: S\nType: Square\nCenter: (50.0, 100.0), Width: 10.0, Color: (0, 255, 0)",
        canvas1.getShape("S").toString());

    // Put Rectangle on canvas
    coordinate = new twoDCoordinate(150, 80);
    rectangle = factory.getShape("rectangle", "R", coordinate, blue, 10, 5);
    // Make command
    putShape = new PutShape(canvas1, "rectangle", "R", rectangle);
    // Invoke Command
    invoker = new Invoker(putShape);
    // Do the command
    invoker.press();
    // Test shape is on the canvas
    assertEquals(
        "Name: R\nType: Rectangle\nCenter: (150.0, 80.0), Length: 10.0, Width: 5.0, Color: (0, 0, 255)",
        canvas1.getShape("R").toString());

    // Undo the command
    invoker.pressUndo();
    assertNull(canvas1.getShape("R"));

    // Test get all shape
    assertEquals(2, canvas1.getAllShape().size());
  }


  /**
   * Test put invalid circle.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testPutInvalidCircle() {
    // Create canvas
    canvas1 = new Canvas(300, 200);
    // Create shape (radius to big)
    coordinate = new twoDCoordinate(295, 100); // Out of bound
    circle = factory.getShape("circle", "C", coordinate, red, 10);
    // Make command
    putShape = new PutShape(canvas1, "circle", "C", circle);
    // Invoke Command
    invoker = new Invoker(putShape);
    // Do the command
    invoker.press();
  }

  /**
   * Test put invalid square on the canvas.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testPutInvalidSquare() {
    // Create canvas
    canvas1 = new Canvas(300, 200);
    // Create shape (radius to big)
    coordinate = new twoDCoordinate(295, 198); // Out of bound
    square = factory.getShape("square", "S", coordinate, red, 10);
    // Make command
    putShape = new PutShape(canvas1, "square", "S", square);
    // Invoke Command
    invoker = new Invoker(putShape);
    // Do the command
    invoker.press();
  }

  /**
   * Test put invalid rectangle on the canvas.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testPutInvalidRectangle() {
    // Create canvas
    canvas1 = new Canvas(300, 200);
    // Create shape (radius to big)
    coordinate = new twoDCoordinate(200, 100); // Out of bound
    rectangle = factory.getShape("rectangle", "R", coordinate, red, 101, 50);
    // Make command
    putShape = new PutShape(canvas1, "rectangle", "R", square);
    // Invoke Command
    invoker = new Invoker(putShape);
    // Do the command
    invoker.press();
  }

  /**
   * Test move circle on the canvas.
   */
  @Test
  public void testMoveCircle() {
    // Create canvas
    canvas1 = new Canvas(300, 200);

    // Put Circle on canvas
    coordinate = new twoDCoordinate(100, 100);
    circle = factory.getShape("circle", "C", coordinate, red, 10);
    // Make command
    putShape = new PutShape(canvas1, "circle", "C", circle);
    // Invoke Command
    invoker = new Invoker(putShape);
    // Do the command
    invoker.press();
    assertEquals("Center: (100.0, 100.0)", canvas1.getShape("C").getCoordinate().toString());
    // Move shape
    coordinate = new twoDCoordinate(200, 190);
    moveShape = new MoveShape(canvas1, "circle", "C", coordinate);
    // Invoke Command
    invoker = new Invoker(moveShape);
    // Do the command
    invoker.press();
    assertEquals("Center: (200.0, 190.0)", canvas1.getShape("C").getCoordinate().toString());

    // Test undo
    invoker.pressUndo();
    assertEquals("Center: (100.0, 100.0)", canvas1.getShape("C").getCoordinate().toString());

  }

  /**
   * Test move square on the canvas.
   */
  @Test
  public void testMoveSquare() {
    // Create canvas
    canvas1 = new Canvas(300, 200);

    // Put Circle on canvas
    coordinate = new twoDCoordinate(100, 100);
    square = factory.getShape("square", "S", coordinate, red, 10);
    // Make command
    putShape = new PutShape(canvas1, "square", "S", square);
    // Invoke Command
    invoker = new Invoker(putShape);
    // Do the command
    invoker.press();
    assertEquals("Center: (100.0, 100.0)", canvas1.getShape("S").getCoordinate().toString());
    // Move shape
    coordinate = new twoDCoordinate(295, 195);
    moveShape = new MoveShape(canvas1, "square", "S", coordinate);
    // Invoke Command
    invoker = new Invoker(moveShape);
    // Do the command
    invoker.press();
    assertEquals("Center: (295.0, 195.0)", canvas1.getShape("S").getCoordinate().toString());

    // Test undo
    invoker.pressUndo();
    assertEquals("Center: (100.0, 100.0)", canvas1.getShape("S").getCoordinate().toString());

  }

  /**
   * Test move rectangle on the canvas.
   */
  @Test
  public void testMoveRectangle() {
    // Create canvas
    canvas1 = new Canvas(300, 200);

    // Put Circle on canvas
    coordinate = new twoDCoordinate(100, 100);
    rectangle = factory.getShape("rectangle", "R", coordinate, red, 10, 30);
    // Make command
    putShape = new PutShape(canvas1, "rectangle", "R", rectangle);
    // Invoke Command
    invoker = new Invoker(putShape);
    // Do the command
    invoker.press();
    assertEquals("Center: (100.0, 100.0)", canvas1.getShape("R").getCoordinate().toString());
    // Move shape
    coordinate = new twoDCoordinate(285, 185);
    moveShape = new MoveShape(canvas1, "rectangle", "R", coordinate);
    // Invoke Command
    invoker = new Invoker(moveShape);
    // Do the command
    invoker.press();
    assertEquals("Center: (285.0, 185.0)", canvas1.getShape("R").getCoordinate().toString());

    // Test undo
    invoker.pressUndo();
    assertEquals("Center: (100.0, 100.0)", canvas1.getShape("R").getCoordinate().toString());

  }

  /**
   * Test move circle out of the bounds of the canvas.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMoveInvalidCircle() {
    // Create canvas
    canvas1 = new Canvas(300, 200);

    // Put Circle on canvas
    coordinate = new twoDCoordinate(100, 100);
    circle = factory.getShape("circle", "C", coordinate, red, 10);
    // Make command
    putShape = new PutShape(canvas1, "circle", "C", circle);
    // Invoke Command
    invoker = new Invoker(putShape);
    // Do the command
    invoker.press();
    assertEquals("Center: (100.0, 100.0)", canvas1.getShape("C").getCoordinate().toString());
    // Move shape
    coordinate = new twoDCoordinate(196, 200);
    moveShape = new MoveShape(canvas1, "circle", "C", coordinate);
    // Invoke Command
    invoker = new Invoker(moveShape);
    // Do the command
    invoker.press();
  }

  /**
   * Test move square out of the bounds of the canvas.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMoveInvalidSquare() {
    // Create canvas
    canvas1 = new Canvas(300, 200);

    // Put Circle on canvas
    coordinate = new twoDCoordinate(100, 100);
    square = factory.getShape("square", "S", coordinate, red, 10);
    // Make command
    putShape = new PutShape(canvas1, "square", "S", square);
    // Invoke Command
    invoker = new Invoker(putShape);
    // Do the command
    invoker.press();
    assertEquals("Center: (100.0, 100.0)", canvas1.getShape("S").getCoordinate().toString());
    // Move shape
    coordinate = new twoDCoordinate(301, 195);
    moveShape = new MoveShape(canvas1, "square", "S", coordinate);
    // Invoke Command
    invoker = new Invoker(moveShape);
    // Do the command
    invoker.press();
  }

  /**
   * Test move rectangle out of the bounds of the canvas.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMoveInvalidRectangle() {
    // Create canvas
    canvas1 = new Canvas(300, 200);

    // Put Circle on canvas
    coordinate = new twoDCoordinate(100, 100);
    rectangle = factory.getShape("rectangle", "R", coordinate, red, 10, 30);
    // Make command
    putShape = new PutShape(canvas1, "rectangle", "R", rectangle);
    // Invoke Command
    invoker = new Invoker(putShape);
    // Do the command
    invoker.press();
    assertEquals("Center: (100.0, 100.0)", canvas1.getShape("R").getCoordinate().toString());
    // Move shape
    coordinate = new twoDCoordinate(286, 185);
    moveShape = new MoveShape(canvas1, "rectangle", "R", coordinate);
    // Invoke Command
    invoker = new Invoker(moveShape);
    // Do the command
    invoker.press();
  }

  /**
   * Test resize shape on the canvas.
   */
  @Test
  public void testResizeShape() {
    // Create canvas
    canvas1 = new Canvas(300, 200);

    // Put Circle on canvas
    coordinate = new twoDCoordinate(100, 100);
    circle = factory.getShape("circle", "C", coordinate, red, 10);
    // Make command
    putShape = new PutShape(canvas1, "circle", "C", circle);
    // Invoke Command
    invoker = new Invoker(putShape);
    // Do the command
    invoker.press();
    // Test shape is on the canvas
    assertEquals("Name: C\nType: Circle\nCenter: (100.0, 100.0), Radius: 10.0, Color: (255, 0, 0)",
        canvas1.getShape("C").toString());
    // Change circle size 
    changeCircleSize = new ChangeCircleSize(canvas1, "circle", "C", 50);
    // Invoke Command
    invoker = new Invoker(changeCircleSize);
    // Do the command
    invoker.press();
    assertEquals("Name: C\nType: Circle\nCenter: (100.0, 100.0), Radius: 50.0, Color: (255, 0, 0)",
        canvas1.getShape("C").toString());
    // Undo the command
    invoker.pressUndo();
    assertEquals("Name: C\nType: Circle\nCenter: (100.0, 100.0), Radius: 10.0, Color: (255, 0, 0)",
        canvas1.getShape("C").toString());

    // Put Square on canvas
    coordinate = new twoDCoordinate(50, 100);
    square = factory.getShape("square", "S", coordinate, green, 10);
    // Make command
    putShape = new PutShape(canvas1, "square", "S", square);
    // Invoke Command
    invoker = new Invoker(putShape);
    // Do the command
    invoker.press();
    // Test shape is on the canvas
    assertEquals("Name: S\nType: Square\nCenter: (50.0, 100.0), Width: 10.0, Color: (0, 255, 0)",
        canvas1.getShape("S").toString());
    // Change square size 
    changeSquareSize = new ChangeSquareSize(canvas1, "square", "S", 1);
    // Invoke Command
    invoker = new Invoker(changeSquareSize);
    // Do the command
    invoker.press();
    assertEquals("Name: S\nType: Square\nCenter: (50.0, 100.0), Width: 1.0, Color: (0, 255, 0)",
        canvas1.getShape("S").toString());
    // Undo the command
    invoker.pressUndo();
    assertEquals("Name: S\nType: Square\nCenter: (50.0, 100.0), Width: 10.0, Color: (0, 255, 0)",
        canvas1.getShape("S").toString());

    // Put Rectangle on canvas
    coordinate = new twoDCoordinate(150, 80);
    rectangle = factory.getShape("rectangle", "R", coordinate, blue, 10, 5);
    // Make command
    putShape = new PutShape(canvas1, "rectangle", "R", rectangle);
    // Invoke Command
    invoker = new Invoker(putShape);
    // Do the command
    invoker.press();
    // Test shape is on the canvas
    assertEquals(
        "Name: R\nType: Rectangle\nCenter: (150.0, 80.0), Length: 10.0, Width: 5.0, Color: (0, 0, 255)",
        canvas1.getShape("R").toString());
    // Change rectangle size 
    changeRectangleSize = new ChangeRectangleSize(canvas1, "rectangle", "R", 50, 10);
    // Invoke Command
    invoker = new Invoker(changeRectangleSize);
    // Do the command
    invoker.press();
    assertEquals(
        "Name: R\nType: Rectangle\nCenter: (150.0, 80.0), Length: 50.0, Width: 10.0, Color: (0, 0, 255)",
        canvas1.getShape("R").toString());

    // Undo the command
    invoker.pressUndo();
    assertEquals(
        "Name: R\nType: Rectangle\nCenter: (150.0, 80.0), Length: 10.0, Width: 5.0, Color: (0, 0, 255)",
        canvas1.getShape("R").toString());
  }

  /**
   * Test resize circle while the circle is out of the bounds of the canvas.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testResizeInvalidCircle() {
    // Create canvas
    canvas1 = new Canvas(300, 200);

    // Put Circle on canvas
    coordinate = new twoDCoordinate(100, 100);
    circle = factory.getShape("circle", "C", coordinate, red, 10);
    // Make command
    putShape = new PutShape(canvas1, "circle", "C", circle);
    // Invoke Command
    invoker = new Invoker(putShape);
    // Do the command
    invoker.press();
    assertEquals("Center: (100.0, 100.0)", canvas1.getShape("C").getCoordinate().toString());
    // Change circle size
    changeCircleSize = new ChangeCircleSize(canvas1, "circle", "C", 101);
    // Invoke Command
    invoker = new Invoker(changeCircleSize);
    // Do the command
    invoker.press();
  }

  /**
   * Test resize square while the square is out of the bounds of the canvas.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testResizeInvalidSquare() {
    // Create canvas
    canvas1 = new Canvas(300, 200);

    // Put Circle on canvas
    coordinate = new twoDCoordinate(100, 100);
    square = factory.getShape("square", "S", coordinate, red, 10);
    // Make command
    putShape = new PutShape(canvas1, "square", "S", square);
    // Invoke Command
    invoker = new Invoker(putShape);
    // Do the command
    invoker.press();
    assertEquals("Center: (100.0, 100.0)", canvas1.getShape("S").getCoordinate().toString());

    // Change square size
    changeSquareSize = new ChangeSquareSize(canvas1, "square", "S", 300);
    // Invoke Command
    invoker = new Invoker(changeSquareSize);
    // Do the command
    invoker.press();
  }

  /**
   * Test resize rectangle while the rectangle is out of the bounds of the canvas.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testResizeInvalidRectangle() {
    // Create canvas
    canvas1 = new Canvas(300, 200);

    // Put Circle on canvas
    coordinate = new twoDCoordinate(100, 100);
    rectangle = factory.getShape("rectangle", "R", coordinate, red, 10, 30);
    // Make command
    putShape = new PutShape(canvas1, "rectangle", "R", rectangle);
    // Invoke Command
    invoker = new Invoker(putShape);
    // Do the command
    invoker.press();
    // Change rectangle size
    changeRectangleSize = new ChangeRectangleSize(canvas1, "rectangle", "R", 50, 201);
    // Invoke Command
    invoker = new Invoker(changeRectangleSize);
    // Do the command
    invoker.press();
  }

  /**
   * Test change shape's color on the canvas.
   */
  @Test
  public void testChangeColor() {
    // Create canvas
    canvas1 = new Canvas(300, 200);

    // Put Circle on canvas
    coordinate = new twoDCoordinate(100, 100);
    circle = factory.getShape("circle", "C", coordinate, red, 10);
    // Make command
    putShape = new PutShape(canvas1, "circle", "C", circle);
    // Invoke Command
    invoker = new Invoker(putShape);
    // Do the command
    invoker.press();
    // Change circle color
    changeColorShape = new ChangeColorShape(canvas1, "C", Color.BLACK);
    // Invoke Command
    invoker = new Invoker(changeColorShape);
    // Do the command
    invoker.press();
    assertEquals("(0, 0, 0)", String.format("(%d, %d, %d)",
        canvas1.getShape("C").getColor().getRed(), canvas1.getShape("C").getColor().getGreen(),
        canvas1.getShape("C").getColor().getBlue()));
    // Undo the command
    invoker.pressUndo();
    assertEquals("(255, 0, 0)", String.format("(%d, %d, %d)",
        canvas1.getShape("C").getColor().getRed(), canvas1.getShape("C").getColor().getGreen(),
        canvas1.getShape("C").getColor().getBlue()));

    // Put Square on canvas
    coordinate = new twoDCoordinate(50, 100);
    square = factory.getShape("square", "S", coordinate, green, 10);
    // Make command
    putShape = new PutShape(canvas1, "square", "S", square);
    // Invoke Command
    invoker = new Invoker(putShape);
    // Do the command
    invoker.press();
    // Change square color
    changeColorShape = new ChangeColorShape(canvas1, "S", Color.BLACK);
    // Invoke Command
    invoker = new Invoker(changeColorShape);
    // Do the command
    invoker.press();
    assertEquals("(0, 0, 0)", String.format("(%d, %d, %d)",
        canvas1.getShape("S").getColor().getRed(), canvas1.getShape("S").getColor().getGreen(),
        canvas1.getShape("S").getColor().getBlue()));
    // Undo the command
    invoker.pressUndo();
    assertEquals("(0, 255, 0)", String.format("(%d, %d, %d)",
        canvas1.getShape("S").getColor().getRed(), canvas1.getShape("S").getColor().getGreen(),
        canvas1.getShape("S").getColor().getBlue()));

    // Put Rectangle on canvas
    coordinate = new twoDCoordinate(150, 80);
    rectangle = factory.getShape("rectangle", "R", coordinate, blue, 10, 5);
    // Make command
    putShape = new PutShape(canvas1, "rectangle", "R", rectangle);
    // Invoke Command
    invoker = new Invoker(putShape);
    // Do the command
    invoker.press();
    // Change rectangle color
    changeColorShape = new ChangeColorShape(canvas1, "R", Color.BLACK);
    // Invoke Command
    invoker = new Invoker(changeColorShape);
    // Do the command
    invoker.press();
    assertEquals("(0, 0, 0)", String.format("(%d, %d, %d)",
        canvas1.getShape("R").getColor().getRed(), canvas1.getShape("R").getColor().getGreen(),
        canvas1.getShape("R").getColor().getBlue()));
    // Undo the command
    invoker.pressUndo();
    assertEquals("(0, 0, 255)", String.format("(%d, %d, %d)",
        canvas1.getShape("R").getColor().getRed(), canvas1.getShape("R").getColor().getGreen(),
        canvas1.getShape("R").getColor().getBlue()));
  }

  /**
   * Test take snapshot for the current status on the canvas.
   */
  @Test
  public void testSnapshot() {
    // Create canvas
    canvas1 = new Canvas(300, 200);

    // Put Circle on canvas
    coordinate = new twoDCoordinate(100, 100);
    circle = factory.getShape("circle", "C", coordinate, red, 10);
    // Make command
    putShape = new PutShape(canvas1, "circle", "C", circle);
    // Invoke Command
    invoker = new Invoker(putShape);
    // Do the command
    invoker.press();

    // Put Square on canvas
    coordinate = new twoDCoordinate(50, 100);
    square = factory.getShape("square", "S", coordinate, green, 10);
    // Make command
    putShape = new PutShape(canvas1, "square", "S", square);
    // Invoke Command
    invoker = new Invoker(putShape);
    // Do the command
    invoker.press();

    // Put Rectangle on canvas
    coordinate = new twoDCoordinate(150, 80);
    rectangle = factory.getShape("rectangle", "R", coordinate, blue, 10, 5);
    // Make command
    putShape = new PutShape(canvas1, "rectangle", "R", rectangle);
    // Invoke Command
    invoker = new Invoker(putShape);
    // Do the command
    invoker.press();

    // Take snapshot
    takeSnapshot = new SnapShotCanvas(canvas1, "First snapshot");
    // Invoke Command
    invoker = new Invoker(takeSnapshot);
    // Do the command
    invoker.press();

    // Change rectangle size, it shout reflect on the second snapshot
    changeRectangleSize = new ChangeRectangleSize(canvas1, "rectangle", "R", 50, 10);
    // Invoke Command
    invoker = new Invoker(changeRectangleSize);
    // Do the command
    invoker.press();
    // Take snapshot
    takeSnapshot = new SnapShotCanvas(canvas1, "Second snapshot");
    // Invoke Command
    invoker = new Invoker(takeSnapshot);
    invoker.press();

    assertEquals(2, canvas1.getAllSnapshot().size());

    invoker.pressUndo();

    assertEquals(1, canvas1.getAllSnapshot().size());
  }

  /**
   * Test print out all the snapshots on the current canvas.
   */
  @Test
  public void testGetSnapshot() {
    // Create canvas
    canvas1 = new Canvas(300, 200);

    // Put Circle on canvas
    coordinate = new twoDCoordinate(100, 100);
    circle = factory.getShape("circle", "C", coordinate, red, 10);
    // Make command
    putShape = new PutShape(canvas1, "circle", "C", circle);
    // Invoke Command
    invoker = new Invoker(putShape);
    // Do the command
    invoker.press();

    // Put Square on canvas
    coordinate = new twoDCoordinate(50, 100);
    square = factory.getShape("square", "S", coordinate, green, 10);
    // Make command
    putShape = new PutShape(canvas1, "square", "S", square);
    // Invoke Command
    invoker = new Invoker(putShape);
    // Do the command
    invoker.press();

    // Put Rectangle on canvas
    coordinate = new twoDCoordinate(150, 80);
    rectangle = factory.getShape("rectangle", "R", coordinate, blue, 10, 5);
    // Make command
    putShape = new PutShape(canvas1, "rectangle", "R", rectangle);
    // Invoke Command
    invoker = new Invoker(putShape);
    // Do the command
    invoker.press();

    // Take snapshot
    takeSnapshot = new SnapShotCanvas(canvas1, "First snapshot");
    // Invoke Command
    invoker = new Invoker(takeSnapshot);
    // Do the command
    invoker.press();
    invoker.press();

    invoker.pressUndo();

    // Test the print snapshots
    printSnapshots = new PrintSnapshots(canvas1);
    invoker = new Invoker(printSnapshots);
    invoker.press();
  }

  /**
   * Test get all shapes on the canvas.
   */
  @Test
  public void testGetAllShape() {
    // Create canvas
    canvas1 = new Canvas(300, 200);

    // Put Circle on canvas
    coordinate = new twoDCoordinate(100, 100);
    circle = factory.getShape("circle", "C", coordinate, red, 10);
    // Make command
    putShape = new PutShape(canvas1, "circle", "C", circle);
    // Invoke Command
    invoker = new Invoker(putShape);
    // Do the command
    invoker.press();

    // Put Square on canvas
    coordinate = new twoDCoordinate(50, 100);
    square = factory.getShape("square", "S", coordinate, green, 10);
    // Make command
    putShape = new PutShape(canvas1, "square", "S", square);
    // Invoke Command
    invoker = new Invoker(putShape);
    // Do the command
    invoker.press();

    // Put Rectangle on canvas
    coordinate = new twoDCoordinate(150, 80);
    rectangle = factory.getShape("rectangle", "R", coordinate, blue, 10, 5);
    // Make command
    putShape = new PutShape(canvas1, "rectangle", "R", rectangle);
    // Invoke Command
    invoker = new Invoker(putShape);
    // Do the command
    invoker.press();

    // Test the print shapes
    printShapes = new PrintShapes(canvas1);
    invoker = new Invoker(printShapes);
    invoker.press();

  }
  
}