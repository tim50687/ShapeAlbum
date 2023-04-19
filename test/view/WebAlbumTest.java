package view;

import static org.junit.Assert.assertEquals;

import controller.Controller;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Canvas;
import org.junit.Before;
import org.junit.Test;

/**
 * The type Web album test.
 */
public class WebAlbumTest {

  private final String inputFile = "./src/assets/commandinputfile/demo_input.txt";
  private final String outputFile = "./test/view/outputTest.html";
  private Canvas canvas;
  private IWebAlbum webAlbum;
  private IGraphicalAlbum graphicalAlbum;
  private Controller controller;


  /**
   * Sets up.
   */
  @Before
  public void setUp() {
    canvas = new Canvas(1600, 800);
    webAlbum = new WebAlbum(outputFile);
    graphicalAlbum = new GraphicalAlbum();
    controller = new Controller(webAlbum, graphicalAlbum, inputFile, canvas);
    // Do all the command from the input file
    controller.run();
  }

  /**
   * Test display snapshots.
   */
  @Test
  public void testDisplaySnapshots() {
    webAlbum.displaySnapshots(canvas.getAllSnapshot());
    try {
      FileInputStream fileIn = new FileInputStream(outputFile);
      byte[] data = fileIn.readAllBytes();
      String test = new String(data);
      // Remove the timestamp
      Pattern pattern = Pattern.compile(
          "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}.\\d{2,3}"); // the regex pattern
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