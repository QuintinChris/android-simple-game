package com.example.project4;

import android.app.Application;

public class MyApplication extends Application {
    private String p1Selection = "";
    private String p2Selection = "";

    public String getP1Selection(){
        return p1Selection;
    }

    public void setP1Selection(String string){
        this.p1Selection = string;
    }

    public String getP2Selection(){
        return p2Selection;
    }

    public void setP2Selection(String string){
        this.p2Selection = string;
    }
}
