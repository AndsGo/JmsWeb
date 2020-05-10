package com.ldy.mapper;


import com.ldy.entity.TalkEntity;
import com.ldy.entity.TextBookEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * 文章Mapper
 * Created by 80002946 on 2018/6/3.
 */
@Mapper
public interface TextMapper {
    @Select("SELECT * FROM textbook WHERE  text_name= #{textName}")
    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "textName",  column = "text_name"),
            @Result(property = "textContent", column = "text_content"),
            @Result(property = "textType",  column = "text_type"),
            @Result(property = "usageCount",  column = "usage_count")
    })
    TextBookEntity getOneText(String textName);

    @Insert("INSERT INTO textbook(text_name,text_type,text_content,usage_count) VALUES(#{textName}, #{textType}, #{textContent},0)")
    void insert(TextBookEntity text);

    @Update("UPDATE textbook SET usage_count= usage_count+1 WHERE id=#{id}")
    void update(Long id);

    @Select("SELECT count(*) FROM textbook WHERE text_name = #{textName}")
    long getCount(String textName);
    @Select("SELECT count(*) FROM textbook WHERE id = #{id}")
    long getCountById(Long id);

    @Select("SELECT * FROM textbook WHERE id = #{id}")
    @Results({
            @Result(property = "textName",  column = "text_name"),
            @Result(property = "textContent", column = "text_content")
    })
    TextBookEntity getOneTextById(Long id);

    @Select("SELECT * FROM textbook order by text_type")
    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "textName",  column = "text_name"),
            @Result(property = "textContent", column = "text_content"),
            @Result(property = "textType",  column = "text_type"),
            @Result(property = "usageCount",  column = "usage_count")
    })
    List<TextBookEntity> getOneTextList();
}
