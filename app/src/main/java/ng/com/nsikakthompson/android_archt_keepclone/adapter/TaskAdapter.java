package ng.com.nsikakthompson.android_archt_keepclone.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ng.com.nsikakthompson.android_archt_keepclone.R;
import ng.com.nsikakthompson.android_archt_keepclone.data.entity.Task;

/**
 * Created by NsikakTom on 7/31/2017.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {

    private List<Task> taskList;
    private Context context;
    private View.OnClickListener deleteClickListener;
    private View.OnClickListener viewClickListener;


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title, description;
        ImageButton delete_button;

        public MyViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.task_title);
            description = (TextView) view.findViewById(R.id.task_description);
            delete_button = view.findViewById(R.id.delete_button);

        }

    }


    public TaskAdapter(Context mContext, List<Task> taskList, View.OnClickListener viewClickListener, View.OnClickListener deleteClickListener) {

        this.context = mContext;
        this.taskList = taskList;
        this.deleteClickListener = deleteClickListener;
        this.viewClickListener = viewClickListener;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        Task item = taskList.get(position);
        holder.itemView.setTag(item);
        holder.title.setText(item.getTitle());
        holder.description.setText(item.getDescription());
        holder.itemView.setOnClickListener(viewClickListener);
        holder.delete_button.setOnClickListener(deleteClickListener);
        holder.delete_button.setTag(item);


    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

   public void setItems(List<Task> tasks) {
        this.taskList = tasks;
        notifyDataSetChanged();
    }
}