package com.thoughtworks.Modle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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


    public static String ReadFile(){
        String path = Book.class.getResource("../../").getPath();
        int pos = path.indexOf("classes");
        path = path.substring(0,pos);
        path += "classes/mocBook.json";
        File file = new File(path);
        Scanner scan = null;
        StringBuilder buffer = new StringBuilder();
        try {
            scan = new Scanner(file,"utf-8");
            while (scan.hasNextLine()){
                buffer.append(scan.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(scan != null){
                scan.close();
            }
        }
          return buffer.toString();
    }

    public static String query(){
        return "[{\"name\":\"Rails之道\",\"isbn\":\"4727011\",\"author\":\"(美)Obie Fernandez\",\"price\":89,\"img_url\":\"https://img3.doubanio.com/mpic/s4282672.jpg\"}]";
    }
}
