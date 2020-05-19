package com.aparm.evs;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteAssetHelper {

    private static final String DB_NAME = "evs.db";
    private static final int DB_VER = 1;


    public Database(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    //используется только для отображения всех слов в начале работы приложения! TODO: В финальной версии удалить!
    public List<Word> getWords(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"Id", "Word", "Type", "Forms", "Number", "Translations", "Text"};
        String tableName = "Words";

        qb.setTables(tableName);
        Cursor cursor = qb.query(db, sqlSelect, null,null,null,null,null);
        List<Word> result = new ArrayList<>();

        if (cursor.moveToFirst()) {
             do {
                Word word = new Word();
                word.setId(cursor.getInt(cursor.getColumnIndex("Id")));
                word.setWord(cursor.getString(cursor.getColumnIndex("Word")));
                word.setType(cursor.getString((cursor.getColumnIndex("Type"))));
                word.setForms(cursor.getString(cursor.getColumnIndex("Forms")));
                word.setNumber(cursor.getInt(cursor.getColumnIndex("Number")));
                word.setTranslations(cursor.getString(cursor.getColumnIndex("Translations")));
                word.setText(cursor.getString(cursor.getColumnIndex("Text")));

                result.add(word);
            } while (cursor.moveToNext());
        }
        return result;
    }

    //TODO: поиск по переводам!!
    public List<Word> findWords(String search){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"Id", "Word", "Type", "Forms", "Number", "Translations", "Text"};
        String tableName = "Words";

        qb.setTables(tableName);
        Cursor cursor = qb.query(db, sqlSelect, "Word LIKE ?" ,new String[]{"%"+search+"%"},null,null,null);
        List<Word> result = new ArrayList<>();

        if (cursor.moveToFirst()) {

            do {
                Word word = new Word();
                word.setId(cursor.getInt(cursor.getColumnIndex("Id")));
                word.setWord(cursor.getString(cursor.getColumnIndex("Word")));
                word.setType(cursor.getString((cursor.getColumnIndex("Type"))));
                word.setForms(cursor.getString(cursor.getColumnIndex("Forms")));
                word.setNumber(cursor.getInt(cursor.getColumnIndex("Number")));
                word.setTranslations(cursor.getString(cursor.getColumnIndex("Translations")));
                word.setText(cursor.getString(cursor.getColumnIndex("Text")));

                result.add(word);
            } while (cursor.moveToNext());
        }
        return result;
    }

    //Используется только для предложения ввода
    public List<String> getEstonianWords(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"Word"};
        String tableName = "Words";

        qb.setTables(tableName);
        Cursor cursor = qb.query(db, sqlSelect, null,null,null,null,null);
        List<String> result = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                result.add(cursor.getString(cursor.getColumnIndex("Word")));
            } while (cursor.moveToNext());
        }
        return result;
    }



}
