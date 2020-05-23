package com.aparm.evs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    SearchAdapter adapter;

    MaterialSearchBar materialSearchBar;
    List<String> suggestList = new ArrayList<>();

    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_search);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        materialSearchBar = (MaterialSearchBar)findViewById(R.id.search_bar);


        database = new Database(this);

        materialSearchBar.setHint("Search");
        materialSearchBar.setCardViewElevation(10);
        loadSuggestList();

        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<String> suggest = new ArrayList<>();

                for (String search:suggestList) {
                    if (search.toLowerCase().startsWith(materialSearchBar.getText().toLowerCase())) {
                        suggest.add(search);
                    }
                }
                materialSearchBar.setLastSuggestions(suggest);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if (!enabled) recyclerView.setAdapter(adapter);
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                startSearch(text.toString());
                //TODO: решить закрывать или нет клавиатуру
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });

        //show "Tervitus" при запуске приложения
        adapter = new SearchAdapter(this, database.findWords("Tervitus"));
        recyclerView.setAdapter(adapter);

    }

    private void startSearch(String text) {
        adapter = new SearchAdapter(this, database.findWords(text));
        recyclerView.setAdapter(adapter);
    }

    private void loadSuggestList() {
        suggestList = database.getEstonianWords();
        materialSearchBar.setLastSuggestions(suggestList);
    }
}
