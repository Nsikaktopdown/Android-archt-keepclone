package ng.com.nsikakthompson.android_archt_keepclone.repository;

import android.arch.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import ng.com.nsikakthompson.android_archt_keepclone.data.database.TaskDatabase;
import ng.com.nsikakthompson.android_archt_keepclone.data.entity.Task;

/**
 * Created by NsikakTom on 7/31/2017.
 */

public class TaskRepositoryImpl implements  TaskRepository {

    @Inject
    TaskDatabase taskDatabase;


    public TaskRepositoryImpl(TaskDatabase taskDatabase) {
        this.taskDatabase = taskDatabase;
    }
    @Override
    public Completable addTask(Task task) {
        return  Completable.fromAction(() -> taskDatabase.taskDao().addTask(task));
    }

    @Override
    public LiveData<List<Task>> getTask() {
        return taskDatabase.taskDao().getAllTask();
    }

    @Override
    public Completable deleteTask(Task task) {
        return null;
    }
}
