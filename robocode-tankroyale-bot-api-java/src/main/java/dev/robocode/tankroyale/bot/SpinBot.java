package dev.robocode.tankroyale.bot;

import dev.robocode.tankroyale.botapi.Bot;
import dev.robocode.tankroyale.botapi.BotInfo;
import dev.robocode.tankroyale.botapi.events.BotHitBotEvent;
import dev.robocode.tankroyale.botapi.events.ScannedBotEvent;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

/**
 * SpinBot - a sample robot.
 *
 * <p>Moves in a circle, firing hard when an enemy is detected.
 */
public class SpinBot extends Bot {

  public static void main(String[] args) throws IOException {
    BotInfo botInfo =
        new BotInfo("SpinBot", "1.0", "fnl", "", "", "dk", Arrays.asList("melee"), "jvm", "java");
    new SpinBot(botInfo).start();
  }

  protected SpinBot(BotInfo botInfo) throws IOException {
    super(botInfo);
  }

  /** SpinBot's run method - Move in a circle */
  @Override
  public void run() {
    /*
    setBodyColor("#0000FF"); // blue
    setTurretColor("#0000FF"); // blue
    setRadarColor("#000000"); // black
    setScanColor("#FFFF00"); // yellow
     */
    setBodyColor("#000000");
    setTurretColor("#000000");
    setRadarColor("#000000");
    setScanColor("#000000");
    setBulletColor("#FF0000");
    setTracksColor("#FFFF00");

    // Repeat while bot is running
    while (isRunning()) {
      // Tell the game that when we take move, we'll also want to turn right... a lot.
      setTurnRight(10000);
      // Limit our speed to 5
      setMaxSpeed(5);
      // Start moving (and turning)
      forward(10000);
    }
  }

  /** onScannedBot: Fire hard when scanning another bot! */
  @Override
  public void onScannedBot(ScannedBotEvent e) {
    fire(3); // Fire the cannon!
  }

  /**
   * onHitBot: If it's our fault, we'll stop turning and moving, so we need to turn again to keep
   * spinning.
   */
  @Override
  public void onHitBot(BotHitBotEvent e) {
    double direction = calcDirection(e.getX(), e.getY());
    double bearing = calcBearing(direction);
    if (bearing > -10 && bearing < 10) {
      fire(3);
    }
    if (e.isRammed()) {
      turnRight(10);
    }
  }
}