package org.kayla.springbean.way1;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.Serializable;
import java.util.Arrays;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Slf4j
public class Student implements Serializable, BeanNameAware, ApplicationContextAware {


    private int id;
    private String name;

    private String beanName;
    private ApplicationContext applicationContext;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void init(){
        System.out.println("hello...........");
    }
    
    public static Student create(){
        return new Student(102,"KK102",null, null);
    }

    public void print() {
        log.info(this.beanName + ": " + this.toString());
        log.info("Student.print() -- context.getBeanDefinitionNames() ===>> ");
        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(s -> log.info("    " + s));
    }


}
