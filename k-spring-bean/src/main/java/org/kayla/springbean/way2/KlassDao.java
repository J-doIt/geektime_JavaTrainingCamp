package org.kayla.springbean.way2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * @author Kayla(J - doIt)
 * @date 2021/10/24 13:01
 **/
@Repository
@Slf4j
public class KlassDao {

    public void print() {
        log.info("KlassDao.print");
    }
}
