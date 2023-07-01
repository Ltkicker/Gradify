package com.github.ltkicker.gradify.activities.classroom;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.activities.authentication.AuthPortalActivity;
import com.github.ltkicker.gradify.data.users.UserCacheData;

public class ClassEmptyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!UserCacheData.isAuthenticated()) {
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

    }
}
