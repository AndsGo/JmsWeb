# LDY

#### 项目介绍
懒的鱼，语音交互

#### 软件架构
软件架构说明


#### 安装教程

1. xxxx
2. xxxx
3. xxxx

#### 使用说明

1. xxxx
2. xxxx
3. xxxx

#### 参与贡献

1. Fork 本项目
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request


#### 码云特技

1. 使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2. 码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3. 你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4. [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5. 码云官方提供的使用手册 [http://git.mydoc.io/](http://git.mydoc.io/)
6. 码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)

####SQL
--创建表
CREATE TABLE public.talks
(
   id SERIAL NOT NULL ,
  key_word character(255) ,
  input_talk character(255) ,
  word_type character(20) ,
  out_talk character(255) ,
  usage_count bigint,
  CONSTRAINT talks_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.talks
  OWNER TO postgres;
COMMENT ON TABLE public.talks
  IS '对话表';
  --插入数据
INSERT INTO talks (key_word, input_talk, word_type,out_talk,usage_count)
VALUES ('hello', 'hello', 'hello','hello',1);

--查询数据
select * from talks;

--创建表
CREATE TABLE public.users
(
   id SERIAL NOT NULL ,
  userName character(50) ,
  passWord character(50) ,
  user_sex character(5) ,
  nick_name character(50) ,
  CONSTRAINT users_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.users
  OWNER TO postgres;
COMMENT ON TABLE public.users
IS '用户表';

CREATE TABLE textbook
(
  id           SERIAL NOT NULL
    CONSTRAINT textbook_pkey
    PRIMARY KEY,
  text_name    CHAR(50),
  text_type    CHAR(20),
  usage_count  BIGINT,
  text_content TEXT
);
COMMENT ON TABLE textbook IS '课文表';