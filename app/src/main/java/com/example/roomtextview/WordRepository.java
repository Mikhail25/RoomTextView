package com.example.roomtextview;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordRepository {
    private LiveData<List<WordEntity>> allResults;
    private WordDao wordDao;

    public WordRepository(Application application){
        RoomDatabase db = RoomDatabase.getInstance(application);
        wordDao = db.wordDao();
        allResults = wordDao.getAllWords();
    }

    public LiveData<List<WordEntity>> getAllResults() {
        return allResults;
    }

    public void insert(WordEntity wordEntity){
        //todo need to happen in a worker thread
        new insertAsyncTask(wordDao).execute(wordEntity);
    }

    private class insertAsyncTask extends AsyncTask<WordEntity, Void, Void> {
        public WordDao wordDao;

        public insertAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }


        @Override
        protected Void doInBackground(WordEntity... wordEntities) {
            wordDao.insertWord(wordEntities[0]);
            return null;
        }
    }
}
