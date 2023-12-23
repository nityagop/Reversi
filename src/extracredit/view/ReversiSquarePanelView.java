package extracredit.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

import extracredit.model.FilledSquare;
import extracredit.model.NoDiscSquare;
import model.AbstractHexagon;
import model.ReadOnlyReversi;


/**
 * An implementation of the Java swing JPanel class
 * that is responsible for drawing the square grid
 * onto the window.
 */
public class ReversiSquarePanelView extends JPanel {

  private final ReadOnlyReversi model;


  /**
   * Constructs a ReversiSquarePanelView.
   *
   * @param model An instance of a ReadOnlyReversi model
   *              that is responsible for providing the
   *              functionality of the ReversiModel.
   */
  public ReversiSquarePanelView(ReadOnlyReversi model) {
    this.model = model;
    this.setBackground(Color.BLACK);
    this.getPreferredSize();

  }

  HashMap<ArrayList<Integer>, ArrayList<Integer>> squares = new HashMap<>();

  ArrayList<AbstractHexagon> selectedTiles = new ArrayList<AbstractHexagon>();

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(1000, 1000);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g.create();
    int size = this.model.getBoardSize();
    g2d.setColor(Color.DARK_GRAY);

    for (NoDiscSquare square : this.model.getBoard()) {
      ArrayList<Integer> originalCoords = new ArrayList<>();
      ArrayList<Integer> drawCoords = new ArrayList<>();
      int x = square.getRow();
      int y = square.getDiagonal();

      originalCoords.add(y);
      originalCoords.add(x);

      int squareSize = 40;
      int yCoord = this.getPreferredSize().height / 2 + (squareSize * (x - (size - 1)));
      int xCoord = this.getPreferredSize().width / 2 + squareSize * (y - (size - 1));

      drawCoords.add(xCoord);
      drawCoords.add(yCoord);

      squares.put(originalCoords, drawCoords);

      AbstractHexagon current = this.model.getHex(y, x);

      fillBoard(g2d, xCoord, yCoord, current);
    }
  }


  /**
   * Determines whether this tile in the grid is filled with a disc or
   * empty. If the disc is filled, depending on the player, it will be
   * filled with a black or white disc. Otherwise, the empty square
   * is grey.
   *
   * @param g2d     The Graphics2D instance responsible for drawing the hexagon.
   * @param x       The x coordinate for where the hexagon will be drawn.
   * @param y       The y coordinate for where the hexagon will be drawn.
   * @param current The current hexagon that will be drawn.
   */
  public void fillBoard(Graphics2D g2d, int x, int y, AbstractHexagon current) {
    if (current instanceof FilledSquare
            && ((FilledSquare) current).getColor() == Color.BLACK) {
      this.drawHexagon(g2d, x, y);
      g2d.setColor(Color.BLACK);
      g2d.fillOval(x - 1, y, 20, 20);
    }
    else if (current instanceof FilledSquare
            && ((FilledSquare) current).getColor() == Color.WHITE) {
      this.drawHexagon(g2d, x, y);
      g2d.setColor(Color.WHITE);
      g2d.fillOval(x - 1, y, 20, 20);
    }
    else {
      g2d.setColor(Color.GRAY);
      this.drawHexagon(g2d, x, y);
    }
  }

  /**
   * Draws a single square using built in Java swing methods.
   *
   * @param g2d    The Graphics2D instance responsible for drawing the hexagon.
   * @param xCoord The x coordinate for where the hexagon will be drawn.
   * @param yCoord The y coordinate for where the hexagon will be drawn.
   */
  public void drawHexagon(Graphics2D g2d, int xCoord, int yCoord) {
    int size = 40;

    g2d.setColor(Color.GRAY);
    g2d.fillRect((int) (xCoord + size * Math.cos(90 * 4)), (int) (yCoord + size * Math.cos(90 * 4)),
            40, 40);
    g2d.setColor(Color.BLACK);
    g2d.drawRect((int) (xCoord + size * Math.cos(90 * 4)), (int) (yCoord + size * Math.cos(90 * 4)),
            40, 40);
  }

  /**
   * Returns the logical coordinates.
   * of the grid and the actual coordinates based on this frame.
   *
   * @return A HashMap containing a list of the logical coordinates of this
   *        grid and a list of the coordinates based on this window.
   */
  public HashMap<ArrayList<Integer>, ArrayList<Integer>> getHexagonMap() {
    return squares;
  }


  /**
   * Draws a single square using built in Java swing methods but changes the
   * color to represent when a tile has been selected. Follows
   * the same process for drawing a gray square.
   *
   * @param g2d    The Graphics2D instance responsible for drawing the hexagon.
   * @param xCoord The x coordinate for where the hexagon will be drawn.
   * @param yCoord The y coordinate for where the hexagon will be drawn.
   */

  public void changeColor(Graphics2D g2d, int xCoord, int yCoord) {
    int size = 40;

    g2d.setColor(Color.CYAN);
    g2d.fillRect((int) (xCoord + size * Math.cos(90 * 4)), (int) (yCoord + size * Math.cos(90 * 4)),
            40, 40);
    g2d.setColor(Color.BLACK);
    g2d.drawRect((int) (xCoord + size * Math.cos(90 * 4)), (int) (yCoord + size * Math.cos(90 * 4)),
            40, 40);
  }

  /**
   * Adds to the list of tiles that have been selected by the given
   * player.
   * @param hex The tile that has been selected.
   */
  public void addSelectedTile(AbstractHexagon hex) {
    selectedTiles.add(hex);
  }

  /**
   * Determines the tile that has been selected most
   * recently.
   * @return The last tile that has been selected.
   */
  public AbstractHexagon getLastSelection() {
    return selectedTiles.get(selectedTiles.size() - 1);
  }







}
