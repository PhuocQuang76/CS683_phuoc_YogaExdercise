package com.androiddev.termproject.database.project.yoga;

public enum TableRoutineColumns {
    TITLE("TEXT", true),
    SUMMARY("TEXT", true),
    LINKS("TEXT", true),
    IMAGEID("INTEGER",false),
    TIME("INTEGER", false),
    FAVORITE("INTEGER", false);


    private String dataType;
    private boolean allowNull;

    TableRoutineColumns(String dataType, boolean allowNull) {
        this.dataType = dataType;
        this.allowNull = allowNull;
    }

    public String getColumnDefinition() {
        return this.toString() + ' ' + this.dataType + (this.allowNull?"": " NOT NULL");
    }
}
