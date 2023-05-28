package com.example.apiddb;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class movieAdapter extends RecyclerView.Adapter<movieAdapter.MyViewHolder> {

    private Context context;
    private List<movieModel> codeList;
    private MovieAdapterlistener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public TextView tvname ,tvtahun;
        public ImageView imageview;

        public MyViewHolder(View view) {
            super(view);
            tvname = view.findViewById(R.id.tvname);
            tvtahun = view.findViewById(R.id.tvtahun);
            imageview = view.findViewById(R.id.movieimage);

            String imageUrl = "https://image.tmdb.org/t/p/w500";
            Glide.with(view.getContext())
                    .load(imageUrl)
                    .into(imageview);

            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onContactSelected(codeList.get(getAdapterPosition()));
        }

        @Override
        public boolean onLongClick(View v) {
            PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
            popupMenu.inflate(R.menu.popup_menu);

            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.menu_delete:
                            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                            builder.setTitle("Delete Item");
                            builder.setMessage("Are you sure you want to delete this item?");
                            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    deleteItem(getAdapterPosition());
                                }
                            });
                            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });

                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                            return true;
                        default:
                            return false;
                    }
                }
            });

            popupMenu.show();

            return true;
        }
    }

    public movieAdapter(Context context, List<movieModel> codeList, MovieAdapterlistener listener) {
        this.context = context;
        this.listener = listener;
        this.codeList = codeList;
    }

    @NonNull
    @Override
    public movieAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull movieAdapter.MyViewHolder holder, int position) {
        final movieModel code = this.codeList.get(position);
        holder.tvname.setText(code.getFilmName());
        holder.tvtahun.setText(code.getDate());
        Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500" + code.getLogoFilm()).into(holder.imageview);
    }


    @Override
    public int getItemCount() {
        return this.codeList.size();
    }

    public interface MovieAdapterlistener {
        void onContactSelected(movieModel contact);

        void onLongClickListener(movieModel film);
    }

    private void deleteItem(int position) {
        codeList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, codeList.size());
    }
}
