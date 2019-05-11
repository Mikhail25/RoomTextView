package com.example.roomtextview;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Database(entities = {WordEntity.class}, version = 1)
public abstract class RoomDatabase extends  androidx.room.RoomDatabase{
    public static volatile  RoomDatabase INSTANCE;
    public abstract WordDao wordDao();

    static RoomDatabase getInstance(final Context context){
        if(INSTANCE == null){
            synchronized (RoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RoomDatabase.class,
                            "word_db")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void>{
        private final WordDao mDao;

        PopulateDbAsync(RoomDatabase db){
            mDao = db.wordDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mDao.deleteAll();
            WordEntity wordEntity = new WordEntity("Hello");
            mDao.insertWord(wordEntity);
            wordEntity = new WordEntity("World");
            mDao.insertWord(wordEntity);
            return null;
        }
    }
}

