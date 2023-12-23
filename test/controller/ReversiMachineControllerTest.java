package controller;

import org.junit.Assert;
import org.junit.Test;

import controller.MachineController;
import controller.MachineControllerMock;
import controller.ModelStatusObservers;
import model.Player;
import model.Reversi;
import model.ReversiModel;
import model.ReversiPlayer;
import model.ReversiPlayerImpl;
import strategy.CaptureMaxTiles;
import strategy.ReversiStrategy;
import view.ReversiGraphicsView;
import view.ReversiPanelView;
import view.ReversiView;

/**
 * A JUnit test class to test the MachineController in Reversi.
 */
public class ReversiMachineControllerTest {

  Reversi rm;
  ModelStatusObservers mock;
  MachineController mc;
  ReversiView view;
  StringBuilder log;
  ReversiPlayer player;
  ReversiStrategy strategy;

  /**
   * Initializes the model, view, and controllers of Reversi.
   */
  private void init() {
    this.rm = new ReversiModel();
    this.view = new ReversiGraphicsView(this.rm, new ReversiPanelView(this.rm));
    this.log = new StringBuilder();
    this.strategy = new CaptureMaxTiles();
    this.player = new ReversiPlayerImpl(Player.A, this.strategy);
    this.mc = new MachineController(this.rm, this.player, this.view);
    this.mock = new MachineControllerMock(this.mc, this.log);
  }

  /**
   * Tests whether the view has been set.
   */
  @Test
  public void testSetView() {
    this.init();
    this.mock.setView(this.view);
    Assert.assertEquals("View set\n", log.toString());
  }

  /**
   * Tests whether the view has been refreshed.
   */
  @Test
  public void testRefreshView() {
    this.init();
    this.mock.refreshView();
    Assert.assertEquals("View refreshed\n", log.toString());
  }

  /**
   * Tests whether the player has been changed.
   */
  @Test
  public void testChangePlayer() {
    this.init();
    this.mock.changePlayer();
    Assert.assertEquals("player is changed\n", log.toString());
  }


}
