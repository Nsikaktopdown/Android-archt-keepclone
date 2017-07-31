package ng.com.nsikakthompson.android_archt_keepclone.repository;

import android.arch.lifecycle.LiveData;

import java.util.List;

import io.reactivex.Completable;
import ng.com.nsikakthompson.android_archt_keepclone.data.entity.Task;

/**
 * Created by NsikakTom on 7/31/2017.
 */

public interface TaskRepository {
    Completable addTask(Task task);

    LiveData<List<Task>> getTask();

    Completable deleteTask(Task task);
}
