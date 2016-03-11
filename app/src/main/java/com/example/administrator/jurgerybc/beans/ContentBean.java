package com.example.administrator.jurgerybc.beans;

import java.util.List;

/**
 * Created by Administrator on 2016/3/8.
 */
public class ContentBean {

    /**
     * code : 200
     * message : 数据获取成功
     * data : [{"ID":"1","post_title":"世界，您好！","post_date":"2016-03-08 10:52:44","guid":"http://ftp490528.host551.zhujiwu.cn/?p=1","imglink":"","post_excerpt":"欢迎使用WordPress。这是您的第一篇文章。编辑或删除它，然后开始写作吧！","post_link":"http://m.amtxts.com/?p=1","view":0,"term_name":"未分类"},{"ID":"2","post_title":"示例页面","post_date":"2016-03-08 10:52:44","guid":"http://ftp490528.host551.zhujiwu.cn/?page_id=2","imglink":"","post_excerpt":"这是一个范例页面。它和博客文章不同，因为它的页面位置是固定的，同时会显示于您的博客导航栏（大多数主题中）。大多数人会新增一个\u201c关于\u201d页面向访客介绍自己。它可能类似下面这样：我是一个很有趣的人，我创建了","post_link":"http://m.amtxts.com/?p=2","view":0,"term_name":""}]
     */

    private int code;
    private String message;
    /**
     * ID : 1
     * post_title : 世界，您好！
     * post_date : 2016-03-08 10:52:44
     * guid : http://ftp490528.host551.zhujiwu.cn/?p=1
     * imglink :
     * post_excerpt : 欢迎使用WordPress。这是您的第一篇文章。编辑或删除它，然后开始写作吧！
     * post_link : http://m.amtxts.com/?p=1
     * view : 0
     * term_name : 未分类
     */

    private List<DataEntity> data;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private String ID;
        private String post_title;
        private String post_date;
        private String guid;
        private String imglink;
        private String post_excerpt;
        private String post_link;
        private int view;
        private String term_name;

        public void setID(String ID) {
            this.ID = ID;
        }

        public void setPost_title(String post_title) {
            this.post_title = post_title;
        }

        public void setPost_date(String post_date) {
            this.post_date = post_date;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }

        public void setImglink(String imglink) {
            this.imglink = imglink;
        }

        public void setPost_excerpt(String post_excerpt) {
            this.post_excerpt = post_excerpt;
        }

        public void setPost_link(String post_link) {
            this.post_link = post_link;
        }

        public void setView(int view) {
            this.view = view;
        }

        public void setTerm_name(String term_name) {
            this.term_name = term_name;
        }

        public String getID() {
            return ID;
        }

        public String getPost_title() {
            return post_title;
        }

        public String getPost_date() {
            return post_date;
        }

        public String getGuid() {
            return guid;
        }

        public String getImglink() {
            return imglink;
        }

        public String getPost_excerpt() {
            return post_excerpt;
        }

        public String getPost_link() {
            return post_link;
        }

        public int getView() {
            return view;
        }

        public String getTerm_name() {
            return term_name;
        }
    }
}
