package extracredit.view;

import java.awt.Color;

import extracredit.model.FilledSquare;
import extracredit.model.NoDiscSquare;
import model.AbstractHexagon;
import model.ReadOnlyReversi;


/**
 * A simple text-based rendering of the Reversi game. In out textual
 * view a "_" represents a tile with no disc, a "X" represents a black
 * disc, and a "O" represents a white disc.
 */
public class ReversiSquareTextualView {
  private final ReadOnlyReversi model;

  public ReversiSquareTextualView(ReadOnlyReversi model) {
    this.model = model;
  }

  /**
   * A String representation of the Reversi game.
   *
   * @return A String of the Reversi game.
   */
  public String toString() {
    return this.getSquareBoard(this.model.getBoardSize());
  }

  // helper method for toString that returns a string representation of the game
  private String getSquareBoard(int size) {
    StringBuilder result = new StringBuilder();

    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        if (i == size / 2 - 1 && j == size / 2 || i == size / 2 && j == size / 2 - 1) {
          AbstractHexagon hex = new FilledSquare(i, j, Color.WHITE);
          result.append(this.fillBoard(hex));
        } else if (i == size / 2 && j == size / 2 || i == size / 2 - 1 && j == size / 2 - 1) {
          AbstractHexagon hex = new FilledSquare(i, j, Color.BLACK);
          result.append(this.fillBoard(hex));
        } else {
          NoDiscSquare hex = new NoDiscSquare(i, j);
          result.append(this.fillBoard(hex));
        }
      }
      result.append("\n");
    }

    return result.toString();
  }

  /**
   * Fills square board with a "_", "X", or "O" depending
   * on the game state.
   *
   * @param current The square that is being updated.
   * @return A String representation of the square based on whether
   *         it is a black disc, white disc, or with no disc.
   */
  private String fillBoard(AbstractHexagon current) {
    String result = "";
    if (current instanceof FilledSquare
            && ((FilledSquare) current).getColor() == Color.BLACK) {
      result += "X ";
    } else if (current instanceof FilledSquare
            && ((FilledSquare) current).getColor() == Color.WHITE) {
      result += "O ";
    } else {
      result += "_ ";
    }
    return result;
  }
}


