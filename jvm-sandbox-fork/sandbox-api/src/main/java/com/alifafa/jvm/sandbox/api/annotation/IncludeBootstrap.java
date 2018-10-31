package com.alifafa.jvm.sandbox.api.annotation;

import com.alifafa.jvm.sandbox.api.filter.Filter;

import java.lang.annotation.*;

/**
 * 拥有此标记的{@link Filter}，
 * 将能匹配到来自{@code BootstrapClassLoader}所加载的类
 *
 * @author luanjia@taobao.com
 * @since {@code sandbox-api:1.0.10}
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface IncludeBootstrap {
}
