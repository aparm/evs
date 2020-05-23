package com.aparm.evs;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

class Database extends SQLiteAssetHelper {

    private static final String DB_NAME = "evs4.db";
    private static final int DB_VER = 1;


    Database(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    //используется только для отображения всех слов в начале работы приложения! TODO: В финальной версии удалить!
    List<Word> getWords(){
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
    
    List<Word> findWords(String search){
        List<Word> result = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"Id", "Word", "Type", "Forms", "Number", "Translations", "Text"};
        String tableName = "Words";

        qb.setTables(tableName);

        //слово которое полностью совпадает
        Cursor cursor = qb.query(db, sqlSelect, "Word = ?" ,new String[]{search},null,null,null);
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



        Cursor cursor1 = qb.query(db, sqlSelect, "Word LIKE ?" ,new String[]{"%"+search+"%"},null,null,null);
        if (cursor1.moveToFirst()) {

            do {
                Word word = new Word();
                word.setId(cursor1.getInt(cursor1.getColumnIndex("Id")));
                word.setWord(cursor1.getString(cursor1.getColumnIndex("Word")));
                word.setType(cursor1.getString((cursor1.getColumnIndex("Type"))));
                word.setForms(cursor1.getString(cursor1.getColumnIndex("Forms")));
                word.setNumber(cursor1.getInt(cursor1.getColumnIndex("Number")));
                word.setTranslations(cursor1.getString(cursor1.getColumnIndex("Translations")));
                word.setText(cursor1.getString(cursor1.getColumnIndex("Text")));

                result.add(word);
            } while (cursor1.moveToNext());
        }


        Cursor cursor2 = qb.query(db, sqlSelect, "Translations LIKE ?" ,new String[]{"%"+search+"%"},null,null,null);
        if (cursor2.moveToFirst()) {

            do {
                Word word = new Word();
                word.setId(cursor2.getInt(cursor2.getColumnIndex("Id")));
                word.setWord(cursor2.getString(cursor2.getColumnIndex("Word")));
                word.setType(cursor2.getString((cursor2.getColumnIndex("Type"))));
                word.setForms(cursor2.getString(cursor2.getColumnIndex("Forms")));
                word.setNumber(cursor2.getInt(cursor2.getColumnIndex("Number")));
                word.setTranslations(cursor2.getString(cursor2.getColumnIndex("Translations")));
                word.setText(cursor2.getString(cursor2.getColumnIndex("Text")));

                result.add(word);
            } while (cursor2.moveToNext());
        }



        return result;
    }

    //Используется только для предложения ввода
    List<String> getEstonianWords(){
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
