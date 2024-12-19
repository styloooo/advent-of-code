package net.olyts.twentythree.day1;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.olyts.interfaces.Exercise;
import net.olyts.utils.FileUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Character.isDigit;
import static java.util.Map.entry;

/**
 *
 * Day One: Trebuchet
 *
**/

@Slf4j
@NoArgsConstructor
public class DayOne implements Exercise {

    private final String inputFilePath = Objects.requireNonNull(getClass().getResource("/2023/input/day1.txt")).getPath();
    private final String sampleFilePath = Objects.requireNonNull(getClass().getResource("/2023/sample/day1.txt")).getPath();
    private final Map<String, String> numeralMap = Map.ofEntries(
            entry("one", "1"),
            entry("two", "2"),
            entry("three", "3"),
            entry("four", "4"),
            entry("five", "5"),
            entry("six", "6"),
            entry("seven", "7"),
            entry("eight", "8"),
            entry("nine", "9")
    );
    private final Pattern digitPattern = Pattern.compile("1|2|3|4|5|6|7|8|9|one|two|three|four|five|six|seven|eight|nine");

    public void execute() {
        int calibrationValue = getCalibrationValue();
        log.info("Solution: {}", calibrationValue);
    }

    private int getCalibrationValue() {
        List<String> inputLines = FileUtils.readFile(inputFilePath);
        List<Integer> lineDigits = inputLines.stream().map(this::findFirstLastDigits).toList();

        int inputValues = 0;
        for (int i = 0; i < lineDigits.size(); i++) {
            Integer digits = lineDigits.get(i);
            log.debug("Line #: {}, Digit: {}, Line: {}", (i + 1), digits, inputLines.get(i));
            inputValues += digits;
        }

        return inputValues;
    }

    // Part 1
    private Integer concatLineFirstLastDigits(String line) {
        Character first = null;
        Character last = null;

        for (int i = 0; i < line.length(); i++) {
            Character l = line.charAt(i);
            Character r = line.charAt(line.length() - i - 1);
            if (isDigit(l) && first == null) first = l;
            if (isDigit(r) && last == null) last = r;
            if (first != null && last != null) break;
        }

        return Integer.parseInt(String.valueOf(first) + last);
    }

    // Part 2
    private Integer findFirstLastDigits(String line) {
        Matcher lineMatcher = digitPattern.matcher(line);

        List<String> matches = new ArrayList<>();

        if (lineMatcher.find()) {
            do {
                matches.add(lineMatcher.group());
            } while (lineMatcher.find(lineMatcher.start() + 1));
        } else {
            throw new RuntimeException(String.format("No pattern matched in line %s", line));
        }

        String firstDigit = matches.get(0);
        String lastDigit = matches.get(matches.size() - 1);

        if (numeralMap.containsKey(firstDigit)) firstDigit = numeralMap.get(firstDigit);
        if (numeralMap.containsKey(lastDigit)) lastDigit = numeralMap.get(lastDigit);

        return Integer.parseInt(firstDigit + lastDigit);
    }
}
