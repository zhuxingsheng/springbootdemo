package com.jack.factorybean.mutil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
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

@Service
@Slf4j
public class RmoteInitService implements InitializingBean {

    @Autowired
    private DefaultListableBeanFactory defaultListableBeanFactory;

    @Autowired
    private ApplicationContext applicationContext;



    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("afterPropertiesSet");
    }

    @PostConstruct
    public void init() throws IOException {
        log.info("init-PostConstruct");
        registeRemoteService();
    }

    private void registeRemoteService() throws IOException {
        SimpleMetadataReaderFactory metadataReaderFactory = new SimpleMetadataReaderFactory(this.applicationContext);

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
