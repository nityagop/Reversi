package controller;

import controller.MachineController;
import controller.ModelStatusObservers;
import controller.PlayerActions;
import controller.ReversiController;
import model.Player;
import view.ReversiView;

/**
 * Creates a mock of the Machine Controller for testing purposes.
 */
public class MachineControllerMock implements ReversiController, PlayerActions,
        ModelStatusObservers {

  private MachineController mc;
  private final StringBuilder log;

  /**
   * Constructs a controller.MachineControllerMock.
   * @param mc The "real" machine controller instance.
   * @param log A StringBuilder that keeps track of the output.
   */
  public MachineControllerMock(MachineController mc, StringBuilder log) {
    this.mc = mc;
    this.log = log;
  }

  @Override
  public void setView(ReversiView v) {
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
    log.append("Player passes\n");

  }



  @Override
  public Player checkTurn() {
    log.append("turn checked\n");
    return mc.checkTurn();

  }


  @Override
  public void changePlayer() {
    log.append("player is changed\n");
  }

}
