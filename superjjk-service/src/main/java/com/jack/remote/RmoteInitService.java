package com.jack.remote;

import com.jack.factorybean.mutil.RemoteProxyFactoryBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 这个类是动态注册bean,需要在引用类使用@Autowired前先加载，但@Order没有生效，不知为何？
 *
 * 如何解决：方法有二
 * 1. 在启动类中主动@Autowired一下
 * 2. 在@ComponentScan指定包时，先扫描
 */
@Service
@Slf4j
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class RmoteInitService implements ApplicationContextAware,InitializingBean, CommandLineRunner, BeanFactoryPostProcessor {

    @Autowired
    private DefaultListableBeanFactory defaultListableBeanFactory;

    @Autowired
    private ApplicationContext applicationContext;



    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("init-afterPropertiesSet");
        registeRemoteService();

    }

    public RmoteInitService() throws IOException {
        log.info("init-Construct");
    }

    @PostConstruct
    public void init() throws IOException {

        log.info("init-PostConstruct");

    }



    @Override
    public void run(String... args) throws Exception {
        log.info("init-CommandLineRunner run");
        //registeRemoteService();
    }

    private void registeRemoteService() throws IOException {
        SimpleMetadataReaderFactory metadataReaderFactory = new SimpleMetadataReaderFactory(this.applicationContext);

        //读取此包下的所有类,进行包装
        Resource[] resources = applicationContext.getResources(resolvePackageToScan("com.jack.remote.service"));

        List<String> classNames = Arrays.stream(resources).map(resource -> {
            MetadataReader metadataReader = null;
            try {
                metadataReader = metadataReaderFactory.getMetadataReader(resource);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return metadataReader.getClassMetadata().getClassName();
        }).collect(Collectors.toList());

        //托管给容器
        classNames.forEach(clazz -> {
            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(RemoteProxyFactoryBean.class);
            beanDefinitionBuilder.addPropertyValue("remoteService",clazz).addPropertyValue("url","http://remotehost/");

            AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
            //beanDefinition.setDependsOn("remoteInitService");
            defaultListableBeanFactory.registerBeanDefinition(clazz+"-proxy", beanDefinition);
        });
    }


    private String resolvePackageToScan(String scanPackage) {
        return "classpath*:" + ClassUtils.convertClassNameToResourcePath(scanPackage) + "/**/*.class";
    }


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
