package org.example.base;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Accessors(chain = true)
public class Page<T>  {

    private Integer current;

    private Integer size;

    private long total;

    private List<T> records;

    public static <T>Page<T> of(SearchHits<T> searchHits, Page page) {
        List<T> records = searchHits.getSearchHits()
                .stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
        return new Page<T>()
                .setCurrent(page.getCurrent())
                .setSize(page.getSize())
                .setTotal(searchHits.getTotalHits())
                .setRecords(records);
    }
}
