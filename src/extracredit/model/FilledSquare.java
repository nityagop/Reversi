package extracredit.model;

import java.awt.Color;

import model.AbstractHexagon;

/**
 * A class to represent a tile that has a disc placed in it
 * in a game of Square Reversi.
 */
public class FilledSquare extends NoDiscSquare {

  private final Color col;

  /**
   * Constructs a FilledSquare.
   * @param diagonal The x coordinate of the square grid.
   * @param row The y coordinate of the square grid.
   * @param col The color of the disc placed inside the tile.
   */
  public FilledSquare(int diagonal, int row, Color col) {
    super(diagonal, row);
    this.col = col;
  }

  /**
   * Constructs a FilledSquare based on inputs.
   *
   * @param diagonal    is the 0 based index (from the left) x coordinate of the tile.
   * @param row         is the 0 based index (from the top) y coordinate of the tile
   * @param right       the tile's right neighbor.
   * @param left        the tile's left neighbor.
   * @param topRight    the tile's top right neighbor.
   * @param topLeft     the tile's top left neighbor.
   * @param bottomRight the tile's bottom right neighbor
   * @param bottomLeft  the tile's bottom left neighbor
   * @param col         the color of the tile (black or white)
   */
  public FilledSquare(int diagonal, int row, AbstractHexagon right, AbstractHexagon left,
                       AbstractHexagon topRight, AbstractHexagon topLeft,
                       AbstractHexagon bottomRight, AbstractHexagon bottomLeft, AbstractHexagon top,
                      AbstractHexagon bottom, Color col) {
    super(diagonal, row, right, left, topRight, topLeft, bottomRight, bottomLeft, top,
            bottom);
    this.col = col;

  }

  /**
   * Access the color of the tile.
   *
   * @return the color of the given tile.
   */
  public Color getColor() {
    return this.col;
  }
}
