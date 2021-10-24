package org.kayla.springbean.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Kayla(J - doIt)
 * @date 2021/10/24 12:40
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Slf4j
@Component
public class Klass {

    List<Student> students;

    public void print() {
        log.info(this.toString());
    }
}
