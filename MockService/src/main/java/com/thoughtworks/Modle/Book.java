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

    public Book(){

    }

    public static String objToJson1(Book bi[]){
        String str="[";
        for (int i=0 ; i<bi.length ; i++){
            str+="{";
            str+="\"id\":\""+bi[i].id+"\",";
            str+="\"name\":\""+bi[i].name+"\",";
            str+="\"isbn\":\""+bi[i].isbn+"\",";
            str+="\"author\":\""+bi[i].author+"\",";
            str+="\"price\":\""+bi[i].price+"\",";
            str+="\"imgUrl\":\""+bi[i].imgUrl+"\",";
            str+="\"description\":\""+bi[i].description+"\"";
            str+="},";
        }
        str = str.substring(0,str.length()-1);
        str+="]";
        return str;
    }

    public static String objToJson2(Book bi[]){
        String str="[";
        for (int i=0 ; i<bi.length ; i++){
            str+="{";
            str+="\"name\":\""+bi[i].name+"\",";
            str+="\"isbn\":\""+bi[i].isbn+"\",";
            str+="\"author\":\""+bi[i].author+"\",";
            str+="\"price\":\""+bi[i].price+"\",";
            str+="\"imgUrl\":\""+bi[i].imgUrl+"\"";
            str+="},";
        }
        str = str.substring(0,str.length()-1);
        str+="]";
        return str;
    }

    public static String dataPro(int num){
        String result=null;
        if(num!=1){
            Book bookImfo[] = new Book[num];
            for (int i=0 ; i<num ; i++){
                bookImfo[i] = new Book();
                bookImfo[i].name = "第"+i+"本书";
                bookImfo[i].author = "作者"+i;
                bookImfo[i].isbn = "ISBN"+i;
                bookImfo[i].price = 10*i+i;
                bookImfo[i].imgUrl = "https://img3.doubanio.com/mpic/s2370875.jpg";
            }
            result = objToJson2(bookImfo);
        }
        else {
            Book bookImfo[] = new Book[1];
            bookImfo[0] = new Book();
            bookImfo[0].id = 100;
            bookImfo[0].name = "第"+100+"本书";
            bookImfo[0].author = "作者"+100;
            bookImfo[0].isbn = "ISBN"+100;
            bookImfo[0].price = 87.2;
            bookImfo[0].imgUrl = "https://img3.doubanio.com/mpic/s2370875.jpg";
            bookImfo[0].description = "第100本书真是好看";
            result = objToJson1(bookImfo);
        }

        return result;

    }
}
