package view;

import controller.Controller;
import model.Canvas;

public class Main {

  public static void main(String[] args) {
    Controller controller;
    Canvas canvas = new Canvas(1600, 900);
    String inputFile;
    inputFile = "./src/assets/commandinputfile/buildings.txt";

    IWebAlbum album = new WebAlbum("test2.html");
    IGraphicalAlbum graphicalAlbum = new GraphicalAlbum();
    controller = new Controller(album, graphicalAlbum, inputFile, canvas);
    controller.run();
    controller.sendSnapshotsToWebView();

    controller.sendSnapshotsToGraphicalView();


  }

}
