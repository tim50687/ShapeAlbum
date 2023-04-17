package controller.command;

/**
 * The Invoker class represents an object that invokes a Command object to perform an action.
 */
public class Invoker {

  Command theCommand;

  /**
   * Instantiates a new Invoker.
   *
   * @param newCommand the new command
   */
  public Invoker(Command newCommand) {
    theCommand = newCommand;
  }

  /**
   * Do the command.
   */
  public void press() {
    theCommand.execute();
  }

  /**
   * Undo the command.
   */
  public void pressUndo() {
    theCommand.undo();
  }


}
