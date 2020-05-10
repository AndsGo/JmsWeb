package com.ldy.service.impl;

import com.ldy.common.exception.LdyRuntimeException;
import com.ldy.common.util.ExcelUtil;
import com.ldy.mapper.QueryDataMapper;
import com.ldy.service.QueryDataService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

/**
 * 描述：sql查询服务类
 */
@Service
public class QueryDataServiceImpl implements QueryDataService{
    @Autowired
    private QueryDataMapper queryDataMapper;

    @Override
    public int getCount(String sql) {
        return queryDataMapper.getCount(sql);
    }

    @Override
    public Map<String, Object> getData(String sql, int start, int limit) {
        Map<String, Object> resultMap = new HashMap<>();
        List<Map<String, Object>> list = queryDataMapper.getData(sql, start, limit);
        List<String> columns = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            Map<String, Object> item = list.get(0);
            for (Map.Entry<String, Object> entry : item.entrySet()) {
                columns.add(entry.getKey());
            }
        }
        resultMap.put("datas", list);
        resultMap.put("columns", columns);
        return resultMap;
    }

    @Override
    public void export(String sql, int start, int limit, HttpServletResponse response) throws LdyRuntimeException {
        List<Map<String, Object>> data = queryDataMapper.getData(sql, start, limit);
        if (CollectionUtils.isEmpty(data)) {
            throw new LdyRuntimeException("无数据导出！");
        }
        String tableName = "[TABLE]";
        OutputStream out = null;
        try {
            String s = sql.substring(sql.indexOf("from") + 4).trim();
            int i = s.indexOf(",");
            int k = s.indexOf(" ");
            int max = i > k ? k < 0 ? i : k : i < 0 ? k : i;
            if (max > 0) {
                s = s.substring(0, max);
            }
            tableName = s;

            List<ExcelUtil.ExcelColumn> columns = new ArrayList<>();
            Map<String, Object> item = data.get(0);
            for (String key : item.keySet()) {
                columns.add(new ExcelUtil.ExcelColumn(key, key));
            }

            HSSFWorkbook hssfWorkbook = ExcelUtil.getHSSFWorkbook(tableName, columns, data, null);
            response.setContentType("application/ms-excel;charset=UTF-8");
            String fileName = UUID.randomUUID() + ".xls";
            response.setHeader("Content-Disposition", "attachment;filename=" + String.valueOf(URLEncoder.encode(fileName, "UTF-8")));
            out = response.getOutputStream();
            hssfWorkbook.write(out);
        } catch (Exception e) {

        }
    }
}
