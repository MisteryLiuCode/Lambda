package com.liu.lambdaMethod;

import java.util.function.IntBinaryOperator;

/**
 * All rights Reserved, Designed By misteryliu. lambda 练习 1
 *
 * @author misteryliu@outlook.com
 * @since 2023/8/27 16:47 Copyright ©2023 misteryliu. All rights reserved. 注意：本内容仅限于结行云创内部传阅，禁止外泄以及用于其他的商业用途。
 */
public class six {
    public static void main(String[] args) {
        int i1 = calculateNum(new IntBinaryOperator() {
            @Override
            public int applyAsInt(int left, int right) {
                return left + right;
            }
        });
        System.out.println(i1);

        //转换成 lambda 表达式
        int i2 = calculateNum((int left, int right) -> left + right);

        //快捷键快速转换成 lambda 表达式 option + 回车
        int i3 = calculateNum((left, right) -> left + right);
    }

    public static int calculateNum(IntBinaryOperator operator) {
        int a = 10;
        int b = 20;
        return operator.applyAsInt(a, b);

    }
}
