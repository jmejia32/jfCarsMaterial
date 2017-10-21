package co.jamesfl.apps.jfcars;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * Created by javie on 7/10/2017.
 */

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarroViewHolder> {
    private LinkedList<Carro> carros;
    private Resources res;
    private OnCarroClickListener listnr;

    public CarAdapter(Context ctx, LinkedList<Carro> carros, OnCarroClickListener listnr) {
        this.carros = carros;
        this.listnr = listnr;
        res = ctx.getResources();
    }

    @Override
    public CarroViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_carro,parent,false);
        return new CarroViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CarroViewHolder holder, int position) {
        final Carro c = carros.get(position);
        holder.ivFoto.setImageDrawable(ResourcesCompat.getDrawable(res, c.getFoto(), null));
        holder.tvPlaca.setText(c.getPlaca());
        holder.tvMarca.setText(c.getMarca());
        holder.tvModelo.setText(c.getModelo());
        holder.tvPrecio.setText(c.getPrecio()+"");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listnr.onCarroClick(c);
            }
        });
    }

    @Override
    public int getItemCount() { return carros.size(); }

    public static class CarroViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivFoto;
        private TextView tvPlaca, tvMarca, tvModelo, tvPrecio;


        public CarroViewHolder(View v) {
            super(v);
            ivFoto = v.findViewById(R.id.ivFoto);
            tvPlaca = v.findViewById(R.id.tvPlaca);
            tvMarca = v.findViewById(R.id.tvMarca);
            tvModelo = v.findViewById(R.id.tvModelo);
            tvPrecio = v.findViewById(R.id.tvPrecio);
        }
    }

    public interface OnCarroClickListener {
        void onCarroClick(Carro c);
    }
}
