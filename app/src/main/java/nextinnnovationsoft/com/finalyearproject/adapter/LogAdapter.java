package nextinnnovationsoft.com.finalyearproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import nextinnnovationsoft.com.finalyearproject.R;
import nextinnnovationsoft.com.finalyearproject.model.Log;

/**
 * Created by PT on 5/28/2017.
 */

public class LogAdapter extends RecyclerView.Adapter<LogAdapter.ViewHolder> {

    ArrayList<Log> logList ;
    Context context ;

    public LogAdapter(Context context , ArrayList<Log>logList){
        this.context = context ;
        this.logList = logList ;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_log, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.appilanceName.setText(logList.get(position).getApplianceName());
        holder.currentDate.setText(logList.get(position).getDate());
        holder.currentTime.setText(logList.get(position).getTime());
        holder.request.setText(logList.get(position).getUpdateRequest());
        holder.status.setText(logList.get(position).getStatus());

    }

    @Override
    public int getItemCount() {
        return logList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView appilanceName , currentTime , currentDate , request , status ;
        public ViewHolder(View itemView) {
            super(itemView);

            appilanceName = (TextView) itemView.findViewById(R.id.appilance_name);
            currentTime = (TextView) itemView.findViewById(R.id.time);
            currentDate = (TextView) itemView.findViewById(R.id.date);
            request = (TextView) itemView.findViewById(R.id.update_rquest);
            status = (TextView) itemView.findViewById(R.id.status);

        }
    }
}
