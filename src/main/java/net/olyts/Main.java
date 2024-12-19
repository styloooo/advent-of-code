package net.olyts;


import lombok.extern.slf4j.Slf4j;
import net.olyts.twentyfour.Main2024;
import net.olyts.twentythree.Main2023;
import net.olyts.utils.StringUtils;

@Slf4j
public class Main {

    public static void main(String[] args) {
        log.info(StringUtils.getHeader("Advent of Code 2023"));
        Main2023.main(args);

        log.info(StringUtils.getHeader("Advent of Code 2024"));
        Main2024.main(args);
    }
}