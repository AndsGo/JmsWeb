package com.ldy.entity;

import java.io.Serializable;

/**
 * Created by 80002946 on 2018/6/3.
 */

public class TalkEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    public TalkEntity() {
        super();
    }
    public TalkEntity(String keyWord, String wordType, String inputTalk, String outTalk) {
        super();
        this.keyWord = keyWord;
        this.wordType = wordType;
        this.outTalk = outTalk;
        this.inputTalk= inputTalk;
    }
    //关键字
    private String keyWord;
    //对话输入
    private String inputTalk;
    //所属类型
    private String wordType;
    //输出对话
    private String outTalk;
    //使用次数
    private Long usageCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getInputTalk() {
        return inputTalk;
    }

    public void setInputTalk(String inputTalk) {
        this.inputTalk = inputTalk;
    }
    public String getWordType() {
        return wordType;
    }

    public void setWordType(String wordType) {
        this.wordType = wordType;
    }

    public String getOutTalk() {
        return outTalk;
    }

    public void setOutTalk(String outTalk) {
        this.outTalk = outTalk;
    }

    public Long getUsageCount() {
        return usageCount;
    }

    public void setUsageCount(Long usageCount) {
        this.usageCount = usageCount;
    }



    @Override

    public String toString() {
        return "TalkEntity{" +
                "id=" + id +
                ", keyWord='" + keyWord + '\'' +
                ", wordType='" + wordType + '\'' +
                ", outTalk='" + outTalk + '\'' +
                ", usageCount=" + usageCount +
                '}';
    }
}