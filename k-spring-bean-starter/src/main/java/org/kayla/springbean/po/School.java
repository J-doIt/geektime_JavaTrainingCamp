package org.kayla.springbean.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Slf4j
public class School {

    private int id;
    private String name;

    private List<Klass> klasses;

}
