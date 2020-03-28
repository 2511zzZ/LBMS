package com.zzz.model;

import com.zzz.result.ResponseCode;

public enum Level {
    TOTAL(1,"total"),
    BRANCH(2, "branch"),
    GROUP(3, "group"),
    TEAM(4, "team"),
    ANCHOR(5,"anchor");

    private int levelCode;
    private String level;

    Level(Integer levelCode, String level) {
        this.levelCode = levelCode;
        this.level = level;
    }

    public Integer getCode() {
        return levelCode;
    }

    public void setCode(Integer levelCode) {
        this.levelCode = levelCode;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

}
