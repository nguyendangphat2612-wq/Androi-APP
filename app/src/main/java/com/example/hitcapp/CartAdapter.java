package com.example.hitcapp;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    public interface OnCartChangedListener {
        void onCartChanged();
    }

    private Context context;
    private List<CartItem> cartList;
    private OnCartChangedListener listener;

    public CartAdapter(Context context, List<CartItem> cartList, OnCartChangedListener listener) {
        this.context = context;
        this.cartList = cartList;
        this.listener = listener;
    }

    private String formatPrice(int price) {
        NumberFormat format = NumberFormat.getInstance(new Locale("vi", "VN"));
        return format.format(price) + " đ";
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem item = cartList.get(position);

        holder.tvCartName.setText(item.getProduct().getName());
        holder.tvCartPrice.setText(formatPrice(item.getProduct().getPrice()));
        holder.tvCartQty.setText(String.valueOf(item.getQuantity()));
        holder.tvCartSubTotal.setText("Tạm tính: " + formatPrice(item.getSubTotal()));

        holder.btnPlus.setOnClickListener(v -> {
            CartManager.increaseQty(holder.getAdapterPosition());
            notifyDataSetChanged();
            listener.onCartChanged();
        });

        holder.btnMinus.setOnClickListener(v -> {
            CartManager.decreaseQty(holder.getAdapterPosition());
            notifyDataSetChanged();
            listener.onCartChanged();
        });

        holder.btnRemove.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Xóa sản phẩm")
                    .setMessage("Bạn muốn xóa sản phẩm này khỏi giỏ hàng?")
                    .setPositiveButton("Xóa", (dialog, which) -> {
                        CartManager.removeItem(holder.getAdapterPosition());
                        notifyDataSetChanged();
                        listener.onCartChanged();
                    })
                    .setNegativeButton("Hủy", null)
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        CardView cardCart;
        TextView tvCartName, tvCartPrice, tvCartQty, tvCartSubTotal;
        Button btnPlus, btnMinus, btnRemove;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            cardCart = itemView.findViewById(R.id.cardCart);
            tvCartName = itemView.findViewById(R.id.tvCartName);
            tvCartPrice = itemView.findViewById(R.id.tvCartPrice);
            tvCartQty = itemView.findViewById(R.id.tvCartQty);
            tvCartSubTotal = itemView.findViewById(R.id.tvCartSubTotal);
            btnPlus = itemView.findViewById(R.id.btnPlus);
            btnMinus = itemView.findViewById(R.id.btnMinus);
            btnRemove = itemView.findViewById(R.id.btnRemove);
        }
    }
}