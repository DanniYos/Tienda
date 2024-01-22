package org.dan.webapp.apiservlet.headers.configs;

import jakarta.inject.Qualifier;

import static java.lang.annotation.ElementType.*;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Qualifier
@Retention(RetentionPolicy.SOURCE)
@Target({TYPE, CONSTRUCTOR, METHOD, FIELD, PARAMETER})
public @interface MysqlConn {

}
