package com.example.roomtextview;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private WordRepository wordRepository;

    public LiveData<List<WordEntity>> getAllWords() {
        return allWords;
    }

    private LiveData<List<WordEntity>> allWords;

    public ViewModel(@NonNull Application application) {
        super(application);
        wordRepository = new WordRepository(application);
        allWords = wordRepository.getAllResults();
    }

    public void insert(WordEntity wordEntity){
        wordRepository.insert(wordEntity);
    }
}
