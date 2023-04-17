package controller;

import static org.junit.Assert.assertEquals;

import model.Canvas;
import controller.command.Command;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * The type File parser test.
 */
public class FileParserTest {

  private FileParser parser;
  private Canvas canvas;
  private List<Command> commands;

  /**
   * Sets up.
   */
  @Before
  public void setUp() {
    canvas = new Canvas(1600, 900);
  }

  /**
   * Test parse commands.
   */
  @Test
  public void testParseCommands() {
    parser = new FileParser("demo_input.txt", canvas);
    parser.parseCommands();
    commands = parser.getCommands();
    assertEquals(
        "[Put myrect on canvas., Put myoval on canvas., Take a snapshot!, Move myrect's coordinate to X: 300, Y: 200., Change myrect's size to Width: 25, Height: 100., Move myrect's coordinate to X: 100, Y: 300., Take a snapshot!, Change myrect's color to (0, 0, 255)., Move myoval's coordinate to X: 500, Y: 400., Take a snapshot!, Remove myrect from canvas., Take a snapshot!]",
        commands.toString());
  }

  /**
   * Test parse commands building.
   */
  @Test
  public void testParseCommandsBuilding() {
    parser = new FileParser("buildings.txt", canvas);
    parser.parseCommands();
    commands = parser.getCommands();
    assertEquals(
        "[Put background on canvas., Put B0 on canvas., Put B1 on canvas., Put B2 on canvas., Put B3 on canvas., Put window000 on canvas., Put window001 on canvas., Put window010 on canvas., Put window011 on canvas., Take a snapshot!, Put window002 on canvas., Put window021 on canvas., Put window022 on canvas., Put window200 on canvas., Take a snapshot!, Put window003 on canvas., Put window033 on canvas., Put window333 on canvas., Put window313 on canvas., Put window004 on canvas., Put window044 on canvas., Put window444 on canvas., Put window414 on canvas., Put moon on canvas., Take a snapshot!]",
        commands.toString());
  }

  /**
   * Test invalid input file.
   */
  @Test
  public void testInvalidInputFile() {
    // Redirect the console output to the outcontent stream
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    parser = new FileParser("building.txt", canvas);
    parser.parseCommands();
    assertEquals("File not found: building.txt\n", outContent.toString());
  }
}