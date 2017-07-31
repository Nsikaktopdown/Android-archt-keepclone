package ng.com.nsikakthompson.android_archt_keepclone.ui.list;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import javax.inject.Inject;

import ng.com.nsikakthompson.android_archt_keepclone.AppController;
import ng.com.nsikakthompson.android_archt_keepclone.R;
import ng.com.nsikakthompson.android_archt_keepclone.Util.DatabaseInit;
import ng.com.nsikakthompson.android_archt_keepclone.adapter.TaskAdapter;
import ng.com.nsikakthompson.android_archt_keepclone.data.database.TaskDatabase;
import ng.com.nsikakthompson.android_archt_keepclone.data.entity.Task;
import ng.com.nsikakthompson.android_archt_keepclone.injection.TaskFactory;
import ng.com.nsikakthompson.android_archt_keepclone.viewModel.TaskListViewModel;

/**
 * A placeholder fragment containing a simple view.
 */
public class TaskListActivityFragment extends LifecycleFragment {

    public static String TAG = "Task_list_Fragment";
    private TaskAdapter taskAdapter;
    private TaskListViewModel taskListViewModel;
    private RecyclerView recyclerView;

    @Inject
    TaskDatabase taskDatabase;
    public TaskListActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_task_list, container, false);
        AppController appController = (AppController) getActivity().getApplication();
        taskListViewModel = ViewModelProviders.of(this, new TaskFactory(appController)).get(TaskListViewModel.class);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.task_list);
        taskAdapter = new TaskAdapter(getActivity(), new ArrayList<Task>());

        DatabaseInit.populateSyn(taskDatabase);


        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        taskListViewModel.getTasks().observe(this, task ->{
        taskAdapter.setItems(task);
            recyclerView.setAdapter(taskAdapter);
        });
    }
}
