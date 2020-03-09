using Newtonsoft.Json;

namespace Robocode.TankRoyale.BotApi
{
  /// <summary>
  /// Event occurring when the bot has hit a wall.
  /// </summary>
  public sealed class BotHitWallEvent : Event
  {
    /// <summary>ID of the victim bot that hit the wall.<summary>
    [Newtonsoft.Json.JsonProperty("victimId", Required = Newtonsoft.Json.Required.Always)]
    public int VictimId { get; }

    /// <summary>
    /// Constructor.
    /// </summary>
    /// <param name="turnNumber">Turn number.</param>
    /// <param name="victimId">ID of the victim bot that hit the wall.</param>
    [JsonConstructor]
    public BotHitWallEvent(int turnNumber, int victimId) : base(turnNumber) => VictimId = victimId;
  }
}