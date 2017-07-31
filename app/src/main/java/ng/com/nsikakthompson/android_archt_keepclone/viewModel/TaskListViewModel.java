package ng.com.nsikakthompson.android_archt_keepclone.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import ng.com.nsikakthompson.android_archt_keepclone.data.database.TaskDatabase;
import ng.com.nsikakthompson.android_archt_keepclone.data.entity.Task;
import ng.com.nsikakthompson.android_archt_keepclone.injection.TaskComponent;
import ng.com.nsikakthompson.android_archt_keepclone.repository.TaskRepository;

/**
 * Created by NsikakTom on 7/31/2017.
 */

public class TaskListViewModel extends ViewModel implements TaskComponent.Injectable {

    @Inject
    TaskRepository taskRepository;
    LiveData<List<Task>> tasks = new MutableLiveData<>();

    @Override
    public void inject(TaskComponent taskComponent) {
        taskComponent.inject(this);
        tasks = taskRepository.getTask();
    }

    public LiveData<List<Task>> getTasks(){
        return  tasks;
    }
}
