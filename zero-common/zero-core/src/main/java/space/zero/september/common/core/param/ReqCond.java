package space.zero.september.common.core.param;

import java.util.Map;

/**
 * @author : penggs
 * @program : september
 * @description : 分页请求条件
 * @create : 2019-07-21 22:04
 */
public class ReqCond {
    /**
     * 页大小
     */
    public int size;
    /**
     * 页码
     */
    public int page;
    /**
     * 排序字段
     */
    public String sort;
    /**
     * 查询条件
     */
    public Map<String, Object> filter;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Map<String, Object> getFilter() {
        return filter;
    }

    public void setFilter(Map<String, Object> filter) {
        this.filter = filter;
    }
}