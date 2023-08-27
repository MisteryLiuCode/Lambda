package org.example.one_sixteen;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Function;

/**
 * All rights Reserved, Designed By misteryliu. 第八节,lambda 表达式练习
 *
 * @author misteryliu@outlook.com
 * @since 2023/8/27 17:09 Copyright ©2023 misteryliu. All rights reserved. 注意：本内容仅限于结行云创内部传阅，禁止外泄以及用于其他的商业用途。
 */
@Slf4j
public class Eight {

    public static void main(String[] args) {
        //普通方式调用
        String s = typeConver(new Function<String, String>() {
            @Override
            public String apply(String s) {
                String substring = s.substring(0, 2);
                return substring;
            }
        });
        log.info("普通方式表达式实现结果:{}", s);
        String s1 = typeConver(value -> value.substring(0, 2));
        log.info("lambda表达式实现结果:{}", s1);
    }

    public static <R> R typeConver(Function<String, R> function) {
        String str = "12345";
        R result = function.apply(str);
        return result;
    }
}
