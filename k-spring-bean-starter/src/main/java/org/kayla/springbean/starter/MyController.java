package org.kayla.springbean.starter;

import lombok.extern.slf4j.Slf4j;
import org.kayla.springbean.po.School;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Kayla(J - doIt)
 * @date 2021/10/24 21:53
 **/
@RestController
@Slf4j
public class MyController {

    @Resource
    private School school;

    @RequestMapping("/print")
    public String print() {
        log.info(school.toString());
        return "";
    }
}
