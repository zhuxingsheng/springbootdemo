package com.jack.factorybean.mutil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class RmoteInitService implements InitializingBean, CommandLineRunner {

    @Autowired
    private DefaultListableBeanFactory defaultListableBeanFactory;

    @Autowired
    private ApplicationContext applicationContext;



    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("afterPropertiesSet");
    }

    public RmoteInitService() throws IOException {
        log.info("init-Construct");
    }

    public void init() throws IOException {
        log.info("init-PostConstruct");
        registeRemoteService();
    }



    @Override
    public void run(String... args) throws Exception {
        log.info("CommandLineRunner run");
        registeRemoteService();
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

            defaultListableBeanFactory.registerBeanDefinition(clazz+"-proxy",beanDefinitionBuilder.getBeanDefinition());
        });
    }


    private String resolvePackageToScan(String scanPackage) {
        return "classpath*:" + ClassUtils.convertClassNameToResourcePath(scanPackage) + "/**/*.class";
    }


}
