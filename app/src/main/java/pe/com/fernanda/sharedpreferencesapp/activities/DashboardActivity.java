package pe.com.fernanda.sharedpreferencesapp.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import pe.com.fernanda.sharedpreferencesapp.R;
import pe.com.fernanda.sharedpreferencesapp.models.User;
import pe.com.fernanda.sharedpreferencesapp.repositories.UserRepository;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DashboardActivity extends AppCompatActivity {

    private static final String TAG = DashboardActivity.class.getSimpleName();

    // SharedPreferences
    private SharedPreferences sharedPreferences;

    private TextView usernameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        usernameText = (TextView)findViewById(R.id.fullname_text);

        // init SharedPreferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // get username from SharedPreferences
        String username = sharedPreferences.getString("username", null);
        Log.d(TAG, "username: " + username);

        User user = UserRepository.getUser(username);

        usernameText.setText(user.getFullname());
    }

    public void callLogout(View view){
        // remove from SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        boolean success = editor.putBoolean("islogged", false).commit();
//        boolean success = editor.clear().commit(); // not recommended

        finish();
    }
    public void callSettingsForm(View view){
        Intent intent = new Intent(this, MyPreferencesActivity.class);
        startActivity(intent);
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
