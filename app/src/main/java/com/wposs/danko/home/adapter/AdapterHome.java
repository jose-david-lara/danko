package com.wposs.danko.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wposs.danko.R;
import com.wposs.danko.home.interfaces.Home_interface;
import com.wposs.danko.home.presenter.HomePresenter;
import com.wposs.danko.login.dto.CategoriasDTO;

import java.util.ArrayList;

public class AdapterHome extends RecyclerView.Adapter<AdapterHome.ViewHolder> {

    ArrayList<CategoriasDTO> categoriasDTOS;
    Context context;

    private Home_interface.Presenter presenter = new HomePresenter();

    public AdapterHome(ArrayList<CategoriasDTO> categoriasDTOS, Context context) {
        this.categoriasDTOS = categoriasDTOS;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterHome.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_home, null, false);
         return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHome.ViewHolder holder, int position) {

        Glide.with(context).load(categoriasDTOS.get(position).getImg()).centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.image_categoria);
        holder.name_categoria.setText(categoriasDTOS.get(position).getName());

        holder.cardView_categoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.setResponse(categoriasDTOS.get(position).getBusinessDTOList(), categoriasDTOS.get(position).getId(), categoriasDTOS.get(position).getName(), context);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoriasDTOS.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image_categoria;
        TextView name_categoria;
        CardView cardView_categoria;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_categoria = itemView.findViewById(R.id.image_categoria);
            name_categoria = itemView.findViewById(R.id.name_categoria);
            cardView_categoria = itemView.findViewById(R.id.card_view);

        }
    }

}
