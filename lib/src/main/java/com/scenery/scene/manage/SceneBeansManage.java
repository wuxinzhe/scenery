package com.scenery.scene.manage;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import com.scenery.scene.annotation.Scene;
import com.scenery.scene.model.SceneConfig;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author wuxinzhe
 */
@Component
public class SceneBeansManage implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    private final Map<Class<?>, List<SceneConfig>> sceneryConfigMap = new ConcurrentHashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @SuppressWarnings("unchecked")
    public <T> T getBeanByScenery(Class<T> sceneryInterface, Object... args) {
        List<SceneConfig> sceneConfigList = this.sceneryConfigMap
            .computeIfAbsent(sceneryInterface, s -> {
                Map<String, ?> sceneryMap = this.applicationContext.getBeansOfType(sceneryInterface);
                return sceneryMap
                    .values()
                    .stream()
                    .map(o -> {
                        Class<?> clazz = o.getClass();
                        Scene scene = clazz.getAnnotation(Scene.class);
                        if (null == scene) {return null;}
                        return new SceneConfig(scene, o);
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            });

        SceneConfig hitSceneConfig = sceneConfigList
            .stream()
            .filter(Objects::nonNull).min((x, y) -> {
                if (x.getDefault() && y.getDefault()) {
                    return 0;
                } else if (x.getDefault() && !y.getDefault()) {
                    return 1;
                } else if (!x.getDefault() && !y.getDefault()) {
                    return 0;
                } else if (!x.getDefault() && y.getDefault()) {
                    return -1;
                } else {
                    return 0;
                }
            })
            .orElse(null);
        if (null == hitSceneConfig) {
            return null;
        }
        return (T)hitSceneConfig.getBean();
    }

}
