package view;

import java.awt.Color;
import java.awt.geom.Path2D;
import java.awt.Graphics2D;

import model.ReadOnlyReversi;

/**
 * A class to represent the hint decoration in a game
 * of Reversi. Players are able to decide whether they
 * want a hint of what tile they should place a disc in.
 * They will be able to enable or disable this hint whenever
 * they want.
 */
public class ReversiDecorator extends ReversiPanelView {

  ReadOnlyReversi model;

  /**
   * Constructs a ReversiDecorator.
   *
   * @param model An instance of a ReadOnlyReversi model
   *              that is responsible for providing the
   *              functionality of the ReversiModel.
   */
  public ReversiDecorator(ReadOnlyReversi model) {
    super(model);
  }


  /**
   * Draws a single hexagon using Path2D.Double but changes the
   * color to represent when a tile has been selected. Follows
   * the same process for drawing a gray hexagon.
   *
   * @param g2d    The Graphics2D instance responsible for drawing the hexagon.
   * @param xCoord The x coordinate for where the hexagon will be drawn.
   * @param yCoord The y coordinate for where the hexagon will be drawn.
   */
  @Override
  public void changeColor(Graphics2D g2d, int xCoord, int yCoord) {

    int size = 20;
    Path2D.Double hex = new Path2D.Double();
    double x = 0;
    double y = 0;

    for (int i = 0; i < 6; i++) {
      double angleDeg = 60 * i - 30;
      double angle = Math.toRadians(angleDeg);
      x = xCoord + size * Math.cos(angle);
      y = yCoord + size * Math.sin(angle);
      if (i == 0) {
        hex.moveTo(x, y);
      } else {
        hex.lineTo(x, y);
      }
    }
    hex.closePath();
    g2d.setColor(Color.CYAN);
    g2d.fill(hex);
    g2d.setColor(Color.BLACK);
    g2d.draw(hex);
    int n = model.getPotentialTiles(xCoord, yCoord);

    String numFlipped = "" + n;
    g2d.drawString(
            numFlipped,
            xCoord,
            yCoord);

  }


}
