package com.example;

public class StockManager {
    private ExternalISBNDataService webService;
    private ExternalISBNDataService databaseService;

    public void setWebService(ExternalISBNDataService webService){
        this.webService = webService;
    }

    public void setDatabaseService(ExternalISBNDataService databaseService){
        this.databaseService = databaseService;
    }

    public String getLocatorCode(String isbn) {
        Book book = databaseService.lookup(isbn);
        if (book == null){
            book = webService.lookup(isbn);
        }

        // last 4 digits of ISBN + initial letter of author's last name + number of words in title
        StringBuilder locator =new StringBuilder();
        locator.append(isbn.substring(isbn.length()-4));

        String[] nameParts =  book.getAuthor().split(" ");
        char firstCharLastName = nameParts[nameParts.length - 1].charAt(0);

        locator.append(firstCharLastName);
        locator.append(book.getTitle().split(" ").length);

        return locator.toString();
    }
}
