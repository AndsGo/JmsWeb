package com.ldy.mapper;


import com.ldy.entity.TalkEntity;
import org.apache.ibatis.annotations.*;


/**
 * Created by 80002946 on 2018/6/3.
 */
@Mapper
public interface TalkMapper {
    @Select("SELECT * FROM talks WHERE input_talk = #{inputTalk}")
    @Results({
            @Result(property = "inputTalk",  column = "input_talk"),
            @Result(property = "outTalk", column = "out_talk")
    })
    TalkEntity getOneTalk(String inputTalk);

    @Insert("INSERT INTO talks(key_word,input_talk,word_type,out_talk,usage_count) VALUES(#{keyWord}, #{inputTalk}, #{wordType}, #{outTalk},0)")
    void insert(TalkEntity talk);

    @Update("UPDATE talks SET usage_count= usage_count+1 WHERE id=#{id}")
    void update(Long id);
    @Select("SELECT count(*) FROM talks WHERE input_talk = #{inputTalk}")
    long getCount(String inputTalk);
}
