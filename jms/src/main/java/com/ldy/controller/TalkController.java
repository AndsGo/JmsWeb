package com.ldy.controller;

import com.ldy.entity.TalkEntity;
import com.ldy.mapper.TalkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Created by 80002946 on 2018/6/3.
 */
@RestController(value = "/talk")
public class TalkController {

    @RequestMapping(value = "/helloTalk",method = RequestMethod.GET)
    @ResponseBody
    public String talk(){
        return  "hello talk";
    }
    @Autowired
    private TalkMapper talkMapper;

    @RequestMapping(value="/getOneTalk/{inputTalk}")
    @ResponseBody
    public String getOneTalk(@PathVariable("inputTalk") String inputTalk) {
        long count = talkMapper.getCount(inputTalk);
        if(count>0){
            TalkEntity oneTalk = talkMapper.getOneTalk(inputTalk);
            talkMapper.update(oneTalk.getId());
            return oneTalk.getOutTalk();
        }
        return "";
    }

    @RequestMapping("/addTalk")
    public void save(TalkEntity talk) {
        System.out.println(talk);
        talkMapper.insert(talk);
    }
}
