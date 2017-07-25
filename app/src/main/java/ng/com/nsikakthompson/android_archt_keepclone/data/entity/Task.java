package ng.com.nsikakthompson.android_archt_keepclone.data.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import static ng.com.nsikakthompson.android_archt_keepclone.data.entity.Task.STATUS;
import static ng.com.nsikakthompson.android_archt_keepclone.data.entity.Task.TABLE_NAME;

/**
 * Created by NsikakTom on 7/25/2017.
 */
@Entity(tableName = TABLE_NAME)
public class Task {

    public static final String TABLE_NAME = "task";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String STATUS = "status";

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = TITLE)
    private String title;
    @ColumnInfo(name = DESCRIPTION)
    private String description;
    @ColumnInfo(name = STATUS)
    private String status;

    public Task(int id, String title, String description, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

}
