package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidatorISBNRTest {

    @Test
    public void checkAValid10DigitISBN(){
        ValidateISBN validateISBN = new ValidateISBN();
        boolean result = validateISBN.check10DigitISBN("0-306-40615-2");
        assertTrue(result, "first valid ISBN");
    }

    @Test
    public void checkAnInValid10DigitISBN(){
        ValidateISBN validateISBN = new ValidateISBN();
        boolean result = validateISBN.check10DigitISBN("0-123-45678-5");
        assertFalse(result, "first invalid ISBN");
    }

    @Test
    public void checkAValid13DigitISBN(){
        ValidateISBN validateISBN = new ValidateISBN();
        boolean result = validateISBN.check13DigitISBN("978-0-306-40615-7");
        assertTrue(result, "first valid ISBN");
    }

    @Test
    public void checkAnInValid13DigitISBN(){
        ValidateISBN validateISBN = new ValidateISBN();
        boolean result = validateISBN.check13DigitISBN("978-0-306-40615-9");
        assertFalse(result, "first invalid ISBN");
    }

    @Test
    public void nonNumericISBNIsNotAllowed(){
        ValidateISBN validateISBN = new ValidateISBN();

        Exception exception = assertThrows(NumberFormatException.class, () -> {
            validateISBN.check13DigitISBN("helloworld");
        });

        String expectedMessage = "Only digits and '-' sign allowed";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage), "NumberFormatException Exception is thrown");

    }
}
