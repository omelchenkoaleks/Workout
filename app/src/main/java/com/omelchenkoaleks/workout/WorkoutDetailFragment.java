package com.omelchenkoaleks.workout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class WorkoutDetailFragment extends Fragment {

    // идентификатор, он будет использован для заполнения представлений фрагмента
    private long workoutId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workout_detail, container, false);
    }

    // обновляем предствление фрагмента
    @Override
    public void onStart() {
        super.onStart();
        // без этого метода не получить корневой объект фрагмента, он нужен чтобы получить ссылку на вьюшки
        View view = getView();
        if (view != null) {
            TextView title = view.findViewById(R.id.textTitle);
            Workout workout = Workout.workouts[(int) workoutId];
            title.setText(workout.getName());
            TextView description = view.findViewById(R.id.textDescription);
            description.setText(workout.getDescription());
        }
    }

    // метод для присваивания идентификатора
    public void setWorkoutId(long id) {
        this.workoutId = id;
    }

}
