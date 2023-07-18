package com.group2.eshopfe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.group2.eshopfe.R;
import com.group2.eshopfe.activity.MainActivity;
import com.group2.eshopfe.modul.Cart;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Cart> arrayListCart;

    public CartAdapter(Context context, ArrayList<Cart> arrayListCart) {
        this.context = context;
        this.arrayListCart = arrayListCart;
    }


    @Override
    public int getCount() {
        return arrayListCart.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayListCart.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        public TextView txtNameCart,txtPriceCard;
        public ImageView imgCart;
        public Button btleft,btvalue,btright;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if(view==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.cart_directory,null);
            viewHolder.txtNameCart=view.findViewById(R.id.textviewtenmonhang);
            viewHolder.txtPriceCard=view.findViewById(R.id.textviewgiamonhang);
            viewHolder.imgCart=view.findViewById(R.id.imagegiohang);
            viewHolder.btleft=view.findViewById(R.id.buttonminusleft);
            viewHolder.btvalue=view.findViewById(R.id.buttonvalue);
            viewHolder.btright=view.findViewById(R.id.buttonminusright);
            view.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) view.getTag();
        }
        Cart cart= (Cart) getItem(i);
        viewHolder.txtNameCart.setText(cart.getNameProduct());
        DecimalFormat decimalFormat =new DecimalFormat("###,###,###");
        viewHolder.txtPriceCard.setText("Giá: "+ decimalFormat.format(cart.getPriceProduct())+"VNĐ");
        viewHolder.btvalue.setText(cart.getQuantity()+"");
        final int counter = Integer.parseInt(viewHolder.btvalue.getText().toString());
        if(counter >=10){
            viewHolder.btright.setVisibility(View.INVISIBLE);
            viewHolder.btleft.setVisibility(View.VISIBLE);
        }else if(counter <=1){
            viewHolder.btleft.setVisibility(View.INVISIBLE);
        }else if(counter >=1){
            viewHolder.btleft.setVisibility(View.VISIBLE);
            viewHolder.btright.setVisibility(View.VISIBLE);
        }
        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.btright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int new_quantity=Integer.parseInt(finalViewHolder.btvalue.getText().toString())+1;
                int current_quantity= MainActivity.arrayListCard.get(i).getQuantity();
                long currentprice=MainActivity.arrayListCard.get(i).getPriceProduct();
                MainActivity.arrayListCard.get(i).setQuantity(new_quantity);
                long newprice=(currentprice*new_quantity)/current_quantity;
                MainActivity.arrayListCard.get(i).setPriceProduct((int) newprice);
                DecimalFormat decimalFormat =new DecimalFormat("###,###,###");
                finalViewHolder.txtPriceCard.setText("Giá: "+ decimalFormat.format(newprice)+"VNĐ");
                if(new_quantity>9){
                    finalViewHolder.btright.setVisibility(View.INVISIBLE);
                    finalViewHolder.btleft.setVisibility(View.VISIBLE);
                    finalViewHolder.btvalue.setText(String.valueOf(new_quantity));
                }else{
                    finalViewHolder.btright.setVisibility(View.VISIBLE);
                    finalViewHolder.btleft.setVisibility(View.VISIBLE);
                    finalViewHolder.btvalue.setText(String.valueOf(new_quantity));
                }
            }
        });
        viewHolder.btleft.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int new_quantity=Integer.parseInt(finalViewHolder.btvalue.getText().toString())-1;
                int current_quantity=MainActivity.arrayListCard.get(i).getQuantity();
                long currentprice=MainActivity.arrayListCard.get(i).getPriceProduct();
                MainActivity.arrayListCard.get(i).setQuantity(new_quantity);
                long newprice=(currentprice*new_quantity)/current_quantity;
                MainActivity.arrayListCard.get(i).setPriceProduct((int) newprice);
                DecimalFormat decimalFormat =new DecimalFormat("###,###,###");
                finalViewHolder.txtPriceCard.setText("Giá: "+ decimalFormat.format(newprice)+"VNĐ");
                if(new_quantity < 2){
                    finalViewHolder.btleft.setVisibility(View.INVISIBLE);
                    finalViewHolder.btright.setVisibility(View.VISIBLE);
                    finalViewHolder.btvalue.setText(String.valueOf(new_quantity));
                }else{
                    finalViewHolder.btright.setVisibility(View.VISIBLE);
                    finalViewHolder.btleft.setVisibility(View.VISIBLE);
                    finalViewHolder.btvalue.setText(String.valueOf(new_quantity));
                }

            }
        });
        return view;
    }
}
