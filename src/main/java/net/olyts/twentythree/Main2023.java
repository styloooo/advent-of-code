package net.olyts.twentythree;

import lombok.extern.slf4j.Slf4j;
import net.olyts.twentythree.day1.DayOne;
import net.olyts.twentythree.day2.DayTwo;
import net.olyts.utils.StringUtils;

@Slf4j
public class Main2023 {
    public static void main(String[] args) {
        DayOne dayOne = new DayOne();
        DayTwo dayTwo = new DayTwo();

        log.info(StringUtils.getDayHeader("ONE"));
        dayOne.execute();

        log.info(StringUtils.getDayHeader("TWO"));
        dayTwo.execute();
    }
}
