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
import com.sslavik.inventory.data.model.Section;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.ViewHolder> {

    // INTERFAZ

    // FIELDS

    List<Section> sectionList;
    Context context;

    public SectionAdapter(Context context) {
        this.sectionList = new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.section_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvNameSection.setText(sectionList.get(position).getName());
        holder.mliIconoSection.setLetter(sectionList.get(position).getName());

    }


    @Override
    public int getItemCount() {
        return sectionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // FIELDS VIEWS
        MaterialLetterIcon mliIconoSection;
        TextView tvNameSection;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mliIconoSection = itemView.findViewById(R.id.mliIconoSection);
            tvNameSection = itemView.findViewById(R.id.tvNameSection);
        }
    }

    // METODOS ADAPTER

    public void clear(){
        sectionList.clear();
        notifyDataSetChanged();
    }

    public void add(Section section){
        sectionList.add(section);
        notifyDataSetChanged();
    }

    public void delete(Section section){
        sectionList.remove(section);
        notifyDataSetChanged();
    }

    public void load(List<Section> sectionList){
        this.sectionList = sectionList;
        notifyDataSetChanged();
    }

    public void edit(Section section){
        for (int i = 0; i < sectionList.size(); i++) {
            if(sectionList.get(i).equals(section))
                sectionList.set(i,section);
        }
        notifyDataSetChanged();
    }
}
