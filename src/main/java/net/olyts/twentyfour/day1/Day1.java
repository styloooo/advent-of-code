package net.olyts.twentyfour.day1;

import lombok.extern.slf4j.Slf4j;
import net.olyts.interfaces.Exercise;
import net.olyts.utils.FileUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Slf4j
public class Day1 implements Exercise {

    private final String inputFilePath = Objects.requireNonNull(getClass().getResource("/2024/input/day1.txt")).getPath();
    private final List<String> inputLines = FileUtils.readFile(inputFilePath);

    public void execute() {
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        String delimiter = "   ";

        for (String row : inputLines) {
            String[] rowParts = row.split(delimiter);

            left.add(Integer.parseInt(rowParts[0]));
            right.add(Integer.parseInt(rowParts[1]));
        }

        Collections.sort(left);
        Collections.sort(right);

        int distances = 0;
        for(int i = 0; i < left.size(); i++){
            distances += (Math.abs(left.get(i) - right.get(i)));
        }
        log.info("Summed distances = {}", distances);
    }
}
