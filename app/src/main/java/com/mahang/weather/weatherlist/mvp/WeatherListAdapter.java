package com.mahang.weather.weatherlist.mvp;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mahang.weather.BR;
import com.mahang.weather.R;
import com.mahang.weather.common.ResManagerImpl;
import com.mahang.weather.model.entity.WeatherInfo;

import java.util.List;


public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListAdapter.ViewHolder> {

    private List<WeatherInfo> mWeatherInfos;

    private Context mContext;

    private View.OnClickListener mOnClickListener;

    private ResManagerImpl mResManagerImpl;

    private int mVariableId;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView city;

        public TextView text;

        public TextView temp;

        public TextView hum;

        public TextView wind;

        public ImageView icon;

        public CardView card;

        private ViewDataBinding binding;

        public ViewHolder(View itemView,ViewDataBinding binding) {
            super(itemView);
            this.binding = binding;
//            city = (TextView) itemView.findViewById(R.id.city);
//            text = (TextView) itemView.findViewById(R.id.text);
//            temp = (TextView) itemView.findViewById(R.id.temp);
//            icon = (ImageView) itemView.findViewById(R.id.icon);
//            card = (CardView) itemView.findViewById(R.id.card);

        }
    }


    public WeatherListAdapter(Context context) {
        mContext = context;
        mResManagerImpl = ResManagerImpl.getInstance(context);
    }




    @Override
    public WeatherListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       // View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_city_list_item,parent,false);
        ViewDataBinding binder = DataBindingUtil.inflate(LayoutInflater.from(mContext),R.layout.fragment_city_list_item_data_binding,parent,false);
        return new WeatherListAdapter.ViewHolder(binder.getRoot(),binder);
    }

    @Override
    public void onBindViewHolder(WeatherListAdapter.ViewHolder holder, int position) {
        ViewDataBinding dataBinding = DataBindingUtil.getBinding(holder.itemView);
        WeatherInfo info = mWeatherInfos.get(position);


        if (info != null) {
            dataBinding.setVariable(BR.weather,info.getRealTimeInfo());
//            holder.city.setText(info.getCityName());
//            holder.icon.setImageDrawable(mResManagerImpl.getWeatherIcon(info.getRealTimeInfo().getCode()));
//            StringBuilder builder = new StringBuilder();
//            builder.append(info.getRealTimeInfo().getTemp()).append(Constants.DEGREE_SIGN);
//            holder.temp.setText(builder.toString());
//            holder.card.setCardBackgroundColor(mResManagerImpl.getToolbarBgColor(info.getRealTimeInfo().getCode(),true));
        }
//        holder.itemView.setOnClickListener(mOnClickListener);
//        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mWeatherInfos == null ? 0 : mWeatherInfos.size();
    }

    public void setOnClickListener(View.OnClickListener l) {
        mOnClickListener = l;
    }

    public void setData(List<WeatherInfo> infos){
        mWeatherInfos = infos;
        notifyDataSetChanged();
    }
}
