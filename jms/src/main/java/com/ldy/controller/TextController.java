package com.ldy.controller;

import com.ldy.entity.TextBookEntity;
import com.ldy.mapper.TextMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 文章控制类
 * Created by 80002946 on 2018/6/3.
 */
@RestController
public class TextController {

    @Autowired
    private TextMapper textMapper;

    /**
     * 根据名称获取文章
     * @param textName
     * @return
     */
    @RequestMapping(value="/getOneText/{textName}")
    @ResponseBody
    public String getOneTalk(@PathVariable("textName") String textName) {
        long count = textMapper.getCount(textName);
        if(count>0){
            TextBookEntity  textBookEntity= textMapper.getOneText(textName);
            textMapper.update(textBookEntity.getId());
            return textBookEntity.getTextContent();
        }
        return "";
    }

    /**
     * 增加文章
     * @param text
     */
    @RequestMapping("/addText")
    public void save(TextBookEntity text) {
        System.out.println(text);
        textMapper.insert(text);
    }

    /**
     * 根据id获取文章
     * @param id
     * @return
     */
    @RequestMapping(value="/getOneTextById/{id}")
    @ResponseBody
    public String getOneTalk(@PathVariable("id") Long id) {
        long count = textMapper.getCountById(id);
        if(count>0){
            TextBookEntity  textBookEntity= textMapper.getOneTextById(id);
            textMapper.update(textBookEntity.getId());
            return textBookEntity.getTextContent();
        }
        return "";
    }

    /**
     * 获取全部文章
     * @return
     */
    @RequestMapping(value="/getTextList")
    @ResponseBody
    public Map<String,List<TextBookEntity>> getOneTextList() {
        List<TextBookEntity> textList=textMapper.getOneTextList();
        //进行分类
        Map<String,List<TextBookEntity>> result=new HashMap<>();
        for(int i=0;i<textList.size();i++){
            TextBookEntity text = textList.get(i);
            if(result.containsKey(text.getTextType())){
                result.get(text.getTextType()).add(text);
            }else {
                result.put(text.getTextType(),new ArrayList<>());
                result.get(text.getTextType()).add(text);
            }
        }
        return result;
    }
}
