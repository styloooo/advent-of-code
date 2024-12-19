package net.olyts.twentyfour;

import lombok.extern.slf4j.Slf4j;
import net.olyts.twentyfour.day1.Day1;
import net.olyts.twentyfour.day2.Day2;
import net.olyts.twentyfour.day3.Day3;
import net.olyts.utils.StringUtils;

@Slf4j
public class Main2024 {
    public static void main(String[] args) {

        Day1 day1 = new Day1();
        log.info(StringUtils.getDayHeader("ONE"));
        day1.execute();

        Day2 day2 = new Day2();
        log.info(StringUtils.getDayHeader("TWO"));
        day2.execute();

        Day3 day3 = new Day3();
        log.info(StringUtils.getDayHeader("THREE"));
        day3.execute();

    }
}
