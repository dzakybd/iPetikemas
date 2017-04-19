package id.ac.its.ipetikemas;

import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.pixplicity.easyprefs.library.Prefs;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();

        if(Prefs.getBoolean("logged",false)){
            move();
        }
        Button button = (Button) findViewById(R.id.login_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Prefs.putBoolean("logged",true);
                move();
            }
        });
    }

    private void move(){
        Intent i = new Intent(Login.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
