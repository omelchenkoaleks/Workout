package com.omelchenkoaleks.workout;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorkoutListFragment extends ListFragment {

    //определяем интерфейс здесь, чтобы дать этому фрагменту взаимодействовать с любой активностью
    interface Listener {
        void itemClicked(long id);
    }


    // необходимо сохранить ссылку на активность, с которой связывается фрагмент
    private Listener listener;

    public WorkoutListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // создаем строковый массив с нужными названиями (name)
        String[] names = new String[Workout.workouts.length];
        for (int i = 0; i < names.length; i++) {
            names[i] = Workout.workouts[i].getName();
        }

        // создаем адаптер массива
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                inflater.getContext(), android.R.layout.simple_list_item_1,
                names);
        // связываем адаптер массива со списковым представлением
        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    // значение переменной listener должно задаваться при связывании фрагмента с активностью - это
    // происходит при вызове метода onAttach() - контекст здесь активность с которой связывается
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.listener = (Listener) context;
    }

    // метод вызвается, когда пользователь выбирает какой-то вариант
    // c помощью этого метода можно передать слущателю идентификатор
    @Override
    public void onListItemClick(ListView listView, View itemView, int position, long id) {
        if (listener != null) {
            listener.itemClicked(id);
        }
    }
}
