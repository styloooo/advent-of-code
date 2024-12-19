package net.olyts.twentythree.day3;

import lombok.extern.slf4j.Slf4j;
import net.olyts.interfaces.Exercise;
import net.olyts.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public class DayThree implements Exercise {

    private final String inputFilePath = Objects.requireNonNull(getClass().getResource("/2023/input/day3.txt")).getPath();
    private final List<String> inputLines = FileUtils.readFile(inputFilePath);

    public void execute() {
        Board board = generateBoard();
        for (Row row : board.getRows()) {
            log.debug("Row: {}", row.toString());
        }
        log.debug("Number of rows: {}", board.getNumRows());
        log.debug("Row length: {}", board.getRowLength());

        List<Integer> numsAdjToSymbols = board.getNumbersAdjacentToSymbols();
        log.debug("Numbers adjacent to symbols: {}", numsAdjToSymbols);
        log.info("Part A Solution: {}", numsAdjToSymbols.stream().reduce(0, Integer::sum));

        board.getGearRatios();
    }

    private Board generateBoard() {

        List<List<Character>> gameRows = new ArrayList<>();

        for (String line : inputLines) {
            List<Character> rowCharacters = line.chars().mapToObj(c -> (char) c).toList();
            gameRows.add(rowCharacters);
        }

        return new Board(gameRows);

    }

}
