package com.padeltrophy.entity;

/**
 * Created by JLRB002 on 17/11/2015.
 */
public class Category {

    private static String CATEGORY_DESC_1 = "Profesional";
    private static String CATEGORY_DESC_2 = "Semiprofesional";
    private static String CATEGORY_DESC_3 = "Avanzado";
    private static String CATEGORY_DESC_4 = "Amateur";
    private static String CATEGORY_DESC_5 = "Iniciación";

    private String level;
    private String sublevel;
    private String gender;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSublevel() {
        return sublevel;
    }

    public void setSublevel(String sublevel) {
        this.sublevel = sublevel;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDescription(){
        switch(this.level){
            case "1":
                return CATEGORY_DESC_1;
            case "2":
                return CATEGORY_DESC_2;
            case "3":
                return CATEGORY_DESC_3;
            case "4":
                return CATEGORY_DESC_4;
            case "5":
                return CATEGORY_DESC_5;
            default:
                return null;
        }
    }
}
