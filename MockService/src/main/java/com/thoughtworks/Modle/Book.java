package com.thoughtworks.Modle;

public class Book {
    public int id;
    public String name;
    public String isbn;
    public String author;
    public double price;
    public String imgUrl;
    public String description;

    @Override
    public String toString() {
        return super.toString();
    }

    public Book(int id, String name, String isbn, String author, double price, String imgUrl, String description) {
        this.id = id;
        this.name = name;
        this.isbn = isbn;
        this.author = author;
        this.price = price;
        this.imgUrl = imgUrl;
        this.description = description;
    }
}
