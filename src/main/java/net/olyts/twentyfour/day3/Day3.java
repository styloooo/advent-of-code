package net.olyts.twentyfour.day3;

import lombok.extern.slf4j.Slf4j;
import net.olyts.interfaces.Exercise;
import net.olyts.utils.FileUtils;

import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class Day3 implements Exercise {

    private static final Pattern MUL_PATTERN = Pattern.compile("mul\\(\\d+,\\d+\\)");
    private static final Pattern DO_PATTERN = Pattern.compile("do\\(\\)");
    private static final Pattern DONT_PATTERN = Pattern.compile("don't\\(\\)");
    private final String inputFilePath = Objects.requireNonNull(getClass().getResource("/2024/input/day3.txt")).getPath();

    private final List<String> inputLines = FileUtils.readFile(inputFilePath);

    public void execute() {
        executePart1();
        executePart2();
    }

    private void executePart1() {
        int sum = 0;
        for (String line : inputLines) {
            Matcher matcher = MUL_PATTERN.matcher(line);
            while (matcher.find()) {
                String match = matcher.group();
                Result operands = parseOperands(match);
                sum += mul(operands.left(), operands.right());
            }
        }
        log.info("PART 1: Sum of uncorrupted instructions: {}", sum);
    }

    private static Result parseOperands(String match) {
        String[] matchParts = match.split(",");
        String lInput = matchParts[0].substring(matchParts[0].indexOf("(") + 1);
        String rInput = matchParts[1].substring(0, matchParts[1].indexOf(")"));
        return new Result(lInput, rInput);
    }

    private record Result(String left, String right) {
    }

    private void executePart2() {
        int sum = 0;
        String lines = inputLines.toString();

        Pattern pattern = Pattern.compile(
                DO_PATTERN.pattern()
                        + "|" + MUL_PATTERN.pattern()
                        + "|" + DONT_PATTERN.pattern()
        );

        Matcher matcher = pattern.matcher(lines);

        boolean mulEnabled = true;
        while (matcher.find()) {
            String match = matcher.group();

            if (DO_PATTERN.matcher(match).find()) {
                mulEnabled = true;
            } else if (mulEnabled && MUL_PATTERN.matcher(match).find()) {
                Result operands = parseOperands(match);
                sum += mul(operands.left(), operands.right());
            } else if (DONT_PATTERN.matcher(match).find()) {
                mulEnabled = false;
            }

        }

        log.info("PART 1: Sum of uncorrupted instructions: {}", sum);

    }

    private int mul(String lStr, String rStr) {
        int lNum = Integer.parseInt(lStr);
        int rNum = Integer.parseInt(rStr);
        return lNum * rNum;
    }

}
