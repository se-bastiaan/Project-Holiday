package nl.teamone.projectholiday.ui.adapters;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import nl.teamone.projectholiday.R;
import nl.teamone.projectholiday.algorithm.Clothing;
import nl.teamone.projectholiday.api.objects.WeatherData;
import nl.teamone.projectholiday.utils.PixelUtils;

public class PackingListAdapter extends HeaderRecyclerViewAdapter {

    private WeatherData mWeather;
    private List<Clothing> mData;

    public PackingListAdapter(WeatherData weatherData, List<Clothing> data) {
        mWeather = weatherData;
        mData = data;
    }

    @Override
    public boolean useHeader() {
        return true;
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType) {
        return new HeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_headeritem, parent, false));
    }

    @Override
    public void onBindHeaderView(RecyclerView.ViewHolder holder, int position) {
        HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
        headerViewHolder.imageView.setImageResource(mWeather.imageRes);
        headerViewHolder.temperatureText.setText(String.format("%.1f °C", (mWeather.temperature - 273)));
        headerViewHolder.typeText.setText(mWeather.weatherType);
    }

    @Override
    public RecyclerView.ViewHolder onCreateBasicItemViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_basicitem, parent, false));
    }

    @Override
    public void onBindBasicItemView(RecyclerView.ViewHolder holder, int position) {
        Clothing clothing = mData.get(position);
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;

        Context context = itemViewHolder.nameText.getContext();
        String packageName = context.getPackageName();
        int resId = context.getResources().getIdentifier(clothing.getId(), "plurals", packageName);
        String name =  context.getResources().getQuantityString(resId, clothing.getQuantity());

        itemViewHolder.nameText.setText(name);
        itemViewHolder.quantityText.setText(Integer.toString(clothing.getQuantity()));

        if (position == getBasicItemCount() - 1|| (position == getBasicItemCount() - 2 && getBasicItemCount() % 2 == 0)) {
            GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) itemViewHolder.itemView.getLayoutParams();
            layoutParams.bottomMargin = PixelUtils.getPixelsFromDp(itemViewHolder.itemView.getContext(), 16);
            itemViewHolder.itemView.setLayoutParams(layoutParams);
        }
    }

    @Override
    public int getBasicItemCount() {
        return mData.size();
    }

    @Override
    public int getBasicItemType(int position) {
        return position;
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.image)
        ImageView imageView;
        @InjectView(R.id.temperature)
        TextView temperatureText;
        @InjectView(R.id.type)
        TextView typeText;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        View itemView;
        @InjectView(R.id.name)
        TextView nameText;
        @InjectView(R.id.quantity)
        TextView quantityText;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
            this.itemView = itemView;
        }

    }




    /* Unused below */
    @Override
    public boolean useFooter() {
        return false;
    }

    @Override
    public RecyclerView.ViewHolder onCreateFooterViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindFooterView(RecyclerView.ViewHolder holder, int position) { }


}
