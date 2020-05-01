package com.scenery.scene.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wuxinzhe
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Scene {

    boolean isDefault() default false;

    String umpChannel() default "";

    String source() default "";

    String bucket() default "";

    String iOSVersion() default "";

    String androidVersion() default "";

    String app() default "";
}
