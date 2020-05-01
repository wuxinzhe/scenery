package com.scenery.scene.proxy;

import java.lang.reflect.Proxy;

import com.scenery.scene.manage.SceneBeansManage;

/**
 * @author wuxinzhe
 */
public class SceneryProxyFactory<T> {

    private final Class<T> sceneryInterface;

    public SceneryProxyFactory(Class<T> sceneryInterface) {
        this.sceneryInterface = sceneryInterface;
    }

    @SuppressWarnings("unchecked")
    protected T newInstance(SceneryProxy<T> sceneryProxy) {
        return (T)Proxy.newProxyInstance(sceneryInterface.getClassLoader(), new Class[] {sceneryInterface}, sceneryProxy);
    }

    public T newInstance(SceneBeansManage sceneBeansManage) {
        final SceneryProxy<T> sceneryProxy = new SceneryProxy<>(sceneryInterface, sceneBeansManage);
        return newInstance(sceneryProxy);
    }
}
