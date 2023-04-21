package controller;

import model.Canvas;
import model.ICanvasModel;
import view.GraphicalAlbum;
import view.IGraphicalAlbum;
import view.IWebAlbum;
import view.WebAlbum;

/**
 * The main class of the PhotoAlbum application.
 */
public class PhotoAlbumMain {

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    String inputFile = null;
    String outputFile = null;
    String view = null;
    // Default value for x and y
    int xmax = 1000;
    int ymax = 1000;
    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("-in")) {
        inputFile = "../src/assets/commandinputfile/" + args[++i];
        System.out.println(inputFile);
      } else if (args[i].equals("-out")) {
        outputFile = args[++i];
      } else if (args[i].equals("-view") || args[i].equals("-v")) {
        view = args[++i];
      } else if (i == args.length - 2) {
        xmax = Integer.parseInt(args[i]);
        ymax = Integer.parseInt(args[i + 1]);
        break;
      } else {
        System.err.println("Invalid arguments: " + args[i]);
        System.exit(1);
      }
    }

    // Checking mandatory argument
    if (inputFile == null || view == null) {
      System.out.println("Missing mandatory arguments: -in <inputFile> and -view <outputFile>");
      System.exit(1);
    }

    // Check valid view
    if (!view.equalsIgnoreCase("web") && !view.equalsIgnoreCase("graphical")) {
      System.out.println("View only has two options: -view <web> or -view <graphical>");
      System.exit(1);
    }

    // Check web view need output file
    if (view.equalsIgnoreCase("web") && outputFile == null) {
      System.out.println("Web view need output file!");
      System.exit(1);
    }

    // Create Model
    ICanvasModel canvas = new Canvas(1600, 900);
    // Create View
    IWebAlbum webAlbum = new WebAlbum(outputFile);
    IGraphicalAlbum graphicalAlbum = new GraphicalAlbum(xmax, ymax);
    // Create controller
    Controller controller = new Controller(webAlbum, graphicalAlbum, inputFile, canvas);
    // Run the command on Model to change the status
    controller.run();

    // Determine the type is web or graphical
    if (view.equalsIgnoreCase("web")) {
      controller.sendSnapshotsToWebView();
    } else if (view.equalsIgnoreCase("graphical")) {
      controller.sendSnapshotsToGraphicalView();
    }

  }

}
