package view;

import java.io.PrintWriter;
import java.util.LinkedHashMap;

public class WebAlbum implements IWebAlbum {

  private PrintWriter fileOut; // HTML file connection


  public WebAlbum(PrintWriter fileOut) {
    this.fileOut = fileOut;

  }

  @Override
  public void displaySnapshots(LinkedHashMap<String, String> snapshots) {
    int numberOfSnapshots; // Check how many snapshots that need to be displayed
    numberOfSnapshots = snapshots.size();
    this.fileOut.println("<html>");
    this.fileOut.println("<head>");
    this.fileOut.println("</head>");
    this.fileOut.println("<body>");
    this.fileOut.println("Tim you are the king");
    this.fileOut.println("</body>");
    this.fileOut.println("</html>");

    System.out.println("HTML file is processed!");
  }


}
