package co.jamesfl.apps.jfcars;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.res.ResourcesCompat;
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

public class CarAdapter extends BaseAdapter {
    private Context ctx;
    private LinkedList<Carro> carros;
    private Resources res;

    public CarAdapter(Context ctx, LinkedList<Carro> carros) {
        this.ctx = ctx;
        this.carros = carros;
        res = ctx.getResources();
    }

    @Override
    public int getCount() {
        return carros.size();
    }

    @Override
    public Object getItem(int i) {
        return carros.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater li = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = li.inflate(R.layout.item_carro, null);
        Carro c = carros.get(i);
        ImageView ivFoto = v.findViewById(R.id.ivFoto);
        TextView tvPlaca = v.findViewById(R.id.tvPlaca);
        TextView tvMarca = v.findViewById(R.id.tvMarca);
        TextView tvModelo = v.findViewById(R.id.tvModelo);
        TextView tvPrecio = v.findViewById(R.id.tvPrecio);

        ivFoto.setImageDrawable(ResourcesCompat.getDrawable(res, c.getFoto(), null));
        tvPlaca.setText(c.getPlaca());
        tvMarca.setText(c.getMarca());
        tvModelo.setText(c.getModelo());
        tvPrecio.setText(c.getPrecio()+"");

        return v;
    }
}
