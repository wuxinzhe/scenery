package com.scenery.scene.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.scenery.scene.manage.SceneBeansManage;

/**
 * @author wuxinzhe
 */
public class SceneryProxy<T> implements InvocationHandler {

    private final Class<T> sceneryInterface;

    private SceneBeansManage sceneBeansManage;

    public SceneryProxy(Class<T> sceneryInterface, SceneBeansManage sceneBeansManage) {
        this.sceneryInterface = sceneryInterface;
        this.sceneBeansManage = sceneBeansManage;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        T bean = this.sceneBeansManage.getBeanByScenery(this.sceneryInterface, args);
        return method.invoke(bean, args);
    }
}
