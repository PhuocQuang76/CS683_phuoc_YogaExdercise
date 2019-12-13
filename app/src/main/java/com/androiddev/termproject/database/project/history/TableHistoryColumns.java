package com.androiddev.termproject.database.project.history;

public enum TableHistoryColumns {

    ROUTINE_ID("INTEGER", false),
    TIME("INTEGER", false),
    DURATION("INTEGER", false);


    private String dataType;
    private boolean allowNull;

    TableHistoryColumns(String dataType, boolean allowNull) {
        this.dataType = dataType;
        this.allowNull = allowNull;
    }

    public String getColumnDefinition() {
        return this.toString() + ' ' + this.dataType + (this.allowNull?"": " NOT NULL");
    }
}
