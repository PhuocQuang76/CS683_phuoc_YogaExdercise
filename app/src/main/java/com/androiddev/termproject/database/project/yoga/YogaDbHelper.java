package com.androiddev.termproject.database.project.yoga;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.androiddev.termproject.database.DBHelper;
import com.androiddev.termproject.dto.Yoga;

import java.util.ArrayList;
import java.util.List;

public class YogaDbHelper {
    private static YogaDbHelper sYogaDbHelper;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static synchronized YogaDbHelper get(Context context) {
        if (sYogaDbHelper == null) {
            sYogaDbHelper = new YogaDbHelper(context);
        }
        return sYogaDbHelper;
    }

    private YogaDbHelper(Context context) {
        mContext = context;
        mDatabase = new DBHelper(mContext).getWritableDatabase();
    }

    private YogaCursor queryYogaRoutines(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                "YOGA",
                null,
                whereClause,
                whereArgs,
                null,
                null,
                TableRoutineColumns.TITLE.toString()
        );
        return new YogaCursor(cursor);
    }

    //Get the whole yoga class in the app
    public List<Yoga> getYogaRoutine() {
        List<Yoga> yogas = new ArrayList<>();
        YogaCursor cursor = queryYogaRoutines(null, null); //Return all
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                yogas.add(cursor.getProject());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return yogas;
    }

    //Get only the accessed yoga class
    public Yoga getYogaRoutine(Integer id) {
        YogaCursor cursor = queryYogaRoutines("_id = ?", new String[]{id.toString()});
        try {
            if (cursor.getCount() == 0) {
                return null;
            } else {
                cursor.moveToFirst();
                return cursor.getProject();
            }
        } finally {
            cursor.close();
        }
    }

    //Update data in database
    public void updateYogaRoutine(Yoga yoga) {
        int id = yoga.getId();
        ContentValues values = getContentValues(yoga);
        mDatabase.update("YOGA", values,
                "_id = ?",
                new String[]{new Integer(yoga.getId()).toString()});
    }

    //Put value into contentValue
    public static ContentValues getContentValues(Yoga yoga) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", yoga.getId());
        contentValues.put(TableRoutineColumns.TITLE.toString(), yoga.getTitle());
        contentValues.put(TableRoutineColumns.SUMMARY.toString(), yoga.getSummary());
        contentValues.put(TableRoutineColumns.LINKS.toString(), yoga.getLinks());
        contentValues.put(TableRoutineColumns.IMAGEID.toString(), yoga.getImageId());
        contentValues.put(TableRoutineColumns.TIME.toString(), yoga.getTime());
        contentValues.put(TableRoutineColumns.FAVORITE.toString(), yoga.getFavorite());

        return contentValues;
    }
}

