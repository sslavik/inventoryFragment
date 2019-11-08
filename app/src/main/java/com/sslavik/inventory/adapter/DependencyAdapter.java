package com.sslavik.inventory.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ivbaranov.mli.MaterialLetterIcon;
import com.sslavik.inventory.R;
import com.sslavik.inventory.data.model.Dependency;
import com.sslavik.inventory.data.repository.DependencyRepository;

import java.util.ArrayList;
import java.util.List;


public class DependencyAdapter extends RecyclerView.Adapter<DependencyAdapter.ViewHolder> {

    // INTREFACE
    public interface OnClickHolderListener{
        void onClick(Dependency dependency);
    }

    // CAMPOS
    private List<Dependency> listDependency;
    private OnClickHolderListener listener;
    private Context context;

    /**
     * Los datos los vamos a obtener directamente desde el Repository
     */
    public DependencyAdapter(Context context, OnClickHolderListener onClickHolderListener) {
        this.listDependency = DependencyRepository.getInstance().getList();
        this.listener = onClickHolderListener;
        this.context = context;
    }

    @NonNull
    @Override
    public DependencyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dependency_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DependencyAdapter.ViewHolder holder, final int position) {
        holder.mliIcono.setLetter(listDependency.get(position).getName());
        holder.tvName.setText(listDependency.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(listDependency.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listDependency.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        MaterialLetterIcon mliIcono;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            mliIcono = itemView.findViewById(R.id.mliIcono);
        }
    }
}
