package org.kayla.springbean;

import lombok.extern.slf4j.Slf4j;
import org.kayla.springbean.way1.Student;
import org.kayla.springbean.way1.StudentService;
import org.kayla.springbean.way2.DiConfig;
import org.kayla.springbean.way2.Klass;
import org.kayla.springbean.way2.KlassService;
//import org.kayla.springbean.way2.DiConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Kayla(J - doIt)
 * @date 2021/10/21 22:39
 **/
@Slf4j
public class SpringMain {

//    @Autowired
    private KlassService klassService;

    @Autowired
    public SpringMain(KlassService klassService) {
        this.klassService = klassService;
    }

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student student_ref_01 = (Student) context.getBean("student_ref_01");
        Student student_ref_02 = (Student) context.getBean("student_ref_02");
        student_ref_01.print();
        student_ref_02.print();
        Student student = Student.create();
//        student.print();// null: Student(id=102, name=KK102, beanName=null, applicationContext=null)

        StudentService service = context.getBean(StudentService.class);
        service.print();

        Klass klass = (Klass) context.getBean("klass");
        klass.print();

        AnnotationConfigApplicationContext annotationConfigAC = new AnnotationConfigApplicationContext(DiConfig.class);
        KlassService klassService = annotationConfigAC.getBean(KlassService.class);
        new SpringMain(klassService).print();

//        Klass klass2 = annotationConfigAC.getBean(Klass.class);
        Klass klass2 = (Klass) annotationConfigAC.getBean("klass");
        klass2.print();
        log.info("klass == klass2 : " + String.valueOf(klass == klass2));
        klass.print();
        klass2.print();
    }

    public void print() {
        klassService.print();
    }
}
