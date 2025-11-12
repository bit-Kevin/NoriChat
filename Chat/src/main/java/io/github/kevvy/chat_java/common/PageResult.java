package io.github.kevvy.chat_java.common;


import lombok.Data;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通用返回结果模板（可扩展版）
 * 支持普通返回、分页返回、附加信息返回
 */
@Data
public class PageResult<T>  {



    /** 总记录数 */
    private Integer totalCount;

    /** 每页条数 */
    private Integer pageSize;

    /** 当前页码 */
    private Integer pageNo;

    /** 总页数 */
    private Integer pageTotal;

    /** 数据列表 */
    private List<T> list = new ArrayList<>();

    // ==================== 构造方法 ====================

    public PageResult() {}

    public PageResult(Integer totalCount, Integer pageSize, Integer pageNo, List<T> list) {
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.pageNo = pageNo;
        this.list = list;
    }

    public PageResult(Integer totalCount, Integer pageSize, Integer pageNo, Integer pageTotal, List<T> list) {
        if (pageNo == 0) {
            pageNo = 1;
        }
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.pageNo = pageNo;
        this.pageTotal = pageTotal;
        this.list = list;
    }

    public PageResult(List<T> list) {
        this.list = list;
    }}




