using Newtonsoft.Json;

namespace Robocode.TankRoyale.BotApi
{
  /// <summary>
  /// Event occurring when game has just started.
  /// </summary>
  public sealed class GameStartedEvent : IMessage
  {
    /// <summary>The ID used for identifying your bot in the current battle.</summary>
    public int MyId { get; }

    /// <summary>The game setup for the battle just started.</summary>
    public GameSetup GameSetup { get; }

    /// <summary>
    /// Constructor.
    /// </summary>
    /// <param name="myId">The ID used for identifying your bot in the current battle.</param>
    /// <param name="gameSetup">The game setup for the battle just started.</param>
    [JsonConstructor]
    public GameStartedEvent(int myId, GameSetup gameSetup) : base() =>
      (MyId, GameSetup) = (myId, gameSetup);
  }
}