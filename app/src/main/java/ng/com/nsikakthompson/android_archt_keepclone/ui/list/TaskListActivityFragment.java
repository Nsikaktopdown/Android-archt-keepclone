package ng.com.nsikakthompson.android_archt_keepclone.ui.list;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import javax.inject.Inject;

import ng.com.nsikakthompson.android_archt_keepclone.AppController;
import ng.com.nsikakthompson.android_archt_keepclone.MainActivity;
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
public class TaskListActivityFragment extends LifecycleFragment  {

    public static String TAG = "Task_list_Fragment";
    private TaskAdapter taskAdapter;
    private TaskListViewModel taskListViewModel;
    private RecyclerView recyclerView;

    @Inject
    TaskDatabase taskDatabase;

    private View.OnClickListener deleteClickListener = rootView -> {
        Task task = (Task) rootView.getTag();
        taskListViewModel.deleteTask(task);

        Toast.makeText(getContext(), "Task deleted", Toast.LENGTH_SHORT).show();
    };

    private View.OnClickListener itemClickListener = rootView -> {
        Task task = (Task) rootView.getTag();

        Toast.makeText(getContext(), "Clicked:" + task.getTitle(), Toast.LENGTH_LONG).show();
    };
    public TaskListActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_task_list, container, false);

        setHasOptionsMenu(true);
        AppController appController = (AppController) getActivity().getApplication();
        taskListViewModel = ViewModelProviders.of(this, new TaskFactory(appController)).get(TaskListViewModel.class);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.task_list);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, 1);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        taskAdapter = new TaskAdapter(getActivity(), new ArrayList<>(), itemClickListener, deleteClickListener);


        taskListViewModel.getTasks().observe(this, tasks ->{
            taskAdapter.setItems(tasks);
            recyclerView.setAdapter(taskAdapter);
        });
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_task_list, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.delete_all_action:
                taskListViewModel.deleteAllTask();
                getActivity().finish();
                // do stuff
                return true;


        }

        return false;
    }
}
