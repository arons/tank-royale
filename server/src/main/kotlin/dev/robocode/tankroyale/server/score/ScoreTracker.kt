package dev.robocode.tankroyale.server.score

import dev.robocode.tankroyale.server.dev.robocode.tankroyale.server.model.ParticipantId
import dev.robocode.tankroyale.server.dev.robocode.tankroyale.server.score.ScoresDecorator
import dev.robocode.tankroyale.server.model.Score
import dev.robocode.tankroyale.server.rules.*

/**
 * Utility class used for keeping track of the score for an individual bot and/or team in a game.
 * @param participantIds is the ids of all participant bots and teams.
 */
class ScoreTracker(private val participantIds: Set<ParticipantId>) {

    // Map over the score and damage records per participant
    private val scoreAndDamages = mutableMapOf<ParticipantId, ScoreAndDamage>()

    // Map over alive participants
    private val aliveParticipants = mutableSetOf<ParticipantId>()

    init {
        participantIds.forEach { scoreAndDamages[it] = ScoreAndDamage() }

        aliveParticipants.addAll(participantIds)
    }

    /**
     * Returns an ordered list containing the current scores, ranks, and placements for all participants.
     * The higher _total_ scores are listed before lower _total_ scores.
     */
    fun getScores(): Collection<Score> {
        val scores = participantIds.map { calculateScore(it) }.sortedByDescending { it.totalScore }
        ScoresDecorator.updateRanks(scores)
        ScoresDecorator.increment1st2ndAnd3rdPlaces(scores)
        return scores
    }

    /**
     * Clears all scores used when a new round is started.
     */
    fun clear() {
        aliveParticipants.apply {
            clear()
            addAll(participantIds)
        }
    }

    /**
     * Calculates and returns the score for a specific participant.
     * @param participantId is the identifier of the participant.
     * @return a [Score] record.
     */
    private fun calculateScore(participantId: ParticipantId): Score {
        getScoreAndDamage(participantId).apply {
            return Score(
                participantId = participantId,
                bulletDamageScore = SCORE_PER_BULLET_DAMAGE * getTotalBulletDamage(),
                bulletKillBonus = BONUS_PER_BULLET_KILL * getBulletKillEnemyIds().sumOf { getTotalDamage(it) },
                ramDamageScore = SCORE_PER_RAM_DAMAGE * getTotalRamDamage(),
                ramKillBonus = BONUS_PER_RAM_KILL * getRamKillEnemyIds().sumOf { getTotalDamage(it) },
                survivalScore = SCORE_PER_SURVIVAL * survivalCount,
                lastSurvivorBonus = BONUS_PER_LAST_SURVIVOR * lastSurvivorCount,
            )
        }
    }

    /**
     * Registers a bullet hit.
     * @param offenderId is the id of the bot hitting a victim by a bullet.
     * @param victimId is the id of the victim bot that got hit by the bullet.
     * @param damage is the damage dealt.
     * @param kill is `true` if the bot got killed by the bullet; `false` otherwise.
     */
    fun registerBulletHit(offenderId: ParticipantId, victimId: ParticipantId, damage: Double, kill: Boolean) {
        getScoreAndDamage(offenderId).apply {
            addBulletDamage(victimId, damage)
            if (kill) {
                addBulletKillEnemyId(victimId)
            }
        }
    }

    /**
     * Registers a ram hit.
     * @param offenderId is the id of the bot ramming a victim.
     * @param victimId is the id of the victim bot that got rammed.
     * @param kill is `true` if the bot got killed by the ramming; `false` otherwise.
     */
    fun registerRamHit(offenderId: ParticipantId, victimId: ParticipantId, kill: Boolean) {
        getScoreAndDamage(offenderId).apply {
            incrementRamHit(victimId)
            if (kill) {
                addRamKillEnemyId(victimId)
            }
        }
    }

    /**
     * Registers the deaths of bots.
     * @param victimIds is the ids of all the victims.
     */
    fun registerDeaths(victimIds: Set<ParticipantId>) {
        if (victimIds.isNotEmpty()) {
            aliveParticipants.apply {
                val lastSurvivors = ArrayList(this)

                removeAll(victimIds)

                forEach { scoreAndDamages[it]?.incrementSurvivalCount() }

                when (size) {
                    0, 1 -> {
                        val deadCount = participantIds.size - size
                        lastSurvivors.forEach { scoreAndDamages[it]?.addLastSurvivorCount(deadCount) }
                    }
                }
            }
        }
    }

    private fun getScoreAndDamage(participantId: ParticipantId): ScoreAndDamage =
        (scoreAndDamages[participantId]
            ?: throw IllegalStateException("No score record for teamOrBotId: $participantId)"))
}