package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Objects;

public class GrammarActivity extends AppCompatActivity {

    ViewLessonWidget viewLessonWidget;
    LinearLayout linearLayout;

    String nouns;
    String verbs;
    String adjectives;
    String prepositions;
    String interjections;

    ArrayList<ViewLessonWidget> viewLessonWidgetArrayList;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar);
        TextView back = findViewById(R.id.back);
        linearLayout = findViewById(R.id.lesson_linear_layout);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        viewLessonWidgetArrayList = new ArrayList<>();

        // nouns

        ViewLessonWidget obj1 = new ViewLessonWidget(this, "Lesson 1: Introduction to Nouns","Definition of a noun:\n" +
                "A noun is a type of word that refers to a person, place, thing, idea, or concept. In other words, a noun is a word that names something. Nouns can be singular (referring to one item) or plural (referring to more than one item), and they can be concrete (referring to things that you can see or touch) or abstract (referring to ideas or concepts).\n" +
                "\n" +
                "Examples of nouns:\n" +
                "Here are some examples of nouns:\n" +
                "\n" +
                "Person: John, teacher, doctor\n" +
                "Place: park, school, city\n" +
                "Thing: book, car, computer\n" +
                "Idea: love, happiness, freedom\n" +
                "Role of nouns in English:\n" +
                "Nouns are an essential part of the English language, as they allow us to name and refer to things in the world around us. They are used in sentences as subjects, objects, and complements, and they can also be used to modify other words in a sentence.\n" +
                "\n" +
                "For example, in the sentence \"John is a teacher,\" \"John\" and \"teacher\" are both nouns. \"John\" is the subject of the sentence, and \"teacher\" is the complement, describing what John is.\n" +
                "\n" +
                "In the sentence \"I saw a book on the table,\" \"book\" is a noun used as the object of the verb \"saw.\" And in the sentence \"She wore a beautiful red dress,\" \"beautiful\" and \"red\" are adjectives modifying the noun \"dress.\"\n" +
                "\n" +
                "Overall, nouns are an important part of building sentences and conveying meaning in English.", "nouns");

        ViewLessonWidget obj2 = new ViewLessonWidget(this, "Lesson 2: Types of Nouns","test", "nouns");
        ViewLessonWidget obj3 = new ViewLessonWidget(this, "Lesson 3: Singular and Plural Nouns","test", "nouns");
        ViewLessonWidget obj4 = new ViewLessonWidget(this, "Lesson 4: Countable and Uncountable Nouns","test", "nouns");
        ViewLessonWidget obj5 = new ViewLessonWidget(this, "Lesson 5: Gender-Specific Nouns","test", "nouns");
        ViewLessonWidget obj6 = new ViewLessonWidget(this, "Lesson 6: Possessive Nouns","test", "nouns");
        ViewLessonWidget obj7 = new ViewLessonWidget(this, "Lesson 7: Noun Phrases","test", "nouns");
        ViewLessonWidget obj8 = new ViewLessonWidget(this, "Lesson 8: Using Nouns in Sentences","test", "nouns");
        ViewLessonWidget obj9 = new ViewLessonWidget(this, "Lesson 9: Common Noun Mistakes to Avoid","test", "nouns");
        ViewLessonWidget obj10 = new ViewLessonWidget(this, "Lesson 10: Using Nouns Effectively in Writing","test", "nouns");
        ViewLessonWidget obj100 = new ViewLessonWidget(this, "Lesson 11: Using Nouns Effectively in Writing","test", "nouns");

        viewLessonWidgetArrayList.add(obj1);
        viewLessonWidgetArrayList.add(obj2);
        viewLessonWidgetArrayList.add(obj3);
        viewLessonWidgetArrayList.add(obj4);
        viewLessonWidgetArrayList.add(obj5);
        viewLessonWidgetArrayList.add(obj6);
        viewLessonWidgetArrayList.add(obj7);
        viewLessonWidgetArrayList.add(obj8);
        viewLessonWidgetArrayList.add(obj9);
        viewLessonWidgetArrayList.add(obj10);
        viewLessonWidgetArrayList.add(obj100);


        // pronouns

        ViewLessonWidget obj11 = new ViewLessonWidget(this, "Lesson 1: Introduction to Pronouns","test", "pronouns");
        ViewLessonWidget obj12 = new ViewLessonWidget(this, "Lesson 2: Personal Pronouns","test", "pronouns");
        ViewLessonWidget obj13 = new ViewLessonWidget(this, "Lesson 3: Possessive Pronouns","test", "pronouns");
        ViewLessonWidget obj14 = new ViewLessonWidget(this, "Lesson 4: Reflexive Pronouns","test", "pronouns");
        ViewLessonWidget obj15 = new ViewLessonWidget(this, "Lesson 5: Demonstrative Pronouns","test", "pronouns");
        ViewLessonWidget obj16 = new ViewLessonWidget(this, "Lesson 6: Interrogative Pronouns","test", "pronouns");
        ViewLessonWidget obj17 = new ViewLessonWidget(this, "Lesson 7: Relative Pronouns","test", "pronouns");
        ViewLessonWidget obj18 = new ViewLessonWidget(this, "Lesson 8: Indefinite Pronouns","test", "pronouns");
        ViewLessonWidget obj19 = new ViewLessonWidget(this, "Lesson 9: Common Pronoun Errors","test", "pronouns");
        ViewLessonWidget obj20 = new ViewLessonWidget(this, "Lesson 10: Using Pronouns Effectively in Writing","test", "pronouns");

        viewLessonWidgetArrayList.add(obj11);
        viewLessonWidgetArrayList.add(obj12);
        viewLessonWidgetArrayList.add(obj13);
        viewLessonWidgetArrayList.add(obj14);
        viewLessonWidgetArrayList.add(obj15);
        viewLessonWidgetArrayList.add(obj16);
        viewLessonWidgetArrayList.add(obj17);
        viewLessonWidgetArrayList.add(obj18);
        viewLessonWidgetArrayList.add(obj19);
        viewLessonWidgetArrayList.add(obj20);

        // verbs

        ViewLessonWidget obj21 = new ViewLessonWidget(this, "Lesson 1: Introduction to Verbs","test", "verbs");
        ViewLessonWidget obj22 = new ViewLessonWidget(this, "Lesson 2: Types of Verbs","test", "verbs");
        ViewLessonWidget obj23 = new ViewLessonWidget(this, "Lesson 3: Verb Tense","test", "verbs");
        ViewLessonWidget obj24 = new ViewLessonWidget(this, "Lesson 4: Regular and Irregular Verbs","test", "verbs");
        ViewLessonWidget obj25 = new ViewLessonWidget(this, "Lesson 5: Verb Agreement","test", "verbs");
        ViewLessonWidget obj26 = new ViewLessonWidget(this, "Lesson 6: Active and Passive Voice","test", "verbs");
        ViewLessonWidget obj27 = new ViewLessonWidget(this, "Lesson 7: Verb Phrases","test", "verbs");
        ViewLessonWidget obj28 = new ViewLessonWidget(this, "Lesson 8: Gerunds and Infinitives","test", "verbs");
        ViewLessonWidget obj29 = new ViewLessonWidget(this, "Lesson 9: Phrasal Verbs","test", "verbs");
        ViewLessonWidget obj30 = new ViewLessonWidget(this, "Lesson 10: Verb Collocations","test", "verbs");
        ViewLessonWidget obj31 = new ViewLessonWidget(this, "Lesson 11: Writing with Verbs","test", "verbs");


        viewLessonWidgetArrayList.add(obj21);
        viewLessonWidgetArrayList.add(obj22);
        viewLessonWidgetArrayList.add(obj23);
        viewLessonWidgetArrayList.add(obj24);
        viewLessonWidgetArrayList.add(obj25);
        viewLessonWidgetArrayList.add(obj26);
        viewLessonWidgetArrayList.add(obj27);
        viewLessonWidgetArrayList.add(obj28);
        viewLessonWidgetArrayList.add(obj29);
        viewLessonWidgetArrayList.add(obj30);
        viewLessonWidgetArrayList.add(obj31);

        //adverbs

        ViewLessonWidget obj32 = new ViewLessonWidget(this, "Lesson 1: Introduction to Adverbs","test", "adverbs");
        ViewLessonWidget obj33 = new ViewLessonWidget(this, "Lesson 2: Types of Adverbs","test", "adverbs");
        ViewLessonWidget obj34 = new ViewLessonWidget(this, "Lesson 3: Adverbs of Manner","test", "adverbs");
        ViewLessonWidget obj35 = new ViewLessonWidget(this, "Lesson 4: Adverbs of Time and Place","test", "adverbs");
        ViewLessonWidget obj36 = new ViewLessonWidget(this, "Lesson 5: Adverbs of Frequency and Degree","test", "adverbs");
        ViewLessonWidget obj37 = new ViewLessonWidget(this, "Lesson 6: AComparison of Adverbs","test", "adverbs");
        ViewLessonWidget obj38 = new ViewLessonWidget(this, "Lesson 7: Position of Adverbs","test", "adverbs");
        ViewLessonWidget obj39 = new ViewLessonWidget(this, "Lesson 8: Adverbs and Negatives","test", "adverbs");
        ViewLessonWidget obj40 = new ViewLessonWidget(this, "Lesson 9: Common Adverb Mistakes to Avoid","test", "adverbs");
        ViewLessonWidget obj41 = new ViewLessonWidget(this, "Lesson 10: Using Adverbs to Express Attitude and Emotion","test", "adverbs");

        viewLessonWidgetArrayList.add(obj32);
        viewLessonWidgetArrayList.add(obj33);
        viewLessonWidgetArrayList.add(obj34);
        viewLessonWidgetArrayList.add(obj35);
        viewLessonWidgetArrayList.add(obj36);
        viewLessonWidgetArrayList.add(obj37);
        viewLessonWidgetArrayList.add(obj38);
        viewLessonWidgetArrayList.add(obj39);
        viewLessonWidgetArrayList.add(obj40);
        viewLessonWidgetArrayList.add(obj41);

        //adjectives

        ViewLessonWidget obj42 = new ViewLessonWidget(this, "Lesson 1: Introduction to Adjectives","test", "adjectives");
        ViewLessonWidget obj43 = new ViewLessonWidget(this, "Lesson 2: Types of Adjectives","test", "adjectives");
        ViewLessonWidget obj44 = new ViewLessonWidget(this, "Lesson 3: Comparative and Superlative Adjectives","test", "adjectives");
        ViewLessonWidget obj45 = new ViewLessonWidget(this, "Lesson 4: Order of Adjectives","test", "adjectives");
        ViewLessonWidget obj46 = new ViewLessonWidget(this, "Lesson 5: Using Adjectives Effectively in Writing","test", "adjectives");
        ViewLessonWidget obj47 = new ViewLessonWidget(this, "Lesson 6: Irregular Adjectives","test", "adjectives");
        ViewLessonWidget obj48 = new ViewLessonWidget(this, "Lesson 7: Adjectives and Articles","test", "adjectives");
        ViewLessonWidget obj49 = new ViewLessonWidget(this, "Lesson 8: Adjective Endings","test", "adjectives");
        ViewLessonWidget obj50 = new ViewLessonWidget(this, "Lesson 9: Adjective Order in Questions","test", "adjectives");

        viewLessonWidgetArrayList.add(obj42);
        viewLessonWidgetArrayList.add(obj43);
        viewLessonWidgetArrayList.add(obj44);
        viewLessonWidgetArrayList.add(obj45);
        viewLessonWidgetArrayList.add(obj46);
        viewLessonWidgetArrayList.add(obj47);
        viewLessonWidgetArrayList.add(obj48);
        viewLessonWidgetArrayList.add(obj49);
        viewLessonWidgetArrayList.add(obj50);

        //prepositions

        ViewLessonWidget obj52 = new ViewLessonWidget(this, "Lesson 1: Introduction to Prepositions","test", "prepositions");
        ViewLessonWidget obj53 = new ViewLessonWidget(this, "Lesson 2: Preposition Categories","test", "prepositions");
        ViewLessonWidget obj54 = new ViewLessonWidget(this, "Lesson 3: Using Prepositions in Simple Sentences","test", "prepositions");
        ViewLessonWidget obj55 = new ViewLessonWidget(this, "Lesson 4: Prepositions with Verbs","test", "prepositions");
        ViewLessonWidget obj56 = new ViewLessonWidget(this, "Lesson 5: Prepositions with Nouns and Pronouns","test", "prepositions");
        ViewLessonWidget obj57 = new ViewLessonWidget(this, "Lesson 6: Prepositions with Adjectives and Adverbs","test", "prepositions");
        ViewLessonWidget obj58 = new ViewLessonWidget(this, "Lesson 7: Prepositions of Time","test", "prepositions");
        ViewLessonWidget obj59 = new ViewLessonWidget(this, "Lesson 8: Prepositions of Location and Direction","test", "prepositions");
        ViewLessonWidget obj60 = new ViewLessonWidget(this, "Lesson 9: Prepositions in Idioms","test", "prepositions");
        ViewLessonWidget obj61 = new ViewLessonWidget(this, "Lesson 10: Preposition Mistakes to Avoid","test", "prepositions");

        viewLessonWidgetArrayList.add(obj52);
        viewLessonWidgetArrayList.add(obj53);
        viewLessonWidgetArrayList.add(obj54);
        viewLessonWidgetArrayList.add(obj55);
        viewLessonWidgetArrayList.add(obj56);
        viewLessonWidgetArrayList.add(obj57);
        viewLessonWidgetArrayList.add(obj58);
        viewLessonWidgetArrayList.add(obj59);
        viewLessonWidgetArrayList.add(obj60);
        viewLessonWidgetArrayList.add(obj61);

        //interjections and conjunctions

        ViewLessonWidget obj62 = new ViewLessonWidget(this, "Lesson 1: Introduction to Interjections and Conjunctions","test", "interjections");
        ViewLessonWidget obj63 = new ViewLessonWidget(this, "Lesson 2: Interjections","test", "interjections");
        ViewLessonWidget obj64 = new ViewLessonWidget(this, "Lesson 3: Conjunction Categories","test", "interjections");
        ViewLessonWidget obj65 = new ViewLessonWidget(this, "Lesson 4: Coordinating Conjunctions","test", "interjections");
        ViewLessonWidget obj66 = new ViewLessonWidget(this, "Lesson 5: Subordinating Conjunctions","test", "interjections");
        ViewLessonWidget obj67 = new ViewLessonWidget(this, "Lesson 6: Correlative Conjunctions","test", "interjections");
        ViewLessonWidget obj68 = new ViewLessonWidget(this, "Lesson 7: Interjections in Writing","test", "interjections");
        ViewLessonWidget obj69 = new ViewLessonWidget(this, "Lesson 8: Conjunctions in Writing","test", "interjections");
        ViewLessonWidget obj70 = new ViewLessonWidget(this, "Lesson 9: Interjection and Conjunction Mistakes to Avoid","test", "interjections");
        ViewLessonWidget obj71 = new ViewLessonWidget(this, "Lesson 10: Using Interjections and Conjunctions in Conversations","test", "interjections");

        viewLessonWidgetArrayList.add(obj62);
        viewLessonWidgetArrayList.add(obj63);
        viewLessonWidgetArrayList.add(obj64);
        viewLessonWidgetArrayList.add(obj65);
        viewLessonWidgetArrayList.add(obj66);
        viewLessonWidgetArrayList.add(obj67);
        viewLessonWidgetArrayList.add(obj68);
        viewLessonWidgetArrayList.add(obj69);
        viewLessonWidgetArrayList.add(obj70);
        viewLessonWidgetArrayList.add(obj71);



        drawWidgets(viewLessonWidgetArrayList);


    }

    public void drawWidgets(ArrayList<ViewLessonWidget> list) {

        Bundle extras = getIntent().getExtras();
        Intent intent = getIntent();
        String type = intent.getStringExtra("OPENTYPE");

        for (ViewLessonWidget widget : list) {
            if (Objects.equals(widget.getType(), type)){
                linearLayout.addView(widget);
            }
        }
    }

}
