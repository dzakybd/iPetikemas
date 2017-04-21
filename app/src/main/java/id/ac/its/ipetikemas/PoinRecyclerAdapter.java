package id.ac.its.ipetikemas;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ilham Aulia Majid on 19-Apr-17.
 */

public class PoinRecyclerAdapter extends RecyclerView.Adapter<PoinRecyclerAdapter.ViewHolder> {

    Context context;
    List<Poin_Item> poin_items;


    public PoinRecyclerAdapter(Context context, List<Poin_Item> poin_items) {
        this.context = context;
        this.poin_items = poin_items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.poin_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Poin_Item poin_item = poin_items.get(position);
        holder.nocontainer.setText(poin_item.nocontainer);
        holder.kecepatan.setText(String.valueOf(poin_item.kecepatan));
        holder.pengiriman.setText(String.valueOf(poin_item.pengiriman));
        holder.waktu.setText(String.valueOf(poin_item.waktu));
        holder.total.setText(String.valueOf(poin_item.kecepatan+poin_item.waktu+poin_item.pengiriman));
//        holder.view.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent intent = new Intent(context, Lihat.class);
//                context.startActivity(intent);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return poin_items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.nocontainer)
        TextView nocontainer;
        @BindView(R.id.waktu)
        TextView waktu;
        @BindView(R.id.kecepatan)
        TextView kecepatan;
        @BindView(R.id.pengiriman)
        TextView pengiriman;
        @BindView(R.id.total)
        TextView total;
        @BindView(R.id.view)
        CardView view;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
