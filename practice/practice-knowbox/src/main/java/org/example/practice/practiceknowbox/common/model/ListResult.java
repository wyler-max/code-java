package org.example.practice.practiceknowbox.common.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.springframework.cglib.beans.BeanCopier;

import com.github.pagehelper.Page;
import com.google.common.collect.Lists;

import lombok.Data;

/**
 * 分页结果
 *
 * @author yijiu.chen
 * @date 2020/04/16
 */
@Data
public class ListResult<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private int pageNum;

    private int pages;

    private int pageSize;

    private long total;

    private List<T> list;

    public static <T> ListResult<T> emptyPage(org.example.practice.practiceknowbox.common.model.PageParam page) {
        ListResult<T> result = new ListResult<>();
        result.setPageNum(page.getPageNum());
        result.setPageSize(page.getPageSize());
        result.setTotal(0);
        result.setPages(0);
        result.setList(Collections.emptyList());
        return result;
    }

    public static <T> ListResult<T> fromPage(Page<T> page) {
        ListResult<T> result = new ListResult<>();
        result.setPageNum(page.getPageNum());
        result.setPageSize(page.getPageSize());
        result.setTotal(page.getTotal());
        result.setPages(page.getPages());
        result.setList(page);
        return result;
    }

    public static <S, D> ListResult<D> fromPage(Page<S> page, BeanCopier copy, Class<D> cls) {
        ListResult<D> result = new ListResult<>();
        result.setPageNum(page.getPageNum());
        result.setPageSize(page.getPageSize());
        result.setTotal(page.getTotal());
        result.setPages(page.getPages());
        List<D> items = Lists.newArrayListWithCapacity(page.size());
        page.forEach(item -> {
            try {
                D d = cls.getDeclaredConstructor().newInstance();
                copy.copy(item, d, null);
                items.add(d);
            } catch (Exception e) {
                // do nothing
            }
        });
        result.setList(items);
        return result;
    }

    public static <S, D> ListResult<D> fromListResult(ListResult<S> origin, List<D> result) {
        ListResult<D> pageResult = new ListResult<>();
        pageResult.setList(result);
        pageResult.setPageNum(origin.getPageNum());
        pageResult.setPageSize(origin.getPageSize());
        pageResult.setTotal(origin.getTotal());
        pageResult.setPages(origin.getPages());
        return pageResult;
    }
}
