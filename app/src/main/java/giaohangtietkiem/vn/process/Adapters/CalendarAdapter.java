package giaohangtietkiem.vn.process.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import giaohangtietkiem.vn.process.Objects.DayObject;
import giaohangtietkiem.vn.process.R;
import giaohangtietkiem.vn.process.Views.CircleView;

public class CalendarAdapter extends BaseAdapter<DayObject, CalendarAdapter.CalendarViewHolder> {

    public CalendarAdapter(@NonNull Context context, int resource, ArrayList<DayObject> data) {
        super(context, resource, data);
    }

    @Override
    protected CalendarViewHolder getViewHolder(View rootView) {
        CalendarViewHolder viewHolder = new CalendarViewHolder();
        viewHolder.txtDay = rootView.findViewById(R.id.item_calendar_txt_day);
        viewHolder.checkMark = rootView.findViewById(R.id.item_calendar_checkmark);
        return viewHolder;
    }

    @Override
    protected void setViewHolderData(CalendarViewHolder viewHolder, int position) {
        DayObject item = getItem(position);
        String day = "";
        if (item.value > 0) {
            day = item.value + "";
        }
        viewHolder.txtDay.setText(day);
        if (item.isToday) {
            viewHolder.checkMark.setVisibility(View.VISIBLE);
        } else {
            viewHolder.checkMark.setVisibility(View.INVISIBLE);
        }
    }

    class CalendarViewHolder {
        TextView txtDay;
        CircleView checkMark;
    }
}
