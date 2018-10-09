package com.omelchenkoaleks.workout;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TempActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        // создаем транзакцию фрагмента для добавления экземпляра StopwatchFragment в TempActivity
        // проверяем - фрагмент должен быть добавлент только в том случае, если активность не создается
        // заново после уничтожения:
        if (savedInstanceState == null) {
            StopwatchFragment stopwatchFragment = new StopwatchFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.stopwatch_container, stopwatchFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            fragmentTransaction.commit();
        }
    }
}
