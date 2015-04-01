package capr.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import capr.com.beans.Tipo_DTO;
import capr.com.glup.R;
import capr.com.util.Util_Fonts;

public class Adapter_Modelo extends BaseAdapter {

    private Context mContext;
    private ArrayList<Tipo_DTO> tipo_dtos;
    private LayoutInflater inflater;

    public Adapter_Modelo(Context mContext, ArrayList<Tipo_DTO> tipo_dtos) {
        this.mContext = mContext;
        this.tipo_dtos = tipo_dtos;
        this.inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return tipo_dtos.size();
    }

    @Override
    public Object getItem(int item) {
        return tipo_dtos.get(item);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View view, ViewGroup container) {
        Holder holder = null;
        Tipo_DTO opcion_dto = tipo_dtos.get(position);

        if (view == null) {
            view = inflater.inflate(R.layout.item_modelo, container, false);
            holder = new Holder();

            holder.name = (TextView) view.findViewById(R.id.option);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }

        holder.name.setText(opcion_dto.getTitle());
        holder.name.setTypeface(Util_Fonts.setPNASemiBold(mContext));

        return view;
    }

    static class Holder {
        TextView name;
    }
}