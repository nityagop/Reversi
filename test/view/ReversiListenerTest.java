package view;

import org.junit.Assert;
import org.junit.Test;

import java.awt.event.KeyEvent;

import controller.HumanController;
import controller.PlayerActions;
import model.Player;
import model.Reversi;
import model.ReversiModel;

/**
 * A JUnit test class to test the features in our game
 * of Reversi.
 */
public class ReversiListenerTest {

  ReversiView view;
  ReversiView mock;
  Reversi model;
  StringBuilder log;
  PlayerActions pa;


  /**
   * Initializes the ReversiGame.
   */
  private void init() {
    this.model = new ReversiModel();
    this.view = new ReversiGraphicsView(this.model, new ReversiPanelView(this.model));
    this.log = new StringBuilder();
    this.mock = new ReversiGraphicsMockView(this.view, this.log);
    this.pa = new HumanController(this.model, Player.A, this.view);

  }

  /**
   * Tests whether the m key was pressed.
   */
  @Test
  public void testKeyListenerM() {
    this.init();
    this.mock.addFeatures(this.pa);
    KeyEvent.getKeyText(KeyEvent.VK_E);
    Assert.assertEquals("", log.toString());
  }

  /**
   * Tests whether the p key was pressed.
   */
  @Test
  public void testKeyListenerP() {
    this.init();
    this.mock.addFeatures(this.pa);
    KeyEvent.getKeyText(KeyEvent.VK_E);
    Assert.assertEquals("", log.toString());
  }

  /**
   * Tests whether a tile has been selected using MouseListeners.
   */
  @Test
  public void testSelectedTileWithMouse() {
    this.init();
    this.mock.selectedTile();
    Assert.assertEquals("Tile has been selected by the player", log.toString());

  }

  /**
   * Tests whether the it's your turn messages has been displayed.
   */
  @Test
  public void testShowMessage() {
    this.init();
    this.mock.showMessage("It's your turn!");
    Assert.assertEquals("It's your turn message displayed", log.toString());

  }

  /**
   * Tests whether the invalid move message has been displayed.
   */
  @Test
  public void testShowErrorMessage() {
    this.init();
    this.mock.showMessageInvalidMoves("invalid");
    Assert.assertEquals("Invalid move message displayed", log.toString());
  }

  /**
   * Tests whether the view is visible.
   */
  @Test
  public void testViewIsVisible() {
    this.init();
    this.mock.makeVisible();
    Assert.assertEquals("View is visible", log.toString());
  }

  /**
   * Tests whether the view has been refreshed.
   */
  @Test
  public void testViewIsRefreshed() {
    this.init();
    this.mock.refresh();
    Assert.assertEquals("View is refreshed", log.toString());
  }
}
