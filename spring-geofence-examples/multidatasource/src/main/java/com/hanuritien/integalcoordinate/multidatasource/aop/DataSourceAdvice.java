package com.hanuritien.integalcoordinate.multidatasource.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;

import com.hanuritien.integalcoordinate.multidatasource.DataSource;
import com.hanuritien.integalcoordinate.multidatasource.DataSourceNameContextHolder;

import java.lang.reflect.Method;

/**
 * DataSource advice
 */
public class DataSourceAdvice implements MethodInterceptor, Ordered {
    private final Logger log = LoggerFactory.getLogger(DataSourceAdvice.class);
    private int order = 0;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method invocationMethod = invocation.getMethod();
        if(log.isDebugEnabled()) {
            log.debug("Methods \"{}\" annotated with @Datasource is start", invocationMethod.toString());
        }
        DataSource dataSource = findDataSourceAnnotation(invocationMethod, invocation.getThis().getClass());
        if (dataSource != null) {
            String currentDataSourceName = DataSourceNameContextHolder.getDataSourceName();
            String currentSettingDataSourceName = dataSource.value();
            if (currentSettingDataSourceName != null &&
                    !currentSettingDataSourceName.equals(currentDataSourceName)) {
                DataSourceNameContextHolder.setDataSourceName(currentSettingDataSourceName);
                log.debug("'{}' changed dataSource", currentSettingDataSourceName);
            }
        }
        try {
            return invocation.proceed();
        } finally {
            DataSourceNameContextHolder.clear();
            if(log.isDebugEnabled()) {
                log.debug("Methods \"{}\" annotated with @Datasource is end", invocationMethod.toString());
            }
        }
    }

    /**
     * Find @DataSource
     * Order: Method -> Class -> package
     *
     * @param method
     * @return
     * @throws Throwable
     */
    private DataSource findDataSourceAnnotation(Method method, Class<?> targetClass) throws Throwable {
        boolean isDebugEnabled = log.isDebugEnabled();
        DataSource dataSource;
        if ((dataSource = AnnotationUtils.findAnnotation(method, DataSource.class)) != null) {
            if (isDebugEnabled) {
                log.debug("Method {}.{} has @DataSource(value={})", targetClass.getSimpleName(), method.getName(), dataSource.value());
            }
            return dataSource;
        }

        Method specificMethod = AopUtils.getMostSpecificMethod(method, targetClass);
        if ((dataSource = AnnotationUtils.findAnnotation(specificMethod, DataSource.class)) != null) {
            if (isDebugEnabled) {
                log.debug("Specific Method {}.{} has @DataSource(value={})", targetClass.getSimpleName(), method.getName(), dataSource.value());
            }
            return dataSource;
        }

        if ((dataSource = AnnotationUtils.findAnnotation(targetClass, DataSource.class)) != null) {
            if (isDebugEnabled) {
                log.debug("Class {} has @DataSource(value={})", targetClass.getSimpleName(), dataSource.value());
            }
            return dataSource;
        }
/*        if ((dataSource = AnnotationUtils.findAnnotation(targetClass.getPackage(), DataSource.class)) != null) {
            if (isDebugEnabled) {
                log.debug("Package {} has @DataSource(value={})", targetClass.getPackage().toString(), dataSource.value());
            }
            return dataSource;
        }*/
        return null;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public int getOrder() {
        return order;
    }
}