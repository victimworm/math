package giaohangtietkiem.vn.process.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public abstract class BaseAdapter<T, Y> extends ArrayAdapter<T> {

    protected Context context;
    private int layout = 0;

    public BaseAdapter(@NonNull Context context, int resource, ArrayList<T> data) {
        super(context, resource, data);
        this.context = context;
        this.layout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Y viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layout, parent, false);
            viewHolder = getViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (Y) convertView.getTag();
        }
        setViewHolderData(viewHolder, position);

        return convertView;
    }

    protected abstract Y getViewHolder(View rootView);

    protected abstract void setViewHolderData(Y viewHolder, int position);
}
