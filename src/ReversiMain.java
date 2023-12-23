import java.util.Scanner;
import controller.HumanController;
import controller.MachineController;
import controller.ModelStatusObservers;
import extracredit.model.SquareReversiModel;
import extracredit.view.ReversiSquareGraphicsView;
import extracredit.view.ReversiSquarePanelView;
import model.Player;
import model.Reversi;
import model.ReversiModel;
import model.ReversiPlayerImpl;
import strategy.CaptureMaxTiles;
import strategy.ReversiStrategy;
import view.ReversiDecorator;
import view.ReversiGraphicsView;
import view.ReversiPanelView;
import view.ReversiView;


/**
 * A class to implement the main method and serve as the entry-point for the Reversi game.
 */
public final class ReversiMain {


  static ModelStatusObservers rc1 = null;
  static ModelStatusObservers rc2 = null;

  // hex
  static ReversiModel rm = new ReversiModel();
  static ReversiPanelView panel = new ReversiPanelView(rm);
  static ReversiPanelView panel2 = new ReversiPanelView(rm);
  static ReversiView view = new ReversiGraphicsView(rm, panel);
  static ReversiView view2 = new ReversiGraphicsView(rm, panel2);


  // square
  static SquareReversiModel square = new SquareReversiModel();
  static ReversiSquarePanelView squarePanel = new ReversiSquarePanelView(square);
  static ReversiSquarePanelView squarePanel2 = new ReversiSquarePanelView(square);
  static ReversiView squareView = new ReversiSquareGraphicsView(square, squarePanel);
  static ReversiView squareView2 = new ReversiSquareGraphicsView(square, squarePanel2);

  /**
   * A main method to allow the user to choose different game variants from the command line,
   * when running the program.
   */
  public static void main(String[] args) {

    StringBuilder str = new StringBuilder();
    String sep = "";
    for (int i = 0; i < args.length; i++) {
      str.append(sep).append(args[i]);
      sep = " ";
    }
    String read = str.toString();
    Scanner scan = new Scanner(read);
    int count = 0;


    String s = scan.next();


    switch (s) {
      case "squareReversi":
        while (scan.hasNext()) {
          String c = scan.next();
          count++;
          switch (c) {
            case "human":
              try {
                if (count == 1) {
                  rc1 = new HumanController(square, Player.A, squareView);

                } else if (count == 2) {
                  rc2 = new HumanController(square, Player.B, squareView2);
                  System.out.print("squareView instantiated");

                }
              } catch (NullPointerException e) {
                System.out.print("Invalid input.");
              }
              break;
            case "strategy1":
              mainHelper(square, new CaptureMaxTiles(), count, squareView, squareView2);
              break;
            case "strategy2":
              //mainHelper(square, new AvoidCorners(), count, squareView, squareView2);
              break;
            case "strategy3":
              //mainHelper(square, new AnyOpenCorner(), count, view, view2);
              break;
            case "strategy4":
              //mainHelper(square, new Minimax(), count, squareView, squareView2);
              break;
            case "h":
              System.out.println("hint on");
              panel = new ReversiDecorator(rm);
              break;
            case "off":
              panel = new ReversiPanelView(rm);
              break;
            default:
              System.out.println("Score: 0");
          }
        }
        try {
          square.addObservers(rc1);
          square.addObservers(rc2);
          squareView.makeVisible();
          squareView2.makeVisible();
          rc1.setView(squareView);
          rc2.setView(squareView2);
          square.startGame();
        } catch (Exception e) {
          System.out.println("Game could not be started because of invalid input");
        }
        break;

      case "originalReversi":
        while (scan.hasNext()) {
          count++;
          String x = scan.next();
          switch (x) {
            case "human":
              try {
                if (count == 1) {
                  rc1 = new HumanController(rm, Player.A, view);
                } else if (count == 2) {
                  rc2 = new HumanController(rm, Player.B, view2);
                }
              } catch (NullPointerException e) {
                System.out.print("Invalid input.");
              }
              break;
            case "strategy1":
              mainHelper(rm, new CaptureMaxTiles(), count, view, view2);
              break;
            case "strategy2":
              //mainHelper(rm, new AvoidCorners(), count, view, view2);
              break;
            case "strategy3":
              //mainHelper(rm, new AnyOpenCorner(), count, view, view2);
              break;
            case "strategy4":
              //mainHelper(rm, new Minimax(), count, view, view2);
              break;
            case "h":
              System.out.println("hint on");
              panel = new ReversiDecorator(rm);
              break;
            case "off":
              panel = new ReversiPanelView(rm);
              break;
            default:
              System.out.println("Score: 0");
          }
        }

        try {
          rm.addObservers(rc1);
          rm.addObservers(rc2);
          view.makeVisible();
          view2.makeVisible();
          rc1.setView(view);
          rc2.setView(view2);
          rm.startGame();
        } catch (Exception e) {
          System.out.println("Game could not be started because of invalid input ");
        }
        break;
      default:
        System.out.println("Score: 0");

    }
  }

  // helper method that handles the try catch inside each case and creates the 2 controllers
  // used in the game
  private static void mainHelper(Reversi model, ReversiStrategy rs, int count, ReversiView view,
                                 ReversiView view2) {
    try {
      if (count == 1) {
        ReversiPlayerImpl player1 = new ReversiPlayerImpl(Player.A, rs);
        rc1 = new MachineController(model, player1, view);
      } else if (count == 2) {
        ReversiPlayerImpl player1 = new ReversiPlayerImpl(Player.B, rs);
        rc2 = new MachineController(model, player1, view2);
      }
    } catch (NullPointerException e) {
      System.out.print("Invalid input");
    }
  }
}