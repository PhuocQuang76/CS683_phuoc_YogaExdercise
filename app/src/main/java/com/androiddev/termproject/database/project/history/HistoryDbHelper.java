package com.androiddev.termproject.database.project.history;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.androiddev.termproject.database.DBHelper;
import com.androiddev.termproject.database.project.favorite.FavoriteCursor;
import com.androiddev.termproject.database.project.yoga.TableRoutineColumns;
import com.androiddev.termproject.database.project.yoga.YogaCursor;
import com.androiddev.termproject.dto.Favorite;
import com.androiddev.termproject.dto.History;

import java.sql.SQLData;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoryDbHelper {
    private static HistoryDbHelper sHistoryDbHelper;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    //When object of HistoryDbHelper created, will block everything
    public static synchronized HistoryDbHelper get(Context context){
        if(sHistoryDbHelper == null){
            sHistoryDbHelper = new HistoryDbHelper(context);
        }
        return sHistoryDbHelper;
    }


    private HistoryCursor queryHistoryRoutines(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query("HISTORY",
                null,
                whereClause,
                whereArgs,
                null,
                null,
                "_id");
        return new HistoryCursor(cursor);
    }


    private HistoryDbHelper(Context context){
        mContext = context;
        mDatabase = new DBHelper(mContext).getWritableDatabase();

    }


    public void addRoutineToHistory(Integer routineId, Integer duration){
        History h = new History();
        h.setRoutineId(routineId);
        h.setDuration(duration);
        h.setStartDate(new Date());
        this.addRoutineToHistory(h);

    }

    public void addRoutineToHistory(History history) {
        long count = mDatabase.insert("HISTORY", null, getContentValues(history));
        if (count == -1) {
            System.out.println(count);
        }
    }

    public List<History> getHistories(Integer routineId) {
        List<History> histories = new ArrayList<>();
        HistoryCursor cursor = queryHistoryRoutines("ROUTINE_ID = ?", new String[]{routineId.toString()});
        //HistoryCursor cursor = queryHistoryRoutines(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                histories.add(cursor.getHistory());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return histories;
    }

    public static ContentValues getContentValues(History history) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TableHistoryColumns.ROUTINE_ID.toString(), history.getRoutineId());
        contentValues.put(TableHistoryColumns.TIME.toString(), history.getTime());
        contentValues.put(TableHistoryColumns.DURATION.toString(), history.getDuration());
        return contentValues;
    }

}
