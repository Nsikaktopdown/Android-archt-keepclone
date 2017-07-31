package ng.com.nsikakthompson.android_archt_keepclone.data.database;

import android.arch.persistence.room.TypeConverter;

import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneOffset;

/**
 * Created by NsikakTom on 7/31/2017.
 */

public class DateTypeConverter {

    @TypeConverter
    public static LocalDateTime toDate(Long timestamp) {
        return timestamp == null ? null : LocalDateTime.ofEpochSecond(timestamp, 0, ZoneOffset.ofTotalSeconds(0));
    }

    @TypeConverter
    public static Long toTimestamp(LocalDateTime date) {
        return date == null ? null : date.toInstant(ZoneOffset.ofTotalSeconds(0)).getEpochSecond();
    }
}