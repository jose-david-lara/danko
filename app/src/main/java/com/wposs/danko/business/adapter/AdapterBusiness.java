package com.wposs.danko.business.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wposs.danko.R;
import com.wposs.danko.business.interfaces.Business_adaper;
import com.wposs.danko.business.presenter.Business_presenter;
import com.wposs.danko.login.dto.BusinessDTO;
import com.wposs.danko.utils.Defines;

import java.util.ArrayList;

public class AdapterBusiness extends RecyclerView.Adapter<AdapterBusiness.Holder> {

    ArrayList<BusinessDTO> businessDTOS;
    Context context;
    int tipoCategoria;
    Business_adaper.Presenter presenter = new Business_presenter();

    public AdapterBusiness(ArrayList<BusinessDTO> businessDTOS, Context context, int tipoCategoria) {
        this.businessDTOS = businessDTOS;
        this.context = context;
        this.tipoCategoria = tipoCategoria;
    }

    @NonNull
    @Override
    public AdapterBusiness.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        switch (tipoCategoria) {
            case Defines.CATEGORIA_SALUD:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.info_bussines, null, false);
                break;

            case Defines.CATEGORIA_CENTROS_COMERCIALES:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.info_bussines, null, false);
                break;

            case Defines.CATEGORIA_EDUCACION:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.info_bussines, null, false);
                break;

            case Defines.CATEGORIA_TURISMO:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.info_bussines, null, false);
                break;

            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.info_bussines, null, false);
                break;
        }
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterBusiness.Holder holder, int position) {
        Glide.with(context).load(businessDTOS.get(position).getImg()).centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);
        holder.name.setText(businessDTOS.get(position).getName());
        holder.description.setText(businessDTOS.get(position).getDescription());
        holder.url.setText(businessDTOS.get(position).getUrl());

        holder.url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.setResponse(businessDTOS.get(position).getUrl(), businessDTOS.get(position).getName(), context);
            }
        });

    }

    @Override
    public int getItemCount() {
        return businessDTOS.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name, description, url;

        public Holder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_Business);
            name = itemView.findViewById(R.id.name_Business);
            description = itemView.findViewById(R.id.descripcion_Business);
            url = itemView.findViewById(R.id.url_Business);
        }
    }
}
