package com.ldy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面控制类
 * Created by 80002946 on 2018/5/25.
 */
@Controller
public class indexContorller {

        @RequestMapping("/index")
        public String index() {
            return "index";
        }
        @RequestMapping("/")
        public String addTalk() {
            return "hello";
        }
        @RequestMapping("/text")
        public String addText() {
            return "text";
        }
        @RequestMapping("/upload")
        public String upload() {
            return "upload";
        }
        @RequestMapping("/queryData")
        public String queryData() {
            return "queryData";
        }
}
