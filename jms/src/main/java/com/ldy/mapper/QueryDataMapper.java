package com.ldy.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 描述：sql查询mapper
 */
@Mapper
public interface QueryDataMapper {
    @Select("SELECT COUNT(1) FROM (${sql}) tb")
    int getCount(@Param(value = "sql") String sql);
    @Select("SELECT * FROM (${sql}) tb LIMIT ${start},${limit}")
    List<Map<String, Object>> getData(@Param(value = "sql") String sql, @Param(value = "start") int start,
                                      @Param(value = "limit") int limit);
}
