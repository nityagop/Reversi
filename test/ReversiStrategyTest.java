import org.junit.Assert;
import org.junit.Test;

import java.awt.Color;
import model.FilledHexagon;
import model.NoDiscHexagon;
import model.Player;
import model.Reversi;
import model.ReversiModel;
import model.ReversiModelDeepCopy;
import strategy.CaptureMaxTiles;
import strategy.ReversiStrategy;
import model.ReversiMockModel;

/**
 * A JUnit class for testing methods that implement a ReversiStrategy.
 */
public class ReversiStrategyTest {

  Reversi mock;
  ReversiStrategy strategy1;
  StringBuilder log;
  ReversiModel model;
  ReversiModelDeepCopy copyModel;
  Player player;

  /**
   * Initializes the ReversiModel and ReversiStrategy.
   */
  private void init() {
    this.model = new ReversiModel();
    this.strategy1 = new CaptureMaxTiles();
    this.log = new StringBuilder();
    this.mock = new ReversiMockModel(model, log);
    this.copyModel = new ReversiModelDeepCopy(this.model);
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
    this.mock.makeMove(Player.A, model.getHex(4, 3));
    Assert.assertEquals(Color.BLACK, ((FilledHexagon) this.model.getHex(5, 4)).getColor());
    Assert.assertEquals(Color.BLACK, ((FilledHexagon) this.model.getHex(6, 5)).getColor());
    Assert.assertEquals("diagonal = 4, row = 2\n", log.toString());
    this.mock.makeMove(Player.B, model.getHex(4, 6));
    Assert.assertEquals(Color.WHITE, ((FilledHexagon) this.model.getHex(4, 5)).getColor());
    Assert.assertEquals("diagonal = 4, row = 2\n", log.toString());
  }

  /**
   * Tests whether the corners and the cells surrounding them
   * are avoided using the uppermost leftmost for ties.
   */
  @Test
  public void testCheckForTies() {
    this.init();
    this.mock.makeMove(Player.A, model.getHex(4, 3));
    this.mock.makeMove(Player.B, model.getHex(4, 7));
    Assert.assertEquals("diagonal = 4, row = 2\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 6, row = 4\n" +
            "diagonal = 3, row = 4\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 4, row = 1\n", log.toString());
  }

  /**
   * Tests whether the CaptureMaxTiles strategy works
   * on a smaller grid as well.
   */
  @Test
  public void testStrategiesOnSmallGrid() {
    ReversiModel model = new ReversiModel(3);
    this.log = new StringBuilder();
    this.mock = new ReversiMockModel(model, log);
    this.mock.makeMove(Player.A, model.getHex(1, 3));
    Assert.assertEquals("diagonal = 0, row = 1\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 3, row = 4\n", log.toString());
  }

  /**
   * Tests whether the uppermost leftmost coordinate is returned when
   * (0,0) is filled.
   */
  @Test
  public void testAnyOpenCornerTopLeft() {
    ReversiModel model = new ReversiModel();
    model.startGame();
    this.mock.startGame();
    this.log = new StringBuilder();
    this.mock = new ReversiMockModel(model, log);
    NoDiscHexagon hex = (NoDiscHexagon) model.getHex(0, 0);
    FilledHexagon filledHex = new FilledHexagon(hex.getDiagonal(), hex.getRow(),
            hex.getRight(), hex.getLeft(),
            hex.getTopRight(), hex.getTopLeft(),
            hex.getBottomRight(), hex.getBottomLeft(),
            Color.BLACK);
    model.getGrid().set(model.getGrid().indexOf(hex), filledHex);
    this.mock.makeMove(Player.A, model.getHex(4, 3));
    this.mock.makeMove(Player.B, model.getHex(4, 7));
    Assert.assertEquals("diagonal = 4, row = 2\n" +
            "diagonal = 0, row = 5\n" +
            "diagonal = 0, row = 1\n" +
            "diagonal = 0, row = 1\n" +
            "diagonal = 6, row = 4\n" +
            "diagonal = 3, row = 4\n" +
            "diagonal = 0, row = 5\n" +
            "diagonal = 0, row = 1\n" +
            "diagonal = 0, row = 1\n" +
            "diagonal = 4, row = 1\n", log.toString());
  }

  /**
   * Tests whether the uppermost leftmost coordinate is returned when
   * (0,5) is filled.
   */
  @Test
  public void testAnyOpenCornerMiddleLeft() {
    ReversiModel model = new ReversiModel();
    this.log = new StringBuilder();
    this.mock = new ReversiMockModel(model, log);
    NoDiscHexagon hex = (NoDiscHexagon) model.getHex(0, model.getBoardSize() - 1);
    FilledHexagon filledHex = new FilledHexagon(hex.getDiagonal(), hex.getRow(),
            hex.getRight(), hex.getLeft(),
            hex.getTopRight(), hex.getTopLeft(),
            hex.getBottomRight(), hex.getBottomLeft(),
            Color.BLACK);
    model.getGrid().set(model.getGrid().indexOf(hex), filledHex);
    this.mock.makeMove(Player.A, model.getHex(4, 3));
    this.mock.makeMove(Player.B, model.getHex(4, 7));
    Assert.assertEquals("diagonal = 4, row = 2\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 6, row = 4\n" +
            "diagonal = 3, row = 4\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 4, row = 1\n", log.toString());
  }

  /**
   * Tests whether the uppermost leftmost coordinate is returned when
   * (0,10) is filled.
   */
  @Test
  public void testAnyOpenCornerBottomLeft() {
    ReversiModel model = new ReversiModel();
    this.log = new StringBuilder();
    this.mock = new ReversiMockModel(model, log);
    NoDiscHexagon hex = (NoDiscHexagon) model.getHex(model.getBoardSize() - 1,
            (2 * model.getBoardSize() - 1) / 2);
    FilledHexagon filledHex = new FilledHexagon(hex.getDiagonal(), hex.getRow(),
            hex.getRight(), hex.getLeft(),
            hex.getTopRight(), hex.getTopLeft(),
            hex.getBottomRight(), hex.getBottomLeft(),
            Color.BLACK);
    model.getGrid().set(model.getGrid().indexOf(hex), filledHex);
    this.mock.makeMove(Player.A, model.getHex(4, 3));
    this.mock.makeMove(Player.B, model.getHex(4, 7));
    Assert.assertEquals("diagonal = 4, row = 2\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 6, row = 4\n" +
            "diagonal = 3, row = 4\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 4, row = 1\n", log.toString());
  }

  /**
   * Tests whether the uppermost leftmost coordinate is returned when
   * (5,0) is filled.
   */
  @Test
  public void testAnyOpenCornerTopRight() {
    ReversiModel model = new ReversiModel();
    this.log = new StringBuilder();
    this.mock = new ReversiMockModel(model, log);
    NoDiscHexagon hex = (NoDiscHexagon) model.getHex((2 * model.getBoardSize() - 1) / 2,
            0);
    FilledHexagon filledHex = new FilledHexagon(hex.getDiagonal(),
            hex.getRow(), hex.getRight(), hex.getLeft(),
            hex.getTopRight(), hex.getTopLeft(), hex.getBottomRight(),
            hex.getBottomLeft(), Color.BLACK);
    model.getGrid().set(model.getGrid().indexOf(hex), filledHex);
    this.mock.makeMove(Player.A, model.getHex(4, 3));
    this.mock.makeMove(Player.B, model.getHex(4, 7));
    Assert.assertEquals("diagonal = 4, row = 2\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 6, row = 4\n" +
            "diagonal = 3, row = 4\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 4, row = 1\n", log.toString());
  }

  /**
   * Tests whether the uppermost leftmost coordinate is returned when
   * (5,5) is filled.
   */
  @Test
  public void testAnyOpenCornerMiddleRight() {
    ReversiModel model = new ReversiModel();
    this.log = new StringBuilder();
    this.mock = new ReversiMockModel(model, log);
    model.startGame();
    mock.startGame();
    NoDiscHexagon hex = (NoDiscHexagon) model.getHex((2 * model.getBoardSize() - 1) / 2,
            model.getBoardSize() - 1);
    FilledHexagon filledHex = new FilledHexagon(hex.getDiagonal(), hex.getRow(),
            hex.getRight(), hex.getLeft(),
            hex.getTopRight(), hex.getTopLeft(),
            hex.getBottomRight(), hex.getBottomLeft(),
            Color.BLACK);
    model.getGrid().set(model.getGrid().indexOf(hex), filledHex);
    this.mock.makeMove(Player.A, model.getHex(4, 3));
    this.mock.makeMove(Player.B, model.getHex(4, 7));
    Assert.assertEquals("diagonal = 4, row = 2\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 6, row = 4\n" +
            "diagonal = 3, row = 4\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 4, row = 1\n", log.toString());
  }

  /**
   * Tests whether the uppermost leftmost coordinate is returned when
   * (10,10) is filled.
   */
  @Test
  public void testAnyOpenCornerBottomRight() {
    ReversiModel model = new ReversiModel();
    this.log = new StringBuilder();
    this.mock = new ReversiMockModel(model, log);
    NoDiscHexagon hex = (NoDiscHexagon) model.getHex((2 * model.getBoardSize() - 1) / 2,
            (2 * model.getBoardSize() - 1) / 2);
    FilledHexagon filledHex = new FilledHexagon(hex.getDiagonal(), hex.getRow(),
            hex.getRight(), hex.getLeft(),
            hex.getTopRight(), hex.getTopLeft(),
            hex.getBottomRight(), hex.getBottomLeft(),
            Color.BLACK);
    model.getGrid().set(model.getGrid().indexOf(hex), filledHex);
    this.mock.makeMove(Player.A, model.getHex(4, 3));
    this.mock.makeMove(Player.B, model.getHex(4, 7));
    Assert.assertEquals("diagonal = 4, row = 2\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 6, row = 4\n" +
            "diagonal = 3, row = 4\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 4, row = 1\n", log.toString());
  }

  /**
   * Tests the extra credit method to determine how
   * it is combining strategies.
   */
  @Test
  public void testExtraCredit() {
    this.init();
    this.mock.makeMove(Player.A, model.getHex(4, 3));
    Assert.assertEquals("diagonal = 4, row = 2\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 6, row = 4\n", log.toString());

  }

  /**
   * Tests the captureMaxTiles strategy.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCaptureMaxTiles() {
    this.init();
    this.mock.makeMove(Player.A, model.getHex(4, 3));
    this.mock.makeMove(Player.B, model.getHex(4, 6));
    this.mock.makeMove(Player.A, model.getHex(4, 7));
    this.mock.makeMove(Player.B, model.getHex(3, 6));
    this.mock.makeMove(Player.A, model.getHex(6, 7));
    this.mock.makeMove(Player.A, model.getHex(6, 9));
    Assert.assertEquals("diagonal = 4, row = 2\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 6, row = 4\n" +
            "diagonal = 3, row = 2\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 4, row = 7\n" +
            "diagonal = 4, row = 8\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 4, row = 8\n" +
            "diagonal = 6, row = 4\n" +
            "diagonal = 3, row = 4\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 4, row = 9\n" +
            "diagonal = 7, row = 6\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 2, row = 3\n" +
            "diagonal = 8, row = 7\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 3, row = 6\n" +
            "diagonal = 2, row = 3\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 0, row = 0\n" +
            "diagonal = 6, row = 4\n", log.toString());
  }

  @Test
  public void testMultipleValidMoveToBottomRight() throws CloneNotSupportedException {
    init();
    ReversiModelDeepCopy m = copyModel.clone();
    m.model.makeMove(player, (NoDiscHexagon) this.model.getHex(4, 3));
    Assert.assertTrue(this.model.getHex(4, 3) instanceof NoDiscHexagon);
    Assert.assertTrue(m.model.getHex(4, 3) instanceof FilledHexagon);
  }

}
