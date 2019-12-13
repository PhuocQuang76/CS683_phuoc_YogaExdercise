package com.androiddev.termproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.androiddev.termproject.R;
import com.androiddev.termproject.database.project.favorite.TableFavoriteColumns;
import com.androiddev.termproject.database.project.history.TableHistoryColumns;
import com.androiddev.termproject.database.project.yoga.TableRoutineColumns;

public class DBHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "yoga.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    private static void insertYogaTable(SQLiteDatabase db, String title, String summary,
                                      String links,int imageId, int time, int favorite){
        ContentValues projectValues = new ContentValues();

        projectValues.put(TableRoutineColumns.TITLE.toString(), title);
        projectValues.put(TableRoutineColumns.SUMMARY.toString(), summary);
        projectValues.put(TableRoutineColumns.LINKS.toString(), links);
        projectValues.put(TableRoutineColumns.IMAGEID.toString(), imageId);
        projectValues.put(TableRoutineColumns.TIME.toString(), time);
        projectValues.put(TableRoutineColumns.FAVORITE.toString(),favorite);

        db.insert("YOGA", null, projectValues);
    }


    private static void insertFavoriteTable(SQLiteDatabase db, Integer routineId, Integer time){
        ContentValues projectValues = new ContentValues();

        projectValues.put(TableFavoriteColumns.ROUTINE_ID.toString(), routineId);
        projectValues.put(TableFavoriteColumns.TIME.toString(), time);

        db.insert("FAVORITES", null, projectValues);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

//        db.execSQL("CREATE TABLE FAVORITE(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
//                + TableRoutineColumns.TITLE.getColumnDefinition()
//
//        );


        db.execSQL("CREATE TABLE YOGA(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TableRoutineColumns.TITLE.getColumnDefinition() + ", "
                + TableRoutineColumns.SUMMARY.getColumnDefinition() + ","
                + TableRoutineColumns.LINKS.getColumnDefinition() + ", "
                + TableRoutineColumns.IMAGEID.getColumnDefinition() + ", "
                + TableRoutineColumns.TIME.getColumnDefinition() + ", "
                + TableRoutineColumns.FAVORITE.getColumnDefinition() + ")"

        );

        insertYogaTable(db, "Mountain Pose", "Mountain Pose is the base for all " +
                        "standing poses; it gives you a sense of how to ground in to your feet " +
                        "and feel the earth below you. Mountain pose may seem like \"simply standing,\" " +
                        "but there is a ton going on",
                "<a href= 'https://www.youtube.com/watch?v=ATLU-XX_lro'> Watch Video  <a>", R.drawable.mountianpose, 0, 0);

        insertYogaTable(db, "Downward Facing Dog", "Downward Dog is used in most yoga practices and yoga " +
                        "classes and it stretches and strengthens the entire body. I always say, “a down dog a day " +
                        "keeps the doctor away.",
                "<a href= 'https://www.youtube.com/watch?v=JmW6Ofblhtk'> Watch Video <a>", R.drawable.downwardfacingdog, 0, 0);

        insertYogaTable(db, "Plank", "Plank teaches us how to balance on our hands while using the entire body" +
                        " to support us. It is a great way to strengthen the abdominals, and learn to use the breath" +
                        " to help us stay in a challenging pose.",
                "<a href= 'https://www.youtube.com/watch?v=dj_naCi_WIM'> Watch Video <a>", R.drawable.plank, 0, 0);

        insertYogaTable(db, "Triangle", "Triangle is a wonderful standing posture to stretch the sides of" +
                        " the waist, open up the lungs, strengthen the legs and tone the entire body.",
                "<a href= 'https://www.youtube.com/watch?v=upFYlxZHif0'> Watch Video <a>", R.drawable.triangle, 0, 0);

        insertYogaTable(db, "Tree", "Tree is an awesome standing balance for beginners to work on to gain " +
                        "focus and clarity, and learn to breathe while standing and keeping the body balanced on one foot",
                "<a href= 'https://www.youtube.com/watch?v=yVE4XXFFO70'> Watch Video <a>", R.drawable.tree, 0, 0);

        insertYogaTable(db, "Warrior 1", "Warrior poses are essential for building strength and stamina in " +
                        "a yoga practice. They give us confidence and stretch the hips and thighs while building " +
                        "strength in the entire lower body and core.",
                "<a href= 'https://www.youtube.com/watch?v=5rT--p_cLOc'> Watch Video <a>", R.drawable.warrior1, 0, 0);

        insertYogaTable(db, "Warrior 2", "Warrior 2 is an external hip opener and opens up the inner " +
                        "thighs and groin. It's a good starting point for many side postures including triangle," +
                        " extended angle and half moon balance.",
                "<a href= 'https://www.youtube.com/watch?v=4Ejz7IgODlU'> Watch Video <a>", R.drawable.warrior2, 0, 0);

        insertYogaTable(db, "Seated Forward Bend", "It’s important to incorporate a forward bend in " +
                        "yoga practice to stretch the hamstrings, lower and upper back and sides. Seated forward " +
                        "bend is the perfect fold for everyone to start to open up the body and learn to breathe " +
                        "through uncomfortable positions.",
                "<a href= 'https://www.youtube.com/watch?v=Xn1wigQSrmI'> Watch Video <a>", R.drawable.seatedforwardbend, 0, 0);

        insertYogaTable(db, "Bridge Pose", "A counter pose to a forward bend is a back bend. Bridge is a " +
                        "good beginner’s back bend that stretches the front body and strengthens the back body.",
                "<a href= 'https://www.youtube.com/watch?v=NnbvPeAIhmA'> Watch Video <a>", R.drawable.bridgepose, 0, 0);

        insertYogaTable(db, "Child's Pose", "Every one needs a good resting pose and Child’s pose is " +
                        "an awesome one not just for beginners but for yoga practitioners of all levels.",
                "<a href= 'https://www.youtube.com/watch?v=eqVMAPM00DM'> Watch Video <a>", R.drawable.childpose, 0, 0);


//        db.execSQL("CREATE TABLE FAVORITELIST (_id INTEGER PRIMARY KEY AUTOINCREMENT,"
//                        + TableRoutineColumns.TITLE.getColumnDefinition() + ", "
//                        + TableRoutineColumns.SUMMARY.getColumnDefinition() + ","
//                        + TableRoutineColumns.LINKS.getColumnDefinition() + ", "
//                        + TableRoutineColumns.IMAGEID.getColumnDefinition() + ", "
//                        + TableRoutineColumns.TIME.getColumnDefinition() + ", "
//                        + TableRoutineColumns.FAVORITE.getColumnDefinition() + ")"
//                );
//
//        }

        db.execSQL("CREATE TABLE FAVORITES (_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TableFavoriteColumns.ROUTINE_ID.getColumnDefinition() + ", "
                + TableFavoriteColumns.TIME.getColumnDefinition() + ", "
                + "FOREIGN KEY (" + TableFavoriteColumns.ROUTINE_ID.toString() + ") "
                + " REFERENCES YOGA(_id))");

//        insertFavoriteTable(db, 1, 5);
//        insertFavoriteTable(db, 2, 5);
//        insertFavoriteTable(db, 3, 5);


        db.execSQL("CREATE TABLE HISTORY (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TableHistoryColumns.ROUTINE_ID.getColumnDefinition() + ", "
                + TableHistoryColumns.TIME.getColumnDefinition() + ", "
                + TableHistoryColumns.DURATION.getColumnDefinition() + ", "
                + "FOREIGN KEY (" + TableHistoryColumns.ROUTINE_ID.toString() + ")"
                + " REFERENCES YOGA(_id))");

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //nothing to do here, we don't upgrade our database (in this release).
    }
}
