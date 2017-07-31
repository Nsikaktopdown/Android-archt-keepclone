package ng.com.nsikakthompson.android_archt_keepclone.data.database;

import android.arch.persistence.room.Database;

import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import ng.com.nsikakthompson.android_archt_keepclone.data.dao.TaskDao;
import ng.com.nsikakthompson.android_archt_keepclone.data.entity.Task;

/**
 * Created by NsikakTom on 7/25/2017.
 */
@Database(entities = {Task.class}, version = 1)
@TypeConverters(DateTypeConverter.class)
public abstract class TaskDatabase extends RoomDatabase {

    abstract TaskDao taskDao();


}
