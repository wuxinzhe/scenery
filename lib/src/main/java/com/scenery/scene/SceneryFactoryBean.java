package com.scenery.scene;

import com.scenery.scene.proxy.SceneryProxyManage;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author wuxinzhe
 */
public class SceneryFactoryBean<T> implements FactoryBean<T> {

    private Class<T> sceneInterface;

    private SceneryProxyManage sceneryProxyManage;

    public SceneryFactoryBean(Class<T> sceneInterface) {
        this.sceneInterface = sceneInterface;
    }

    public void setSceneryProxyManage(SceneryProxyManage sceneryProxyManage) {
        this.sceneryProxyManage = sceneryProxyManage;
    }

    @Override
    public T getObject() throws Exception {
        return this.sceneryProxyManage.getScene(this.sceneInterface);
    }

    @Override
    public Class<T> getObjectType() {
        return this.sceneInterface;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
