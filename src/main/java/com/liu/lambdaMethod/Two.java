package com.liu.lambdaMethod;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * All rights Reserved, Designed By jiexingcloud. 举例使用 Lambda 的好处
 *
 * @author liushuaibiao@jiexingcloud.com
 * @since 2023/8/27 15:08 Copyright ©2023 jiexingcloud. All rights reserved. 注意：本内容仅限于结行云创内部传阅，禁止外泄以及用于其他的商业用途。
 */
@Slf4j
public class Two {
    public static void main(String[] args) {
        List<Author> authors = new ArrayList<>();
        creatData(authors);
        List<Book> traBooks = traditionalWay(authors);
        log.info("传统方式处理数据结果:{}", traBooks);
        log.info("===========");
        List<Book> lamBooks = lambdaWay(authors);
        log.info("lambda方式处理数据结果:{}", lamBooks);
    }

    /**
     * 传统方式实现
     *
     * @param authors
     * @return java.util.List<org.example.one_sixteen.Book>
     * @since 2023/8/27 15:24 by liushuaibiao
     **/
    public static List<Book> traditionalWay(List<Author> authors) {
        //查询未成年作家评分在 70 岁以上的数据,需要去重
        List<Book> bookList = new ArrayList();
        //创建hashSet用于去重
        HashSet<Book> uniqueBookValues = new HashSet();
        HashSet<Author> uniqueAuthorValues = new HashSet();
        for (Author author : authors) {
            //校验作者是否唯一,判断能否加入 set
            if (uniqueAuthorValues.add(author)) {
                //判断小于 18 岁
                if (author.getAge() < 18) {
                    //筛选作者下面的书
                    List<Book> books = author.getBooks();
                    for (Book book : books) {
                        if (book.getScore() > 70) {
                            //对书籍去重
                            if (uniqueBookValues.add(book)) {
                                bookList.add(book);
                            }
                        }
                    }
                }
            }
        }
        return bookList;
    }

    /**
     * lambda方式实现
     *
     * @param
     * @return void
     * @since 2023/8/27 15:25 by liushuaibiao
     **/
    public static List<Book> lambdaWay(List<Author> authors) {
        List<Book> booksList = authors.stream().distinct().filter(author -> author.getAge() < 18)
                .map(author -> author.getBooks()).flatMap(Collection::stream).filter(book -> book.getScore() > 70)
                .distinct().collect(Collectors.toList());

        return booksList;

    }

    public static void creatData(List<Author> authors) {
        Author author1 = new Author();
        Author author2 = new Author();
        author1.setAge(17);
        List<Book> books1 = new ArrayList<>();
        Book book1 = new Book();
        Book book2 = new Book();
        book1.setScore(75);
        book2.setScore(60);
        books1.add(book1);
        books1.add(book2);
        author1.setBooks(books1);

        author2.setAge(16);
        List<Book> books2 = new ArrayList<>();
        Book book3 = new Book();
        Book book4 = new Book();
        book3.setScore(80);
        book4.setScore(90);
        books2.add(book3);
        books2.add(book4);
        author2.setBooks(books2);

        authors.add(author1);
        authors.add(author2);
    }
}

@Data
class Book {
    private int score;

    @Override
    public String toString() {
        return "Book{" + "score=" + score + '}';
    }
}

@Data
class Author {
    private int age;
    private List<Book> books;

    @Override
    public String toString() {
        return "Author{" + "age=" + age + ", books=" + books + '}';
    }
}
