package net.olyts.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StringUtils {

    public static String padString(String stringToPad, Character paddingChar, int paddedStringLength) {
        if (stringToPad.length() >= paddedStringLength) return stringToPad;

        StringBuilder sb = new StringBuilder(stringToPad);
        int numToPad = (paddedStringLength - stringToPad.length()) / 2;

        for (; numToPad > 0; numToPad--) {
            sb.insert(0, paddingChar);
            sb.append(paddingChar);
        }

        return sb.toString();
    }

    public static String getDayHeader(String dayValue) {
        final String DAY_STRING_TEMPLATE = "DAY %s";
        return getHeader(String.format(DAY_STRING_TEMPLATE, dayValue));
    }

    public static String getHeader(String value) {
        final Character PADDING_CHARACTER = '=';
        final int PADDED_STRING_LENGTH = 100;

        return padString(value, PADDING_CHARACTER, PADDED_STRING_LENGTH);
    }
}
