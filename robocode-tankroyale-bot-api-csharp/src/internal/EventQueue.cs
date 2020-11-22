using System;
using System.Collections.Concurrent;
using System.Collections.Generic;
using Robocode.TankRoyale.BotApi.Events;

namespace Robocode.TankRoyale.BotApi.Internal
{
  internal sealed class EventQueue
  {
    private const int MaxEventAge = 2; // turns

    private readonly BaseBotInternals baseBotInternals;
    private readonly BotEventHandlers botEventHandlers;

    private readonly IDictionary<int, BotEvent> events = new ConcurrentDictionary<int, BotEvent>();

    internal EventQueue(BaseBotInternals baseBotInternals, BotEventHandlers botEventHandlers)
    {
      this.baseBotInternals = baseBotInternals;
      this.botEventHandlers = botEventHandlers;
    }

    public void Clear()
    {
      events.Clear();
    }

    private void AddEvent(IBaseBot baseBot, BotEvent botEvent)
    {
      int priority;

      if (botEvent is TickEvent)
      {
        priority = EventPriority.OnTick;
      }
      else if (botEvent is ScannedBotEvent)
      {
        priority = EventPriority.OnScannedBot;
      }
      else if (botEvent is SkippedTurnEvent)
      {
        priority = EventPriority.OnSkippedTurn;
      }
      else if (botEvent is HitBotEvent)
      {
        priority = EventPriority.OnHitBot;
      }
      else if (botEvent is HitWallEvent)
      {
        priority = EventPriority.OnHitWall;
      }
      else if (botEvent is BulletFiredEvent)
      {
        priority = EventPriority.OnBulletFired;
      }
      else if (botEvent is BulletHitWallEvent)
      {
        priority = EventPriority.OnBulletHitWall;
      }
      else if (botEvent is BulletHitBotEvent)
      {
        BulletHitBotEvent bulletEvent = (BulletHitBotEvent)botEvent;
        if (bulletEvent.VictimId == baseBot.MyId)
        {
          priority = EventPriority.OnHitByBullet;
        }
        else
        {
          priority = EventPriority.OnBulletHit;
        }
      }
      else if (botEvent is DeathEvent)
      {
        DeathEvent deathEvent = (DeathEvent)botEvent;
        if (deathEvent.VictimId == baseBot.MyId)
        {
          priority = EventPriority.OnDeath;
        }
        else
        {
          priority = EventPriority.OnBotDeath;
        }
      }
      else if (botEvent is BulletHitBulletEvent)
      {
        priority = EventPriority.OnBulletHitBullet;
      }
      else if (botEvent is WonRoundEvent)
      {
        priority = EventPriority.OnWonRound;
      }
      else if (botEvent is CustomEvent)
      {
        priority = EventPriority.OnCondition;
      }
      else
      {
        throw new InvalidOperationException("Unhandled event type: " + botEvent);
      }
      events.TryAdd(priority, botEvent);
    }

    internal void AddEventsFromTick(IBaseBot baseBot, TickEvent tickEvent)
    {
      AddEvent(baseBot, tickEvent);
      foreach (BotEvent botEvent in tickEvent.Events)
      {
        AddEvent(baseBot, botEvent);
      }
      AddCustomEvents(baseBot);
    }

    private void AddCustomEvents(IBaseBot baseBot)
    {
      foreach (Events.Condition condition in baseBotInternals.Conditions)
      {
        if (condition.Test())
        {
          AddEvent(baseBot, new CustomEvent(baseBotInternals.CurrentTick.TurnNumber, condition));
        }
      }
    }

    internal void DispatchEvents(int currentTurnNumber)
    {
      // Remove all old entries
      foreach (var item in events)
      {
        var botEvent = item.Value;
        if (botEvent.TurnNumber < currentTurnNumber - MaxEventAge)
        {
          events.Remove(item.Key);
        }
      }

      // Publish all event in the order of the keys, i.e. event priority order
      foreach (var item in events)
      {
        var botEvent = item.Value;
        events.Remove(item.Key);
        botEventHandlers.Fire(botEvent);
      }
    }
  }
}