package ng.com.nsikakthompson.android_archt_keepclone.viewModel;

import android.arch.lifecycle.ViewModel;
import android.support.design.widget.Snackbar;
import android.util.Log;

import javax.inject.Inject;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ng.com.nsikakthompson.android_archt_keepclone.callbacks.AddTaskViewContract;
import ng.com.nsikakthompson.android_archt_keepclone.data.entity.Task;
import ng.com.nsikakthompson.android_archt_keepclone.injection.TaskComponent;
import ng.com.nsikakthompson.android_archt_keepclone.repository.TaskRepository;
import timber.log.Timber;

/**
 * Created by NsikakTom on 7/31/2017.
 */

public class AddTaskViewModel extends ViewModel implements TaskComponent.Injectable {

    public static final String TAG = "AddTaskViewModel";

    AddTaskViewContract.View mView;

    @Inject
    TaskRepository repository;
    private String title;
    private String description;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void inject(TaskComponent taskComponent) {
        taskComponent.inject(this);
    }

    public void addTask() {
        Task task = new Task();
        task.setId(0);
        task.setTitle(title);
        task.setDescription(description);
        repository.addTask(task).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                        Log.v(TAG, "Task added Successfully");
                        // mView.showSuccessMessage("Task added Successfully");


                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v(TAG, "Error: task was not added");
                    }
                });
    }
}
