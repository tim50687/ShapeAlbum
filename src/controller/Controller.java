package controller;

import model.Canvas;
import controller.command.Command;
import java.util.List;
import view.IView;

public class Controller {

  private IView view;
  private Canvas canvas;
  private FileParser fileParser;
  private List<Command> commands; // List of commands should be executed

  public Controller(IView view, Canvas canvas, FileParser fileParser, List<Command> commands) {
    this.view = view;
    this.canvas = canvas;
    this.fileParser = fileParser;
    this.commands = commands;
  }
}
