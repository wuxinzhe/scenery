package com.scenery.scene;

import com.scenery.scene.annotation.Scene;
import com.scenery.scene.annotation.Scenery;
import com.scenery.scene.scanner.ClassPathSceneBeanScanner;
import com.scenery.scene.scanner.ClassPathSceneryScanner;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

/**
 * @author wuxinzhe
 */
public class SceneryScannerConfigurer implements BeanDefinitionRegistryPostProcessor {

    private String basePackages;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        ClassPathSceneryScanner scanner = new ClassPathSceneryScanner(registry);
        scanner.setAnnotationClass(Scenery.class);
        scanner.registerFilters();
        scanner.scan(basePackages);

        ClassPathSceneBeanScanner beanScanner = new ClassPathSceneBeanScanner(registry);
        beanScanner.setAnnotationClass(Scene.class);
        beanScanner.registerFilters();
        beanScanner.scan(basePackages);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory)
        throws BeansException {
    }

    public String getBasePackages() {
        return basePackages;
    }

    public void setBasePackages(String basePackages) {
        this.basePackages = basePackages;
    }
}
