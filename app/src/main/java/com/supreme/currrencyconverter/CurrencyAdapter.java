package com.supreme.currrencyconverter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by supreme on 6/24/17.
 */


public class CurrencyAdapter extends ArrayAdapter<Currency_Converter> {

    private static final String LOCATION_SEPARATOR = " of ";


    public CurrencyAdapter(Context context, List<Currency_Converter> currency) {
        super(context, 0, currency);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.currency_menu_list, parent, false);
        }

        Currency_Converter currentCurrency = getItem(position);

        TextView btcCurrency = (TextView) listItemView.findViewById(R.id.btc_currency);
        if (currentCurrency != null) {
            btcCurrency.setText(currentCurrency.getmBTC());
        }

        TextView ethCurrency = (TextView) listItemView.findViewById(R.id.eth_currency);
        if (currentCurrency != null) {
            ethCurrency.setText(currentCurrency.getmETH());
        }

        ImageView countryImage = (ImageView) listItemView.findViewById(R.id.country_image);

        if (currentCurrency != null) {
            Glide.with(getContext()).load(currentCurrency.getmCountryImage()).into(countryImage);
        }


        return listItemView;
    }

}
