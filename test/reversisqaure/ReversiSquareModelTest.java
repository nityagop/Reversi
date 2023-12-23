package reversisqaure;

import org.junit.Assert;
import org.junit.Test;

import java.awt.Color;
import java.util.List;

import extracredit.model.FilledSquare;
import extracredit.model.NoDiscSquare;
import extracredit.model.SquareReversiModel;
import model.EmptyHexagon;
import model.FilledHexagon;
import model.NoDiscHexagon;
import model.Player;
import model.Reversi;

/**
 * A JUnit test class to determine whether a given
 * SquareReversiModel works as accordingly.
 */
public class ReversiSquareModelTest {

  Reversi rm;
  List<NoDiscSquare> grid;

  private void init() {
    this.rm = new SquareReversiModel();
    this.grid = this.rm.getBoard();
  }

  /**
   * Tests whether the default grid is constructed.
   */
  @Test
  public void testInitialGrid() {
    this.init();
    Assert.assertEquals(8, this.rm.getBoardSize());
    Assert.assertEquals(64, this.rm.getBoard().size());

  }

  /**
   * Tests whether grids can only be positive + even numbers.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInitialGridInvalidInputs() {
    SquareReversiModel negBoard = new SquareReversiModel(-1);
    SquareReversiModel oddBoard = new SquareReversiModel(3);

  }

  /**
   * Tests whether the grid is constructed when the user
   * inputs different sizes.
   */
  @Test
  public void testInitialGridWithDifferentSizes() {
    SquareReversiModel smallGrid = new SquareReversiModel(10);
    Assert.assertEquals(100, smallGrid.getBoard().size());

    SquareReversiModel largeGrid = new SquareReversiModel(100);
    Assert.assertEquals(10000, largeGrid.getBoard().size());
  }



  /**
   * Tests whether an IllegalArgumentException is thrown when
   * the makeMove method has invalid arguments.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMakeMoveInvalidArgs() {
    init();
    this.rm.makeMove(null, new NoDiscSquare(3, 4));
    this.rm.makeMove(Player.A, null);
    //wrong player's turn
    this.rm.makeMove(Player.B, new NoDiscSquare(3, 3));
  }



  /**
   * Tests whether an IllegalArgException is thrown
   * when a player moves a disc to an empty tile or
   * a tile that's already filled.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMakeMoveInvalid() {
    init();
    // invalid to make a move in an empty hexagon
    this.rm.makeMove(Player.A, new EmptyHexagon(-1, -1));
    // invalid to make a move in a hexagon with a disc inside it already
    this.rm.makeMove(Player.A, new FilledSquare(3, 3, Color.WHITE));
  }


  /**
   * Tests whether an exception is thrown when there are legal and
   * illegal moves.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMultipleMovesValidAndInvalid() {
    init();
    this.rm.makeMove(Player.A, this.rm.getHexagon2(4, 3, this.rm.getBoard()));
    this.rm.makeMove(Player.B, this.rm.getHexagon2(4, 6, this.rm.getBoard()));
    this.rm.makeMove(Player.A, this.rm.getHexagon2(6, 6, this.rm.getBoard()));
  }


  /**
   * Tests a valid move made by the player with black discs.
   */
  @Test
  public void testValidMakeMove() {
    init();
    this.rm.startGame();
    this.rm.makeMove(Player.A, this.rm.getHexagon2(2, 4, this.rm.getBoard()));
    Assert.assertTrue(this.rm.getHexagon2(2, 4, this.grid) instanceof FilledSquare);
    Assert.assertEquals(Color.BLACK, ((FilledSquare) this.rm.getHexagon2(3, 4,
            this.rm.getBoard())).getColor());
  }



  /**
   * Tests that a players turn is passed when a move is not
   * possible.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMakeMoveNotPossible() {
    init();
    // tries to make a move that is not possible
    this.rm.makeMove(Player.A, this.rm.getHexagon2(0, 5, this.rm.getBoard()));
    Assert.assertTrue(this.rm.getHexagon2(0, 5, this.rm.getBoard()) instanceof NoDiscHexagon);
  }


  /**
   * Tests a valid move by adding to a line from the top right.
   */
  @Test
  public void testValidMakeMoveToTopRight() {
    init();
    this.rm.startGame();
    this.rm.makeMove(Player.A, this.rm.getHexagon2(2, 4, this.rm.getBoard()));
    this.rm.makeMove(Player.B, this.rm.getHexagon2(2, 5, this.rm.getBoard()));
    Assert.assertTrue(this.rm.getHexagon2(2, 5, this.rm.getBoard()) instanceof FilledSquare);
    Assert.assertEquals(Color.BLACK, ((FilledSquare) this.rm.getHexagon2(2, 4,
            this.rm.getBoard())).getColor());
    Assert.assertEquals(Color.WHITE, ((FilledSquare) this.rm.getHexagon2(2, 5,
            this.rm.getBoard())).getColor());
    Assert.assertEquals(Color.BLACK, ((FilledSquare) this.rm.getHexagon2(2, 4,
            this.rm.getBoard())).getColor());
  }


  /**
   * Tests valid move to add to a line from the bottom right corner.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMultipleValidMoveToBottomRight() {
    init();
    this.rm.makeMove(Player.A, this.rm.getHexagon2(4, 3, this.rm.getBoard()));
    this.rm.makeMove(Player.B, this.rm.getHexagon2(4, 6, this.rm.getBoard()));
    this.rm.makeMove(Player.A, this.rm.getHexagon2(7, 6, this.rm.getBoard()));
    Assert.assertEquals(Color.BLACK, ((FilledHexagon) this.rm.getHexagon2(6, 5,
            this.rm.getBoard())).getColor());
    Assert.assertEquals(Color.BLACK, ((FilledHexagon) this.rm.getHexagon2(5, 4,
            this.rm.getBoard())).getColor());
    Assert.assertEquals(Color.BLACK, ((FilledHexagon) this.rm.getHexagon2(4, 3,
            this.rm.getBoard())).getColor());
  }


  /**
   * Tests the getScore method.
   */
  @Test
  public void testGetScore() {
    init();
    this.rm.startGame();
    this.rm.makeMove(Player.A, this.rm.getHexagon2(2, 4, this.rm.getBoard()));
    Assert.assertEquals(4, this.rm.getScore(Player.A));
    this.rm.makeMove(Player.B, this.rm.getHexagon2(2, 5, this.rm.getBoard()));
    Assert.assertEquals(3, this.rm.getScore(Player.B));
  }



  /**
   * Tests whether the game is not over initially.
   */
  @Test(expected = IllegalStateException.class)
  public void testGameOverBeforeGameStart() {
    SquareReversiModel rm = new SquareReversiModel();
    Assert.assertFalse(rm.gameOver());
  }


  /**
   * Tests whether the game is not over when there are still
   * moves to be made.
   */
  @Test
  public void testGameOverGameNotOver() {
    SquareReversiModel rm = new SquareReversiModel();
    rm.startGame();
    rm.makeMove(Player.A, rm.getHexagon2(2, 4, rm.getBoard()));
    rm.makeMove(Player.B, rm.getHexagon2(2, 5, rm.getBoard()));
    Assert.assertFalse(rm.gameOver());
  }


  /**
   * Tests whether the game is over when there are no moves
   * to be made.
   */
  @Test(expected = IllegalStateException.class)
  public void testGameOverNoMoreMoves() {
    SquareReversiModel rm = new SquareReversiModel(4);
    rm.startGame();
    rm.makeMove(Player.A, rm.getHexagon2(2, 2, rm.getBoard()));
    Assert.assertTrue(rm.gameOver());
  }

}
