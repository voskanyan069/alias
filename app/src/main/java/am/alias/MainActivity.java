package am.alias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private Spinner langSelect;
    private Spinner pointsSelect;
    private Spinner durationsSelect;

    private EditText editTextTeamName1;
    private EditText editTextTeamName2;
    private Button next;

    private String selectedLang;
    private String selectedPoints;
    private String selectedDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        nextBtn();
    }

    public void init() {
        Resources.teamPoints1 = 0;
        Resources.teamPoints2 = 0;

        langSelect = findViewById(R.id.lang_select);
        pointsSelect = findViewById(R.id.points_select);
        durationsSelect = findViewById(R.id.duration_select);

        editTextTeamName1 = (findViewById(R.id.team_1));
        editTextTeamName2 = (findViewById(R.id.team_2));

        next = findViewById(R.id.next);

        setAdapters();
    }

    public void setAdapters() {
        ArrayAdapter<CharSequence> langAdapter = ArrayAdapter.createFromResource(this, R.array.languages, R.layout.spinner_item);
        langAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        langSelect.setAdapter(langAdapter);

        ArrayAdapter<CharSequence> pointsAdapter = ArrayAdapter.createFromResource(this, R.array.points, R.layout.points_spinner_item);
        pointsAdapter.setDropDownViewResource(R.layout.points_dropdown_item);
        pointsSelect.setAdapter(pointsAdapter);

        ArrayAdapter<CharSequence> durationAdapter = ArrayAdapter.createFromResource(this, R.array.durations, R.layout.duratiton_spinner_item);
        durationAdapter.setDropDownViewResource(R.layout.duration_dropdown_item);
        durationsSelect.setAdapter(durationAdapter);
    }

    public void nextBtn() {
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextList();
                selectedLang = String.valueOf(langSelect.getSelectedItem());
                selectedPoints = String.valueOf(pointsSelect.getSelectedItem());
                selectedDuration = String.valueOf(durationsSelect.getSelectedItem());

                Resources.winPoints = Integer.parseInt(selectedPoints);
                Resources.endSeconds = Integer.parseInt(selectedDuration) * 1000;

                System.out.println(selectedLang);
                switch (selectedLang) {
                    case "AM":
                        System.out.println("-------------");
                        startActivity(new Intent("am.alias.GameAmActivity"));
                        break;
                }
            }
        });
    }

    public void editTextList() {
        try {
            if (editTextTeamName1.length() == 0 && editTextTeamName1.length() > 15) {
                Resources.teamName1 = "Group Name 1";
            }
            if (editTextTeamName1.length() == 0 && editTextTeamName1.length() > 15) {
                Resources.teamName2 = "Group Name 2";
            }
            Resources.teamName1 = String.valueOf(editTextTeamName1.getText());
            Resources.teamName2 = String.valueOf(editTextTeamName2.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}