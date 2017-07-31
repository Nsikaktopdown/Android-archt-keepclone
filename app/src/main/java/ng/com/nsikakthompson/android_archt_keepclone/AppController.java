package ng.com.nsikakthompson.android_archt_keepclone;

import android.app.Application;

import com.jakewharton.threetenabp.AndroidThreeTen;

import timber.log.Timber;

/**
 * Created by NsikakTom on 7/31/2017.
 */

public class AppController extends Application {

    private final CountdownComponent countDownComponent = createCountdownComponent();

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidThreeTen.init(this);

    }

    protected CountdownComponent createCountdownComponent() {
        return DaggerCountdownComponent.builder()
                .countdownModule(new CountdownModule(this))
                .build();
    }

    public CountdownComponent getCountDownComponent() {
        return countDownComponent;
    }

}