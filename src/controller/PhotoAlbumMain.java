package controller;

import model.Canvas;
import view.GraphicalAlbum;
import view.IGraphicalAlbum;
import view.IWebAlbum;
import view.WebAlbum;

public class PhotoAlbumMain {

  public static void main(String[] args) {
    String inputFile;
    String outputFile;
    String view;
    // Default value for
    int x = 1000;
    int y = 1000;
    Controller controller;
    Canvas canvas = new Canvas(1600, 900);
    inputFile = "./src/assets/commandinputfile/buildings.txt";

    IWebAlbum album = new WebAlbum("test2.html");
    IGraphicalAlbum graphicalAlbum = new GraphicalAlbum(1000, 1000);
    controller = new Controller(album, graphicalAlbum, inputFile, canvas);
    controller.run();
    controller.sendSnapshotsToWebView();

    controller.sendSnapshotsToGraphicalView();


  }

}
