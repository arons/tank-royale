package dev.robocode.tankroyale.server.mappers;

import dev.robocode.tankroyale.server.model.Round;
import dev.robocode.tankroyale.server.model.Turn;
import dev.robocode.tankroyale.schema.TickEventForObserver;

public final class TurnToGameTickForObserverMapper {

	private TurnToGameTickForObserverMapper() {}

	public static TickEventForObserver map(Round round, Turn turn) {
		TickEventForObserver tick = new TickEventForObserver();
		tick.setType(TickEventForObserver.Type.TICK_EVENT_FOR_OBSERVER);
		tick.setRoundNumber(round.getRoundNumber());
		tick.setTurnNumber(turn.getTurnNumber());
		tick.setBotStates(BotsToBotsWithIdMapper.map(turn.getBots()));
		tick.setBulletStates(BulletsToBulletStatesMapper.map(turn.getBullets()));
		tick.setEvents(EventsToEventsMapper.map(turn.getObserverEvents()));
		return tick;
	}
}