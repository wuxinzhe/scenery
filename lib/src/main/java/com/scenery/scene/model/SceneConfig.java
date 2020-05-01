package com.scenery.scene.model;

import com.scenery.scene.annotation.Scene;

/**
 * @author wuxinzhe
 */
public class SceneConfig {

    private String umpChannel;

    private String source;

    private String bucket;

    private String iosVersion;

    private String androidVersion;

    private String app;

    private Boolean isDefault;

    private Object bean;

    public SceneConfig(Scene scene, Object bean) {
        this.umpChannel = scene.umpChannel();
        this.source = scene.source();
        this.bucket = scene.bucket();
        this.iosVersion = scene.iOSVersion();
        this.androidVersion = scene.androidVersion();
        this.app = scene.app();
        this.isDefault = scene.isDefault();
        this.bean = bean;
    }

    public String getUmpChannel() {
        return umpChannel;
    }

    public String getSource() {
        return source;
    }

    public String getBucket() {
        return bucket;
    }

    public String getIosVersion() {
        return iosVersion;
    }

    public String getAndroidVersion() {
        return androidVersion;
    }

    public String getApp() {
        return app;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public Object getBean() {
        return bean;
    }
}
