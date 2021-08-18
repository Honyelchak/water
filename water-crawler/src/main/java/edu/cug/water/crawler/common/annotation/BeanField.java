package edu.cug.water.crawler.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BeanField {

    /**
     * 返回该属性在Bean中的位置
     * @return
     */
    int order() default -1;

    /**
     * 标记处理该属性的方法
     * @return
     */
    int dealNumber() default -1;

    /**
     * 该属性对应的property映射名
     * @return
     */
    String property() default "";

    /**
     * 表明此属性所对应的类别
     * @return
     */
    int belongs() default 0;
}
