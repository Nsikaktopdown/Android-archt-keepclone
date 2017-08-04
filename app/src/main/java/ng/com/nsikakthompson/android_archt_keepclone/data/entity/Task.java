package ng.com.nsikakthompson.android_archt_keepclone.data.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import org.threeten.bp.LocalDateTime;

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
    public static final String DATE_FIELD = "date";

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = TITLE)
    private String title;
    @ColumnInfo(name = DESCRIPTION)
    private String description;



    /*@ColumnInfo(name = DATE_FIELD)
    private LocalDateTime date;*/

    //constructor
    public Task(){

    }
    public Task(int id, String title, String description, String status) {
        this.id = id;
        this.title = title;
        this.description = description;

    }
    /*public LocalDateTime getDate() {
        return date;
    }*/

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



    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
