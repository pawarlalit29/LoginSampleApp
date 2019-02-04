package com.lalitp.myapplication.UserInterface.Adaptor;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.lalitp.myapplication.Pojo.LogsResult;
import com.lalitp.myapplication.R;
import com.lalitp.myapplication.Utils.Common_Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LogsCursorAdapter extends RecyclerView.Adapter<LogsCursorAdapter.ViewHolder> {


    private List<LogsResult> logsResults;
    private OnItemClickListener mItemClickListener;
    private int lastPosition = -1;

    public LogsCursorAdapter(List<LogsResult> resultList) {
        this.logsResults = resultList;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.txt_title)
        TextView txtTitle;
        @BindView(R.id.txt_message)
        TextView txtMessage;
        @BindView(R.id.txt_timestamp)
        TextView txtTimestamp;
        @BindView(R.id.lv_main)
        RelativeLayout lvMain;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(getAdapterPosition());
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(
            final OnItemClickListener mitemClickListener) {
        this.mItemClickListener = mitemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_store_logs_list_row, parent, false);
        ViewHolder vh = new ViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        /** onRefresh Animation added*/
        LogsResult logsResult = logsResults.get(position);

        holder.txtTitle.setText("User logged in  with '" + logsResult.getUsername()+"'.");

        holder.txtMessage.setText(logsResult.getMessage());
        holder.txtTimestamp.setText(Common_Utils.getTimeStamp(logsResult.getTimestamp()));




    }

    @Override
    public int getItemCount() {
        return logsResults.size();
    }




}