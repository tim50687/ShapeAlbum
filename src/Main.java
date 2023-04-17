import album.Canvas;
import command.Command;
import command.Invoker;
import command.PrintShapes;
import command.PrintSnapshots;
import command.PutShape;
import command.RemoveShape;
import command.SnapShotCanvas;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import shape.ICoordinate;
import shape.IShape;
import shape.ShapeFactory;
import shape.twoDCoordinate;
import view.WebAlbum;

public class Main {

  public static void main(String[] args) {
    // Create canvas
    Canvas canvas1;
    // Create shape
    ShapeFactory factory = new ShapeFactory();
    IShape circle;
    IShape square;
    IShape rectangle;
    ICoordinate coordinate;
    Color red = Color.RED;
    Color green = Color.GREEN;
    Color blue = Color.BLUE;

    // Create command
    Command putShape;
    Command takeSnapshot;
    Command printShapes;
    Command removeShape;
    Command changeCircleSize;
    Command showSnapshots;

    // Create invoker
    Invoker invoker;

    // Create canvas
    canvas1 = new Canvas(300, 200);

    // Put Oval on canvas
    System.out.println("Add circle!");
    coordinate = new twoDCoordinate(100, 100);
    circle = factory.getShape("circle", "C", coordinate, red, 10);
    // Make command
    putShape = new PutShape(canvas1, "circle", "C", circle);
    // Invoke Command
    invoker = new Invoker(putShape);
    // Do the command
    invoker.press();

    // Put Square on canvas
    System.out.println("Add square!");
    coordinate = new twoDCoordinate(50, 100);
    square = factory.getShape("square", "S", coordinate, green, 10);
    // Make command
    putShape = new PutShape(canvas1, "square", "S", square);
    // Invoke Command
    invoker = new Invoker(putShape);
    // Do the command
    invoker.press();

    // Put Rectangle on canvas
    System.out.println("Add rectangle!");
    coordinate = new twoDCoordinate(150, 80);
    rectangle = factory.getShape("rectangle", "R", coordinate, blue, 10, 5);
    // Make command
    putShape = new PutShape(canvas1, "rectangle", "R", rectangle);
    // Invoke Command
    invoker = new Invoker(putShape);
    // Do the command
    invoker.press();

    System.out.println("Shows all the shapes on the canvas!:");
    System.out.println("-".repeat(35));
    // Print shapes
    printShapes = new PrintShapes(canvas1);
    invoker = new Invoker(printShapes);
    invoker.press();
    System.out.println("-".repeat(35));

    System.out.println("Take Snapshot!!!");
    // Take snapshot
    takeSnapshot = new SnapShotCanvas(canvas1, "First snapshot");
    // Invoke Command
    invoker = new Invoker(takeSnapshot);
    // Do the command
    invoker.press();

    System.out.println("Now remove Square on the canvas");
    System.out.println("And also...");
    removeShape = new RemoveShape(canvas1, "S");
    // Invoke Command
    invoker = new Invoker(removeShape);
    // Do the command
    invoker.press();
    System.out.println("Change the radius of the circle to 20");
    System.out.println();
    changeCircleSize = new ChangeCircleSize(canvas1, "circle", "C", 20);
    invoker = new Invoker(changeCircleSize);
    invoker.press();
    System.out.println("Print out again all of the shapes");
    // Print shapes
    printShapes = new PrintShapes(canvas1);
    invoker = new Invoker(printShapes);
    invoker.press();

    System.out.println("Take a snapshot again!");
    // Take snapshot
    takeSnapshot = new SnapShotCanvas(canvas1, "Second snapshot");
    // Invoke Command
    invoker = new Invoker(takeSnapshot);
    // Do the command
    invoker.press();

    System.out.println("Last, show the history of the snapshots!");
    showSnapshots = new PrintSnapshots(canvas1);
    // Invoke Command
    invoker = new Invoker(showSnapshots);
    // Do the command
    invoker.press();

    // ------------- smoke test for view
    String filenameOut = "test.html";
    try {
      PrintWriter writer = new PrintWriter(filenameOut);
      WebAlbum web = new WebAlbum(writer);
      web.displaySnapshots(canvas1.getAllSnapshot());
      writer.close();
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }

  }

}
