package com.omelchenkoaleks.workout;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Locale;

public class StopwatchFragment extends Fragment implements View.OnClickListener{

    // хранится текущее количество секунд
    private int seconds = 0;
    // хранит значение работает ли секундомер в настоящий момент = Флаг работы
    private boolean running;
    // хранит информацию о том, работал ли секундомер перед вызовом метода onStop()
    private boolean wasRunning;

    public StopwatchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // восстанавливаем состояние активности по значениям, прочитанным из Bundle
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
    }

    // метод для сохранения значений переменных для их будущего востановления вместе с активностью
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_stopwatch, container, false);
        runTimer(layout);
        return layout;
    }

    // если активность приостанавливается, остановить отсчет времени
    @Override
    public void onPause() {
        super.onPause();
        // сохраняем информацию о том, работал ли секундомер на момент вызова метода onStop()
        wasRunning = running;
        running = false;
    }

    // если активность возобновляет работу, снова запустить отсчет времени, если он происходил до этого
    @Override
    public void onResume() {
        super.onResume();
        if (wasRunning) {
            running = true;
        }
    }

    // чтобы обновить секундомер планируем многократное выполнение кода с использованием Handler
    private void runTimer(View view) {
        final TextView timeView = view.findViewById(R.id.time_view);
        final Handler handler = new Handler();
        // вызов метода post() обеспечивает выполнение без задержки
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;
                String time = String.format(Locale.getDefault(),
                        "%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);
                if (running) {
                    seconds++;
                }
                // код передается на повторное исполнение через 1 секунду
                handler.postDelayed(this, 1000);
            }
        });
    }

    private void onClickStart() {
        running = true;
    }

    private void onClickStop() {
        running = false;
    }

    private void onClickReset() {
        // останавливаем секундомер
        running = false;
        // обнуляем секундомер
        seconds = 0;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_button:
                onClickStart();
                break;
            case R.id.stop_button:
                onClickStop();
                break;
            case R.id.reset_button:
                onClickReset();
                break;
        }
    }
}
