package ng.com.nsikakthompson.android_archt_keepclone.injection;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ng.com.nsikakthompson.android_archt_keepclone.AppController;
import ng.com.nsikakthompson.android_archt_keepclone.data.database.TaskDatabase;
import ng.com.nsikakthompson.android_archt_keepclone.repository.TaskRepository;
import ng.com.nsikakthompson.android_archt_keepclone.repository.TaskRepositoryImpl;

/**
 * Created by NsikakTom on 7/31/2017.
 */

@Module
public class TaskModule {

    private AppController appController;

    public TaskModule(AppController appController) {
        this.appController = appController;
    }

    @Provides
    Context applicationContext() {
        return appController;
    }

    @Provides
    @Singleton
    TaskRepository providesTaskRepo(TaskDatabase taskDatabase) {
        return new TaskRepositoryImpl(taskDatabase);
    }

    @Provides
    @Singleton
    TaskDatabase providesTaskDatabase(Context context) {
        return Room.databaseBuilder(context.getApplicationContext(), TaskDatabase.class, "task_db").build();
    }
}