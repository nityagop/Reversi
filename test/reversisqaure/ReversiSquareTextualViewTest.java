package reversisqaure;

import org.junit.Assert;
import org.junit.Test;

import extracredit.model.SquareReversiModel;
import extracredit.view.ReversiSquareTextualView;
import model.Player;
import model.Reversi;

/**
 * A JUnit class for testing methods in ReversiTextualView.
 */
public class ReversiSquareTextualViewTest {

  Reversi rm;
  ReversiSquareTextualView tv;
  StringBuilder view;

  /**
   * Initializes the ReversiModel and ReversiTextualView.
   */
  private void init() {
    this.rm = new SquareReversiModel();
    this.tv = new ReversiSquareTextualView(this.rm);
    this.view = new StringBuilder();


  }

  /**
   * Tests whether the initial, default grid is rendered.
   */
  @Test
  public void testViewInitialGrid() {
    init();
    Assert.assertEquals("" +
            "_ _ _ _ _ _ _ _ \n" +
            "_ _ _ _ _ _ _ _ \n" +
            "_ _ _ _ _ _ _ _ \n" +
            "_ _ _ X O _ _ _ \n" +
            "_ _ _ O X _ _ _ \n" +
            "_ _ _ _ _ _ _ _ \n" +
            "_ _ _ _ _ _ _ _ \n" +
            "_ _ _ _ _ _ _ _ \n", this.tv.toString());
  }

  /**
   * Tests whether a user inputted small board is rendered.
   */
  @Test
  public void testSmallGridView() {
    SquareReversiModel rm = new SquareReversiModel(4);
    ReversiSquareTextualView tv = new ReversiSquareTextualView(rm);
    Assert.assertEquals("" +
            "_ _ _ _ \n" +
            "_ X O _ \n" +
            "_ O X _ \n" +
            "_ _ _ _ \n", tv.toString());
  }


  /**
   * Tests whether a user inputted large board is rendered.
   */
  @Test
  public void testLargeGridView() {
    SquareReversiModel rm = new SquareReversiModel(10);
    ReversiSquareTextualView tv = new ReversiSquareTextualView(rm);
    Assert.assertEquals("" +
            "_ _ _ _ _ _ _ _ _ _ \n" +
            "_ _ _ _ _ _ _ _ _ _ \n" +
            "_ _ _ _ _ _ _ _ _ _ \n" +
            "_ _ _ _ _ _ _ _ _ _ \n" +
            "_ _ _ _ X O _ _ _ _ \n" +
            "_ _ _ _ O X _ _ _ _ \n" +
            "_ _ _ _ _ _ _ _ _ _ \n" +
            "_ _ _ _ _ _ _ _ _ _ \n" +
            "_ _ _ _ _ _ _ _ _ _ \n" +
            "_ _ _ _ _ _ _ _ _ _ \n", tv.toString());
  }


  /**
   * Tests whether the board is updated after multiple moves.
   */
  @Test
  public void testViewMoves() {
    init();
    this.rm.startGame();
    this.rm.makeMove(Player.A, this.rm.getHexagon2(2, 4, this.rm.getBoard()));

    Assert.assertEquals("" +
            "_ _ _ _ _ _ _ _ \n" +
            "_ _ _ _ _ _ _ _ \n" +
            "_ _ _ _ _ _ _ _ \n" +
            "_ _ _ X O _ _ _ \n" +
            "_ _ _ O X _ _ _ \n" +
            "_ _ _ _ _ _ _ _ \n" +
            "_ _ _ _ _ _ _ _ \n" +
            "_ _ _ _ _ _ _ _ \n", this.tv.toString());
  }
}