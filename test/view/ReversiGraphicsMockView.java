package view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import controller.PlayerActions;
import model.AbstractHexagon;

/**
 * Creates a mock of the ReversiGraphicsView class for testing
 * purposes.
 */
public class ReversiGraphicsMockView extends JFrame implements ReversiView {

  private ReversiView view;
  final StringBuilder log;

  /**
   * Constructs a view.ReversiGraphicsMockView.
   * @param view The "real" ReversiGraphicsView.
   * @param log The StringBuilder that keeps track of the output.
   */
  public ReversiGraphicsMockView(ReversiView view, StringBuilder log) {
    this.view = view;
    this.log = log;
  }

  @Override
  public void makeVisible() {
    this.view.makeVisible();
    log.append("View is visible");
  }

  @Override
  public void refresh() {
    log.append("View is refreshed");
  }


  @Override
  public void addClickListener() {
    log.append("listeners added");
  }

  @Override
  public void addFeatures(PlayerActions features) {
    this.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {
        // Determines which key the user typed.
      }

      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'M' || e.getKeyChar() == 'm') {
          log.append("m pressed");

        }
        else if (e.getKeyChar() == 'P' || e.getKeyChar() == 'p') {
          log.append("p pressed");
        }
      }

      @Override
      public void keyReleased(KeyEvent e) {
        // Determines when the user released a key.
      }
    });
  }

  @Override
  public AbstractHexagon selectedTile() {
    log.append("Tile has been selected by the player");
    return null;
  }

  @Override
  public void showMessage(String message) {
    log.append("It's your turn message displayed");
  }

  @Override
  public void showMessageInvalidMoves(String s) {
    log.append("Invalid move message displayed");
  }
}
