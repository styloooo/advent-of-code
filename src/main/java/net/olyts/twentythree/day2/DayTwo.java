package net.olyts.twentythree.day2;

import lombok.extern.slf4j.Slf4j;
import net.olyts.interfaces.Exercise;
import net.olyts.utils.FileUtils;

import java.util.*;

/**
 *
 * Day Two: Cube Conundrum
 *
 **/
@Slf4j
public class DayTwo implements Exercise {
    private final String inputFilePath = Objects.requireNonNull(getClass().getResource("/2023/input/day2.txt")).getPath();
    private final List<String> inputLines = FileUtils.readFile(inputFilePath);
    private static final int MAX_RED_CUBES = 12;
    private static final int MAX_GREEN_CUBES = 13;
    private static final int MAX_BLUE_CUBES = 14;
    private final Game validGame = new Game(0, MAX_RED_CUBES, MAX_GREEN_CUBES, MAX_BLUE_CUBES);

    public void execute() {
        Integer summedIDs = validateGamesAndSumIDs();
        log.info("Part A Solution: {}", summedIDs);

        Integer summedPowersOfMinimumGames = sumPowersOfMinimumGames();
        log.info("Part B Solution: {}", summedPowersOfMinimumGames);
    }

    private Integer validateGamesAndSumIDs() {
        return inputLines.stream().map(
                line -> validateGameSets(processLineIntoGames(line))
        ).reduce(0, Integer::sum);
    }

    private Integer sumPowersOfMinimumGames() {
        Map<Integer, List<Game>> gameSetMap = new HashMap<>();
        for (String line : inputLines) {
            List<Game> gameSets = processLineIntoGames(line);
            Integer gameId = gameSets.get(0).getId();
            gameSetMap.put(gameId, gameSets);
        }
        return gameSetMap.values().stream().map(this::findMinimumGameForSets).map(this::calculateGamePower).reduce(0, Integer::sum);
    }

    private List<Game> processLineIntoGames(String line) {
        String[] linePartsSplitByColon = line.split(": ");
        String[] gameIDParts = linePartsSplitByColon[0].split(" ");
        Integer gameID = Integer.valueOf(gameIDParts[1]);
        String[] gameSets = linePartsSplitByColon[1].split("; ");

        List<Game> games = new ArrayList<>();

        Map<CubeColor, Integer> cubeCountMap = new HashMap<>();
        for (String gameSet : gameSets) {

            String[] gameParams = gameSet.split(", ");

            for (String param : gameParams) {
                String[] paramParts = param.split(" ");
                Integer numCubes = Integer.valueOf(paramParts[0]);
                String cubeColorLabel = paramParts[1];
                if (cubeColorLabel.equals("red")) cubeCountMap.put(CubeColor.RED, numCubes);
                if (cubeColorLabel.equals("green")) cubeCountMap.put(CubeColor.GREEN, numCubes);
                if (cubeColorLabel.equals("blue")) cubeCountMap.put(CubeColor.BLUE, numCubes);
            }

            Integer numRed = cubeCountMap.getOrDefault(CubeColor.RED, 0);
            Integer numGreen = cubeCountMap.getOrDefault(CubeColor.GREEN, 0);
            Integer numBlue = cubeCountMap.getOrDefault(CubeColor.BLUE, 0);
            Game setToValidate = new Game(gameID, numRed, numGreen, numBlue);
            games.add(setToValidate);

        }

        return games;
    }

    private Integer validateGameSets(List<Game> gamesToValidate) {
        // Returns game ID as an Integer if the game was valid according to validGame
        for (Game gameToValidate : gamesToValidate) {
            if (gameToValidate.getNumRedCubes() > validGame.getNumRedCubes()) return 0;
            if (gameToValidate.getNumGreenCubes() > validGame.getNumGreenCubes()) return 0;
            if (gameToValidate.getNumBlueCubes() > validGame.getNumBlueCubes()) return 0;
        }

        return gamesToValidate.get(0).getId();

    }

    private Game findMinimumGameForSets(List<Game> sets) {
        int minRed = 0;
        int minGreen = 0;
        int minBlue = 0;

        for (Game set : sets) {
            minRed = Math.max(minRed, set.getNumRedCubes());
            minGreen = Math.max(minGreen, set.getNumGreenCubes());
            minBlue = Math.max(minBlue, set.getNumBlueCubes());
        }
        return new Game(sets.get(0).getId(), minRed, minGreen, minBlue);
    }

    private Integer calculateGamePower(Game game) {
        return game.getNumRedCubes() * game.getNumGreenCubes() * game.getNumBlueCubes();
    }

}
