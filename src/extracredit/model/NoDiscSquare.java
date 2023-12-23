package extracredit.model;

import model.AbstractHexagon;
import model.EmptyHexagon;
import model.Player;

import java.util.List;
import java.awt.Color;
import java.util.ArrayList;

/**
 * A class to represent a tile that does not have a disc
 * in it but which a player may place a disc inside.  It has a diagonal and
 * row coordinate to represent its position and 8 neighbor tiles.
 */
public class NoDiscSquare extends AbstractHexagon {

  //the 0-based index (from the left) diagonal coordinate of this tile
  //x coordinate
  private final int diagonal;

  //the 0-based index (from the top) row coordinate of this tile
  //y coordinate
  private final int row;

  // left neighbor tile of this tile
  private AbstractHexagon left;

  //top left neighbor tile of this tile
  private AbstractHexagon topLeft;

  // top right neighbor tile of this tile
  private AbstractHexagon topRight;

  // right neighbor tile of this tile
  private AbstractHexagon right;

  // bottom left neighbor tile of this tile
  private AbstractHexagon bottomLeft;

  //bottom right neighbor tile of this tile
  private AbstractHexagon bottomRight;

  //bottom neighbor tile of this tile
  private AbstractHexagon top;

  //top neighbor tile of this tile
  private AbstractHexagon bottom;

  /**
   * Constructs a NoDiscSquare.
   * @param diagonal The x coordinate of the square grid.
   * @param row The y coordinate of the square grid.
   */
  public NoDiscSquare(int diagonal, int row) {
    super(diagonal, row);
    if (diagonal < 0 || row < 0) {
      throw new IllegalArgumentException();
    }
    this.diagonal = diagonal;
    this.row = row;
    this.left = new EmptyHexagon(-1, -1);
    this.topLeft = new EmptyHexagon(-1, -1);
    this.right = new EmptyHexagon(-1, -1);
    this.topRight = new EmptyHexagon(-1, -1);
    this.bottomLeft = new EmptyHexagon(-1, -1);
    this.bottomRight = new EmptyHexagon(-1, -1);
    this.top = new EmptyHexagon(-1, -1);
    this.bottom = new EmptyHexagon(-1, -1);
  }

  /**
   * Constructs a NoDiscSquare where the arguments can be inputted.
   *
   * @param diagonal    is the 0 based index (from the left) x coordinate of the tile.
   * @param row         is the 0 based index (from the top) y coordinate of the tile
   * @param right       the tile's right neighbor.
   * @param left        the tile's left neighbor.
   * @param topRight    the tile's topRight neighbor.
   * @param topLeft     the tile's topLeft neighbor.
   * @param bottomRight the tile's bottomRight neighbor.
   * @param bottomLeft  the tile's bottomLeft neighbor.
   */
  public NoDiscSquare(int diagonal, int row, AbstractHexagon right, AbstractHexagon left,
                       AbstractHexagon topRight, AbstractHexagon topLeft,
                       AbstractHexagon bottomRight, AbstractHexagon bottomLeft, AbstractHexagon top,
                      AbstractHexagon bottom) {
    super(diagonal, row);
    if (diagonal < 0 || row < 0) {
      throw new IllegalArgumentException();
    }
    this.diagonal = diagonal;
    this.row = row;
    this.right = right;
    this.left = left;
    this.topRight = topRight;
    this.topLeft = topLeft;
    this.bottomRight = bottomRight;
    this.bottomLeft = bottomLeft;
    this.top = top;
    this.bottom = bottom;
  }

  /**
   * Gets the 0-based index (from the left) diagonal (x) coordinate of this tile.
   * @return the diagonal coordinate of this tile.
   */
  public int getDiagonal() {
    return this.diagonal;
  }


  /**
   * Gets the 0-based index (from the top) row (y) coordinate of this tile.
   * @return the row coordinate of this tile.
   */
  public int getRow() {
    return this.row;
  }


  /**
   * Sets the right neighbor of this tile to the given tile.
   * @param hex the given AbstractHexagon tile.
   */
  public void setRight(AbstractHexagon hex) {
    this.right = hex;
  }

  /**
   * Sets the bottom right neighbor of this tile to the given tile.
   * @param hex the given AbstractHexagon tile.
   */
  public void setBottomRight(AbstractHexagon hex) {
    this.bottomRight = hex;

  }

  /**
   * Sets the left neighbor of this tile to the given tile.
   * @param hex the given AbstractHexagon tile.
   */
  public void setLeft(AbstractHexagon hex) {
    this.left = hex;

  }

  /**
   * Sets the top right neighbor of this tile to the given tile.
   * @param hex the given AbstractHexagon tile.
   */
  public void setTopRight(AbstractHexagon hex) {
    this.topRight = hex;
  }

  /**
   * Sets the top left neighbor of this tile to the given tile.
   * @param hex the given AbstractHexagon tile.
   */
  public void setTopLeft(AbstractHexagon hex) {
    this.topLeft = hex;
  }

  /**
   * Sets the bottom left neighbor of this tile to the given tile.
   * @param hex the given AbstractHexagon tile.
   */
  public void setBottomLeft(AbstractHexagon hex) {
    this.bottomLeft = hex;
  }

  public void setTop(AbstractHexagon hex) {
    this.top = hex;
  }

  public void setBottom(AbstractHexagon hex) {
    this.bottom = hex;
  }

  /**
   * Checks to see if all the surrounding tiles of the given NoDiscTile are empty.
   * @return true if all the neighbor tiles of this tile have no disc inside it or are an
   *         EmptyHexagon,false if even one of the neighbor tiles have a disc inside it
   */
  protected boolean allNeighborsEmpty() {
    return (!(this.topLeft instanceof FilledSquare)
            && !(this.left instanceof FilledSquare)
            && !(this.bottomLeft instanceof FilledSquare)
            && !(this.topRight instanceof FilledSquare)
            && !(this.right instanceof FilledSquare)
            && !(this.bottomRight instanceof FilledSquare));
  }


  /**
   * Finds and returns a list of tiles that directly neighbor this tile and contain a disc of the
   * opposite player.
   * @param col the given color to of the player who is currently playing.
   * @return a list of filled hexagons of neighboring tiles of the opposite player's color.
   */
  public List<FilledSquare> getFilledHexagons(Color col) {
    List<FilledSquare> filledHexagons = new ArrayList<>();

    if (this.topLeft instanceof FilledSquare) {
      FilledSquare topLeft = (FilledSquare) this.topLeft;
      if (topLeft.getColor() != col) {
        filledHexagons.add((FilledSquare) this.topLeft);
      }
    }
    if (this.topRight instanceof FilledSquare) {
      FilledSquare topRight = (FilledSquare) this.topRight;
      if (topRight.getColor() != col) {
        filledHexagons.add((FilledSquare) this.topRight);

      }
    }
    if (right instanceof FilledSquare) {
      FilledSquare right = (FilledSquare) this.right;
      if (right.getColor() != col) {
        filledHexagons.add((FilledSquare) this.right);
      }
    }
    if (this.left instanceof FilledSquare) {
      FilledSquare left = (FilledSquare) this.left;
      if (left.getColor() != col) {
        filledHexagons.add((FilledSquare) this.left);
      }
    }
    if (this.bottomLeft instanceof FilledSquare) {
      FilledSquare bottomLeft = (FilledSquare) this.bottomLeft;
      if (bottomLeft.getColor() != col) {
        filledHexagons.add((FilledSquare) this.bottomLeft);
      }
    }
    if (this.bottomRight instanceof FilledSquare) {
      FilledSquare bottomRight = (FilledSquare) this.bottomRight;
      if (bottomRight.getColor() != col) {
        filledHexagons.add((FilledSquare) this.bottomRight);
      }
    }
    if (this.top instanceof FilledSquare) {
      FilledSquare top = (FilledSquare) this.top;
      if (top.getColor() != col) {
        filledHexagons.add((FilledSquare) this.top);
      }
    }
    if (this.bottom instanceof FilledSquare) {
      FilledSquare bottom = (FilledSquare) this.bottom;
      if (bottom.getColor() != col) {
        filledHexagons.add((FilledSquare) this.bottom);
      }
    }
    return filledHexagons;
  }

  /**
   * Accesses the right neighbor of this tile.
   * @return the right neighbor.
   */
  public AbstractHexagon getRight() {
    return this.right;
  }

  /**
   * Accesses the bottom right neighbor of this tile.
   * @return the bottom right neighbor.
   */
  public AbstractHexagon getBottomRight() {
    return this.bottomRight;
  }

  /**
   * Accesses the left neighbor of this tile.
   * @return the left neighbor.
   */
  public AbstractHexagon getLeft() {
    return this.left;
  }

  /**
   * Accesses the top right neighbor of this tile.
   * @return the top right neighbor.
   */
  public AbstractHexagon getTopRight() {
    return this.topRight;
  }

  /**
   * Accesses the top left neighbor of this tile.
   * @return the top left neighbor.
   */
  public AbstractHexagon getTopLeft() {
    return this.topLeft;
  }

  /**
   * Accesses the bottom left neighbor of this tile.
   * @return the bottom left neighbor.
   */
  public AbstractHexagon getBottomLeft() {
    return this.bottomLeft;
  }

  /**
   * Accesses the bottom neighbor of this tile.
   * @return the bottom neighbor.
   */
  public AbstractHexagon getBottom() {
    return this.bottom;
  }

  /**
   * Accesses the top neighbor of this tile.
   * @return the top left neighbor.
   */
  public AbstractHexagon getTop() {
    return this.top;
  }

  /**
   * A helper method that finds and returns a list of the lists of tiles in different directions
   * that neighbor the tile the current player wants to move in and can be flipped to the current
   * player's tiles according to the rules of the game. They are tiles
   * sandwiched between two tiles of the current player.
   * @param lofh is the list of neighbor tiles with discs of the opposite player's color.
   * @param from is the tile the player potentially wants to make a move in.
   * @param col is the color of the current player.
   * @return a list that contains the lists of tiles with discs in different directions of this tile
   *         that can be flipped to this player's tiles.
   */
  public List<List<FilledSquare>> checkAll(List<FilledSquare> lofh,
                                            NoDiscSquare from, Color col) {
    List<List<FilledSquare>> validLine = new ArrayList<>();
    for (FilledSquare hex : lofh) {
      ArrayList<FilledSquare> l = new ArrayList<>();
      // bottomLeft case
      if ((from.getDiagonal() - hex.getDiagonal() == 1) && (hex.getRow() - from.getRow() == 1)) {
        this.check("bottom left", hex, col, l);
      }
      // bottomRight
      if ((hex.getDiagonal() - from.getDiagonal() == 1) && (hex.getRow() - from.getRow() == 1)) {
        this.check("bottom right", hex, col, l);
      }
      // topRight case
      else if ((hex.getDiagonal() - from.getDiagonal() == 1) &&
              (from.getRow() - hex.getRow() == 1)) {
        this.check("top right", hex, col, l);

      }
      // right case
      else if ((hex.getDiagonal() - from.getDiagonal() == 1) && hex.getRow() == from.getRow()) {
        this.check("right", hex, col, l);
      }

      // left case
      else if ((from.getDiagonal() - hex.getDiagonal() == 1) && hex.getRow() == from.getRow()) {
        this.check("left", hex, col, l);
      }

      // topLeft case
      else if ((from.getDiagonal() - hex.getDiagonal() == 1) &&
              (from.getRow() - hex.getRow() == 1)) {
        this.check("top left", hex, col, l);
      }
      //top case
      else if ((from.getDiagonal() == hex.getDiagonal()) &&
              (from.getRow() - hex.getRow() == 1)) {
        this.check("top", hex, col, l);
      }

      //bottom case
      else if ((from.getDiagonal() == hex.getDiagonal()) &&
              (hex.getRow() - from.getRow() == 1)) {
        this.check("bottom", hex, col, l);
      }

      if (!l.isEmpty() && l.get(l.size() - 1).getColor() == col) {
        l.remove(l.size() - 1);
        validLine.add(l);
      }
    }
    return validLine;

  }

  // A helper method that checks if the straight line neighbors of the given filled hexagon tile
  // in one direction is of the opposite player's color and returns a list of those
  // subsequent neighbors.
  private void check(String str, FilledSquare hex, Color col, List<FilledSquare> l) {

    if (str.equals("bottom left")) {
      if (hex.getBottomLeft() instanceof FilledSquare) {
        FilledSquare h = (FilledSquare) hex.getBottomLeft();
        if (h.getColor() == col) {
          l.add(hex);
          l.add(h);

        } else {
          l.add(hex);
          this.check("bottom left", h, col, l);
        }
      }
    } else if (str.equals("bottom right")) {
      if (hex.getBottomRight() instanceof FilledSquare) {
        FilledSquare h = (FilledSquare) hex.getBottomRight();
        if (h.getColor() == col) {
          l.add(hex);
          l.add(h);

        } else {
          l.add(hex);
          this.check("bottom right", h, col, l);
        }
      }
    } else if (str.equals("top left")) {
      if (hex.getTopLeft() instanceof FilledSquare) {
        FilledSquare h = (FilledSquare) hex.getTopLeft();
        if (h.getColor() == col) {
          l.add(hex);
          l.add(h);

        } else {

          l.add(hex);
          this.check("top left", h, col, l);
        }
      }
    } else if (str.equals("top right")) {
      if (hex.getTopRight() instanceof FilledSquare) {
        FilledSquare h = (FilledSquare) hex.getTopRight();
        if (h.getColor() == col) {
          l.add(hex);
          l.add(h);

        } else {

          l.add(hex);
          this.check("top right", h, col, l);
        }
      }
    } else if (str.equals("right")) {
      if (hex.getRight() instanceof FilledSquare) {
        FilledSquare h = (FilledSquare) hex.getRight();
        if (h.getColor() == col) {
          l.add(hex);
          l.add(h);

        } else {
          l.add(hex);
          this.check("right", h, col, l);
        }
      }
    } else if (str.equals("left")) {
      if (hex.getLeft() instanceof FilledSquare) {
        FilledSquare h = (FilledSquare) hex.getLeft();
        if (h.getColor() == col) {
          l.add(hex);
          l.add(h);

        } else {
          l.add(hex);
          this.check("left", h, col, l);
        }
      }
    }
    else if (str.equals("top")) {
      if (hex.getTop() instanceof FilledSquare) {
        FilledSquare h = (FilledSquare) hex.getTop();
        if (h.getColor() == col) {
          l.add(hex);
          l.add(h);

        } else {
          l.add(hex);
          this.check("top", h, col, l);
        }
      }
    }
    else if (str.equals("bottom")) {
      if (hex.getBottom() instanceof FilledSquare) {
        FilledSquare h = (FilledSquare) hex.getBottom();
        if (h.getColor() == col) {
          l.add(hex);
          l.add(h);

        } else {
          l.add(hex);
          this.check("bottom", h, col, l);
        }
      }
    }
  }

  /**
   * Accesses the number of tiles that
   * can be flipped if a player chooses to
   * place a disc in this tile.
   * @param player The player whose turn it is.
   * @return The number of tiles that the
   *         player will flip if they make this move.
   */
  public int getValidMoves(Player player) {
    int size = 0;

    List<List<FilledSquare>> list =
            this.checkAll((this.getFilledHexagons(player.getColor())),
                    this, player.getColor());

    for (List<FilledSquare> l : list) {
      size = size + l.size();
    }

    return size;
  }

}
