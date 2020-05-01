package com.scenery.scene.proxy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.scenery.scene.manage.SceneBeansManage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wuxinzhe
 */
@Component
public class SceneryProxyManage {

    @Autowired
    private SceneBeansManage sceneBeansManage;

    private final Map<Class<?>, SceneryProxyFactory<?>> knownMiscDataMap = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    public <T> T getScene(Class<T> sceneInterface) {
        final SceneryProxyFactory<T> sceneryProxyFactory = (SceneryProxyFactory<T>)knownMiscDataMap.computeIfAbsent(sceneInterface, SceneryProxyFactory::new);
        return sceneryProxyFactory.newInstance(sceneBeansManage);
    }

}
