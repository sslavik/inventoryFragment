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
    public interface OnManageDependencyListener {
        // Si se hace click en una dependencia se edita (onClickListener())
        void onUpdateDependency(Dependency dependency);
        // Si se hace una pulsacion larga se elimina la dependencia (onLongClickListener())
        void onDeleteDependency(Dependency dependency);
    }

    // CAMPOS
    private List<Dependency> listDependency;
    private OnManageDependencyListener listener;
    private Context context;

    /**
     * Los datos los vamos a obtener directamente desde el Repository
     */
    public DependencyAdapter(Context context, OnManageDependencyListener onClickHolderListener) {
        this.listDependency = new ArrayList<>();
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
                listener.onUpdateDependency(listDependency.get(position));
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.onDeleteDependency(listDependency.get(position));
                return true;
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

    public void clear(){
        listDependency.clear();
    }

    public void load(List<Dependency> dependencyList){
        listDependency.addAll(dependencyList);
    }
}
