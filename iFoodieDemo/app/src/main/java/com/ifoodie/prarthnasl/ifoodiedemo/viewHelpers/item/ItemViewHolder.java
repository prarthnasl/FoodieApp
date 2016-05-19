package com.ifoodie.prarthnasl.ifoodiedemo.viewHelpers.item;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ifoodie.prarthnasl.ifoodiedemo.R;
import com.ifoodie.prarthnasl.ifoodiedemo.constants.Constants;
import com.ifoodie.prarthnasl.ifoodiedemo.controllers.item.ItemListActivity;
import com.ifoodie.prarthnasl.ifoodiedemo.models.Item;
import com.ifoodie.prarthnasl.ifoodiedemo.utils.Strings;

/**
 * Created by prarthnasl on 4/30/2016.
 */

public class ItemViewHolder extends RecyclerView.ViewHolder {

    private ItemListActivity activity;
    private Item item;

    private TextView itemName;
    private TextView itemDescription;
    private TextView itemPrice;
    private ImageView itemImage;

    private CardView cardView;

    public ItemViewHolder(View itemLayoutView) {
        super(itemLayoutView);

        cardView = (CardView) itemLayoutView.findViewById(R.id.card_view);

        itemName = (TextView) itemLayoutView.findViewById(R.id.item_name);
        itemDescription = (TextView) itemLayoutView.findViewById(R.id.item_description);
        itemPrice = (TextView) itemLayoutView.findViewById(R.id.item_price);
        itemImage = (ImageView) itemLayoutView.findViewById(R.id.item_image);
    }

    public void setup(final Item item, final ItemListActivity activity) {
        this.activity = activity;
        this.item = item;

        cardView.setVisibility(item == null ? View.GONE : View.VISIBLE);

        itemName.setText(item.getName());
        itemDescription.setText(item.getDescription());
        itemPrice.setText(Strings.getFormattedPrice(item.getPrice()));
        Glide.with(activity).load(String.format(Constants.ABSOLUTE_BASE_URL_LOGO)).into(itemImage);
    }
}
