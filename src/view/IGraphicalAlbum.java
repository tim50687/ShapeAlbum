package view;

import java.util.LinkedHashMap;

/**
 * This interface defines the methods required for displaying a photo album in a graphical view.
 */
public interface IGraphicalAlbum {

  /**
   * Sets the snapshots data to be displayed in the graphical album.
   *
   * @param snapshots a LinkedHashMap of snapshots to be displayed
   */
  void setSnapshots(LinkedHashMap<String, String> snapshots);

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


}
