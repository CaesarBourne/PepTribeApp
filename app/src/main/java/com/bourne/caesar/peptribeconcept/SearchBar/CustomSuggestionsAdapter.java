
package com.bourne.caesar.peptribeconcept.SearchBar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bourne.caesar.peptribeconcept.R;
import com.mancj.materialsearchbar.adapter.SuggestionsAdapter;

import java.util.ArrayList;


public class CustomSuggestionsAdapter extends SuggestionsAdapter<Product, CustomSuggestionsAdapter.SuggestionHolder> {

    public CustomSuggestionsAdapter(LayoutInflater inflater) {
        super(inflater);
    }

    @Override
    public int getSingleViewHeight() {
        return 80;
    }

    @Override
    public SuggestionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = getLayoutInflater().inflate(R.layout.activity_custom_suggestions_adapter, parent, false);
        return new SuggestionHolder(view);
    }

    @Override
    public void onBindSuggestionHolder(Product suggestion, SuggestionHolder holder, int position) {
        holder.title.setText(suggestion.getTitle());
        holder.subtitle.setText("The price is " + suggestion.getPrice() + "$");
    }

    /**
     * <b>Override to customize functionality</b>
     * <p>Returns a filter that can be used to constrain data with a filtering
     * pattern.</p>
     * <p>
//     * <p>This method is usually implemented by {@link
     *
     * @return a filter used to constrain data
     */
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                String term = constraint.toString();
                if(term.isEmpty())
                    suggestions = suggestions_clone;
                else {
                    suggestions = new ArrayList<Product>();
                    for (Product item: suggestions_clone)
                        if(item.getTitle().toLowerCase().contains(term.toLowerCase()))
                            suggestions.add(item);
                }
                results.values = suggestions;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                suggestions = (ArrayList<Product>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    static class SuggestionHolder extends RecyclerView.ViewHolder{
        protected TextView title;
        protected TextView subtitle;
        protected ImageView image;

        public SuggestionHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            subtitle = (TextView) itemView.findViewById(R.id.subtitle);
        }
    }

}

