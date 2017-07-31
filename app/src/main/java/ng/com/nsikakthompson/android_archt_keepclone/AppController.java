package ng.com.nsikakthompson.android_archt_keepclone;

import android.app.Application;

import com.jakewharton.threetenabp.AndroidThreeTen;

import ng.com.nsikakthompson.android_archt_keepclone.injection.DaggerTaskComponent;
import ng.com.nsikakthompson.android_archt_keepclone.injection.TaskComponent;
import ng.com.nsikakthompson.android_archt_keepclone.injection.TaskModule;


/**
 * Created by NsikakTom on 7/31/2017.
 */

public class AppController extends Application {

    private final TaskComponent taskComponent = createTaskComponent();

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidThreeTen.init(this);
    }

    protected TaskComponent createTaskComponent() {
        return DaggerTaskComponent.builder()
                .taskModule(new TaskModule(this))
                .build();
    }

    public TaskComponent getTaskComponent() {
        return taskComponent;
    }

}