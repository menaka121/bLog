package com.blog.model;

import java.util.Date;
import java.util.Random;

/**
 * Created by menaka on 5/8/15.
 */
public class BlogModel {
    private String user_Name;
    private String blog_Title;
    private String blog_Content;
    private String blog_Id;
    private String created_Date;

    public BlogModel(String user_Name, String blog_Title, String blog_Content) {
        this.user_Name = user_Name;
        this.blog_Title = blog_Title;
        this.blog_Content = blog_Content;
        this.created_Date = System.currentTimeMillis()+"";
        this.blog_Id = this.created_Date;
    }

    public String getBlog_Title() {
        return blog_Title;
    }

    public String getBlog_Content() {
        return blog_Content;
    }

    public String getCreated_Date() {
        return created_Date;
    }

    public String getBlog_Id() {
        return blog_Id;
    }

    public String getUser_Name() {
        return user_Name;
    }
}
