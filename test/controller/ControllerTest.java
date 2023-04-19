package controller;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Canvas;
import org.junit.Before;
import org.junit.Test;
import view.IWebAlbum;
import view.WebAlbum;

/**
 * The type Controller test.
 */
public class ControllerTest {

  private Controller controller;
  private Canvas canvas;
  private String inputFile;
  private final String outputFile = "./test/controller/outputTest.html";
  private IWebAlbum webAlbum;

  /**
   * Sets up.
   */
  @Before
  public void setUp() {
    canvas = new Canvas(1600, 900);
  }

  /**
   * Test run on demo_input.txt.
   */
  @Test
  public void testRunOnDemo() {
    inputFile = "./src/assets/commandinputfile/demo_input.txt";
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

  /**
   * Test run on buildings.txt.
   */
  @Test
  public void testRunOnBuildings() {
    inputFile = "./src/assets/commandinputfile/buildings.txt";
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

  /**
   * Test send snapshots to web view.
   */
  @Test
  public void testSendSnapshotsToWebView() {
    inputFile = "./src/assets/commandinputfile/demo_input.txt";
    webAlbum = new WebAlbum(outputFile);
    controller = new Controller(webAlbum, inputFile, canvas);
    controller.run();
    controller.sendSnapshotsToWebView();
    try {
      FileInputStream fileIn = new FileInputStream(outputFile);
      byte[] data = fileIn.readAllBytes();
      String test = new String(data);
      // Remove the timestamp
      Pattern pattern = Pattern.compile(
          "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}.\\d{3}"); // the regex pattern
      Matcher matcher;
      matcher = pattern.matcher(test);
      test = matcher.replaceAll("");
      assertEquals("<html>\n"
          + "<head>\n"
          + "</head>\n"
          + "<body>\n"
          + "<svg width = \"1600\" height= \"900\">\n"
          + "<rect x=\"5\" y=\"5\" width=\"800\" height=\"80\" stroke=\"red\" stroke-width=\"1\" fill=\"rgb(255, 182, 193)\" />\n"
          + "<text x=\"10\" y=\"35\" font-family=\"Garamond\" font-size=\"30\" fill=\"rgb(0, 0, 0)\">Snapshot No.1</text>\n"
          + "<text x=\"10\" y=\"70\" font-family=\"Garamond\" font-size=\"20\" fill=\"rgb(0, 0, 0)\">After first selfie</text>\n"
          + "<text x=\"600\" y=\"70\" font-family=\"Garamond\" font-size=\"15\" fill=\"rgb(0, 0, 0)\"></text>\n"
          + "<rect x=\"205\" y=\"285\" width=\"50\" height=\"100\" fill=\"rgb(255, 0, 0)\" />\n"
          + "<ellipse cx=\"505\" cy=\"185\" rx=\"60\" ry=\"30\" fill=\"rgb(0, 255, 1)\" />\n"
          + "</svg>\n"
          + "<svg width = \"1600\" height= \"900\">\n"
          + "<rect x=\"5\" y=\"5\" width=\"800\" height=\"80\" stroke=\"red\" stroke-width=\"1\" fill=\"rgb(255, 182, 193)\" />\n"
          + "<text x=\"10\" y=\"35\" font-family=\"Garamond\" font-size=\"30\" fill=\"rgb(0, 0, 0)\">Snapshot No.2</text>\n"
          + "<text x=\"10\" y=\"70\" font-family=\"Garamond\" font-size=\"20\" fill=\"rgb(0, 0, 0)\">2nd selfie</text>\n"
          + "<text x=\"600\" y=\"70\" font-family=\"Garamond\" font-size=\"15\" fill=\"rgb(0, 0, 0)\"></text>\n"
          + "<rect x=\"105\" y=\"385\" width=\"25\" height=\"100\" fill=\"rgb(255, 0, 0)\" />\n"
          + "<ellipse cx=\"505\" cy=\"185\" rx=\"60\" ry=\"30\" fill=\"rgb(0, 255, 1)\" />\n"
          + "</svg>\n"
          + "<svg width = \"1600\" height= \"900\">\n"
          + "<rect x=\"5\" y=\"5\" width=\"800\" height=\"80\" stroke=\"red\" stroke-width=\"1\" fill=\"rgb(255, 182, 193)\" />\n"
          + "<text x=\"10\" y=\"35\" font-family=\"Garamond\" font-size=\"30\" fill=\"rgb(0, 0, 0)\">Snapshot No.3</text>\n"
          + "<text x=\"10\" y=\"70\" font-family=\"Garamond\" font-size=\"20\" fill=\"rgb(0, 0, 0)\"></text>\n"
          + "<text x=\"600\" y=\"70\" font-family=\"Garamond\" font-size=\"15\" fill=\"rgb(0, 0, 0)\"></text>\n"
          + "<rect x=\"105\" y=\"385\" width=\"25\" height=\"100\" fill=\"rgb(0, 0, 255)\" />\n"
          + "<ellipse cx=\"505\" cy=\"485\" rx=\"60\" ry=\"30\" fill=\"rgb(0, 255, 1)\" />\n"
          + "</svg>\n"
          + "<svg width = \"1600\" height= \"900\">\n"
          + "<rect x=\"5\" y=\"5\" width=\"800\" height=\"80\" stroke=\"red\" stroke-width=\"1\" fill=\"rgb(255, 182, 193)\" />\n"
          + "<text x=\"10\" y=\"35\" font-family=\"Garamond\" font-size=\"30\" fill=\"rgb(0, 0, 0)\">Snapshot No.4</text>\n"
          + "<text x=\"10\" y=\"70\" font-family=\"Garamond\" font-size=\"20\" fill=\"rgb(0, 0, 0)\">Selfie after removing the rectangle from the picture</text>\n"
          + "<text x=\"600\" y=\"70\" font-family=\"Garamond\" font-size=\"15\" fill=\"rgb(0, 0, 0)\"></text>\n"
          + "<ellipse cx=\"505\" cy=\"485\" rx=\"60\" ry=\"30\" fill=\"rgb(0, 255, 1)\" />\n"
          + "</svg>\n"
          + "</body>\n"
          + "</html>\n", test);
      fileIn.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

}