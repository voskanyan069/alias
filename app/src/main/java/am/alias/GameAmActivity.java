package am.alias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class GameAmActivity extends AppCompatActivity {

    private TextView teamTitle;
    private TextView word;
    private ImageButton wrong;
    private ImageButton right;
    private TextView stopWatch;
    private CountDownTimer timer;

    private Button ready;

    private String wordFromArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_am);

        init();
        teamTitle.setText(Resources.teamName1);
        ready_2();
    }

    public void init() {
        teamTitle = findViewById(R.id.team_name);
        word = findViewById(R.id.word);
        wrong = findViewById(R.id.wrong_answer);
        right = findViewById(R.id.right_answer);
        stopWatch = findViewById(R.id.timer);
        ready = findViewById(R.id.ready_btn);
    }

    public void gameTeam1() {

        teamTitle.setText(Resources.teamName1);
        randWord();

        timer = new CountDownTimer(Resources.endSeconds, 1000) {

            public void onTick(long millisUntilFinished) {
                stopWatch.setText(String.valueOf(millisUntilFinished / 1000));
                System.out.println("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                ready_1();
                System.out.println("time out!");
            }
        };

        timer.start();

        wrongAnswer();
        rightAnswerGame1();
    }

    public void gameTeam2() {

        teamTitle.setText(Resources.teamName2);
        randWord();

        timer = new CountDownTimer(Resources.endSeconds, 1000) {

            public void onTick(long millisUntilFinished) {
                stopWatch.setText(String.valueOf(millisUntilFinished / 1000));
                System.out.println("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                ready_2();
                System.out.println("time out!");
            }
        };

        timer.start();

        wrongAnswer();
        rightAnswerGame2();
    }

    public void wrongAnswer() {
        wrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randWord();
            }
        });
    }

    public void rightAnswerGame1() {
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++Resources.teamPoints1;
                teamTitle.setText(Resources.teamName1 + " | " + Resources.teamPoints1);

                if (Resources.teamPoints1 >= Resources.winPoints) {
                    teamWin(Resources.teamName1);
                }

                randWord();
            }
        });
    }

    public void rightAnswerGame2() {
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++Resources.teamPoints2;
                teamTitle.setText(Resources.teamName2 + " | " + Resources.teamPoints2);

                if (Resources.teamPoints2 >= Resources.winPoints) {
                    teamWin(Resources.teamName2);
                }

                randWord();
            }
        });
    }

    public void teamWin(String teamName) {
        Resources.winnerTeamName = teamName;
        timer.cancel();
        startActivity(new Intent("am.alias.WinActivity"));
    }

    public void ready_1() {
        stopWatch.setVisibility(View.GONE);
        ready.setVisibility(View.VISIBLE);

        ready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameTeam2();
                stopWatch.setVisibility(View.VISIBLE);
                ready.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void ready_2() {
        stopWatch.setVisibility(View.GONE);
        ready.setVisibility(View.VISIBLE);

        ready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameTeam1();
                stopWatch.setVisibility(View.VISIBLE);
                ready.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void randWord() {
        Random random = new Random();

        System.out.println("---------------");
        int randIndex = random.nextInt(Math.abs(Resources.armWordList.length));
        wordFromArray = Resources.armWordList[randIndex];
        word.setText(wordFromArray);
        System.out.println(wordFromArray);
        System.out.println("---------------");
    }
}