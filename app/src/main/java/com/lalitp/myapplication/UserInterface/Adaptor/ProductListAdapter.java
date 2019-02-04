package com.lalitp.myapplication.UserInterface.Adaptor;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.lalitp.myapplication.Pojo.ProductList.ProductList;
import com.lalitp.myapplication.R;
import com.lalitp.myapplication.Utils.Common_Utils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {


    private List<ProductList> productList;
    private static OnItemClickListener mItemClickListener;

    private OnCheckedChangeListener onItemCheckedListener;

    public interface OnCheckedChangeListener {
        void onCheckedChanged(int position , boolean isChecked);
    }


    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        onItemCheckedListener = listener;
    }


    public ProductListAdapter(List<ProductList> resultList) {
        this.productList = resultList;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_product)
        ImageView imgProduct;
        @BindView(R.id.txt_product_name)
        TextView txtProductName;
       @BindView(R.id.txt_desc)
        TextView txtDesc;
        @BindView(R.id.switchOnOff)
        SwitchCompat switchCompat;


        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }

        @OnClick(R.id.lv_layout)
        public void onViewClicked() {
            if (mItemClickListener != null)
                mItemClickListener.onItemClick(getAdapterPosition());
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_product_list, parent, false);
        ViewHolder vh = new ViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ProductList product = productList.get(position);

        String pname = product.getPTitle();
        String desc = product.getPDesc();
        String p_image = product.getPImageurl();


        holder.txtProductName.setText(pname);
        holder.txtDesc.setText(desc);

        if (Common_Utils.isNotNullOrEmpty(p_image)) {
            Picasso.get()
                    .load(p_image)
                    .resize(50, 50)
                    .centerCrop()
                    .placeholder(R.drawable.default_img)
                    .error(R.drawable.default_img)
                    .into(holder.imgProduct);
        }

        holder.switchCompat.setChecked(product.getPIsnew());

            holder.switchCompat.setTag(position);
        holder.switchCompat.setOnCheckedChangeListener(mOnCheckedChangeListener);
    }

    @Override
    public int getItemCount() {
        //return 5;
        return productList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }


    public void setOnItemClickListener(
            final OnItemClickListener mitemClickListener) {
        this.mItemClickListener = mitemClickListener;
    }

    private CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            int position = (int) buttonView.getTag();

            if (onItemCheckedListener != null) {
                onItemCheckedListener.onCheckedChanged(position, isChecked);
            }
        }
    };
}