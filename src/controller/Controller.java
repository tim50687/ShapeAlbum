package controller;

import controller.command.Command;
import controller.command.Invoker;
import java.util.ArrayList;
import java.util.List;
import model.ICanvasModel;
import view.IGraphicalAlbum;
import view.IWebAlbum;

/**
 * The Controller class handles the interaction between the View and the Model.
 */
public class Controller {

  private ICanvasModel canvas; // Model
  private IWebAlbum webAlbum; // View -> output
  private IGraphicalAlbum graphicalAlbum; // View -> output
  private String inputFileName; // View -> Input
  private FileParser fileParser;
  private List<Command> commands; // List of commands should be executed

  /**
   * Instantiates a new Controller.
   *
   * @param webAlbum  the web album
   * @param inputFile the input file
   * @param canvas    the canvas
   */
  public Controller(IWebAlbum webAlbum, IGraphicalAlbum graphicalAlbum, String inputFile,
      ICanvasModel canvas) {
    this.webAlbum = webAlbum;
    this.graphicalAlbum = graphicalAlbum;
    this.canvas = canvas;
    this.inputFileName = inputFile;
    this.fileParser = new FileParser(this.inputFileName, this.canvas);
    this.commands = new ArrayList<>();
  }

  /**
   * Change the state in the model.
   */
  public void run() { // Change the state on the model based on commands
    this.fileParser.parseCommands();
    this.commands = this.fileParser.getCommands(); // Get all commands
    Invoker invoker;
    for (Command eachCommand : this.commands) {
      invoker = new Invoker(eachCommand);
      invoker.press();
    }
  }

  /**
   * Send snapshots to view.
   */
  public void sendSnapshotsToWebView() {
    this.webAlbum.displaySnapshots(this.canvas.getAllSnapshot());
  }

  public void sendSnapshotsToGraphicalView() {
    this.graphicalAlbum.setSnapshots(this.canvas.getAllSnapshot());
  }

}
