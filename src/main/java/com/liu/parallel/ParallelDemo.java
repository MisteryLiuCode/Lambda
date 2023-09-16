package com.liu.parallel;

import com.liu.entity.Author;
import com.liu.entity.InitData;

import java.util.List;
import java.util.function.Consumer;

/**
 * All rights Reserved, Designed By misteryliu.
 * 并行流
 *
 * @author misteryliu@outlook.com
 * @since 2023/9/16 17:36 Copyright ©2023 misteryliu. All rights reserved. 注意：本内容仅限于结行云创内部传阅，禁止外泄以及用于其他的商业用途。
 */
public class ParallelDemo {
    public static void main(String[] args) {
        ordinary();
        System.out.println("=======并行流");
        parallel();
    }



    /**
     * 传统方式,不使用并行流
     *
     * @param
     * @return void
     * @since 2023/9/16 17:42 by misteryliu
     **/
    public static void ordinary(){
        List<Author> authors = InitData.getAuthors();
        //模拟数据处理
        authors.stream()
                //一个用于 debug 的
                .peek(new Consumer<Author>() {
                    @Override
                    public void accept(Author author) {
                        System.out.println(author.getId()+Thread.currentThread().getName());
                    }
                })
                .map(author -> author.getAge())
                .map(age->age+10)
                .filter(age->age>18)
                .map(age->age+20)
                .forEach(System.out::println);
    }

    /**
     * 并行流
     *
     * @param 
     * @return void
     * @since 2023/9/16 17:47 by misteryliu
     **/
    public static void parallel(){
        List<Author> authors = InitData.getAuthors();
        authors.parallelStream()
                //也可以直接使用上面的方式
//                .parallel()
                //一个用于 debug 的
                .peek(new Consumer<Author>() {
                    @Override
                    public void accept(Author author) {
                        System.out.println(author.getId()+Thread.currentThread().getName());
                    }
                })
                .map(author -> author.getAge())
                .map(age->age+10)
                .filter(age->age>18)
                .map(age->age+20)
                .forEach(System.out::println);
        
    }



}
