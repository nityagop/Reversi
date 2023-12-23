package extracredit.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import controller.ModelStatusObservers;
import model.AbstractHexagon;
import model.EmptyHexagon;
import model.NoDiscHexagon;
import model.Player;
import model.Reversi;

/**
 * A model implementation of a square version of the Reversi
 * game. The same rules apply as the hexagonal version of Reversi
 * except you can capture tiles in 8 directions as opposed to 6
 * (along vertical, horizontal, or diagonal lines).
 */
public class SquareReversiModel implements Reversi {

  // current state of the game
  private boolean gameStarted;

  // list of tiles in the board of the game
  private List<NoDiscSquare> grid;

  // represents the player who is not next in turn.
  private Player turn;

  // the board size representing the number of tiles along the length of each side of the hexagonal
  // board (user inputted)
  // INVARIANT: the size is always greater than 1
  private final int size;

  private List<ModelStatusObservers> controllers = new ArrayList<>();


  /**
   * Constructs a representation of Reversi on a square board.
   *
   * @param size The size of the length of the board.
   */
  public SquareReversiModel(int size) {
    if (size % 2 != 0 || size < 0 || size == 2) {
      throw new IllegalArgumentException("Board size must be an even, positive number" +
              "greater than 2");
    }
    this.gameStarted = false;
    this.turn = Player.A;
    this.size = size;
    this.grid = this.linkedGrid(this.initialGrid(this.size));

  }

  /**
   * Default constructor for an instance of a square Reversi.
   */
  public SquareReversiModel() {
    this.size = 8;
    this.gameStarted = false;
    this.turn = Player.A;
    this.grid = this.linkedGrid(this.initialGrid(this.size));
  }

  /**
   * Returns the initial list of tiles in the game board of given size with no neighbors linked.
   *
   * @return the board
   */
  public List<NoDiscSquare> initialGrid(int size) {
    // Maintains the invariant that the size is greater than 1 because an illegal argument
    // exception is thrown
    if (size <= 1) {
      throw new IllegalArgumentException("Size must be greater than 1");
    }
    List<NoDiscSquare> grid = new ArrayList<>();

    for (int i = 0; i < size; i++) {

      for (int j = 0; j < size; j++) {
        if (i == size / 2 - 1 && j == size / 2 || i == size / 2 && j == size / 2 - 1) {
          FilledSquare hex = new FilledSquare(i, j, Color.WHITE);
          grid.add(hex);
        } else if (i == size / 2 && j == size / 2 || i == size / 2 - 1 && j == size / 2 - 1) {
          FilledSquare hex = new FilledSquare(i, j, Color.BLACK);
          grid.add(hex);
        } else {
          NoDiscSquare hex = new NoDiscSquare(i, j);
          grid.add(hex);
        }
      }
    }
    return grid;
  }

  /**
   * Returns the list of tiles in the game board with all of its neighbors linked.
   *
   * @param grid is the list of board tiles with no neighbors linked.
   * @return the board
   */
  public List<NoDiscSquare> linkedGrid(List<NoDiscSquare> grid) {

    for (NoDiscSquare hex : grid) {
      int i = hex.getDiagonal();
      int j = hex.getRow();
      if (i == 0) {
        hex.setLeft(this.getHexagon(i - 1, j, grid));
        hex.setRight(this.getHexagon(i + 1, j, grid));
        hex.setBottom(this.getHexagon(i, j + 1, grid));
        hex.setBottomRight(this.getHexagon(i + 1, j + 1, grid));
        hex.setBottomLeft(this.getHexagon(i - 1, j + 1, grid));

      } else if (i == size - 1) {
        hex.setLeft(this.getHexagon(i - 1, j, grid));
        hex.setRight(this.getHexagon(i + 1, j, grid));
        hex.setTopRight(this.getHexagon(i, j - 1, grid));
        hex.setTopLeft(this.getHexagon(i - 1, j - 1, grid));
        hex.setTop(this.getHexagon(i, j - 1, grid));

      } else if (j == 0) {
        hex.setRight(this.getHexagon(i + 1, j, grid));
        hex.setTopRight(this.getHexagon(i + 1, j - 1, grid));
        hex.setBottom(this.getHexagon(i, j + 1, grid));
        hex.setBottomRight(this.getHexagon(i + 1, j + 1, grid));
        hex.setBottomLeft(this.getHexagon(i - 1, j + 1, grid));
      } else if (j == size - 1) {
        hex.setLeft(this.getHexagon(i - 1, j, grid));
        hex.setTopLeft(this.getHexagon(i - 1, j - 1, grid));
        hex.setTop(this.getHexagon(i, j - 1, grid));
        hex.setBottomRight(this.getHexagon(i + 1, j + 1, grid));
        hex.setBottomLeft(this.getHexagon(i - 1, j + 1, grid));
      } else {
        hex.setLeft(this.getHexagon(i - 1, j, grid));
        hex.setRight(this.getHexagon(i + 1, j, grid));
        hex.setTopRight(this.getHexagon(i + 1, j - 1, grid));
        hex.setTopLeft(this.getHexagon(i - 1, j - 1, grid));
        hex.setTop(this.getHexagon(i, j - 1, grid));
        hex.setBottom(this.getHexagon(i, j + 1, grid));
        hex.setBottomRight(this.getHexagon(i + 1, j + 1, grid));
        hex.setBottomLeft(this.getHexagon(i - 1, j + 1, grid));
      }
    }
    return grid;
  }

  /**
   * Returns the tile with the given coordinates in the given list of tiles.
   *
   * @param i    the 0-based index (from the left) of the diagonal coordinate of the tile.
   * @param j    the 0-based index (from the top) of the row coordinate of the tile.
   * @param grid the grid of tiles to find the tile of given coordinates in.
   */
  public AbstractHexagon getHexagon(int i, int j, List<NoDiscSquare> grid) {
    NoDiscSquare hex = null;

    for (NoDiscSquare h : grid) {
      if (h.getDiagonal() == i && h.getRow() == j) {
        hex = h;
        break;
      }
    }
    if (hex == null) {
      return new EmptyHexagon(-1, -1);
    } else {
      return hex;
    }
  }


  @Override
  public boolean gameOver() {
    if (!this.gameStarted) {
      throw new IllegalStateException("Game has not started");
    }
    for (NoDiscSquare hex : this.grid) {
      if (!(hex instanceof FilledSquare)) {
        if ((!hex.allNeighborsEmpty())
                && ((!hex.checkAll(((hex).getFilledHexagons(Color.BLACK)),
                hex, Color.BLACK).isEmpty())
                || (!hex.checkAll(((hex).getFilledHexagons(Color.WHITE)),
                hex, Color.WHITE).isEmpty()))) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public int getScore(Player who) {
    ArrayList<FilledSquare> tiles = new ArrayList<>();
    if (!this.gameStarted) {
      throw new IllegalStateException("Game has not started");
    }

    for (NoDiscSquare hex : this.grid) {
      if (hex instanceof FilledSquare) {
        if (((FilledSquare) hex).getColor().equals(who.getColor())) {
          tiles.add((FilledSquare) hex);
        }
      }
    }
    return tiles.size();
  }

  @Override
  public AbstractHexagon getHex(int i, int j) {
    NoDiscSquare hex = null;

    for (NoDiscSquare h : this.grid) {
      if (h.getDiagonal() == i && h.getRow() == j) {
        hex = h;
        break;
      }
    }
    if (hex == null) {
      return new EmptyHexagon(-1, -1);
    } else {
      return hex;
    }
  }

  @Override
  public int getBoardSize() {
    return this.size;
  }

  @Override
  public List<NoDiscHexagon> getGrid() {
    return new ArrayList<>();
  }

  public List<NoDiscSquare> getBoard() {
    return this.grid;
  }

  @Override
  public AbstractHexagon getHexagon2(int i, int i1, List<NoDiscSquare> board) {
    NoDiscSquare hex = null;

    for (NoDiscSquare h : grid) {
      if (h.getDiagonal() == i && h.getRow() == i1) {
        hex = h;
        break;
      }
    }
    if (hex == null) {
      return new EmptyHexagon(-1, -1);
    } else {
      return hex;
    }
  }

  @Override
  public Player getPlayer() {
    return this.turn;
  }

  @Override
  public boolean gameState() {
    return this.gameStarted;
  }

  @Override
  public void makeMove(Player who, AbstractHexagon where) {
    // illegal state exceptions for if the game hasn't been started or if the player
    // is trying to move to an empty spot
    if (!gameStarted) {
      throw new IllegalArgumentException("Game has not been started");
    }
    if (who == null || where == null) {
      throw new IllegalArgumentException();
    }
    // cannot put a disc in a hexagon that is empty or already has a disc in it
    else if (where instanceof EmptyHexagon || where instanceof FilledSquare) {
      throw new IllegalStateException();
    }
    // throw an illegal argument when the player is incorrect
    else if (who != this.turn) {
      throw new IllegalArgumentException();
    }

    // if one of the neighbors have a hexagon with a disc in it and the subsequent hexagons make for
    // a valid move we can make the changes to the board accordingly
    else if ((!((NoDiscSquare) where).allNeighborsEmpty())
            && !((NoDiscSquare) where).checkAll((((NoDiscSquare) where).
                    getFilledHexagons(who.getColor())),
            (NoDiscSquare) where, who.getColor()).isEmpty()) {
      System.out.print("valid move");
      List<List<FilledSquare>> l =
              ((NoDiscSquare) where).checkAll((((NoDiscSquare) where).
                              getFilledHexagons(who.getColor())),
                      (NoDiscSquare) where, who.getColor());
      NoDiscSquare h = (NoDiscSquare) where;
      FilledSquare newH = new FilledSquare(h.getDiagonal(),
              h.getRow(), h.getRight(), h.getLeft(), h.getTopRight(), h.getTopLeft(),
              h.getBottomRight(), h.getBottomLeft(), h.getTop(), h.getBottom(), who.getColor());
      this.grid.set(this.getBoard().indexOf(where), newH);
      this.changeNeighbors(h, newH);
      for (List<FilledSquare> lofh : l) {
        for (FilledSquare hex : lofh) {
          FilledSquare newHex = new FilledSquare(hex.getDiagonal(), hex.getRow(),
                  hex.getRight(), hex.getLeft(), hex.getTopRight(), hex.getTopLeft(),
                  hex.getBottomRight(), hex.getBottomLeft(),
                  hex.getTop(), hex.getBottom(), who.getColor());
          this.grid.set(this.getBoard().indexOf(hex), newHex);
          this.changeNeighbors(hex, newHex);
        }
      }
      for (ModelStatusObservers ms : controllers) {
        ms.refreshView();
      }
      this.nextPlayer(who);
    } else {
      throw new IllegalArgumentException();
    }
  }

  // updates the linking of neighbor tiles after a valid move has been made in one tile
  private void changeNeighbors(NoDiscSquare oldHex, FilledSquare hex) {
    oldHex.getRight().setLeft(hex);
    oldHex.getLeft().setRight(hex);
    oldHex.getBottomLeft().setTopRight(hex);
    oldHex.getBottomRight().setTopLeft(hex);
    oldHex.getTopLeft().setBottomRight(hex);
    oldHex.getTopRight().setBottomLeft(hex);
  }


  @Override
  public void nextPlayer(Player player) {
    if (player.equals(Player.A)) {
      this.turn = Player.B;
    } else {
      this.turn = Player.A;
    }
    for (ModelStatusObservers ms : controllers) {
      ms.changePlayer();
    }
  }

  @Override
  public int getPotentialTiles(int x, int y) {
    return 0;
  }

  @Override
  public void addObservers(ModelStatusObservers rc) {
    controllers.add(rc);
  }

  /**
   * Starts the game by setting the current state of the game to started and notifying the
   * observers that it is the first player's turn.
   */
  public void startGame() {
    this.gameStarted = true;
    for (ModelStatusObservers ms : controllers) {
      ms.changePlayer();
    }
  }

  @Override
  public List<AbstractHexagon> getBoardGame() {
    List<AbstractHexagon> list = new ArrayList<>();
    list.addAll(this.grid);
    return list;
  }


}
