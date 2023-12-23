package reversisqaure;

import java.util.List;

import controller.ModelStatusObservers;
import extracredit.model.NoDiscSquare;
import extracredit.model.SquareReversiModel;
import model.AbstractHexagon;
import model.NoDiscHexagon;
import model.Player;
import model.Reversi;
import strategy.CaptureMaxTiles;

/**
 * Creates a mock of the SquareReversiModel.
 */
public class ReversiSquareModelMock implements Reversi {

  private SquareReversiModel model;
  final StringBuilder log;

  public ReversiSquareModelMock(SquareReversiModel model, StringBuilder log) {
    this.model = model;
    this.log = log;
  }

  @Override
  public boolean gameOver() {
    return this.model.gameOver();
  }

  @Override
  public int getScore(Player who) {
    return this.model.getScore(who);
  }

  @Override
  public AbstractHexagon getHex(int i, int j) {
    return this.model.getHex(i, j);
  }

  @Override
  public int getBoardSize() {
    return this.model.getBoardSize();
  }

  @Override
  public List<NoDiscHexagon> getGrid() {
    return this.model.getGrid();
  }

  @Override
  public Player getPlayer() {
    return this.model.getPlayer();
  }

  @Override
  public boolean gameState() {
    return false;
  }

  @Override
  public int getPotentialTiles(int x, int y) {
    return 0;
  }

  @Override
  public List<NoDiscSquare> getBoard() {
    return null;
  }

  @Override
  public AbstractHexagon getHexagon2(int i, int i1, List<NoDiscSquare> board) {
    return null;
  }

  @Override
  public void startGame() {
    this.model.startGame();
  }

  @Override
  public List<AbstractHexagon> getBoardGame() {
    return null;
  }

  @Override
  public void makeMove(Player who, AbstractHexagon where) {
    this.model.startGame();
    this.model.makeMove(who, where);
    CaptureMaxTiles c = new CaptureMaxTiles();
    this.model.nextPlayer(who);
    List<Integer> capturedTile = c.chooseTile(this.model, this.model.getPlayer());
    log.append(String.format("diagonal = %s, row = %d\n",
            capturedTile.get(0), capturedTile.get(1)));
    this.model.nextPlayer(who);

  }

  @Override
  public void nextPlayer(Player player) {
    this.model.nextPlayer(player);
  }

  @Override
  public void addObservers(ModelStatusObservers rc) {
    this.model.addObservers(rc);
  }
}
