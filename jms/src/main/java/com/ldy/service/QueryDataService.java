package com.ldy.service;

import com.ldy.common.exception.LdyRuntimeException;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 描述：sql查询服务类接口
 */
public interface QueryDataService {
    int getCount(String sql);

    Map<String, Object> getData(String sql, int start, int limit);

    void export(String sql, int start, int limit, HttpServletResponse response) throws LdyRuntimeException;
}
