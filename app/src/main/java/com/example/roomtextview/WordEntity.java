package com.example.roomtextview;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class WordEntity {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")

    private String word;

    public WordEntity(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }
}
