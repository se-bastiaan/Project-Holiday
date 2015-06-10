package nl.teamone.projectholiday.ui.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;

import nl.teamone.projectholiday.api.PlacesApi;
import nl.teamone.projectholiday.api.responses.places.Prediction;
import rx.functions.Action1;
import rx.functions.Func1;

public class PlacesAutoCompleteAdapter extends ArrayAdapter<String> implements Filterable {
 
    private ArrayList<String> mResultList;
 
    public PlacesAutoCompleteAdapter(Context context, int resource) {
        super(context, resource);
    }
 
    @Override
    public int getCount() {
        return mResultList.size();
    }
 
    @Override
    public String getItem(int position) {
        return mResultList.get(position);
    }
 
    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new Filter.FilterResults();
                if (constraint != null) {
                    PlacesApi.getPredictions(constraint.toString()).map(new Func1<List<Prediction>, List<String>>() {
                        @Override
                        public ArrayList<String> call(List<Prediction> predictions) {
                            ArrayList<String> results = new ArrayList<>();
                            for(Prediction prediction : predictions) {
                                results.add(prediction.description);
                            }
                            return results;
                        }
                    }).subscribe(new Action1<List<String>>() {
                        @Override
                        public void call(List<String> strings) {
                            mResultList = (ArrayList<String>) strings;
                            if(mResultList.size() > 0)
                                notifyDataSetChanged();
                        }
                    });
                }

                filterResults.values = mResultList = new ArrayList<>();
                filterResults.count = mResultList.size();
                notifyDataSetChanged();
 
                return filterResults;
            }
 
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count > 0) {
                    notifyDataSetChanged();
                }
                else {
                    notifyDataSetInvalidated();
                }
            }
        };
 
        return filter;
    }
}
