package id.ac.its.ipetikemas;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ilham Aulia Majid on 19-Apr-17.
 */

public class PesanRecyclerAdapter extends RecyclerView.Adapter<PesanRecyclerAdapter.ViewHolder> {

    Context context;
    List<Pesan_Item> pesans;

    public PesanRecyclerAdapter(Context context, List<Pesan_Item> pesans) {
        this.context = context;
        this.pesans = pesans;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pesan_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Pesan_Item pesan = pesans.get(position);
        holder.image.setImageResource(pesan.foto);
        holder.konten.setText(pesan.konten);
        holder.nama.setText(pesan.nama);
        holder.waktu.setText(pesan.waktu);
//        holder.view.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent intent = new Intent(context, Lihat.class);
//                context.startActivity(intent);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return pesans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.nama)
        TextView nama;
        @BindView(R.id.waktu)
        TextView waktu;
        @BindView(R.id.konten)
        TextView konten;
        @BindView(R.id.view)
        CardView view;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
