package com.liu.stream;

import com.liu.entity.Author;
import com.liu.entity.Book;
import com.liu.entity.InitData;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * All rights Reserved, Designed By misteryliu. 流 demo
 *
 * @author misteryliu@outlook.com
 * @since 2023/8/27 19:42 Copyright ©2023 misteryliu. All rights reserved. 注意：本内容仅限于结行云创内部传阅，禁止外泄以及用于其他的商业用途。
 */

@Slf4j
public class StreamDemo {

    public static void main(String[] args) {
        //filter 过滤
        //        test04();

        // map
        //        test05();
        //distinct
        //        test06();
        //sort
        //        test07();
        //        使用 limit 控制数据长度
        //        test08();
        //使用 skip 跳过
        //        test09();
        //        test10();
        //        test11();
        //        test12();
        //        test13();
        //        test14();
        //        test15();
        //        test16();
        //        test17();
        //        test18();
        test19();
    }

    /**
     * 创建流
     *
     * @param
     * @return void
     * @since 2023/9/3 15:51 by misteryliu
     **/
    public static void test01() {
        Integer[] arr = { 1, 2, 3, 4 };
        Stream<Integer> arrStream = Arrays.stream(arr);
        arrStream.distinct();
        //创建流的第二种方法
        Stream<Integer> arrStream1 = Stream.of(arr);
        arrStream1.forEach(integer -> System.out.println(integer));
    }

    /**
     * 双列集合创建流
     *
     * @param
     * @return void
     * @since 2023/9/3 15:51 by misteryliu
     **/
    public static void test02() {
        HashMap<String, Integer> map = new HashMap<>();

        map.put("test1", 1);
        map.put("test2", 2);
        map.put("test3", 3);
        map.put("test4", 4);

        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        entries.stream().filter(stringIntegerEntry -> stringIntegerEntry.getValue() < 3).forEach(
                stringIntegerEntry -> System.out.println(
                        stringIntegerEntry.getKey() + ":" + stringIntegerEntry.getValue()));
    }

    /**
     * 打印年龄小于 18 的作者名字,并且去重
     *
     * @param
     * @return void
     * @since 2023/9/3 15:53 by misteryliu
     **/
    public static void test03() {
        List<Author> authors = InitData.getAuthors();
        //我的写法
        List<String> authorNames = authors.stream() //集合转换成流
                //filter 入参是Predicate,里面就是 lambda表达式实现方法
                .filter(author -> author.getAge() < 18).distinct()
                // map 入参是 Function,实现的是 apply 方法,入参是 author,返回得是 name
                .map(author -> author.getName()).collect(Collectors.toList());
        log.info("我的写法:{}", authorNames);

        //匿名内部类的写法
        authors.stream().distinct().filter(new Predicate<Author>() {
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
                        log.info("匿名内部类的写法{}", author.getName());
                    }
                });
        //lambda表达式写法

        authors.stream().distinct().filter(author -> author.getAge() < 18)
                .forEach(author -> log.info("lambda表达式写法{}", author.getName()));
    }

    /**
     * 打印名字小于 1 的作家姓名
     *
     * @param
     * @return void
     * @since 2023/9/3 16:02 by misteryliu
     **/
    private static void test04() {
        List<Author> authors = InitData.getAuthors();
        //使用 filter 方法过滤:打印出所有姓名长度大于 3 的作家的姓名
        authors.stream().filter(author -> author.getName().length() > 3)
                .forEach(author -> System.out.println(author.getName()));
    }



    /**
     * stream流之 map 操作 打印和操作
     *
     * @param
     * @return void
     * @since 2023/9/3 16:04 by misteryliu
     **/
    private static void test05() {
        List<Author> authors = InitData.getAuthors();
        // 第一种方案
        authors.stream().forEach(author -> System.out.println(author.getName()));

        // 使用map,可以进行类型转换,这里就是参数是 author 对象,需要转换为 String 对象
        authors.stream().map(author -> author.getName()).forEach(name -> System.out.println(name));

        // 使用 map 对流中的元素进行计算
        authors.stream().map(author -> author.getAge() + 10).forEach(age -> System.out.println(age));
    }

    /**
     * distinct 操作
     *
     * @param
     * @return void
     * @since 2023/9/3 16:53 by misteryliu
     **/
    public static void test06() {
        List<Author> authors = InitData.getAuthors();
        authors.stream().distinct().forEach(author -> System.out.println(author.getName()));
    }

    /**
     * 使用 sort 排序
     *
     * @param
     * @return void
     * @since 2023/9/3 16:55 by misteryliu
     **/
    private static void test07() {
        List<Author> authors = InitData.getAuthors();
        authors.stream().sorted().forEach(author -> System.out.println(author.getAge()));
    }

    /**
     * 使用 limit 控制数据长度
     *
     * @param
     * @return void
     * @since 2023/9/3 17:34 by misteryliu
     **/
    private static void test08() {

        List<Author> authors = InitData.getAuthors();
        authors.stream().sorted().limit(2).forEach(author -> System.out.println(author.getName()));

    }

    /**
     * 打印除了年龄最大的作家外的其他作家,不能重复
     *
     * @param
     * @return void
     * @since 2023/9/3 19:23 by misteryliu
     **/
    private static void test09() {
        List<Author> authors = InitData.getAuthors();
        authors.stream().distinct().sorted().skip(1).forEach(author -> System.out.println(author.getName()));
    }

    /**
     * 使用 flatMap 打印出所有书籍的名字,去重
     *
     * @param
     * @return void
     * @since 2023/9/3 19:36 by misteryliu
     **/
    private static void test10() {
        List<Author> authors = InitData.getAuthors();
        //用 Map 的方案 TODO 还未完成
        ArrayList<Book> resBookList = new ArrayList<>();
        ArrayList<String> resBookNameList = new ArrayList<>();
        authors.stream().map(new Function<Author, List<Book>>() {
            @Override
            public List<Book> apply(Author author) {
                List<Book> bookList = author.getBookList();

                return bookList;
            }
        }).map(new Function<List<Book>, List<String>>() {
            @Override
            public List<String> apply(List<Book> books) {
                for (Book book : books) {
                    resBookNameList.add(book.getName());
                }
                return resBookNameList;
            }
        }).distinct().forEach(name -> System.out.println(name.toString()));

        authors.stream().flatMap(new Function<Author, Stream<Book>>() {
            @Override
            public Stream<Book> apply(Author author) {
                return author.getBookList().stream();
            }
        }).distinct().forEach(book -> System.out.println(book.getName()));

        //得到都有哪些分类
        //Author -> bookList-> categary:需要用","分割，得到书籍类型，去重，输出
        authors.stream().flatMap(author -> author.getBookList().stream())
                .flatMap(book -> Arrays.stream(book.getCategory().split(","))).distinct()
                .forEach(category -> System.out.println(category));
    }

    /**
     * 终结操作:max&min 获取书籍最高分和最低分
     *
     * @param
     * @return void
     * @since 2023/9/3 20:37 by misteryliu
     **/
    private static void test11() {
        List<Author> authors = InitData.getAuthors();
        Optional<Double> max = authors.stream().flatMap(author -> author.getBookList().stream())
                .map(book -> book.getScore()).max(new Comparator<Double>() {
                    @Override
                    public int compare(Double o1, Double o2) {
                        return (int) (o1 - o2);
                    }
                });
        System.out.println("max:" + max.get());

        Optional<Double> min = authors.stream().flatMap(author -> author.getBookList().stream())
                .map(book -> book.getScore()).min(new Comparator<Double>() {
                    @Override
                    public int compare(Double o1, Double o2) {
                        return (int) (o1 - o2);
                    }
                });

        System.out.println("min:" + min.get());

    }

    /**
     * 终结操作,转换为 List,Set,Map
     *
     * @param
     * @return void
     * @since 2023/9/3 21:19 by misteryliu
     **/
    private static void test12() {

        //list 集合
        List<Author> authors = InitData.getAuthors();
        List<String> collectList = authors.stream().map(author -> author.getName()).collect(Collectors.toList());

        //         set 集合
        Set<String> collectSet = authors.stream().flatMap(author -> author.getBookList().stream())
                .map(book -> book.getName()).collect(Collectors.toSet());

        //map 集合
        // 作者名字作为 key,BookList 作为 value
        //value
        Map<String, List<Book>> collectMap = authors.stream().distinct().collect(Collectors.toMap(
                //key
                author -> author.getName(), author -> author.getBookList()));

    }

    /**
     * anyMatch allMatch
     *
     * @param
     * @return void
     * @since 2023/9/3 21:38 by misteryliu
     **/
    private static void test13() {
        //判断是否有任意一个大于 25 岁的作家
        boolean any = InitData.getAuthors().stream().anyMatch(author -> author.getAge() > 25);
        System.out.println("是否有任意一个大于 25 岁的作家:" + any);
        //       判断所有作家年龄都大于 18 岁
        boolean all = InitData.getAuthors().stream().allMatch(author -> author.getAge() > 18);
        System.out.println("是否全部都大于 18" + all);
    }

    /**
     * findAny findFirst
     *
     * @param
     * @return void
     * @since 2023/9/3 21:44 by misteryliu
     **/
    private static void test14() {
        //       获取任意一个年龄大于 18 的元素
        List<Author> authors = InitData.getAuthors();
        Optional<Author> any = authors.stream().filter(author -> author.getAge() > 18).findAny();
        //ifPresent避免空指针,如果找不到不会执行
        any.ifPresent(author -> System.out.println(author.getAge()));

        // 获取年龄最小的,输出姓名
        Optional<Author> first = authors.stream().sorted(new Comparator<Author>() {
            @Override
            public int compare(Author o1, Author o2) {
                return o1.getAge() - o2.getAge();
            }
        }).findFirst();
        first.ifPresent(author -> System.out.println(author.getAge()));

    }

    /**
     * 使用stream求所有作者年龄的和
     *
     * @param
     * @return void
     * @since 2023/9/10 16:16 by misteryliu
     **/
    public static void test15() {
        List<Author> authors = InitData.getAuthors();
        System.out.println(
                authors.stream().map(author -> author.getAge()).reduce(0, (result, element) -> result + element));

    }

    /**
     * 求所有作者中年龄最大的值
     *
     * @param
     * @return void
     * @since 2023/9/10 16:25 by misteryliu
     **/
    public static void test16() {
        //        使用 reduce 求所有作者中年龄最大的值
        List<Author> authors = InitData.getAuthors();
        System.out.println(authors.stream().map(author -> author.getAge()).reduce(0, new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer result, Integer element) {
                return Math.max(result, element);
            }
        }));
    }

    /**
     * 求所有作者中年龄最小的值
     *
     * @param
     * @return void
     * @since 2023/9/10 16:25 by misteryliu
     **/
    public static void test17() {
        //        使用 reduce 求所有作者中年龄最大的值
        List<Author> authors = InitData.getAuthors();
        System.out.println(authors.stream().map(author -> author.getAge())
                .reduce(Integer.MAX_VALUE, new BinaryOperator<Integer>() {
                    @Override
                    public Integer apply(Integer result, Integer element) {
                        return Math.min(result, element);
                    }
                }));
    }

    /**
     * 求所有作者中年龄最小的值(reduce一个入参)
     *
     * @param
     * @return void
     * @since 2023/9/10 16:25 by misteryliu
     **/
    public static void test18() {
        //        使用 reduce 求所有作者中年龄最大的值
        List<Author> authors = InitData.getAuthors();
        System.out.println(authors.stream().map(author -> author.getAge()).reduce(new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer result, Integer element) {
                return Math.min(result, element);
            }
        }).get());
    }

    /**
     * 演示流不能被重复使用
     *
     * @param
     * @return void
     * @since 2023/9/10 16:54 by misteryliu
     **/
    public static void test19() {
        List<Author> authors = InitData.getAuthors();
        // 创建流
        Stream<Author> stream = authors.stream();
        // 初次使用流
        stream.map(author -> author.getName()).forEach(name -> System.out.println(name));
        //再次使用流 stream has already been operated upon or closed
        stream.map(author -> author.getName()).forEach(name -> System.out.println(name));
    }

    /**
     * stream 对原数据的影响
     *
     * @param
     * @return void
     * @since 2023/9/10 17:03 by misteryliu
     **/
    public static void test20() {
        List<Author> authors = InitData.getAuthors();
        // 创建流
        Stream<Author> stream = authors.stream();

        //这样不会影响
        stream.map(author -> author.getAge() + 10).forEach(age -> System.out.println(age));

        authors.stream().map(new Function<Author, Author>() {
            @Override
            public Author apply(Author author) {
                //直接调用 set 方法是会影响的
                author.setAge(author.getAge()+10);
                return author;
            }
        }).forEach(age-> System.out.println(age));

    }
}

