package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import album.Canvas;
import controller.command.ChangeColorShape;
import controller.command.ChangeSizeShape;
import controller.command.Command;
import controller.command.Invoker;
import controller.command.MoveShape;
import controller.command.PrintShapes;
import controller.command.PrintSnapshots;
import controller.command.PutShape;
import controller.command.SnapShotCanvas;
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
  private IShape oval;
  private IShape rectangle;
  private ICoordinate coordinate;
  private Color red;
  private Color green;
  private Color blue;
  // Create command
  private Command changeSizeShape;
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
    assertEquals("Canvas:\nLength: 300, Height: 200", canvas1.toString());
    canvas2 = new Canvas(100, 500);
    assertEquals("Canvas:\nLength: 100, Height: 500", canvas2.toString());
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

    // Put Oval on canvas
    coordinate = new twoDCoordinate(100, 100);
    oval = factory.getShape("oval", "O", coordinate, red, 10, 10);
    // Make command
    putShape = new PutShape(canvas1, "O", oval);
    // Invoke Command
    invoker = new Invoker(putShape);
    // Do the command
    invoker.press();
    // Test shape is on the canvas
    assertEquals(
        "Name: O\nType: Oval\nUpper Left: (100, 100), Width: 10, Height: 10, Color: (255, 0, 0)",
        canvas1.getShape("O").toString());

    // Put Square on canvas
    coordinate = new twoDCoordinate(50, 100);
    rectangle = factory.getShape("rectangle", "R", coordinate, green, 10, 10);
    // Make command
    putShape = new PutShape(canvas1, "R", rectangle);
    // Invoke Command
    invoker = new Invoker(putShape);
    // Do the command
    invoker.press();
    // Test shape is on the canvas
    assertEquals(
        "Name: R\nType: Rectangle\nUpper Left: (50, 100), Width: 10, Height: 10, Color: (0, 255, 0)",
        canvas1.getShape("R").toString());

    // Put Rectangle on canvas
    coordinate = new twoDCoordinate(150, 80);
    rectangle = factory.getShape("rectangle", "R2", coordinate, blue, 10, 5);
    // Make command
    putShape = new PutShape(canvas1, "R2", rectangle);
    // Invoke Command
    invoker = new Invoker(putShape);
    // Do the command
    invoker.press();
    // Test shape is on the canvas
    assertEquals(
        "Name: R2\nType: Rectangle\nUpper Left: (150, 80), Width: 10, Height: 5, Color: (0, 0, 255)",
        canvas1.getShape("R2").toString());

    // Undo the command
    invoker.pressUndo();
    assertNull(canvas1.getShape("R2"));

    // Test get all shape
    assertEquals(2, canvas1.getAllShape().size());
  }

  /**
   * Test move circle on the canvas.
   */
  @Test
  public void testMoveCircle() {
    // Create canvas
    canvas1 = new Canvas(300, 200);

    // Put Oval on canvas
    coordinate = new twoDCoordinate(100, 100);
    oval = factory.getShape("oval", "O", coordinate, red, 10, 10);
    // Make command
    putShape = new PutShape(canvas1, "O", oval);
    // Invoke Command
    invoker = new Invoker(putShape);
    // Do the command
    invoker.press();
    assertEquals("Upper Left: (100, 100)", canvas1.getShape("O").getCoordinate().toString());
    // Move shape
    coordinate = new twoDCoordinate(200, 190);
    moveShape = new MoveShape(canvas1, "O", coordinate);
    // Invoke Command
    invoker = new Invoker(moveShape);
    // Do the command
    invoker.press();
    assertEquals("Upper Left: (200, 190)", canvas1.getShape("O").getCoordinate().toString());

    // Test undo
    invoker.pressUndo();
    assertEquals("Upper Left: (100, 100)", canvas1.getShape("O").getCoordinate().toString());

  }

  /**
   * Test move rectangle on the canvas.
   */
  @Test
  public void testMoveRectangle() {
    // Create canvas
    canvas1 = new Canvas(300, 200);

    // Put Oval on canvas
    coordinate = new twoDCoordinate(100, 100);
    rectangle = factory.getShape("rectangle", "R", coordinate, red, 10, 30);
    // Make command
    putShape = new PutShape(canvas1, "R", rectangle);
    // Invoke Command
    invoker = new Invoker(putShape);
    // Do the command
    invoker.press();
    assertEquals("Upper Left: (100, 100)", canvas1.getShape("R").getCoordinate().toString());
    // Move shape
    coordinate = new twoDCoordinate(285, 185);
    moveShape = new MoveShape(canvas1, "R", coordinate);
    // Invoke Command
    invoker = new Invoker(moveShape);
    // Do the command
    invoker.press();
    assertEquals("Upper Left: (285, 185)", canvas1.getShape("R").getCoordinate().toString());

    // Test undo
    invoker.pressUndo();
    assertEquals("Upper Left: (100, 100)", canvas1.getShape("R").getCoordinate().toString());

  }


  /**
   * Test resize shape on the canvas.
   */
  @Test
  public void testResizeShape() {
    // Create canvas
    canvas1 = new Canvas(300, 200);

    // Put Oval on canvas
    coordinate = new twoDCoordinate(100, 100);
    oval = factory.getShape("oval", "O", coordinate, red, 10, 20);
    // Make command
    putShape = new PutShape(canvas1, "O", oval);
    // Invoke Command
    invoker = new Invoker(putShape);
    // Do the command
    invoker.press();
    // Test shape is on the canvas
    assertEquals(
        "Name: O\nType: Oval\nUpper Left: (100, 100), Width: 10, Height: 20, Color: (255, 0, 0)",
        canvas1.getShape("O").toString());
    // Change circle size
    changeSizeShape = new ChangeSizeShape(canvas1, "O", 50, 30);
    // Invoke Command
    invoker = new Invoker(changeSizeShape);
    // Do the command
    invoker.press();
    assertEquals(
        "Name: O\nType: Oval\nUpper Left: (100, 100), Width: 50, Height: 30, Color: (255, 0, 0)",
        canvas1.getShape("O").toString());
    // Undo the command
    invoker.pressUndo();
    assertEquals(
        "Name: O\nType: Oval\nUpper Left: (100, 100), Width: 10, Height: 20, Color: (255, 0, 0)",
        canvas1.getShape("O").toString());

    // Put Rectangle on canvas
    coordinate = new twoDCoordinate(150, 80);
    rectangle = factory.getShape("rectangle", "R", coordinate, blue, 10, 5);
    // Make command
    putShape = new PutShape(canvas1, "R", rectangle);
    // Invoke Command
    invoker = new Invoker(putShape);
    // Do the command
    invoker.press();
    // Test shape is on the canvas
    assertEquals(
        "Name: R\nType: Rectangle\nUpper Left: (150, 80), Width: 10, Height: 5, Color: (0, 0, 255)",
        canvas1.getShape("R").toString());
    // Change rectangle size
    changeSizeShape = new ChangeSizeShape(canvas1, "R", 50, 10);
    // Invoke Command
    invoker = new Invoker(changeSizeShape);
    // Do the command
    invoker.press();
    assertEquals(
        "Name: R\nType: Rectangle\nUpper Left: (150, 80), Width: 50, Height: 10, Color: (0, 0, 255)",
        canvas1.getShape("R").toString());

    // Undo the command
    invoker.pressUndo();
    assertEquals(
        "Name: R\nType: Rectangle\nUpper Left: (150, 80), Width: 10, Height: 5, Color: (0, 0, 255)",
        canvas1.getShape("R").toString());
  }


  /**
   * Test change shape's color on the canvas.
   */
  @Test
  public void testChangeColor() {
    // Create canvas
    canvas1 = new Canvas(300, 200);

    // Put Oval on canvas
    coordinate = new twoDCoordinate(100, 100);
    oval = factory.getShape("oval", "O", coordinate, red, 10, 20);
    // Make command
    putShape = new PutShape(canvas1, "O", oval);
    // Invoke Command
    invoker = new Invoker(putShape);
    // Do the command
    invoker.press();
    // Change circle color
    changeColorShape = new ChangeColorShape(canvas1, "O", Color.BLACK);
    // Invoke Command
    invoker = new Invoker(changeColorShape);
    // Do the command
    invoker.press();
    assertEquals("(0, 0, 0)", String.format("(%d, %d, %d)",
        canvas1.getShape("O").getColor().getRed(), canvas1.getShape("O").getColor().getGreen(),
        canvas1.getShape("O").getColor().getBlue()));
    // Undo the command
    invoker.pressUndo();
    assertEquals("(255, 0, 0)", String.format("(%d, %d, %d)",
        canvas1.getShape("O").getColor().getRed(), canvas1.getShape("O").getColor().getGreen(),
        canvas1.getShape("O").getColor().getBlue()));

    // Put Rectangle on canvas
    coordinate = new twoDCoordinate(150, 80);
    rectangle = factory.getShape("rectangle", "R", coordinate, blue, 10, 5);
    // Make command
    putShape = new PutShape(canvas1, "R", rectangle);
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

    // Put Oval on canvas
    coordinate = new twoDCoordinate(100, 100);
    oval = factory.getShape("oval", "O", coordinate, red, 10, 30);
    // Make command
    putShape = new PutShape(canvas1, "O", oval);
    // Invoke Command
    invoker = new Invoker(putShape);
    // Do the command
    invoker.press();

    // Put Rectangle on canvas
    coordinate = new twoDCoordinate(150, 80);
    rectangle = factory.getShape("rectangle", "R", coordinate, blue, 10, 5);
    // Make command
    putShape = new PutShape(canvas1, "R", rectangle);
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

    // Change rectangle size, it should reflect on the second snapshot
    changeSizeShape = new ChangeSizeShape(canvas1, "R", 50, 10);
    // Invoke Command
    invoker = new Invoker(changeSizeShape);
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

    // Put Oval on canvas
    coordinate = new twoDCoordinate(100, 100);
    oval = factory.getShape("oval", "O", coordinate, red, 10, 25);
    // Make command
    putShape = new PutShape(canvas1, "O", oval);
    // Invoke Command
    invoker = new Invoker(putShape);
    // Do the command
    invoker.press();

    // Put Rectangle on canvas
    coordinate = new twoDCoordinate(150, 80);
    rectangle = factory.getShape("rectangle", "R", coordinate, blue, 10, 5);
    // Make command
    putShape = new PutShape(canvas1, "R", rectangle);
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

    // Put Oval on canvas
    coordinate = new twoDCoordinate(100, 100);
    oval = factory.getShape("oval", "O", coordinate, red, 10, 50);
    // Make command
    putShape = new PutShape(canvas1, "O", oval);
    // Invoke Command
    invoker = new Invoker(putShape);
    // Do the command
    invoker.press();

    // Put Rectangle on canvas
    coordinate = new twoDCoordinate(150, 80);
    rectangle = factory.getShape("rectangle", "R", coordinate, blue, 10, 5);
    // Make command
    putShape = new PutShape(canvas1, "R", rectangle);
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