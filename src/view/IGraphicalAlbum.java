package view;

/**
 * This interface defines the methods required for displaying a photo album in a graphical view.
 */
public interface IGraphicalAlbum {

  /**
   * Displays a snapshot in the web view.
   *
   * @param snapshot A String representing the snapshot to be displayed
   */
  void displaySnapshot(String snapshot);

  /**
   * Advances to the next snapshot on the album.
   */
  void goNext();

  /**
   * Goes back to the previous snapshot on the album.
   */
  void goBack();

  /**
   * Searches for a specific snapshot on the album.
   */
  void searchSnapshot();

  /**
   * Displays an error message in the view.
   */
  void displayError();

  /**
   * Quits the application and closes the view.
   */
  void quit();
}
