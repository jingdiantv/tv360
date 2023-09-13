package com.example.myapplication.Model;

import java.util.List;

public class HomeModel {
    private  String id;
    private  String name;
    private  String type;
    private List<Banner> content;

    public HomeModel(String id, String name, String type, List<Banner> content) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Banner> getContent() {
        return content;
    }

    public void setContent(List<Banner> content) {
        this.content = content;
    }
}
