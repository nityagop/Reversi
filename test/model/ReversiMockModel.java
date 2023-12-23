package model;

import java.util.List;
import java.util.Objects;

import controller.ModelStatusObservers;
import extracredit.model.NoDiscSquare;
import strategy.CaptureMaxTiles;


/**
 * Creates a mock of the ReversiModel.
 */
public class ReversiMockModel implements Reversi {

  private ReversiModel model;
  final StringBuilder log;

  /**
   * Constructs a mock ReversiModel.
   * @param model The "real" Reversi Model.
   * @param log The StringBuilder to keep track of the output.
   */
  public ReversiMockModel(ReversiModel model, StringBuilder log) {
    this.model = model;
    this.log = Objects.requireNonNull(log);
  }


  @Override
  public boolean gameOver() {
    this.model.gameOver();
    return false;
  }

  @Override
  public int getScore(Player who) {
    return 0;
  }

  @Override
  public AbstractHexagon getHex(int i, int j) {
    return null;
  }

  @Override
  public int getBoardSize() {
    return 0;
  }

  @Override
  public List<NoDiscHexagon> getGrid() {
    return null;
  }

  @Override
  public Player getPlayer() {
    return null;
  }

  @Override
  public boolean gameState() {
    return false;
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

    /*AnyOpenCorner aoc = new AnyOpenCorner();
    NoDiscHexagon openCornerTile = aoc.chooseTile(this.model, this.model.getPlayer());
    log.append(String.format("diagonal = %s, row = %d\n",
            openCornerTile.getDiagonal(), openCornerTile.getRow()));
    this.model.nextPlayer(who);

    AvoidCorners avoidCorners = new AvoidCorners();
    NoDiscHexagon avoidCornersTile = avoidCorners.chooseTile(this.model, this.model.getPlayer());
    log.append(String.format("diagonal = %s, row = %d\n",
            avoidCornersTile.getDiagonal(), avoidCornersTile.getRow()));
    this.model.nextPlayer(who);

    Minimax minimax = new Minimax();
    NoDiscHexagon mini = minimax.chooseTile(this.model, this.model.getPlayer());
    log.append(String.format("diagonal = %s, row = %d\n",
            mini.getDiagonal(), mini.getRow()));

    this.model.nextPlayer(who);

    ExtraCredit ec = new ExtraCredit();
    List<ReversiStrategy> strategies = Arrays.asList(c, aoc, avoidCorners);
    List<NoDiscHexagon> moves = ec.chooseTiles(this.model, this.model.getPlayer(), strategies);
    for (NoDiscHexagon move : moves) {
      log.append(String.format("diagonal = %s, row = %d\n",
              move.getDiagonal(), move.getRow()));
    }*/
  }

  @Override
  public void nextPlayer(Player player) {
    this.model.nextPlayer(player);
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
  public void addObservers(ModelStatusObservers rc) {
    this.model.addObservers(rc);
  }

}