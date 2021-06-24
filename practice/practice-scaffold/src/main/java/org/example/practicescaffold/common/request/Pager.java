package org.example.practicescaffold.common.request;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pager implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final int DEFAULT_PAGE_NUM = 1;
    public static final int DEFAULT_PAGE_SIZE = 20;

    private int pageSize;
    private int pageNum;
    private int count;

    public static Pager defaultPager() {
        return defaultPager(DEFAULT_PAGE_SIZE, DEFAULT_PAGE_NUM);
    }

    public static Pager defaultPager(int pageSize, int pageNum) {
        Pager pager = new Pager();
        pager.pageSize = pageSize;
        pager.pageNum = pageNum;
        return pager;
    }
}
