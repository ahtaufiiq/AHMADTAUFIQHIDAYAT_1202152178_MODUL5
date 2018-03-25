package com.example.ataufiq.ahmad_taufiq_hidayat_1202152178_modul5;

/**
 * Created by ataufiq on 25/03/18.
 */

public class ListItem {
    private String todo, desc, prior;


    public ListItem(String todo, String desc, String prior) {
        this.todo = todo;
        this.desc = desc;
        this.prior = prior;
    }


    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrior() {
        return prior;
    }

    public void setPrior(String prior) {
        this.prior = prior;
    }
}
