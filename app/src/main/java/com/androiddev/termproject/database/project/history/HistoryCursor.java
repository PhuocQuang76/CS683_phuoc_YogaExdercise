package com.androiddev.termproject.database.project.history;

import android.database.Cursor;
import android.database.CursorWrapper;


import com.androiddev.termproject.database.project.yoga.TableRoutineColumns;
import com.androiddev.termproject.dto.History;

public class HistoryCursor extends CursorWrapper {

    public HistoryCursor(Cursor cursor){
        super(cursor);

    }

    public History getHistory(){
        History history = new History();
        history.setId(getInt(getColumnIndex("_id")));
        history.setDuration(getInt(getColumnIndex(TableHistoryColumns.DURATION.toString())));
        history.setRoutineId(getInt(getColumnIndex(TableHistoryColumns.ROUTINE_ID.toString())));
        history.setTime(getLong(getColumnIndex(TableHistoryColumns.TIME.toString())));






        return history;
    }
}
