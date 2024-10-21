package com.example;

public class ValidateISBN {

    public boolean check10DigitISBN(String ISBN){
        checkIfISBNContainsInvalidCharacters(ISBN);
        int total = 0;

        ISBN = ISBN.replaceAll("\\D", "");
        for(int i=0; i<10; i++){
//            total += ISBN.charAt(i) - '0';
            total += Character.getNumericValue(ISBN.charAt(i)) * (10-i);
        }
        return (total % 11 == 0);
    }

    public boolean check13DigitISBN(String ISBN){
        checkIfISBNContainsInvalidCharacters(ISBN);
        int total = 0;

        boolean multiplyByOne = true;
        ISBN = ISBN.replaceAll("\\D", "");
        for(int i=0; i<13; i++){
             int mult = multiplyByOne ? 1 : 3;
             total += Character.getNumericValue(ISBN.charAt(i)) * mult;
             multiplyByOne = !multiplyByOne;
        }
        return (total % 10 == 0);
    }

    private void checkIfISBNContainsInvalidCharacters(String ISBN)
    {
        boolean containsInvalidChars = !ISBN.matches("[0-9-]+");

        if (containsInvalidChars) throw new NumberFormatException("Only digits and '-' sign allowed");
    }

}
