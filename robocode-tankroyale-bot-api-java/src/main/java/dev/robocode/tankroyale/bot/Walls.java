package dev.robocode.tankroyale.bot;

import dev.robocode.tankroyale.botapi.Bot;
import dev.robocode.tankroyale.botapi.BotInfo;
import dev.robocode.tankroyale.botapi.events.BotHitBotEvent;
import dev.robocode.tankroyale.botapi.events.ScannedBotEvent;
import dev.robocode.tankroyale.botapi.events.TickEvent;

import java.io.IOException;

/**
 * Walls - a sample bot, original version by Mathew Nelson for Robocode.
 * Modified by Flemming N. Larsen.
 *
 * <p>Moves around the outer edge with the gun facing in.
 */
public class Walls extends Bot {

  boolean peek; // Don't turn if there's a robot there
  double moveAmount; // How much to move

  boolean stopMoving; // flag for stop moving temporarily

  /** Main method starts our bot */
  public static void main(String[] args) throws IOException {
    new Walls().start();
  }

  /** Constructor, which loads the bot settings file */
  protected Walls() throws IOException {
    super(BotInfo.fromFile("walls.properties"));
  }

  /** run: This method runs our bot program, where each command is executed one at a time in a loop. */
  @Override
  public void run() {
    // Set colors
    setBodyColor("#000"); // black
    setGunColor("#000"); // black
    setRadarColor("#F90"); // orange
    setTurretColor("#F90"); // orange
    setBulletColor("#0FF"); // cyan

    // Initialize moveAmount to the maximum possible for the arena
    moveAmount = Math.max(getArenaWidth(), getArenaHeight());
    // Initialize peek to false
    peek = false;

    // turn to face a wall.
    // 'getDirection() % 90' means the remainder of getDirection() divided by 90.
    turnRight(getDirection() % 90);
    forward(moveAmount);

    // Turn the gun to turn right 90 degrees.
    peek = true;
    turnGunRight(90);
    turnRight(90);

    // Main loop
    while (isRunning()) {
      if (stopMoving) {
        go(); // Do nothing this turn, but let the turn pass
      } else {
        // Peek before we turn when forward() completes.
        peek = true;
        // Move up the wall
        forward(moveAmount);
        // Don't peek now
        peek = false;
        // Turn to the next wall
        turnRight(90);
      }
    }
  }

  /** onTick: Every new turn, reset/remove the interrupt */
  @Override
  public void onTick(TickEvent e) {
    stopMoving = false; // Reset the stopMoving flag automatically each turn. Only onScannedBot() will set it
  }

  /** onHitBot: Move away a bit. */
  @Override
  public void onHitBot(BotHitBotEvent e) {
    // If he's in front of us, set back up a bit.
    double bearing = bearingTo(e.getX(), e.getY());
    if (bearing > -90 && bearing < 90) {
      back(100);
    } else { // else he's in back of us, so set ahead a bit.
      forward(100);
    }
  }

  /** onScannedBot: Fire! */
  @Override
  public void onScannedBot(ScannedBotEvent e) {
    fire(2);

    if (peek) {
      stopMoving = true; // interrupt/stop turning the gun in the main loop in the run() method
    }
  }
}