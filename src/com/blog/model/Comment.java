package com.blog.model;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;

import java.util.Random;

/**
 * Created by menaka on 5/9/15.
 */
public class Comment extends JSONModel {
    private String comment;
    private String name;
    private String commentId;
    private boolean author = false;

    //Otherwise
    public Comment(String comment, String name) {
        this.comment = comment;
        this.name = name;
        this.commentId = System.nanoTime() + "";
    }

    public void createComment(String fileName) {
        JsonObject obj = super.readFromJSON(fileName + ".json");
        System.out.println("Before ----> " + obj);
        JsonObject obj2 = new JsonObject();
        obj2.add("Com_Id", this.commentId).add("Name", this.name).add("content", this.comment).add("Authed", this.author);

        JsonArray jarray = obj.get("comments").asArray();
        jarray.add(obj2);

//        jarray.add(obj2);
        obj.set("comments", jarray).set("comts", (obj.get("comts").asInt()+1));
        System.out.println("Now---->" + obj);
        super.createJSONFile(fileName, obj);
    }

    public void read_comments() {

    }

}
