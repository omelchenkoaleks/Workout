package com.omelchenkoaleks.workout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_WORKOUT_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // ссылку на фрагмент можно получить только после вызова метода setContentView() -
        // до этого фрагмент еще не создан
        // используем диспетчер фрагментов и идентификатор фрагмента, чтобы получить ссылку на фрагмент
        WorkoutDetailFragment workoutDetailFragment = (WorkoutDetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.detail_fragment);
        // теперь можно приказать вывести нужную информацию по id
        int workoutId = (int) getIntent().getExtras().get(EXTRA_WORKOUT_ID);
        workoutDetailFragment.setWorkoutId(workoutId);
    }
}
