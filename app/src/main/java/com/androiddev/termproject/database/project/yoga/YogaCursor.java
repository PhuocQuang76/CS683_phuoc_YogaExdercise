package com.androiddev.termproject.database.project.yoga;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.androiddev.termproject.dto.Yoga;

public class YogaCursor extends CursorWrapper {
    public YogaCursor(Cursor cursor) {
        super(cursor);
    }

    public Yoga getProject() {
        Yoga yoga = new Yoga();
        yoga.setId(getInt(getColumnIndex("_id")));
        yoga.setTitle(getString(getColumnIndex(TableRoutineColumns.TITLE.toString())));
        yoga.setSummary(getString(getColumnIndex(TableRoutineColumns.SUMMARY.toString())));
        yoga.setLinks(getString(getColumnIndex(TableRoutineColumns.LINKS.toString())));
        yoga.setImageId(getInt(getColumnIndex(TableRoutineColumns.IMAGEID.toString())));
        yoga.setTime(getInt(getColumnIndex(TableRoutineColumns.TIME.toString())));
        yoga.setFavorite(getInt(getColumnIndex(TableRoutineColumns.FAVORITE.toString())));
        return yoga;
    }
}
