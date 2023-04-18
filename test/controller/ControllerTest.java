package controller;

import static org.junit.Assert.assertEquals;

import model.Canvas;
import org.junit.Before;
import org.junit.Test;
import view.IWebAlbum;

public class ControllerTest {

  private Controller controller;
  private Canvas canvas;
  private String inputFile;
  private IWebAlbum webAlbum;

  @Before
  public void setUp() {
    canvas = new Canvas(1600, 900);
  }

  @Test
  public void testRunOnDemo() {
    inputFile = "demo_input.txt";
    controller = new Controller(webAlbum, inputFile, canvas);
    controller.run();
    // Test the number of snapshots
    assertEquals(4, canvas.getAllSnapshot().size());
    // Test the shape inside the canvas
    assertEquals(1, canvas.getAllShape().size());
    // Test the oval that still on the canvas
    // Coordinate
    assertEquals(500, this.canvas.getShape("myoval").getCoordinate().getX());
    assertEquals(400, this.canvas.getShape("myoval").getCoordinate().getY());
    // Size
    assertEquals(60, this.canvas.getShape("myoval").getWidth());
    assertEquals(30, this.canvas.getShape("myoval").getHeight());

  }

  @Test
  public void testRunOnBuildings() {
    inputFile = "buildings.txt";
    controller = new Controller(webAlbum, inputFile, canvas);
    controller.run();
    // Test the number of snapshots
    assertEquals(3, canvas.getAllSnapshot().size());
    // Test the shape inside the canvas
    assertEquals(22, canvas.getAllShape().size());
    // Test shape on the canvas
    // Coordinate
    assertEquals(0, this.canvas.getShape("background").getCoordinate().getX());
    assertEquals(0, this.canvas.getShape("background").getCoordinate().getY());

    assertEquals(620, this.canvas.getShape("B3").getCoordinate().getX());
    assertEquals(445, this.canvas.getShape("B3").getCoordinate().getY());

    assertEquals(280, this.canvas.getShape("window002").getCoordinate().getX());
    assertEquals(500, this.canvas.getShape("window002").getCoordinate().getY());

    assertEquals(460, this.canvas.getShape("window003").getCoordinate().getX());
    assertEquals(500, this.canvas.getShape("window003").getCoordinate().getY());

    assertEquals(640, this.canvas.getShape("window004").getCoordinate().getX());
    assertEquals(500, this.canvas.getShape("window004").getCoordinate().getY());

    // Size
    assertEquals(800, this.canvas.getShape("background").getWidth());
    assertEquals(800, this.canvas.getShape("background").getHeight());

    assertEquals(100, this.canvas.getShape("B3").getWidth());
    assertEquals(305, this.canvas.getShape("B3").getHeight());

    assertEquals(20, this.canvas.getShape("window002").getWidth());
    assertEquals(20, this.canvas.getShape("window002").getHeight());

    assertEquals(20, this.canvas.getShape("window003").getWidth());
    assertEquals(20, this.canvas.getShape("window003").getHeight());

    assertEquals(20, this.canvas.getShape("window004").getWidth());
    assertEquals(20, this.canvas.getShape("window004").getHeight());

    assertEquals(20, this.canvas.getShape("window044").getWidth());
    assertEquals(20, this.canvas.getShape("window044").getHeight());

  }


}