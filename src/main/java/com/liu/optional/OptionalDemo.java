package com.liu.optional;

import com.liu.entity.Author;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * All rights Reserved, Designed By misteryliu. optional
 *
 * @author misteryliu@outlook.com
 * @since 2023/9/10 18:11 Copyright ©2023 misteryliu. All rights reserved. 注意：本内容仅限于结行云创内部传阅，禁止外泄以及用于其他的商业用途。
 */
public class OptionalDemo {
    private static final Author author = new Author(1L, "misteryliu", "哲学", 25, null);

    public static void main(String[] args) {
        //        Author author = getAuthor();
        //        // 创建 optional 对象
        //        Optional<Author> authorOptional = Optional.ofNullable(author);
        //        Optional<Author> authorOptional = getAuthorForOfNullable();
        Optional<Author> authorForOf = getAuthorForOf();
        // 使用 如果author 不为 null的时候调用,否则不调用
        //        authorOptional.ifPresent(author1 -> System.out.println(author1.getName()));
        authorForOf.ifPresent(author1 -> System.out.println(author1.getName()));
        //如果不为 null 返回值,为 null 抛出异常
        authorForOf.get();
        // 如果有值返回,没值返回你创建的
        authorForOf.orElseGet(() -> new Author());
        //有值返回,没有抛异常
        try {
            authorForOf.orElseThrow(new Supplier<Throwable>() {
                @Override
                public Throwable get() {
                    return new RuntimeException("值为 null");
                }
            });
        }
        catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 演示 Optional.ofNullable()
     *
     * @param
     * @return java.util.Optional<com.liu.entity.Author>
     * @since 2023/9/10 18:22 by misteryliu
     **/
    public static Optional<Author> getAuthorForOfNullable() {

        //        return author;
        return Optional.ofNullable(author);
    }

    /**
     * 演示Optiona.of()
     *
     * @param
     * @return java.util.Optional<com.liu.entity.Author>
     * @since 2023/9/10 18:25 by misteryliu
     **/
    public static Optional<Author> getAuthorForOf() {
        //        return Optional.of(author);
        return Optional.of(null);
    }

    /**
     * optional.filter
     *
     * @param 
     * @return void
     * @since 2023/9/10 18:49 by misteryliu
     **/
    public static void testFilter() {
        Optional<Author> authorForOfNullable = getAuthorForOfNullable();
        //如果author==null 不执行,不符合过滤条件,返回 optional封装的 null 对象
        Optional<Author> authorOptional = authorForOfNullable.filter(author -> author.getAge() > 25);
    }


    /**
     * 测试map
     *
     * @param 
     * @return void
     * @since 2023/9/10 18:55 by misteryliu
     **/
    public static void testMap(){
        getAuthorForOfNullable().map(author1 -> author1.getBookList())
                .ifPresent(books -> System.out.println(books));
    }

}
