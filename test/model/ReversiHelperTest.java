package model;

import org.junit.Assert;
import org.junit.Test;

import java.awt.Color;
import java.util.List;

import model.AbstractHexagon;
import model.EmptyHexagon;
import model.FilledHexagon;
import model.NoDiscHexagon;
import model.ReversiModel;
import model.Player;

/**
 * A JUnit class for testing helper methods in ReversiModel.
 */
public class ReversiHelperTest {

  ReversiModel rm;
  List<NoDiscHexagon> grid;
  AbstractHexagon empty;
  Player player1;
  Player player2;

  /**
   * Initializes the ReversiGame and creates some example tiles.
   */
  private void init() {
    this.rm = new ReversiModel();
    this.grid = this.rm.getGrid();
    this.empty = new EmptyHexagon(-1, -1);
    this.player1 = Player.A;
    this.player2 = Player.B;
    this.rm.startGame();
  }

  /**
   * Tests whether the top corner of the grid is accurately linked
   * with the correct neighbors.
   */
  @Test
  public void testLinkedListTopCorner() {
    init();
    Assert.assertEquals(this.rm.getGrid().get(0).getRight(),
            this.rm.getGrid().get(6));
    Assert.assertEquals(this.rm.getGrid().get(0).getBottomLeft(),
            this.rm.getGrid().get(1));
    Assert.assertEquals(this.rm.getGrid().get(0).getBottomRight(),
            this.rm.getGrid().get(7));
    Assert.assertTrue(this.rm.getGrid().get(0).getLeft() instanceof EmptyHexagon);
    Assert.assertTrue(this.rm.getGrid().get(0).getTopLeft() instanceof EmptyHexagon);
    Assert.assertTrue(this.rm.getGrid().get(0).getTopRight() instanceof EmptyHexagon);
  }

  /**
   * Tests the getHexagon() method to ensure its returning the
   * proper tiles.
   */
  @Test
  public void testGetHexagon() {
    init();
    this.rm.makeMove(player1, this.rm.getHexagon(4, 3, this.rm.getGrid()));
    this.rm.makeMove(player2, this.rm.getHexagon(4, 6, this.grid));
    Assert.assertTrue(
            this.rm.getHexagon(4, 6, this.grid) instanceof FilledHexagon);
  }

  /**
   * Tests the getDiagonal() and getRow() methods.
   */
  @Test
  public void testValidDiagAndRow() {
    init();
    Assert.assertEquals(0, this.rm.getGrid().get(0).getDiagonal());
    Assert.assertEquals(0, this.rm.getGrid().get(0).getRow());
  }

  /**
   * Tests whether an IllegalArgumentException is thrown for diagonal
   * and row values less than 0.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidDiagAndRow() {
    NoDiscHexagon hexInvalidDiag = new NoDiscHexagon(-1, 1);
    NoDiscHexagon hexInvalidRow = new NoDiscHexagon(1, -1);
  }

  /**
   * Tests the getColor() method.
   */
  @Test
  public void testGetColor() {
    init();
    this.rm.makeMove(player1, this.rm.getHexagon(4, 3, this.rm.getGrid()));
    this.rm.makeMove(player2, this.rm.getHexagon(4, 6, this.rm.getGrid()));
    Assert.assertTrue(this.rm.getHexagon(4, 6, this.rm.getGrid()) instanceof FilledHexagon);
    Assert.assertEquals(Color.WHITE, ((FilledHexagon) this.rm.getHexagon(4, 6,
            this.rm.getGrid())).getColor());
  }
}
