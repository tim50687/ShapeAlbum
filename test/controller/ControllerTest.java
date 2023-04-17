package controller;

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
  public void testRun() {
    inputFile = "demo_input.txt";
    controller = new Controller(webAlbum, inputFile, canvas);
    controller.run();
    // Test the number of snapshots
//    assertEquals(4, canvas.getAllSnapshot().size());
          
    canvas.printSnapshots();
  }


}