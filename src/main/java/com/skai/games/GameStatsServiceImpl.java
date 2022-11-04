package com.skai.games;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class GameStatsServiceImpl implements GameStatsService {

    @Override
    public String mvp(Collection<GameRecord> gameRecords) {
        GameRecord mvp = null;

        Map<TeamAndPlayer, GameRecord> teamAndPlayerWithRecordsMap =
                gameRecords.stream().collect(Collectors.toMap(TeamAndPlayer::new,
                        el -> el, (e1, e2) -> {
                            e1.scores += e2.scores;
                            e1.passes += e2.passes;
                            return e1;
                        }));

        for (TeamAndPlayer key : teamAndPlayerWithRecordsMap.keySet()) {
            if (mvp == null || teamAndPlayerWithRecordsMap.get(key).scores > mvp.scores) {
                mvp = teamAndPlayerWithRecordsMap.get(key);
            } else if (teamAndPlayerWithRecordsMap.get(key).scores == mvp.scores &
                    teamAndPlayerWithRecordsMap.get(key).passes > mvp.passes) {
                mvp = teamAndPlayerWithRecordsMap.get(key);
            }
        }

        return (mvp != null) ? mvp.player : null;
    }

    private class TeamAndPlayer {
        private final String team;
        private final String player;

        public TeamAndPlayer(GameRecord gameRecord) {
            this.team = gameRecord.team;
            this.player = gameRecord.player;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TeamAndPlayer that = (TeamAndPlayer) o;
            return team.equals(that.team) && player.equals(that.player);
        }

        @Override
        public int hashCode() {
            return Objects.hash(team, player);
        }
    }

    @Override
    public String winner(Collection<GameRecord> gameRecords) {
        String winner = null;

        Map<String, Integer> teamsMap = gameRecords.stream().collect(
                Collectors.groupingBy(record -> record.team, Collectors.summingInt(record -> record.scores)));

        for (String key : teamsMap.keySet()) {
            if (winner == null || teamsMap.get(key) > teamsMap.get(winner)) {
                winner = key;
            }
        }

        return winner;
    }

}
