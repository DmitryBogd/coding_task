package com.skai.games;

import org.junit.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GameRecordStatsServiceImplTest {
    GameStatsServiceImpl gameStatsService = new GameStatsServiceImpl();

    private final Collection<GameRecord> gameRecords = List.of(
            new GameRecord("New York Rangers", "Zibanezhad", 2, 1),
            new GameRecord("New York Rangers", "Zibanezhad", 2, 1),
            new GameRecord("New York Rangers", "Zibanezhad", 2, 1),
            new GameRecord("Boston Bruins", "Marchan", 2, 1),
            new GameRecord("Boston Bruins", "Pastrniak", 5, 1)
    );

    private final Collection<GameRecord> gameRecords2 = List.of(
            new GameRecord("New York Rangers", "Zibanezhad", 2, 1),
            new GameRecord("New York Rangers", "Zibanezhad", 2, 1),
            new GameRecord("Boston Bruins", "Zibanezhad", 5, 1),
            new GameRecord("Boston Bruins", "Pastrniak", 6, 1)
    );

    private final Collection<GameRecord> gameRecords3 = List.of(
            new GameRecord("New York Rangers", "Zibanezhad", 2, 1),
            new GameRecord("Boston Bruins", "Marchan", 5, 1),
            new GameRecord("Boston Bruins", "Pastrniak", 5, 2)
    );

    @Test
    public void winner() {
        assertEquals("Boston Bruins", gameStatsService.winner(gameRecords));
        assertEquals("Boston Bruins", gameStatsService.winner(gameRecords2));
        assertEquals("Boston Bruins", gameStatsService.winner(gameRecords3));
    }

    @Test
    public void mvp() {
        assertEquals("Zibanezhad", gameStatsService.mvp(gameRecords));
        assertEquals("Pastrniak", gameStatsService.mvp(gameRecords2));
        assertEquals("Pastrniak", gameStatsService.mvp(gameRecords3));
    }

}


