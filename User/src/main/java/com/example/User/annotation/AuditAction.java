package com.example.User.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuditAction {

    String action();
}
// @AuditAction(action="create") : uses in serviceimpl class with CREATE / UPDATE / DELETE methods.
