package com.aparm.evs;


import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.text.Spanned;
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


        //преобразование в HTML текст для TextView
        Spanned spannedText;

        String formtext = words.get(position).getText();

        //проверка что не NULL в БД (если NULL то будет NullPointerException)
        if (formtext == null) {
            formtext = "<b>Нет описания!</b>";
        }

        //Html.fromHtml изменился в API 24
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            spannedText = Html.fromHtml(formtext,0);
        } else {
            spannedText = Html.fromHtml(formtext);
        }

        holder.text.setText(spannedText);


    }

    @Override
    public int getItemCount() {
        return words.size();
    }
}
