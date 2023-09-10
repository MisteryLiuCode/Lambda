package com.liu.lambdaMethod;

/**
 * All rights Reserved, Designed By jiexingcloud. lambda 表达式一些用法
 *
 * @author misteryliu
 * @since 2023/8/27 16:07 Copyright ©2023 misteryliu. All rights reserved.
 */
public class Five {

    public static void main(String[] args) {
        // 传统方式
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("新线程启动了");
            }
        }).start();

        /*
        lambda 表达式方法
        lambda 只关注参数和具体的实现
        上面的例子,不关注对象是否是 Runnable
         */
        new Thread(() -> System.out.println("新线程启动了")).start();
    }
}
