package ng.com.nsikakthompson.android_archt_keepclone.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ng.com.nsikakthompson.android_archt_keepclone.data.database.TaskDatabase;
import ng.com.nsikakthompson.android_archt_keepclone.data.entity.Task;
import ng.com.nsikakthompson.android_archt_keepclone.injection.TaskComponent;
import ng.com.nsikakthompson.android_archt_keepclone.repository.TaskRepository;
import timber.log.Timber;

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

    public void deleteTask(Task task) {
        taskRepository.deleteTask(task)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                        Log.e("TaskListViewModel", "task deleted");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
    public void deleteAllTask() {
        taskRepository.deleteAllTask()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                        Log.e("TaskListViewModel", "All task deleted");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
}
