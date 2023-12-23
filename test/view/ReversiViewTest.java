package view;

import org.junit.Assert;
import org.junit.Test;

import model.Player;
import model.ReversiModel;
import view.ReversiTextualView;

/**
 * A JUnit class for testing methods in ReversiTextualView.
 */
public class ReversiViewTest {

  ReversiModel rm;
  ReversiTextualView tv;
  StringBuilder view;

  /**
   * Initializes the ReversiModel and ReversiTextualView.
   */
  private void init() {
    this.rm = new ReversiModel();
    this.tv = new ReversiTextualView(this.rm);
    this.view = new StringBuilder();
    this.rm.startGame();
  }

  /**
   * Tests whether the initial, default grid is rendered.
   */
  @Test
  public void testViewInitialGrid() {
    init();
    Assert.assertEquals("" +
            "     _ _ _ _ _ _\n" +
            "    _ _ _ _ _ _ _\n" +
            "   _ _ _ _ _ _ _ _\n" +
            "  _ _ _ _ _ _ _ _ _\n" +
            " _ _ _ _ X O _ _ _ _\n" +
            "_ _ _ _ O _ X _ _ _ _\n" +
            " _ _ _ _ X O _ _ _ _\n" +
            "  _ _ _ _ _ _ _ _ _\n" +
            "   _ _ _ _ _ _ _ _\n" +
            "    _ _ _ _ _ _ _\n" +
            "     _ _ _ _ _ _\n", this.tv.toString());
  }

  /**
   * Tests whether a user inputted small board is rendered.
   */
  @Test
  public void testSmallGridView() {
    ReversiModel rm = new ReversiModel(3);
    ReversiTextualView tv = new ReversiTextualView(rm);
    Assert.assertEquals("" +
            "  _ _ _\n" +
            " _ X O _\n" +
            "_ O _ X _\n" +
            " _ X O _\n" +
            "  _ _ _\n", tv.toString());
  }


  /**
   * Tests whether a user inputted large board is rendered.
   */
  @Test
  public void testLargeGridView() {
    ReversiModel rm = new ReversiModel(10);
    ReversiTextualView tv = new ReversiTextualView(rm);
    Assert.assertEquals("" +
            "         _ _ _ _ _ _ _ _ _ _\n" +
            "        _ _ _ _ _ _ _ _ _ _ _\n" +
            "       _ _ _ _ _ _ _ _ _ _ _ _\n" +
            "      _ _ _ _ _ _ _ _ _ _ _ _ _\n" +
            "     _ _ _ _ _ _ _ _ _ _ _ _ _ _\n" +
            "    _ _ _ _ _ _ _ _ _ _ _ _ _ _ _\n" +
            "   _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _\n" +
            "  _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _\n" +
            " _ _ _ _ _ _ _ _ X O _ _ _ _ _ _ _ _\n" +
            "_ _ _ _ _ _ _ _ O _ X _ _ _ _ _ _ _ _\n" +
            " _ _ _ _ _ _ _ _ X O _ _ _ _ _ _ _ _\n" +
            "  _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _\n" +
            "   _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _\n" +
            "    _ _ _ _ _ _ _ _ _ _ _ _ _ _ _\n" +
            "     _ _ _ _ _ _ _ _ _ _ _ _ _ _\n" +
            "      _ _ _ _ _ _ _ _ _ _ _ _ _\n" +
            "       _ _ _ _ _ _ _ _ _ _ _ _\n" +
            "        _ _ _ _ _ _ _ _ _ _ _\n" +
            "         _ _ _ _ _ _ _ _ _ _\n", tv.toString());
  }

  /**
   * Tests whether the board is updated after 1 move.
   */
  @Test
  public void testViewOneMove() {
    init();
    Assert.assertEquals("" +
            "     _ _ _ _ _ _\n" +
            "    _ _ _ _ _ _ _\n" +
            "   _ _ _ _ _ _ _ _\n" +
            "  _ _ _ _ _ _ _ _ _\n" +
            " _ _ _ _ X O _ _ _ _\n" +
            "_ _ _ _ O _ X _ _ _ _\n" +
            " _ _ _ _ X O _ _ _ _\n" +
            "  _ _ _ _ _ _ _ _ _\n" +
            "   _ _ _ _ _ _ _ _\n" +
            "    _ _ _ _ _ _ _\n" +
            "     _ _ _ _ _ _\n", this.tv.toString());
    this.rm.makeMove(Player.A, this.rm.getHexagon(4, 3, this.rm.getGrid()));
    Assert.assertEquals("" +
            "     _ _ _ _ _ _\n" +
            "    _ _ _ _ _ _ _\n" +
            "   _ _ _ _ _ _ _ _\n" +
            "  _ _ _ _ X _ _ _ _\n" +
            " _ _ _ _ X X _ _ _ _\n" +
            "_ _ _ _ O _ X _ _ _ _\n" +
            " _ _ _ _ X O _ _ _ _\n" +
            "  _ _ _ _ _ _ _ _ _\n" +
            "   _ _ _ _ _ _ _ _\n" +
            "    _ _ _ _ _ _ _\n" +
            "     _ _ _ _ _ _\n", this.tv.toString());
  }

  /**
   * Tests whether the board is updated after multiple moves.
   */
  @Test
  public void testViewMultipleMoves() {
    init();
    this.rm.makeMove(Player.A, this.rm.getHexagon(4, 3, this.rm.getGrid()));

    this.rm.makeMove(Player.B, this.rm.getHexagon(4, 6, this.rm.getGrid()));

    this.rm.makeMove(Player.A, this.rm.getHexagon(4, 7, this.rm.getGrid()));
    this.rm.makeMove(Player.B, this.rm.getHexagon(3, 6, this.rm.getGrid()));

    this.rm.makeMove(Player.A, this.rm.getHexagon(6, 7, this.rm.getGrid()));

    Assert.assertEquals("" +
            "     _ _ _ _ _ _\n" +
            "    _ _ _ _ _ _ _\n" +
            "   _ _ _ _ _ _ _ _\n" +
            "  _ _ _ _ X _ _ _ _\n" +
            " _ _ _ _ X X _ _ _ _\n" +
            "_ _ _ _ X _ X _ _ _ _\n" +
            " _ _ O O X X _ _ _ _\n" +
            "  _ _ X _ X _ _ _ _\n" +
            "   _ _ _ _ _ _ _ _\n" +
            "    _ _ _ _ _ _ _\n" +
            "     _ _ _ _ _ _\n", this.tv.toString());
  }
}