package com.kata.stringtonumberscalculator;

import com.kata.exceptions.DelimiterException;
import com.kata.exceptions.NegativeNumbersException;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringToNumbersCalculator {

    public static final String STARTING_CALCULATION_OF_NUMBERS_FROM_STRING = "START CALCULATION OF NUMBERS FROM STRING >>>>";
    public static final String END_CALCULATION_OF_NUMBERS_FROM_STRING = "END CALCULATION OF NUMBERS FROM STRING <<<<<";
    private static final String NO_NUMBERS_DETECTED = "No numbers detected";
    private static final String NO_STRINGS_ENTERED = "You didn't enter any string";

    private static final Logger LOGGER = Logger.getLogger(StringToNumbersCalculator.class.getName());

    public static int add(String string) throws NegativeNumbersException, DelimiterException {

        if (!string.isEmpty()) {
            List<Integer> extractedNumbers = stringToNumberExtractor(string);
            findNegativesNumbers(extractedNumbers);

            if (extractedNumbers.isEmpty())
                LOGGER.warning(NO_NUMBERS_DETECTED);
            return extractedNumbers.stream()
                    .reduce(Integer::sum)
                    .orElseThrow();

        }
        //will return 0 if there were no numbers
        LOGGER.info(NO_STRINGS_ENTERED);
        return 0;
    }

    private static void findNegativesNumbers(List<Integer> numbers) throws NegativeNumbersException {
        StringBuilder stringBuilder = new StringBuilder();
        numbers.stream()
                .filter(num -> num < 0)
                .forEach(num -> stringBuilder.append(num).append(" "));

        if (!stringBuilder.toString().isEmpty()) {
            throw new NegativeNumbersException(stringBuilder.toString());
        }
    }

    private static List<Integer> stringToIntegerList(String[] stringArr) {
        return Arrays.stream(stringArr)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static List<Integer> stringToNumberExtractor(String string) throws DelimiterException {
        if (string.startsWith("//")) {
            // (.*) find any character symbol
            Matcher matcher = Pattern.compile("//(.*)\n(.*)").matcher(string);
            if (matcher.matches()) {
                //get the custom delimiter
                String customDelimiter = matcher.group(1);
                //remove the // and the \n and get the string with delimiter
                String stringToSplit = matcher.group(2);
                //get strings using the delimiter
                String[] splittedString = stringToSplit.split(customDelimiter);
                return stringToIntegerList(splittedString);

            }
            throw new DelimiterException();
        }
        //if string doesn't contain "//" split by ; or \n
        String[] splittedString = string.split("[,\n]");
        return stringToIntegerList(splittedString);
    }

    public static void main(String[] args) throws NegativeNumbersException, DelimiterException {

        LOGGER.log(Level.INFO, STARTING_CALCULATION_OF_NUMBERS_FROM_STRING);
        //numbers are extracted and its sum is calculated
        LOGGER.log(Level.INFO, Integer.toString(add("//X\n5X2")), "");

        LOGGER.log(Level.INFO, END_CALCULATION_OF_NUMBERS_FROM_STRING);

    }
}
