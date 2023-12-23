package model;

import java.util.List;

import strategy.ReversiStrategy;

/**
 * A simple Player implementation that delegates most of its
 * complexity to a for choosing where to play next.
 */
public class ReversiPlayerImpl implements ReversiPlayer {
  private final Player player;
  private final ReversiStrategy strategy;

  public ReversiPlayerImpl(Player player, ReversiStrategy strategy) {
    this.player = player;
    this.strategy = strategy;
  }

  /**
   * Allows the player to choose the tile
   * they want to move in and play the game.
   * @param model The Reversi model that provides the ability
   *              to move a disc into a tile.
   * @return The tile that the player wants to move into.
   */
  @Override
  public List<Integer> play(Reversi model) {
    return strategy.chooseTile(model, this.player);
  }

  /**
   * Determines the Player whose turn it currently is.
   * @return The Player who is currently playing the game.
   */
  public Player getPlay() {
    return this.player;
  }
}