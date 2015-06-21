package com.blog.model;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.FileHandler;

/**
 * Created by menaka on 5/8/15.
 */
public class JSONModel {
    private JsonObject job;
    private JsonObject j_obj;
//    private String file_Dir = "../webapps/bLoG/posts/";
//    private String file_Dir_win = "..\\webapps\\bLoG\\posts\\";
    private String sep = File.separator;
    private String file_Dir = ".."+ sep +"webapps"+ sep +"bLoG"+ sep + "posts" + sep;

    /**
     * JSON object model
     * {
     * "id" : _generated id, -> System.cuttentTimeMills();
     * "title" : title
     * "content" : blog_content,
     * "No of hits" : int
     * "No of comments": int
     * "comments" :
     *  [
     *      {
     *          "id" : System.cuttentTimeMills();
     *          "name" : viewer name,
     *          "comment" : comment
     *          "Authed" : true/false
     *      }
     *  ]
     * }
     */

    private BlogModel post;
    private LinkedList<File> json_File_List = new LinkedList<File>();
    private Map<String, JsonObject> jmap = new HashMap<String, JsonObject>();
    private LinkedList<String> list = new LinkedList<String>();


    //Constructor to store a json file
    public JSONModel(BlogModel post) {
        this.post = post;
    }

    //Constructor to read json files
    public JSONModel() {

    }

    //Create a json object according to the given data.
    public void jsonParser() {
        this.job = new JsonObject()
                .add("id", post.getBlog_Id())
                .add("name", post.getUser_Name())
                .add("title", post.getBlog_Title())
                .add("Date", post.getCreated_Date())
                .add("content", post.getBlog_Content())
                .add("hits", 0)
                .add("comts", 0)
                .add("comments", new JsonArray().add(new JsonObject().add("name", "initial comment")));
    }

    public String toString() {
        return job.toString();
    }

    //Create a new json file : when adding a new post.
    public void createJSONFile() {
        File newJSONFile = new File(this.file_Dir + System.currentTimeMillis() + ".json");
        newJSONFile.getParentFile().mkdirs();
        try {
            FileWriter fw = new FileWriter(newJSONFile);
            BufferedWriter bfrd = new BufferedWriter(fw);
            job.writeTo(bfrd);
            System.out.println("file " + this.file_Dir + System.currentTimeMillis() + ".json is created");
            bfrd.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Create a json file according to the given object: For edits and comments
    public void createJSONFile(String filename, JsonObject obj) {
        File file = new File(this.file_Dir + filename + ".json");
        file.getParentFile().mkdirs();
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bfrd = new BufferedWriter(fw);
            obj.writeTo(bfrd);
            System.out.println("file " + this.file_Dir + filename + ".json is created");
            bfrd.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//----------------READING THE FILES------------------------------------------------------------------

    public void deleteFile(String filename) {
        Path path = Paths.get(this.file_Dir + filename);
        try {
            Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Read all the files in the posts directory and add them to a hash map.
    public boolean read() {
        System.out.println(file_Dir);
        File f_dir = new File(this.file_Dir);
        try {
            if (f_dir.listFiles().length == 0) return false;

            for (File f : f_dir.listFiles()) {
                this.json_File_List.add(f);
                this.jmap.put(f.getName(), readFromJSON(f.getName()));
            }
            Collections.sort(this.json_File_List);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return true;
    }


    public LinkedList<String> latestTen() {
        if (!this.read()) {
            return this.list;
        }
        String s = "";
        for (int i = 0; i < json_File_List.size(); i++) {
            String fileName = this.json_File_List.get(json_File_List.size() - 1 - i).getName();
            JsonObject job = this.jmap.get(fileName);
            String id = job.get("id").toString().replaceAll("\"", "");
            String title = job.get("title").asString();
            String content = job.get("content").asString();
            if (content.length() > 100)
                content = content.substring(0, 100) + "...<br>";
            s = "<li class=\"list-group-item\">"
                    + "<a method=\"post\" onclick=\"showContent(" + id + ")\" href=\"/bLoG/ReadJSON?id="
                    + fileName.replaceAll(".json", "") + "\"" + ">"
                    + "<h4>" + title + "</h4>"
                    + "</a>"
                    + "<span id=\"content\"><p>" + content + "</p></span>"
                    + "</li> </br>";
            this.list.add(s);
        }
        return this.list;
    }

    // Read json file in the directory and return the json data as an object.

    public JsonObject readFromJSON(String fileName) {

        File jsonFile = new File(file_Dir + fileName);
        try {
            FileReader j_fread = new FileReader(jsonFile);
            BufferedReader j_Bfread = new BufferedReader(j_fread);
            this.j_obj = JsonObject.readFrom(j_Bfread);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return this.j_obj;
    }

    //return the hash map
    public Map<String, JsonObject> getMap(){
        return this.jmap;
    }
}
