package am.alias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WinActivity extends AppCompatActivity {

    private TextView teamName;
    private Button toHomeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        init();
    }

    public void init() {
        teamName = findViewById(R.id.winner_team_name);
        toHomeBtn = findViewById(R.id.to_home_btn);

        teamName.setText(Resources.winnerTeamName);
        toHome();
    }

    public void toHome() {
        toHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("am.alias.AfterGameMainActivity"));
            }
        });
    }
}