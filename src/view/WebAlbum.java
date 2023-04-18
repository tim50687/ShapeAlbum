package view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;

public class WebAlbum implements IWebAlbum {

  private PrintWriter fileOut; // HTML file connection
  private static final int CANVAS_WIDTH = 1600;
  private static final int CANVAS_HEIGHT = 900;

  // Detail for snapshot title
  private static final int SNAPSHOT_TITLE_UPPER_LEFT_X = 5;
  private static final int SNAPSHOT_TITLE_UPPER_LEFT_Y = 5;
  private static final int SNAPSHOT_TITLE_WIDTH = 800;
  private static final int SNAPSHOT_TITLE_HEIGHT = 80;
  private static final String SNAPSHOT_TITLE_COLOR = "(255, 182, 193)";

  // Detail for snapshot subtitle
  private static final int SNAPSHOT_SUBTITLE_X = 10;
  private static final int SNAPSHOT_SUBTITLE_Y = 35;
  private static final int SNAPSHOT_SUBTITLE_FONT = 30;
  private static final String SNAPSHOT_SUBTITLE_COLOR = "(0, 0, 0)";

  // Detail for snapshot description
  private static final int SNAPSHOT_DESCRIPTION_X = 10;
  private static final int SNAPSHOT_DESCRIPTION_Y = 70;
  private static final int SNAPSHOT_DESCRIPTION_FONT = 20;
  private static final String SNAPSHOT_DESCRIPTION_COLOR = "(0, 0, 0)";

  // Detail for snapshot timestamp
  private static final int SNAPSHOT_TIMESTAMP_X = 600;
  private static final int SNAPSHOT_TIMESTAMP_Y = 70;
  private static final int SNAPSHOT_TIMESTAMP_FONT = 15;
  private static final String SNAPSHOT_TIMESTAMP_COLOR = "(0, 0, 0)";


  public WebAlbum(String outputFile) {
    File file = new File(outputFile); // Will create file if not exist
    try {
      this.fileOut = new PrintWriter(file);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void displaySnapshots(LinkedHashMap<String, String> snapshots) {
    int numberOfSnapshots; // Check how many snapshots that need to be displayed
    numberOfSnapshots = snapshots.size();
    this.fileOut.println("<html>");
    this.fileOut.println("<head>");
    this.fileOut.println("</head>");
    this.fileOut.println("<body>");

    // Do the command
    int count = 0;
    String timestamp = "";
    String description = "";
    String type = "";
    int x = 0;
    int y = 0;
    int width = 0;
    int height = 0;
    int r = 0;
    int g = 0;
    int b = 0;

    // Put the shape in snapshot to the web album
    for (String snapshot : snapshots.values()) {
      this.fileOut.println(String.format("<svg width = \"%d\" height= \"%d\">", CANVAS_WIDTH,
          CANVAS_HEIGHT));
      count += 1;
      String[] lines = snapshot.split("\n");
      for (String line : lines) {
        // Get the timestamp
        if (line.startsWith("Timestamp")) {
          timestamp = line.split(": ")[1];
          // Get the type of the shape
        } else if (line.startsWith("Description")) {
          // Check if it is an empty description
          if (line.split(": ").length == 1) {
            description = "";
          } else {
            description = line.split(": ")[1];
          }
          // Whenever get the description, then write the title (timestamp, description) of snapshot on web
          fileOut.println(
              "<rect x=\"" + SNAPSHOT_TITLE_UPPER_LEFT_X + "\" y=\"" + SNAPSHOT_TITLE_UPPER_LEFT_Y
                  + "\" width=\""
                  + SNAPSHOT_TITLE_WIDTH + "\" height=\"" + SNAPSHOT_TITLE_HEIGHT
                  + "\" stroke=\"red\" stroke-width=\"1\" fill=\"rgb" + SNAPSHOT_TITLE_COLOR
                  + "\" />");
          fileOut.println(
              "<text x=\"" + SNAPSHOT_SUBTITLE_X + "\" y=\"" + SNAPSHOT_SUBTITLE_Y
                  + "\" font-family=\"" + "Garamond"
                  + "\" font-size=\"" + SNAPSHOT_SUBTITLE_FONT + "\" fill=\"rgb"
                  + SNAPSHOT_SUBTITLE_COLOR + "\">Snapshot No."
                  + count + "</text>");
          fileOut.println(
              "<text x=\"" + SNAPSHOT_DESCRIPTION_X + "\" y=\"" + SNAPSHOT_DESCRIPTION_Y
                  + "\" font-family=\"" + "Garamond"
                  + "\" font-size=\"" + SNAPSHOT_DESCRIPTION_FONT + "\" fill=\"rgb"
                  + SNAPSHOT_DESCRIPTION_COLOR + "\">"
                  + description + "</text>");
          fileOut.println(
              "<text x=\"" + SNAPSHOT_TIMESTAMP_X + "\" y=\"" + SNAPSHOT_TIMESTAMP_Y
                  + "\" font-family=\"" + "Garamond"
                  + "\" font-size=\"" + SNAPSHOT_TIMESTAMP_FONT + "\" fill=\"rgb"
                  + SNAPSHOT_TIMESTAMP_COLOR + "\">"
                  + timestamp + "</text>");
        } else if (line.startsWith("Type")) {
          type = line.split(": ")[1];
          // Get the information of the shape
        } else if (line.startsWith("Upper Left")) {
          String[] parts = line.split(": ")[1].replace("(", "").replace(")", "").split(", ");
          x = Integer.parseInt(parts[0]);
          y = Integer.parseInt(parts[1]);
          width = Integer.parseInt(line.split(": ")[2].split(",")[0]);
          height = Integer.parseInt(line.split(": ")[3].split(",")[0]);
          r = Integer.parseInt(
              line.split(": ")[4].replace("(", "").replace(")", "").split(", ")[0]);
          g = Integer.parseInt(
              line.split(": ")[4].replace("(", "").replace(")", "").split(", ")[1]);
          b = Integer.parseInt(
              line.split(": ")[4].replace("(", "").replace(")", "").split(", ")[2]);
        } else if (line.startsWith("---")) {
          // if reach "--------------------", put a shape on web
          String shape = "";
          if (type.equalsIgnoreCase("rectangle")) {
            shape =
                "<rect x=\"" + (x + SNAPSHOT_TITLE_UPPER_LEFT_X) + "\" y=\"" + (y
                    + SNAPSHOT_TITLE_UPPER_LEFT_Y + SNAPSHOT_TITLE_HEIGHT) + "\" width=\"" + width
                    + "\" height=\"" + height
                    + "\" fill=\"rgb" + "(" + r + ", " + g + ", " + b + ")" + "\" />";
          } else if (type.equalsIgnoreCase("Oval")) {
            shape =
                "<ellipse cx=\"" + (x + SNAPSHOT_TITLE_UPPER_LEFT_X) + "\" cy=\""
                    + (y + SNAPSHOT_TITLE_UPPER_LEFT_Y + SNAPSHOT_TITLE_HEIGHT) + "\" rx=\""
                    + width + "\" ry=\"" + height
                    + "\" fill=\"rgb" + "(" + r + ", " + g + ", " + b + ")" + "\" />";
          }
          fileOut.println(shape);
        }

      }
      this.fileOut.println("</svg>");
    }

    this.fileOut.println("</body>");
    this.fileOut.println("</html>");

    System.out.println("HTML file is processed!");
    this.fileOut.close();
  }


}
