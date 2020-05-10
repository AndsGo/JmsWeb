package com.ldy.entity;

import java.io.Serializable;

/**
 * 文章类
 * Created by 80002946 on 2018/6/3.
 */

public class TextBookEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    //文章名称
    private String textName;
    //所属类型
    private String textType;
    //文章内容
    private String textContent;
    //使用次数
    private Long usageCount;
    public TextBookEntity(){
        super();
    }

    public TextBookEntity(String textName, String textType, String textContent) {
        super();
        this.textName = textName;
        this.textType = textType;
        this.textContent = textContent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTextName() {
        return textName.replace("\r\n","");
    }

    public void setTextName(String textName) {
        this.textName = textName.replace("\r\n","");
    }

    public String getTextType() {
        return textType;
    }

    public void setTextType(String textType) {
        this.textType = textType;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public Long getUsageCount() {
        return usageCount;
    }

    public void setUsageCount(Long usageCount) {
        this.usageCount = usageCount;
    }

    @Override
    public String toString() {
        return "TextBookEntity{" +
                "id=" + id +
                ", textName='" + textName + '\'' +
                ", textType='" + textType + '\'' +
                ", textContent='" + textContent + '\'' +
                ", usageCount=" + usageCount +
                '}';
    }
}