package reversisqaure;

import org.junit.Assert;
import org.junit.Test;

import controller.HumanController;
import controller.HumanControllerMock;
import controller.MachineController;
import controller.MachineControllerMock;
import controller.ModelStatusObservers;
import controller.PlayerActions;
import extracredit.model.SquareReversiModel;
import extracredit.view.ReversiSquareGraphicsView;
import extracredit.view.ReversiSquarePanelView;
import model.Player;
import model.Reversi;
import model.ReversiPlayer;
import model.ReversiPlayerImpl;
import strategy.CaptureMaxTiles;
import strategy.ReversiStrategy;
import view.ReversiView;

/**
 * A JUnit test class to test whether the controllers
 * work with a ReversiSquareModel.
 */
public class ReversiSquareControllerTest {

  Reversi rm;
  PlayerActions paMock;
  HumanController hc;
  ReversiView view;
  StringBuilder paLog;
  StringBuilder maLog;
  ReversiSquarePanelView panel;
  ModelStatusObservers mcMock;
  MachineController mc;
  ReversiPlayer player;
  ReversiStrategy strategy;

  /**
   * Initializes the Reversi model, controller, and view.
   */
  private void init() {
    this.rm = new SquareReversiModel();
    this.panel = new ReversiSquarePanelView(rm);
    this.view = new ReversiSquareGraphicsView(this.rm, this.panel);
    this.paLog = new StringBuilder();
    this.maLog = new StringBuilder();
    this.hc = new HumanController(this.rm, Player.A, this.view);
    this.paMock = new HumanControllerMock(this.hc, this.paLog);
    this.strategy = new CaptureMaxTiles();
    this.player = new ReversiPlayerImpl(Player.A, this.strategy);
    this.mc = new MachineController(this.rm, this.player, this.view);
    this.mcMock = new MachineControllerMock(this.mc, this.maLog);
  }

  /**
   * Tests whether a player is able to make a move.
   */
  @Test
  public void testPlayerMove() {
    this.init();
    this.paMock.playerMove();
    Assert.assertEquals("Player moved\n", paLog.toString());
  }

  /**
   * Tests whether a player is able to pass their turn.
   */
  @Test
  public void testPlayerPasses() {
    this.init();
    this.paMock.playerPass();
    Assert.assertEquals("Player passes\n", paLog.toString());
    this.paMock.playerMove();
    Assert.assertEquals("Player passes\n" +
            "Player moved\n", paLog.toString());
  }

  /**
   * Tests whether the view has been set.
   */
  @Test
  public void testSetView() {
    this.init();
    this.mcMock.setView(this.view);
    Assert.assertEquals("View set\n", maLog.toString());
  }

  /**
   * Tests whether the view has been refreshed.
   */
  @Test
  public void testRefreshView() {
    this.init();
    this.mcMock.refreshView();
    Assert.assertEquals("View refreshed\n", maLog.toString());
  }

  /**
   * Tests whether the player has been changed.
   */
  @Test
  public void testChangePlayer() {
    this.init();
    this.mcMock.changePlayer();
    Assert.assertEquals("player is changed\n", maLog.toString());
  }


}
