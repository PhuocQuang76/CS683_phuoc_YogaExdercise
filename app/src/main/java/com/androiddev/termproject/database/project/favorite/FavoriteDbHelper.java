package com.androiddev.termproject.database.project.favorite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.androiddev.termproject.database.DBHelper;
import com.androiddev.termproject.dto.Favorite;
import com.androiddev.termproject.dto.Yoga;

import java.util.ArrayList;
import java.util.List;

public class FavoriteDbHelper {
    private static FavoriteDbHelper sFavoriteDbHelper;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    //When object of FavoriteDbHelper created, will block everything
    public static synchronized FavoriteDbHelper get(Context context) {
        if (sFavoriteDbHelper == null) {
            sFavoriteDbHelper = new FavoriteDbHelper(context);
        }
        return sFavoriteDbHelper;
    }

    private FavoriteDbHelper(Context context) {
        mContext = context;
        mDatabase = new DBHelper(mContext).getWritableDatabase();
    }

    private FavoriteCursor queryFavorites(String whereClause, String[] whereArgs) {
        StringBuilder query = new StringBuilder("SELECT F._id, F.ROUTINE_ID," +
                "F.TIME, R.TITLE, R.LINKS, R.SUMMARY, R.IMAGEID " +
                "FROM FAVORITES F INNER JOIN YOGA R ON F.ROUTINE_ID = R._id ");
        if (whereClause != null) {
            query.append(" WHERE " + whereClause);
        }

        query.append(" ORDER BY R.TITLE");

        Cursor cursor = mDatabase.rawQuery(
                query.toString(),
                whereArgs
        );
        return new FavoriteCursor(cursor);
    }

    public Favorite getFavoriteById(Integer id) {
        String whereClause = "F._id = ?";
        FavoriteCursor cursor = queryFavorites(whereClause, new String[]{id.toString()});
        try {
            if (cursor.getCount() == 0) {
                return null;
            } else {
                cursor.moveToFirst();
                return cursor.getFavorite();
            }
        } finally {
            cursor.close();
        }
    }

    private Favorite findFavoriteByRoutineId(Integer routineId) {
        FavoriteCursor cursor = queryFavorites("F.ROUTINE_ID = ?", new String[]{routineId.toString()});
        try {
            if (cursor.getCount() == 0) {
                return null;
            } else {
                cursor.moveToFirst();
                return cursor.getFavorite();
            }
        } finally {
            cursor.close();
        }
    }

    private void updateFavorite(Favorite favorite) {
        int id = favorite.getId();
        ContentValues values = getContentValues(favorite);
        mDatabase.update("YOGA", values,
                "_id = ?",
                new String[]{new Integer(favorite.getId()).toString()});
    }

//    public void addRoutineToFavorite(Yoga yoga, Integer time) {
//        Favorite f = findFavoriteByRoutineId(yoga.getId());
//        if (f == null) {
//            f.setRoutineId(yoga.getId());
//            f.setTime(time);
//            mDatabase.insert("FAVORITES", null, getContentValues(f));
//        } else {
//            //just update the favorite with the time.
//            f.setTime(time);
//            updateFavorite(f);
//        }
//    }

    public void addRoutineToFavorite(Integer yogaId, Integer time) {
        Favorite f = findFavoriteByRoutineId(yogaId);
        if (f == null) {
            f = new Favorite();
            f.setRoutineId(yogaId);
            f.setTime(time);
            mDatabase.insert("FAVORITES", null, getContentValues(f));
        } else {
            //just update the favorite with the time.
            f.setTime(time);
            updateFavorite(f);
        }
    }

    //Get all favorite list
    public List<Favorite> getFavorites() {
        List<Favorite> favorites = new ArrayList<>();
        FavoriteCursor cursor = queryFavorites(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                favorites.add(cursor.getFavorite());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return favorites;
    }

//    public FavoriteCursor getFavorites() {
//        List<Favorite> favorites = new ArrayList<>();
//        FavoriteCursor cursor = queryFavorites(null, null);
//
//        return cursor;
//    }


    public void deleteFavorite(Integer favoriteId){
        int i = mDatabase.delete("FAVORITES", "_id = ?", new String[] {favoriteId.toString()});
        System.out.println(i);
    }

    public static ContentValues getContentValues(Favorite favorite) {
        ContentValues contentValues = new ContentValues();
        if (favorite.getId() != 0) {
            contentValues.put("_id", favorite.getId());
        }
        contentValues.put(TableFavoriteColumns.ROUTINE_ID.toString(), favorite.getRoutineId());
        contentValues.put(TableFavoriteColumns.TIME.toString(), favorite.getTime());
        return contentValues;
    }
}
