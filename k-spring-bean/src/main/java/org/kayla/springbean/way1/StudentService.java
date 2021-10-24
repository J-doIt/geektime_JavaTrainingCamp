package org.kayla.springbean.way1;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Kayla(J - doIt)
 * @date 2021/10/21 23:48
 **/
@Slf4j
public class StudentService {

    private StudentDao studentDao;

    public StudentService(StudentDao studentDao) {
        log.info("autowiring by constructor");
        this.studentDao = studentDao;
    }

    public void print() {
        studentDao.print();
        log.info("StudentService.print");
    }

    /*public void setStudentDao(StudentDao studentDao) {
        log.info("autowiring by type");
        this.studentDao = studentDao;
    }*/
}
