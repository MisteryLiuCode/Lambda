package com.liu.lambdaMethod;

import java.util.function.IntPredicate;

/**
 * All rights Reserved, Designed By misteryliu. 第 7 节,lambda 表达式练习
 *
 * @author misteryliu@outlook.com
 * @since 2023/8/27 16:57 Copyright ©2023 misteryliu. All rights reserved. 注意：本内容仅限于结行云创内部传阅，禁止外泄以及用于其他的商业用途。
 */
public class Seven {

    public static void main(String[] args) {

        //普通方式实现
        printNum(new IntPredicate() {
            @Override
            public boolean test(int value) {
                return value % 2 == 0;
            }
        });

        //lambda 表达式
        printNum(value -> value % 2 == 0);

    }

    public static void printNum(IntPredicate predicate) {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        for (int i : arr) {
            if (predicate.test(i)) {
                System.out.println(i);
            }
        }

    }

}
