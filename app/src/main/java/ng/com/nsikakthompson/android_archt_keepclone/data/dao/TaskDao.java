package ng.com.nsikakthompson.android_archt_keepclone.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import ng.com.nsikakthompson.android_archt_keepclone.data.entity.Task;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by NsikakTom on 7/25/2017.
 */
@Dao
public interface TaskDao {

    @Query("SELECT * FROM " + Task.TABLE_NAME )
    LiveData<List<Task>> getAllTask();

    @Insert(onConflict = REPLACE)
    void addTask(Task task);

    @Delete
    void deleteTask(Task task);

    @Update(onConflict = REPLACE)
    void updateTask(Task task);

    @Query("DELETE  FROM " + Task.TABLE_NAME)
    void deleteAll();


}
