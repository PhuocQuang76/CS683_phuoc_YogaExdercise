package com.androiddev.termproject.database.project.favorite;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.androiddev.termproject.database.project.yoga.TableRoutineColumns;
import com.androiddev.termproject.dto.Favorite;

public class FavoriteCursor extends CursorWrapper {
    public FavoriteCursor(Cursor cursor) {
        super(cursor);
    }

    public Favorite getFavorite() {
        Favorite favorite = new Favorite();
        favorite.setId(getInt(getColumnIndex("_id")));
        favorite.setTitle(getString(getColumnIndex(TableRoutineColumns.TITLE.toString())));
        favorite.setSummary(getString(getColumnIndex(TableRoutineColumns.SUMMARY.toString())));
        favorite.setImageId(getInt(getColumnIndex(TableRoutineColumns.IMAGEID.toString())));
        favorite.setLinks(getString(getColumnIndex(TableRoutineColumns.LINKS.toString())));
        favorite.setRoutineId(getInt(getColumnIndex(TableFavoriteColumns.ROUTINE_ID.toString())));

        favorite.setTime(getInt(getColumnIndex(TableFavoriteColumns.TIME.toString())));

        return favorite;
    }
}
