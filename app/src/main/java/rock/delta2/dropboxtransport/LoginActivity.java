package rock.delta2.dropboxtransport;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.dropbox.core.android.Auth;

import rock.delta2.dropboxtransport.Preferences.DropboxPreferences;
import rock.delta2.dropboxtransport.Preferences.PreferencesHelper;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ImageView logo  = (ImageView) findViewById(R.id.ivLogo);
        logo.setImageDrawable(getResources().getDrawable(R.drawable.logo));

        Intent returnIntent = new Intent();
        setResult(AppCompatActivity.RESULT_CANCELED, returnIntent);
    }

    public void onClick(View view) {
        Auth.startOAuth2Authentication(LoginActivity.this, DropboxPreferences.APP_KEY);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getAccessToken();
    }



    public void getAccessToken() {
        String accessToken = Auth.getOAuth2Token(); //generate Access Token
        if (accessToken != null) {
            PreferencesHelper.setToken(accessToken);

            Intent returnIntent = new Intent();
            setResult(AppCompatActivity.RESULT_OK, returnIntent);
            finish();
        }

    }

}
