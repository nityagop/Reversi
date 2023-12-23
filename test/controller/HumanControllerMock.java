package controller;

import controller.HumanController;
import controller.ModelStatusObservers;
import controller.PlayerActions;
import controller.ReversiController;
import model.Player;
import view.ReversiView;

/**
 * Creates a mock of the HumanController for testing purposes.
 */
public class HumanControllerMock implements ReversiController, PlayerActions,
        ModelStatusObservers {

  private HumanController hc;
  private final StringBuilder log;

  /**
   * Constructs a controller.HumanControllerMock.
   * @param hc The "real" human controller instance.
   * @param log A StringBuilder to keep track of the outputs.
   */
  public HumanControllerMock(HumanController hc, StringBuilder log) {
    this.hc = hc;
    this.log = log;
  }

  @Override
  public Player checkTurn() {
    log.append("Turn checked\n");
    return this.hc.checkTurn();
  }

  @Override
  public void changePlayer() {
    log.append("Player changed\n");
    this.hc.changePlayer();
  }

  @Override
  public void setView(ReversiView view) {
    log.append("View set\n");
  }

  @Override
  public void refreshView() {
    log.append("View refreshed\n");
  }

  @Override
  public void playerMove() {
    log.append("Player moved\n");
  }

  @Override
  public void playerPass() {
    this.hc.playerPass();
    log.append("Player passes\n");
  }
}
