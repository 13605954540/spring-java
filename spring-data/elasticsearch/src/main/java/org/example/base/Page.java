package org.example.base;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class Page<T> {

    private long current;

    private long size;

    private long total;

    private List<T> records;
}
