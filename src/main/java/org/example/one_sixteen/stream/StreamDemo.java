package org.example.one_sixteen.stream;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * All rights Reserved, Designed By misteryliu.
 * 流 demo
 *
 * @author misteryliu@outlook.com
 * @since 2023/8/27 19:42 Copyright ©2023 misteryliu. All rights reserved. 注意：本内容仅限于结行云创内部传阅，禁止外泄以及用于其他的商业用途。
 */

@Slf4j
public class StreamDemo {

    public static void main(String[] args) {

        //创建流
        Integer[] arr = {1,2,3,4};
        Stream<Integer> arrStream = Arrays.stream(arr);
        arrStream.distinct();
        //创建流的第二种方法
        Stream<Integer> arrStream1 = Stream.of(arr);
        arrStream1.forEach(integer -> System.out.println(integer));

//        双列集合创建流
        HashMap<String, Integer> map = new HashMap<>();

        map.put("test1",1);
        map.put("test2",2);
        map.put("test3",3);
        map.put("test4",4);

        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        entries.stream()
                .filter(stringIntegerEntry -> stringIntegerEntry.getValue()<3)
                .forEach(stringIntegerEntry -> System.out.println(stringIntegerEntry.getKey()+":"+stringIntegerEntry.getValue()));



        List<Author> authors = getAuthors();

        //我的写法
        //打印年龄小于 18 的作者名字,并且去重
        List<String> authorNames = authors.stream() //集合转换成流
                //filter 入参是Predicate,里面就是 lambda表达式实现方法
                .filter(author -> author.getAge() < 18)
                .distinct()
                // map 入参是 Function,实现的是 apply 方法,入参是 author,返回得是 name
                .map(author -> author.getName())
                .collect(Collectors.toList());
        log.info("我的写法:{}",authorNames);

        //匿名内部类的写法
        authors.stream()
                .distinct()
                .filter(new Predicate<Author>() {
                    @Override
                    public boolean test(Author author) {
                        System.out.println("test");
                        return author.getAge() < 18;
                    }
                })
        //没有终结操作,中间操作也不会执行
                .forEach(new Consumer<Author>() {
                    @Override
                    public void accept(Author author) {
                        log.info("匿名内部类的写法{}",author.getName());
                    }
                });
        //lambda表达式写法

        authors.stream().
                distinct()
                .filter(author -> author.getAge()< 18)
                .forEach(author -> log.info("lambda表达式写法{}",author.getName()));

    }

    // 初始化一些数据
    private static List<Author> getAuthors() {
        Author author1 = new Author(1L, "杨杰炜", "my introduction 1", 18, null);
        Author author2 = new Author(2L, "yjw", "my introduction 2", 19, null);
        Author author3 = new Author(3L, "yjw", "my introduction 3", 14, null);
        Author author4 = new Author(4L, "wdt", "my introduction 4", 29, null);
        Author author5 = new Author(5L, "wtf", "my introduction 5", 12, null);

        List<Book> books1 = new ArrayList<>();
        List<Book> books2 = new ArrayList<>();
        List<Book> books3 = new ArrayList<>();

        // 上面是作者和书
        books1.add(new Book(1L, "类别,分类啊", "书名1", 45D, "这是简介哦"));
        books1.add(new Book(2L, "高效", "书名2", 84D, "这是简介哦"));
        books1.add(new Book(3L, "喜剧", "书名3", 83D, "这是简介哦"));

        books2.add(new Book(5L, "天啊", "书名4", 65D, "这是简介哦"));
        books2.add(new Book(6L, "高效", "书名5", 89D, "这是简介哦"));

        books3.add(new Book(7L, "久啊", "书名6", 45D, "这是简介哦"));
        books3.add(new Book(8L, "高效", "书名7", 44D, "这是简介哦"));
        books3.add(new Book(9L, "喜剧", "书名8", 81D, "这是简介哦"));

        author1.setBookList(books1);
        author2.setBookList(books2);
        author3.setBookList(books3);
        author4.setBookList(books3);
        author5.setBookList(books2);

        return new ArrayList<>(Arrays.asList(author1, author2, author3, author4, author5));
    }
}

