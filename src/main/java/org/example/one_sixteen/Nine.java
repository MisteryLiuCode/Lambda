package org.example.one_sixteen;

import lombok.extern.slf4j.Slf4j;

import java.util.function.IntConsumer;

/**
 * All rights Reserved, Designed By misteryliu. 第八节,lambda 表达式练习
 *
 * @author misteryliu@outlook.com
 * @since 2023/8/27 17:25 Copyright ©2023 misteryliu. All rights reserved. 注意：本内容仅限于结行云创内部传阅，禁止外泄以及用于其他的商业用途。
 */
@Slf4j
public class Nine {

    public static void main(String[] args) {
        // 普通方式实现
        foreachArr(new IntConsumer() {
            @Override
            public void accept(int value) {
                if (value % 2 == 0) {
                    System.out.println(value);
                }
            }
        });

        // lambda 表达式实现
        foreachArr(value-> {
            if (value % 2 == 0) {
                System.out.println(value);
            }
        });
    }

    public static void foreachArr(IntConsumer intConsumer) {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        for (int i : arr) {
            intConsumer.accept(i);
        }
    }

}
