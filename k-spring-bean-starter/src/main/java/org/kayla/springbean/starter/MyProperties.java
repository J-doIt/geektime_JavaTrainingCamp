package org.kayla.springbean.starter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Kayla(J - doIt)
 * @date 2021/10/24 19:31
 **/
@ConfigurationProperties(prefix = "kayla.startar")
@Data
public class MyProperties {

    private boolean enabled = false;
    private int studentid = 1;
    private String studentname = "student_1";
    private int schoolid = 1;
    private String schoolname = "Inner Mongolia University";

}
