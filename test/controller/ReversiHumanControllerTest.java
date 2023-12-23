package controller;

import org.junit.Assert;
import org.junit.Test;

import controller.HumanController;
import controller.HumanControllerMock;
import controller.PlayerActions;
import model.Player;
import model.Reversi;
import model.ReversiModel;
import view.ReversiGraphicsView;
import view.ReversiPanelView;
import view.ReversiView;

/**
 * A JUnit test class to test the HumanController class.
 */
public class ReversiHumanControllerTest {

  Reversi rm;
  PlayerActions mock;
  HumanController pa;
  ReversiView view;
  StringBuilder log;
  ReversiPanelView panel;

  /**
   * Initializes the Reversi model, controller, and view.
   */
  private void init() {
    this.rm = new ReversiModel();
    this.panel = new ReversiPanelView(rm);
    this.view = new ReversiGraphicsView(this.rm, panel);
    this.log = new StringBuilder();
    this.pa = new HumanController(this.rm, Player.A, this.view);
    this.mock = new HumanControllerMock(this.pa, this.log);
  }

  /**
   * Tests whether a player is able to make a move.
   */
  @Test
  public void testPlayerMove() {
    this.init();
    this.mock.playerMove();
    Assert.assertEquals("Player moved\n", log.toString());
  }

  /**
   * Tests whether a player is able to pass their turn.
   */
  @Test
  public void testPlayerPasses() {
    this.init();
    this.mock.playerPass();
    Assert.assertEquals("Player passes\n", log.toString());
    this.mock.playerMove();
    Assert.assertEquals("Player passes\n" +
            "Player moved\n", log.toString());

  }
}
