package com.github.ltkicker.gradify.activities;

        import android.os.Bundle;

        import androidx.appcompat.app.AppCompatActivity;

        import com.github.ltkicker.gradify.R;
        import com.github.ltkicker.gradify.activities.authentication.AuthPortalActivity;

        import android.content.Intent;

public class loading extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_loadingpage);
//<<<<<<< Updated upstream
        Intent intent = new Intent(loading.this, AuthPortalActivity.class);
        startActivity(intent);
//=======



//>>>>>>> //Stashed changes

    }
}
