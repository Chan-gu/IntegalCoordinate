package com.hanuritien.integalcoordinate.multidatasource;

import java.lang.annotation.*;

/**
 * Choice DataSource annotation
 *
 */
@Target({ElementType.PACKAGE, ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
    /**
     * @return datasource name
     */
    String value();
}
