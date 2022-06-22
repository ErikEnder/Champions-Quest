package com.erikender.webapp.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * This class acts as a Data Transfer Object for the Form from the Camp page containing any potential character
 * alterations the User may have chosen.
 */
public class FormDto {
    private String heroAN; // Hero Altered Name value
    private String compAN; // Companion Altered Name value
    private String dogAN; // Dog altered name value

    private String heroModel; // Hero's model value
    private String compModel; // Companion's model value

    private List<String> values; // List that will contain the values for easier handling

    public FormDto() {
        // Generates 5 slots in the values list
        values = new ArrayList<String>();
        values.add("");
        values.add("");
        values.add("");
        values.add("");
        values.add("");
    }

    // Constructor sets each value into their appropriate slot
    public FormDto(String heroAN, String compAN, String dogAN, String heroModel, String compModel) {
        values.set(0, heroAN);
        values.set(1, compAN);
        values.set(2, dogAN);
        values.set(3, heroModel);
        values.set(4, compModel);
    }

    // Setters and getters
    public String getHeroAN() {
        return values.get(0);
    }

    public void setHeroAN(String heroAN) {
        values.set(0, heroAN);
    }

    public String getCompAN() {
        return values.get(1);
    }

    public void setCompAN(String compAN) {
        values.set(1, compAN);
    }

    public String getDogAN() {
        return values.get(2);
    }

    public void setDogAN(String dogAN) {
        values.set(2, dogAN);
    }

    public String getHeroModel() {
        return values.get(3);
    }

    public void setHeroModel(String heroModel) {
        values.set(3, heroModel);
    }

    public String getCompModel() {
        return values.get(4);
    }

    public void setCompModel(String compModel) {
        values.set(4, compModel);
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
}
