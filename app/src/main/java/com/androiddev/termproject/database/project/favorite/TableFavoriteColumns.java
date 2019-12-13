package com.androiddev.termproject.database.project.favorite;

public enum TableFavoriteColumns {
    ROUTINE_ID("INTEGER", false),
    TIME("INTEGER", false);

    private String dataType;
    private boolean allowNull;

    TableFavoriteColumns(String dataType, boolean allowNull) {
        this.dataType = dataType;
        this.allowNull = allowNull;
    }

    public String getColumnDefinition() {
        return this.toString() + ' ' + this.dataType + (this.allowNull?"": " NOT NULL");
    }
}
