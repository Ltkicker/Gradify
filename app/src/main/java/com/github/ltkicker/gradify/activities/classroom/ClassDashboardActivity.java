package com.github.ltkicker.gradify.activities.classroom;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.github.ltkicker.gradify.R;
import com.github.ltkicker.gradify.fragments.PopupFragment;

public class ClassDashboardActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classroom);
        showPopup();

    }

    private void showPopup() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.popuptemp, new PopupFragment());
        fragmentTransaction.commit();
    }
}
