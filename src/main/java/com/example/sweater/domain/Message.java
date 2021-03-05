package com.example.sweater.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Message {
    @Id // говорит о том, что поле id будет идентификатором,
        // иден-р нам нужен для того, чтобы различать записи в табличке
    @GeneratedValue(strategy = GenerationType.AUTO) // говорит о том, чтобы база данных и фреймфорк "сами разобрались"
                                                    // в каком виде и порядке будут формироваться эти иден-ры
    private Integer id;

    private String text;
    private String tag;

    public Message(String text, String tag) {
        this.text = text;
        this.tag = tag;
    }

    public Message() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}