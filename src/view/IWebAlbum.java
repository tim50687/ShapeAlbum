package view;

import java.util.LinkedHashMap;

/**
 * This interface defines the methods required for displaying a photo album in a web view.
 */
public interface IWebAlbum {

  /**
   * Displays a list of snapshots in the web view.
   *
   * @param snapshots A list of Snapshot objects to be displayed
   */
  void displaySnapshots(LinkedHashMap<String, String> snapshots);

}
