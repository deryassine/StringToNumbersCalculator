package com.kata.exceptions;

public class NegativeNumbersException extends Exception{

    public NegativeNumbersException(String errorMessage) {
        super("Negatives not allowed " +errorMessage );
    }
}
