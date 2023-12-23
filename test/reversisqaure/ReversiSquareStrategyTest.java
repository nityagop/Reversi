package reversisqaure;

import org.junit.Assert;
import org.junit.Test;

import java.awt.Color;

import extracredit.model.FilledSquare;
import extracredit.model.SquareReversiModel;
import model.Player;
import model.Reversi;
import strategy.CaptureMaxTiles;
import strategy.ReversiStrategy;

/**
 * A JUnit test class to determine whether the ReversiStrategy
 * works accurately.
 */
public class ReversiSquareStrategyTest {

  Reversi mock;
  ReversiStrategy strategy1;
  StringBuilder log;
  SquareReversiModel model;
  Player player;

  private void init() {
    this.model = new SquareReversiModel();
    this.strategy1 = new CaptureMaxTiles();
    this.log = new StringBuilder();
    this.mock = new ReversiSquareModelMock(model, log);
    this.player = Player.A;
    this.model.startGame();
    this.mock.startGame();
  }

  /**
   * Tests whether the CaptureMaxTiles() strategy is choosing
   * the correct tile.
   */
  @Test
  public void testStrategiesDefaultGrid() {
    this.init();
    this.mock.makeMove(Player.A, model.getHex(2, 4));
    Assert.assertEquals(Color.BLACK, ((FilledSquare) this.model.getHex(2, 4)).getColor());
    Assert.assertEquals(Color.BLACK, ((FilledSquare) this.model.getHex(3, 4)).getColor());
    Assert.assertEquals("diagonal = 2, row = 3\n", log.toString());
  }

  /**
   * Tests whether the corners and the cells surrounding them
   * are avoided using the uppermost leftmost for ties.
   */
  @Test
  public void testCheckForTies() {
    this.init();
    this.mock.makeMove(Player.A, model.getHex(2, 4));
    this.mock.makeMove(Player.B, model.getHex(2, 5));
    Assert.assertEquals("diagonal = 2, row = 3\n" +
                                 "diagonal = 2, row = 4\n", log.toString());
  }

  /**
   * Tests whether the CaptureMaxTiles() strategy is choosing
   * the correct tile.
   */
  @Test
  public void testStrategiesMoveToBottom() {
    this.init();
    this.mock.makeMove(Player.A, model.getHex(4, 2));
    Assert.assertEquals(Color.BLACK, ((FilledSquare) this.model.getHex(4, 2)).getColor());
    Assert.assertEquals("diagonal = 3, row = 2\n", log.toString());
  }

  /**
   * Tests whether the CaptureMaxTiles() strategy is choosing
   * the correct tile.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testStrategiesInvalidMove() {
    this.init();
    this.mock.makeMove(Player.A, model.getHex(5, 4));
    Assert.assertEquals(Color.BLACK, ((FilledSquare) this.model.getHex(4, 2)).getColor());
    Assert.assertEquals("diagonal = 3, row = 2\n", log.toString());
  }
}
