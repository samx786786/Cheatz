package com.cheatz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class syllabusrecycleradapter extends RecyclerView.Adapter<syllabusrecycleradapter.ViewHolder> {

    public List<syllabusmodel> mainList;
    public Context context;
    public syllabusrecycleradapter(List<syllabusmodel> notifList) {
        this.mainList=notifList;
    }


    @NonNull
    @Override
    public syllabusrecycleradapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.selectorlayot, parent, false);
        context = parent.getContext();
        return new syllabusrecycleradapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull syllabusrecycleradapter.ViewHolder holder, int position) {
        String syllabus=mainList.get(position).getSyllabus();
        holder.textview.setText(syllabus);
    }

    @Override
    public int getItemCount() {
        return mainList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textview;
        View mView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView=itemView;
            textview=mView.findViewById(R.id.textviewselctor);
        }
    }
}
