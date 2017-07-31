package ng.com.nsikakthompson.android_archt_keepclone.injection;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Component;
import ng.com.nsikakthompson.android_archt_keepclone.AppController;

/**
 * Created by NsikakTom on 7/31/2017.
 */

public class TaskFactory extends ViewModelProvider.NewInstanceFactory {

    private AppController application;

    public TaskFactory(AppController application) {
        this.application = application;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        T t = super.create(modelClass);
        if (t instanceof TaskComponent.Injectable) {
            ((TaskComponent.Injectable) t).inject(application.getTaskComponent());
        }
        return t;
    }
}