package org.kayla.springbean.way2;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author Kayla(J - doIt)
 * @date 2021/10/24 13:01
 **/
@Service
@Slf4j
@Data
public class KlassService implements ApplicationContextAware {

    @Autowired
    private KlassDao klassDao;
    private ApplicationContext applicationContext;

    public void print() {
        log.info("KlassService.print() -- context.getBeanDefinitionNames() ===>> ");
        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(s -> log.info("    " + s));
    }
}
