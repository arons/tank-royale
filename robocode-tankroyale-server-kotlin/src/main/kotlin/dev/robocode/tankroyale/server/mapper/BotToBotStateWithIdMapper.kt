package dev.robocode.tankroyale.server.mapper

import dev.robocode.tankroyale.schema.BotStateWithId
import dev.robocode.tankroyale.server.math.normalAbsoluteDegrees
import dev.robocode.tankroyale.server.model.Bot

object BotToBotStateWithIdMapper {
    fun map(bot: Bot): BotStateWithId {
        val botState = BotStateWithId()
        bot.apply {
            botState.id = id.value
            botState.energy = energy
            botState.x = x
            botState.y = y
            botState.speed = speed
            botState.turnRate = turnRate
            botState.gunTurnRate = gunTurnRate
            botState.radarTurnRate = radarTurnRate
            botState.direction = normalAbsoluteDegrees(direction)
            botState.gunDirection = normalAbsoluteDegrees(gunDirection)
            botState.radarDirection = normalAbsoluteDegrees(radarDirection)
            botState.radarSweep = radarSpreadAngle
            botState.gunHeat = gunHeat
            botState.bodyColor = bodyColor?.value
            botState.turretColor = turretColor?.value
            botState.radarColor = radarColor?.value
            botState.bulletColor = bulletColor?.value
            botState.scanColor = scanColor?.value
            botState.tracksColor = tracksColor?.value
            botState.gunColor = gunColor?.value
            return botState
        }
    }
}