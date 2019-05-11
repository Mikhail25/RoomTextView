package com.example.roomtextview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder>{

    class WordViewHolder extends RecyclerView.ViewHolder{

        private final TextView wordItemView;

        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);

        }

    }
    private final LayoutInflater inflater;
    private List<WordEntity> wordEntities; // Chached copy of words

    public WordListAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_layout,parent,false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        if(wordEntities != null){
            WordEntity current = wordEntities.get(position);
            holder.wordItemView.setText(current.getWord());
        }else{
            holder.wordItemView.setText("No word");
        }
    }

    void setWords(List<WordEntity> words){
        wordEntities = words;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(wordEntities != null)
            return wordEntities.size();
        else return 0;
    }



}
