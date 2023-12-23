package extracredit.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


import controller.PlayerActions;
import model.AbstractHexagon;
import model.ReadOnlyReversi;
import view.ReversiKey;
import view.ReversiView;

/**
 * This is an implementation of the ReversiView interface
 * that uses Java Swing to draw the initial square grid of a Reversi board.
 * It also allows users to select and deselect tiles and prints out the
 * coordinate that was selected in the console.
 */
public class ReversiSquareGraphicsView extends JFrame implements ReversiView {

  private final ReadOnlyReversi model;
  private final ReversiSquarePanelView panel;

  /**
   * Constructs a ReversiSquareGraphicsView. Involves creating a JPanel
   * and instantiates the mouse and key listeners.
   * @param model The ReadOnlyReversi model that provides the
   *              functionality for the view.
   */
  public ReversiSquareGraphicsView(ReadOnlyReversi model, ReversiSquarePanelView panel) {
    super();
    this.panel = panel;

    this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    // instantiates a JPanel and adds it to the frame
    this.add( panel);
    this.pack();


    this.model = Objects.requireNonNull(model);

    // adds the mouse and key listener
    this.addClickListener();
    this.addKeyListener(new ReversiKey());
    //this.setLocationRelativeTo(null);
  }

  /**
   * Shows or hides the GUI based on what the visibility is
   * set to.
   */
  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  /**
   * Calls the windows paint component to refresh the
   * game board.
   */
  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void addClickListener() {
    this.panel.addMouseListener(new ReversiSquareMouse(this.model, this.panel));
    this.refresh();
  }



  @Override
  public void addFeatures(PlayerActions features) {
    this.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {
        // nothing needed here
      }

      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'M' || e.getKeyChar() == 'm') {
          System.out.print("user pressed key m in add features");
          features.playerMove();

        }
        else if (e.getKeyChar() == 'P' || e.getKeyChar() == 'p') {
          System.out.print("user pressed key p in add features");
          features.playerPass();

        }
      }

      @Override
      public void keyReleased(KeyEvent e) {
        // nothing needed here
      }
    });
  }

  @Override
  public AbstractHexagon selectedTile() {
    return this.panel.getLastSelection();
  }

  @Override
  public void showMessage(String message) {
    JOptionPane.showMessageDialog(this, message,
            "Information", JOptionPane.PLAIN_MESSAGE);
  }

  @Override
  public void showMessageInvalidMoves(String s) {
    JOptionPane.showMessageDialog(this, s,
            "Error Message", JOptionPane.ERROR_MESSAGE);
  }


}
