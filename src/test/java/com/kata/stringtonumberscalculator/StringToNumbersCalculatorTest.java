package com.kata.stringtonumberscalculator;

import com.kata.exceptions.DelimiterException;
import com.kata.exceptions.NegativeNumbersException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


class StringToNumbersCalculatorTest {

    @Test
    void return_zero_if_empty_string() throws NegativeNumbersException, DelimiterException {
        Assertions.assertThat(StringToNumbersCalculator.add("")).isZero();
    }

    @Test
    void return_sum_of_numbers() throws NegativeNumbersException, DelimiterException {
        Assertions.assertThat(StringToNumbersCalculator.add("1,10")).isEqualTo(11);
    }

    @Test
    void return_sum_of_unknown_amount_of_numbers() throws NegativeNumbersException, DelimiterException {
        Assertions.assertThat(StringToNumbersCalculator.add("1,1,10")).isEqualTo(12);
    }

    @Test
    void return_sum_of_numbers_by_using_comma_and_new_line_as_delimiters() throws NegativeNumbersException, DelimiterException {
        Assertions.assertThat(StringToNumbersCalculator.add("1,1\n10")).isEqualTo(12);
    }

    @Test
    void return_sum_of_numbers_split_by_different_delimiters() throws NegativeNumbersException, DelimiterException {
        Assertions.assertThat(StringToNumbersCalculator.add("//;\n1;1;10")).isEqualTo(12);
        Assertions.assertThat(StringToNumbersCalculator.add("//D\n1D1D10")).isEqualTo(12);
    }

    @Test
    void throw_exception_for_comma_next_to_new_line() {
        Assertions.assertThatThrownBy(() -> StringToNumbersCalculator.add("1,\n2"))
                .isInstanceOf(NumberFormatException.class);
    }

    @Test
    void throw_exception_for_negatives() {
        Assertions.assertThatThrownBy(() -> StringToNumbersCalculator.add("1,-2,3,-4"))
                .hasMessageContaining("Negatives not allowed ")
                .hasMessageContaining("-2 -4")
                .isInstanceOf(NegativeNumbersException.class);

    }

}
