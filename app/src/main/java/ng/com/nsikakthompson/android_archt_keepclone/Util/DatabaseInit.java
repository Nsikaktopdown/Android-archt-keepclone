package ng.com.nsikakthompson.android_archt_keepclone.Util;

import android.arch.persistence.room.Database;
import android.os.AsyncTask;

import javax.inject.Inject;

import ng.com.nsikakthompson.android_archt_keepclone.data.database.TaskDatabase;
import ng.com.nsikakthompson.android_archt_keepclone.data.entity.Task;

import static android.R.attr.id;
import static android.R.attr.name;
import static android.R.attr.titleTextAppearance;

/**
 * Created by NsikakTom on 7/31/2017.
 */

public  class DatabaseInit {


    public static void populateSyn(final TaskDatabase dbm){

        PopulateDbAsyncTask populateDbAsyncTask = new PopulateDbAsyncTask(dbm);
        populateDbAsyncTask.execute();
    }
    private static Task addUser(final TaskDatabase db,final String title,
                                final String description) {
        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        db.taskDao().addTask(task);
        return task;
    }

    public static void populateDummyData(TaskDatabase db){
//        db.taskDao().deleteAll();

        Task  task = addUser(db, "ForLoopUyo", "This is the Maiden Edititon of ForLoopUyo happening in Uyo this weekend");
        addUser(db, "ForLoopUyo", "This is the Maiden Edititon of ForLoopUyo happening in Uyo this weekend");


    }
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

        private final TaskDatabase mDb;

        PopulateDbAsyncTask(TaskDatabase db) {
            mDb = db;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            populateDummyData(mDb);
            return null;
        }

    }
}
