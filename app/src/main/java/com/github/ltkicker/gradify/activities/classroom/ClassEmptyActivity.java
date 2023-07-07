package com.github.ltkicker.gradify.activities.classroom;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.activities.authentication.AuthPortalActivity;
import com.github.ltkicker.gradify.data.users.CacheData;
import com.github.ltkicker.gradify.activities.navigation.LogoutActivity;
import com.github.ltkicker.gradify.activities.navigation.MenuActivity;

public class ClassEmptyActivity extends AppCompatActivity {
    Button backbutton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!CacheData.isAuthenticated()) {
            Intent intent = new Intent(this, AuthPortalActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        setContentView(R.layout.activity9a_addclass_teacher);

        ImageButton addClass = findViewById(R.id.create_class_button);
        addClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClassEmptyActivity.this, ClassEditActivity.class);
                startActivity(intent);
            }
        });
        backbutton = (Button)findViewById(R.id.img_backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClassEmptyActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

    }
}
