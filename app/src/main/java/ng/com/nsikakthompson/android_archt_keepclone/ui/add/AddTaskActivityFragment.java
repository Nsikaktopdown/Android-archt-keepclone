package ng.com.nsikakthompson.android_archt_keepclone.ui.add;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProviders;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ng.com.nsikakthompson.android_archt_keepclone.AppController;
import ng.com.nsikakthompson.android_archt_keepclone.R;
import ng.com.nsikakthompson.android_archt_keepclone.callbacks.AddTaskViewContract;
import ng.com.nsikakthompson.android_archt_keepclone.injection.TaskFactory;
import ng.com.nsikakthompson.android_archt_keepclone.viewModel.AddTaskViewModel;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddTaskActivityFragment extends LifecycleFragment implements AddTaskViewContract.View {

    private AddTaskViewModel addTaskViewModel;
    private EditText add_title_edtxt, add_description_edtxt;
    private String title = "";
    private String description = "";
    View rootView;

    public AddTaskActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         rootView = inflater.inflate(R.layout.fragment_add_task, container, false);
        setHasOptionsMenu(true);
        setupViewModel();
        add_title_edtxt = rootView.findViewById(R.id.add_title_edt);
        add_description_edtxt = rootView.findViewById(R.id.add_description_edt);

        return rootView;
    }

    private void setupViewModel() {
        AppController application = (AppController) getActivity().getApplication();
        addTaskViewModel = ViewModelProviders.of(this, new TaskFactory(application))
                .get(AddTaskViewModel.class);
    }

    private Completable saveTaskData() {
        title = add_title_edtxt.getText().toString();
        description = add_description_edtxt.getText().toString();
        addTaskViewModel.setTitle(title);
        addTaskViewModel.setDescription(description);
       return Completable.fromAction(()-> addTaskViewModel.addTask());
    }

    @Override
    public void showSuccessMessage(String message) {
        //Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_add_task, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.save_task:
                saveTaskData().observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new CompletableObserver() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onComplete() {
                            Snackbar.make(rootView, "Task Added", Snackbar.LENGTH_LONG)
                                    .setAction("DONE", null).show();
                            getActivity().finish();



                        }

                        @Override
                        public void onError(Throwable e) {

                        }
                    });


                // do stuff
                return true;


        }

        return false;
    }
}
