package uk.co.dekoorb.c3469162.minesweeper;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import uk.co.dekoorb.c3469162.minesweeper.fragments.MineFragment.OnListFragmentInteractionListener;
import uk.co.dekoorb.c3469162.minesweeper.model.Mine;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Mine} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class MineRecyclerViewAdapter extends RecyclerView.Adapter<MineRecyclerViewAdapter.ViewHolder> {

    private final List<Mine> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MineRecyclerViewAdapter(List<Mine> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_mine, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        long id = mValues.get(position).getId();
        holder.mIdView.setText(Long.toString(id));
        holder.mContentView.setText(mValues.get(position).toString());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Mine mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
