package model;

import org.junit.Assert;
import org.junit.Test;

import java.awt.Color;
import java.util.List;

import model.AbstractHexagon;
import model.EmptyHexagon;
import model.FilledHexagon;
import model.NoDiscHexagon;
import model.Player;
import model.ReversiModel;

/**
 * A JUnit class for testing methods in ReversiModel.
 */
public class ReversiModelTest {

  ReversiModel rm;
  FilledHexagon initialGridTopLeft;
  AbstractHexagon initialGridTopRight;
  NoDiscHexagon toMove;
  List<NoDiscHexagon> grid;
  AbstractHexagon empty;
  Player player1;
  Player player2;


  /**
   * Initializes the ReversiGame and creates some example tiles.
   */
  private void init() {
    this.rm = new ReversiModel();
    this.initialGridTopLeft = new FilledHexagon(4, 4, Color.BLACK);
    this.initialGridTopRight = new FilledHexagon(5, 5, Color.WHITE);
    this.toMove = new NoDiscHexagon(3, 4);
    this.grid = this.rm.getGrid();
    this.empty = new EmptyHexagon(-1,-1);
    this.player1 = Player.A;
    this.player2 = Player.B;
    this.rm.startGame();

  }

  /**
   * Tests whether the default grid is constructed.
   */
  @Test
  public void testInitialGrid() {
    init();
    Assert.assertEquals(91, this.rm.getGrid().size());
  }

  /**
   * Tests whether the grid is constructed when the user
   * inputs different sizes.
   */
  @Test
  public void testInitialGridWithDifferentSizes() {
    init();
    ReversiModel smallGrid = new ReversiModel(3);
    Assert.assertEquals(19, smallGrid.getGrid().size());

    ReversiModel largeGrid = new ReversiModel(10);
    Assert.assertEquals(271, largeGrid.getGrid().size());
  }


  /**
   * Tests whether an IllegalArgumentException is thrown when
   * the makeMove method has invalid arguments.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMakeMoveInvalidArgs() {
    init();
    this.rm.makeMove(null, this.toMove);
    this.rm.makeMove(player1, null);
    //wrong player's turn
    this.rm.makeMove(player2, this.toMove);
  }


  /**
   * Tests whether an IllegalStateException is thrown
   * when a player moves a disc to an empty tile or
   * a tile that's already filled.
   */
  @Test(expected = IllegalStateException.class)
  public void testMakeMoveInvalid() {
    init();
    // invalid to make a move in an empty hexagon
    this.rm.makeMove(player1, new EmptyHexagon(-1, -1));
    // invalid to make a move in a hexagon with a disc inside it already
    this.rm.makeMove(player1, this.initialGridTopLeft);
  }

  /**
   * Tests whether an exception is thrown when there are legal and
   * illegal moves.
   */
  @Test(expected = IllegalStateException.class)
  public void testMultipleMovesValidAndInvalid() {
    init();
    this.rm.makeMove(player1, this.rm.getHexagon(4, 3, this.rm.getGrid()));
    this.rm.makeMove(player2, this.rm.getHexagon(4, 6, this.rm.getGrid()));
    this.rm.makeMove(player2, this.rm.getHexagon(6, 6, this.rm.getGrid()));
  }

  /**
   * Tests a valid move made by the player with black discs.
   */
  @Test
  public void testValidMakeMove() {
    init();
    this.rm.makeMove(player1, this.rm.getHexagon(4, 3, this.rm.getGrid()));
    Assert.assertTrue(this.rm.getHexagon(4, 3, this.grid) instanceof FilledHexagon);
    Assert.assertEquals(Color.BLACK, ((FilledHexagon) this.rm.getHexagon(5, 4,
            this.rm.getGrid())).getColor());
    Assert.assertEquals(Color.BLACK, ((FilledHexagon) this.rm.getHexagon(6, 5,
            this.rm.getGrid())).getColor());
  }


  /**
   * Tests that a players turn is passed when a move is not
   * possible.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMakeMoveNotPossible() {
    init();
    // tries to make a move that is not possible
    this.rm.makeMove(player1, this.rm.getHexagon(5, 5, this.rm.getGrid()));
    Assert.assertTrue(this.rm.getHexagon(5, 5, this.rm.getGrid()) instanceof NoDiscHexagon);
  }

  /**
   * Tests a valid move by adding to a line from the top right.
   */
  @Test
  public void testValidMakeMoveToTopRight() {
    init();
    this.rm.makeMove(player1, this.rm.getHexagon(4, 3, this.rm.getGrid()));
    this.rm.makeMove(player2, this.rm.getHexagon(4, 6, this.rm.getGrid()));
    Assert.assertTrue(this.rm.getHexagon(4, 6, this.rm.getGrid()) instanceof FilledHexagon);
    Assert.assertEquals(Color.WHITE, ((FilledHexagon) this.rm.getHexagon(4, 6,
            this.rm.getGrid())).getColor());
    Assert.assertEquals(Color.WHITE, ((FilledHexagon) this.rm.getHexagon(4, 5,
            this.rm.getGrid())).getColor());
    Assert.assertEquals(Color.WHITE, ((FilledHexagon) this.rm.getHexagon(5, 6,
            this.rm.getGrid())).getColor());
    this.rm.makeMove(player1, this.rm.getHexagon(4, 7, this.rm.getGrid()));
    Assert.assertEquals(Color.BLACK, ((FilledHexagon) this.rm.getHexagon(4, 6,
            this.rm.getGrid())).getColor());
    Assert.assertEquals(Color.BLACK, ((FilledHexagon) this.rm.getHexagon(4, 5,
            this.rm.getGrid())).getColor());
  }

  /**
   * Tests valid move to add to a line from the bottom right corner.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMultipleValidMoveToBottomRight() {
    init();
    this.rm.makeMove(player1, this.rm.getHexagon(4, 3, this.rm.getGrid()));
    this.rm.makeMove(player2, this.rm.getHexagon(4, 6, this.rm.getGrid()));
    this.rm.makeMove(player1, this.rm.getHexagon(7, 6, this.rm.getGrid()));
    Assert.assertEquals(Color.BLACK, ((FilledHexagon) this.rm.getHexagon(6, 5,
            this.rm.getGrid())).getColor());
    Assert.assertEquals(Color.BLACK, ((FilledHexagon) this.rm.getHexagon(5, 4,
            this.rm.getGrid())).getColor());
    Assert.assertEquals(Color.BLACK, ((FilledHexagon) this.rm.getHexagon(4, 3,
            this.rm.getGrid())).getColor());
  }

  /**
   * Tests a valid move to add to a line from the bottom left corner.
   */
  @Test
  public void testValidMoveToBottomLeft() {
    init();
    this.rm.makeMove(player1, this.rm.getHexagon(4, 6, this.rm.getGrid()));
    this.rm.makeMove(player2, this.rm.getHexagon(5, 5, this.rm.getGrid()));
    this.rm.makeMove(player1, this.rm.getHexagon(6, 7, this.rm.getGrid()));
    Assert.assertEquals(Color.BLACK, ((FilledHexagon) this.rm.getHexagon(5, 6,
            this.rm.getGrid())).getColor());
    Assert.assertEquals(Color.BLACK, ((FilledHexagon) this.rm.getHexagon(6, 7,
            this.rm.getGrid())).getColor());
  }

  /**
   * Tests a valid move to add to a line from the bottom left corner.
   */
  @Test
  public void testValidMoveLeft() {
    init();
    this.rm.makeMove(player1, this.rm.getHexagon(3, 4, this.rm.getGrid()));
    this.rm.makeMove(player2, this.rm.getHexagon(4, 6, this.rm.getGrid()));
    Assert.assertEquals(Color.BLACK, ((FilledHexagon) this.rm.getHexagon(3, 4,
            this.rm.getGrid())).getColor());
    Assert.assertEquals(Color.BLACK, ((FilledHexagon) this.rm.getHexagon(4, 4,
            this.rm.getGrid())).getColor());
  }


  /**
   * Tests the getScore method.
   */
  @Test
  public void testGetScore() {
    init();
    this.rm.makeMove(player1, this.rm.getHexagon(4, 3, this.rm.getGrid()));
    Assert.assertEquals(5, this.rm.getScore(this.player1));
    this.rm.makeMove(player2, this.rm.getHexagon(4, 6, this.rm.getGrid()));
    Assert.assertEquals(4, this.rm.getScore(this.player2));
  }

  /**
   * Tests whether the game is not over initially.
   */
  @Test
  public void testGameOverBeforeGameStart() {
    ReversiModel rm = new ReversiModel();
    Assert.assertFalse(rm.gameOver());
  }

  /**
   * Tests whether the game is not over when there are still
   * moves to be made.
   */
  @Test
  public void testGameOverGameNotOver() {
    ReversiModel rm = new ReversiModel(3);
    rm.makeMove(Player.A, rm.getGrid().get(18));
    rm.makeMove(Player.B, rm.getGrid().get(1));
    rm.makeMove(Player.A, rm.getGrid().get(9));
    rm.makeMove(Player.B, rm.getGrid().get(16));
    Assert.assertFalse(rm.gameOver());
  }

  /**
   * Tests whether the game is over when there are no moves
   * to be made.
   */
  @Test(expected = IllegalStateException.class)
  public void testGameOverNoMoreMoves() {
    ReversiModel rm = new ReversiModel(2);
    rm.startGame();
    rm.makeMove(Player.A, rm.getGrid().get(5));
    Assert.assertTrue(rm.gameOver());
  }
}


