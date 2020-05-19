package com.aparm.evs;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class SearchViewHolder extends RecyclerView.ViewHolder {

    public TextView word, forms, translations, text;

    public SearchViewHolder(@NonNull View itemView) {
        super(itemView);
        word = (TextView)itemView.findViewById(R.id.word);
        forms = (TextView)itemView.findViewById(R.id.forms);
        translations = (TextView)itemView.findViewById(R.id.translations);
        text = (TextView)itemView.findViewById(R.id.text);
    }
}

public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder>{

    private Context context;
    private List<Word> words;

    public SearchAdapter(Context context, List<Word> words) {
        this.context = context;
        this.words = words;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.layout_item,parent,false);
        return new SearchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {

        holder.word.setText(words.get(position).getWord());
        holder.forms.setText(words.get(position).getForms());
        holder.translations.setText(words.get(position).getTranslations());
        holder.text.setText(words.get(position).getText());

    }

    @Override
    public int getItemCount() {
        return words.size();
    }
}
