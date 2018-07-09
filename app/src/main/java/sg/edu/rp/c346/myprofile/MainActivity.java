package sg.edu.rp.c346.myprofile;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.PerformanceTestCase;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etGPA;
    RadioGroup rgGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextName);
        etGPA = findViewById(R.id.editTextGPA);
        rgGender = findViewById(R.id.RadioGroupGender);
    }

    @Override
    protected void onPause(){
        super.onPause();

        String strName = etName.getText().toString();
        float fltGpa = Float.valueOf(etGPA.getText().toString());
        int chkRad = rgGender.getCheckedRadioButtonId();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = prefs.edit();
        prefEdit.putString("Name", strName);
        prefEdit.putFloat("GPA", fltGpa);
        prefEdit.putInt("Gender", chkRad);
        prefEdit.commit();
    }

    @Override
    protected void onResume(){
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Value must be default or empty.
        String msg = prefs.getString("Name", "");
        float gpa = prefs.getFloat("GPA", 0.0f);
        int gender = prefs.getInt("Gender", 0);
        etName.setText(msg);
        etGPA.setText(String.valueOf(gpa));
        rgGender.check(gender);
    }
}
