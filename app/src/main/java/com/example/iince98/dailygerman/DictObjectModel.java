package com.example.iince98.dailygerman;

/**
 * Created by iince98 on 02/12/2017.
 */

public class DictObjectModel {

        String word, meaning;

        public DictObjectModel(String word, String meaning){

            this.word=word;
            this.meaning = meaning;


        }
        public String getWord()
        {
            return word;
        }

        public String getMeaning()
        {
            return meaning;
        }

    }
