package album;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Map;
import shape.IShape;

/**
 * The Snapshot class represents a snapshot of a collection of shapes at a specific point in time.
 */
public class Snapshot {

  private String ID;
  private Timestamp timeStamp;
  private String description;
  private Map<String, IShape> shapes;

  /**
   * A helper method that generates a SHA-256 hash of the timestamp in order to make unique ID.
   *
   * @param input timestamp
   * @return a byte array of hashed value
   * @throws NoSuchAlgorithmException
   */
  private byte[] getSHA(String input) throws NoSuchAlgorithmException {
    // GetInstance method is called with hashing SHA
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    return md.digest(input.getBytes(StandardCharsets.UTF_8));
  }

  /**
   * A helper method that turns the byte array return by hash function to String value.
   *
   * @param hash byte array
   * @return String value of the byte array
   */
  private String toHexString(byte[] hash) {
    BigInteger number = new BigInteger(1, hash);
    // Convert message digest into hex value
    StringBuilder hexString = new StringBuilder(number.toString(16));
    // Pad with leading zeros
    while (hexString.length() < 64) {
      hexString.insert(0, '0');
    }
    return hexString.toString();
  }

  /**
   * Instantiates a new Snapshot.
   *
   * @param description the description of the snapshot
   * @param shapes      all shapes on the canvas
   * @throws NoSuchAlgorithmException the no such algorithm exception
   */
  public Snapshot(String description, Map<String, IShape> shapes) throws NoSuchAlgorithmException {
    Timestamp time = new Timestamp(System.currentTimeMillis());
    this.ID = toHexString(getSHA(time.toString()));
    this.timeStamp = time;
    this.description = description;
    this.shapes = shapes;
  }

  /**
   * Gets description of the snapshot.
   *
   * @return the description
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Gets id of the snapshot.
   *
   * @return the id
   */
  public String getID() {
    return this.ID;
  }

  /**
   * Gets time stamp of the snapshot.
   *
   * @return the time stamp
   */
  public Timestamp getTimeStamp() {
    return this.timeStamp;
  }

  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    for (IShape shape : this.shapes.values()) {
      str.append(shape.toString());
      str.append("\n" + "-".repeat(20) + "\n");
    }
    return String.format("Snapshot ID: %s\nTimestamp: %s\nDescription: %s\nShape Information: \n",
        this.ID, this.timeStamp, this.description)
        + str.toString();
  }
}
