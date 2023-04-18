package controller;

import controller.command.Command;
import controller.command.Invoker;
import java.util.ArrayList;
import java.util.List;
import model.Canvas;
import view.IWebAlbum;

/**
 * The Controller class handles the interaction between the View and the Model.
 */
public class Controller {

  private Canvas canvas; // Model
  private IWebAlbum view; // View -> output
  private String inputFileName; // View -> Input
  private FileParser fileParser;
  private List<Command> commands; // List of commands should be executed

  /**
   * Instantiates a new Controller.
   *
   * @param view      the view
   * @param inputFile the input file
   * @param canvas    the canvas
   */
  public Controller(IWebAlbum view, String inputFile, Canvas canvas) {
    this.view = view;
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
    this.view.displaySnapshots(this.canvas.getAllSnapshot());
  }

}
