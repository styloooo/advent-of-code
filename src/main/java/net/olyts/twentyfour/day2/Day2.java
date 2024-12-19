package net.olyts.twentyfour.day2;

import lombok.extern.slf4j.Slf4j;
import net.olyts.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public class Day2 {
    private final String inputFilePath = Objects.requireNonNull(getClass().getResource("/2024/input/day2.txt")).getPath();

    private final List<String> inputLines = FileUtils.readFile(inputFilePath);

    private final List<Report> reports = new ArrayList<>();


    public void execute() {
        for (String line : inputLines) {
            List<String> splitLine = List.of(line.split(" "));
            Report report = new Report(splitLine.stream().map(Integer::parseInt).toList());
            reports.add(report);
        }

        executePart1();
        executePart2();
    }

    public void executePart1() {
        int numSafeReports = 0;

        for (Report report : reports) {
            if (report.isReportSafe()) {
                numSafeReports++;
            }
        }

        log.info("PART 1: Number of safe reports: {}", numSafeReports);

    }

    public void executePart2() {
        int numSafeReports = 0;

        for (Report report : reports) {
            if(report.isReportSafeWithProblemDampener()) {
                numSafeReports++;
            }
        }

        log.info("PART 2: Number of safe reports: {}", numSafeReports);


    }

}
