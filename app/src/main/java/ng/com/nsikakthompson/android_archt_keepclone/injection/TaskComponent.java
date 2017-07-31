package ng.com.nsikakthompson.android_archt_keepclone.injection;

import javax.inject.Singleton;

import dagger.Component;
import ng.com.nsikakthompson.android_archt_keepclone.viewModel.AddTaskViewModel;
import ng.com.nsikakthompson.android_archt_keepclone.viewModel.TaskListViewModel;

/**
 * Created by NsikakTom on 7/31/2017.
 */
@Singleton
@Component(modules = {CountdownModule.class})
public interface TaskComponent {

        void inject(TaskListViewModel taskListViewModel);

        void inject(AddTaskViewModel addTaskViewModel);

        interface Injectable {
            void inject(TaskComponent taskComponent);
        }
    }

}
