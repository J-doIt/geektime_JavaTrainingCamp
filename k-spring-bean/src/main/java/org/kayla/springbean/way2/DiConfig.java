package org.kayla.springbean.way2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.kayla.springbean.way1.Student;
import org.kayla.springbean.way2.Klass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置类
 *
 * @author Kayla(J - doIt)
 * @date 2021/10/24 14:40
 **/
@Configuration
@ComponentScan("org.kayla.springbean.way2")
public class DiConfig {

    @Autowired(required=true)
//    @Qualifier("klass2")
    private Klass klass2;

    private ApplicationContext applicationContext;

//    @Bean("klass2")
    /*public Klass getKlass() {
        Klass klass = new Klass();
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student student_ref_01 = (Student) context.getBean("student_ref_01");
        Student student_ref_02 = (Student) context.getBean("student_ref_02");
        student_ref_01.print();
        student_ref_02.print();
        List list = new ArrayList();
        list.add(student_ref_01);
        list.add(student_ref_02);
        klass.setStudents(list);
        return this.klass3;
    }*/
}
