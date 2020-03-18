package com.example.financialliteracy.Activities;

import androidx.appcompat.app.AppCompatActivity;

import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.financialliteracy.Databases.QuestionDatabase;
import com.example.financialliteracy.Models.Question;
import com.example.financialliteracy.R;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private Button continueBtn;
    private TextView questionText;
    private RadioButton optionA;
    private RadioButton optionB;
    private RadioButton optionC;
    private TextView scoreText;
    // private QuizActivity quizActivity = this;

    private QuestionDatabase db;

    private int score = 0;
    private int questionNum = 0;
    private Question currentQuestion;
    private List<Question> questionList;

    public static ArrayList<Integer> scoreHistoryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        ConstraintLayout quizConstraintLayout = findViewById(R.id.quiz_constraint_layout);

        questionText = quizConstraintLayout.findViewById(R.id.text_question);
        scoreText = quizConstraintLayout.findViewById(R.id.text_score);

        optionA = quizConstraintLayout.findViewById(R.id.radio_quiz_a);
        optionB = quizConstraintLayout.findViewById(R.id.radio_quiz_b);
        optionC = quizConstraintLayout.findViewById(R.id.radio_quiz_c);

        continueBtn = quizConstraintLayout.findViewById(R.id.button_continue);
        continueBtn.setText("Continue");

        Intent quizIntent = getIntent();
        int difficulty = quizIntent.getIntExtra("Difficulty", 1);

        db = db.getInstance(this);

        db.questionDao().insertAll(getQuestionList());
        questionList = db.questionDao().getDifficultyQuestion(difficulty);
        handleQuestionReturned(questionList);



    }

    public void setQuestion(Question question, int score){

        scoreText.setText("Score: " + score);
        questionText.setText(" " + question.getQuestion()+ " ");
        optionA.setText(question.getOptionA());
        optionB.setText(question.getOptionB());
        optionC.setText(question.getOptionC());
    }
















    public static List<Question> getQuestionList(){
        //TODO:: to put this somewhere that's not here
        //TODO:: add url from api, not sure we can find relevant data in our APIs for these questions
        //questions and answers from https://www.sageadvice.eu/2017/11/27/trivia-quiz-on-5th-edition-dd-rules/
        List<Question> questionList = new ArrayList<>();
        questionList.add(0, new Question(0,
                "Can you cast underwater?",
                "No",
                "Yes, but fire spells deal no damage.",
                "Yes",
                "Yes",
                3));
        questionList.add(1, new Question(1,
                "Is there alignment restriction for classes in Player's Handbook?",
                "Yes, Paladin must be Lawful Good, Druid must be Neutral and Assassin must be evil.",
                "No",
                "Yes, Paladin must be Good and Monk Lawful.",
                "No",
                1));
        questionList.add(2, new Question(2,
                "Can you knock a creature out with a melee spell attack?",
                "Only with Spell Sniper feat.",
                "No, only with a melee weapon.",
                "Yes",
                "Yes",
                2));
        questionList.add(3, new Question(3,
                "Can you use a shield with Mage Armor spell?",
                "Only with a light shield or buckler shield.",
                "Yes, Mage Armor spell works with a shield",
                "Nope, they do not stack.",
                "Yes, Mage Armor spell works with a shield.",
                3));
        questionList.add(4, new Question(4,
                "A monster is immune to damage from nonmagical bludgeoning weapons. Does he still take damage from falling?",
                "Yes, but has resistance to damage.",
                "No, fall is a bludgeoning damage.",
                "Yes, fall is not a weapon.",
                "Yes, fall is not a weapon.",
                1));
        questionList.add(5, new Question(5,
                "If a 5th level wizard casts a Fireball during surprise, do the enemies get disadvantage on their saving throw?",
                "No",
                "Only if wizard has with War Caster feat.",
                "Yes",
                "No",
                1));
        questionList.add(6, new Question(6,
                "Is a 1 on an ability check an automatic failure?",
                "No",
                "Yes.",
                "Yes and roll a d10 on Fumble table.",
                "No",
                2));
        questionList.add(7, new Question(7,
                "Can rogue get sneak attack damage against undead?",
                "Yes, Sneak Attack works against Undead.",
                "No, undeads have resistance to sneak attack.",
                "Only if you use a bludgeoning weapon.",
                "Yes, Sneak Attack works against Undead.",
                1));
        questionList.add(8, new Question(8,
                "If you have a creature between you and the target...",
                "Target has cover +4 bonus to AC.",
                "Target has half-cover +2 bonus to AC.",
                "You have disadvantage.",
                "Target has half-cover +2 bonus to AC.",
                2));
        questionList.add(9, new Question(9,
                "Can you make an attack action from Prone condition?",
                "Yes, but you have disadvantage on attack rolls.",
                "No, you must stand up (half movement) and attack.",
                "Only with a weapon with reach property.",
                "Yes, but you have disadvantage on attack rolls.",
                3));

        return questionList;
    }


    public void handleQuestionReturned(List<Question> questionList) {
        currentQuestion = questionList.get(0);
        setQuestion(currentQuestion, score);
        final List<Question> questions = questionList;


        continueBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                RadioGroup radioGroup = findViewById(R.id.options_quiz);
                if (radioGroup.getCheckedRadioButtonId() == -1) {
                } else {
                    RadioButton answer = findViewById(radioGroup.getCheckedRadioButtonId());
                    if (currentQuestion.getAnswer().equals(answer.getText())) {

                        score++;
                    } else {
                        //TODO://get rid of this

                    }
                    answer.setChecked(false);
                    Toast.makeText(getApplicationContext(), Integer.toString(questionNum), Toast.LENGTH_LONG).show();

                    // Below code is to make sure that the button text changes to finish quiz on final question, and to
                    // record the final score in an ArrayList
                    if (questionNum < questions.size() - 2) {
                        questionNum++;

                        setQuestion(questions.get(questionNum),score);

                    } else if (questionNum == questions.size() - 2) {
                        questionNum++;
                        setQuestion(questions.get(questionNum),score);

                        continueBtn.setText("Finish Quiz");
                    } else {
                        //scoreHistoryList.add(score);
                        //Toast to check for score at the end
                        Toast.makeText(getApplicationContext(), Integer.toString(score), Toast.LENGTH_LONG).show();
                        finish();
                    }

                }
            }
        });

    }

    public Question retrieveQuestionFromDb (int questionNum){
        Question question = db.questionDao().getQuestion(questionNum);
        return question;
    }
}

