package command;

/**
 * The Command interface represents command that can be executed and undone.
 */
public interface Command {

  /**
   * Execute the command.
   */
  void execute();

  /**
   * Undo the command.
   */
  void undo();

}
