package org.kayla.springbean.starter;

import com.google.common.collect.Lists;
import org.kayla.springbean.po.Klass;
import org.kayla.springbean.po.School;
import org.kayla.springbean.po.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Kayla(J - doIt)
 * @date 2021/10/24 19:33
 **/
@Configuration
@ConditionalOnProperty(prefix = "kayla.startar", value = "enabled" ,havingValue = "true")
@EnableConfigurationProperties(MyProperties.class)
public class MyAutoConfiguration {

    @Autowired
    MyProperties properties;

    @Bean
    public Student initStudent() {
        return new Student(properties.getStudentid(), properties.getStudentname());
    }

    @Bean
    @ConditionalOnBean(Student.class)
    public Klass initKlass() {
        return new Klass(Lists.newArrayList(initStudent()));
    }

    @Bean
    @ConditionalOnBean(Klass.class)
    public School initSchool() {
        return new School(properties.getSchoolid(), properties.getSchoolname(), Lists.newArrayList(initKlass()));
    }



}
