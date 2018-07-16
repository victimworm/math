package giaohangtietkiem.vn.process;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

import giaohangtietkiem.vn.math.Math;
import giaohangtietkiem.vn.process.Adapters.CalendarAdapter;
import giaohangtietkiem.vn.process.Objects.DayObject;

public class MainActivity extends AppCompatActivity {

    private int[] months = {Calendar.JANUARY, Calendar.FEBRUARY, Calendar.MARCH, Calendar.APRIL, Calendar.MAY, Calendar.JUNE, Calendar.JULY, Calendar.AUGUST, Calendar.SEPTEMBER, Calendar.OCTOBER, Calendar.NOVEMBER, Calendar.DECEMBER};

    private int currentMonth = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViews();
    }

    private int getCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH);
    }

    private GridView calendar;
    private CalendarAdapter adapter;
    private ArrayList<DayObject> dataList = new ArrayList<>();
    private TextView txtTime;
    private ImageButton btnBack;
    private ImageButton btnNext;

    private void setupViews() {
        calendar = findViewById(R.id.activity_main_calendar);
        txtTime = findViewById(R.id.activity_main_txt_time);
        btnBack = findViewById(R.id.activity_main_back);
        btnNext = findViewById(R.id.activity_main_next);
        adapter = new CalendarAdapter(getApplicationContext(), R.layout.item_calendar, dataList);
        calendar.setAdapter(adapter);

        currentMonth = getCurrentMonth();
        txtTime.setText("Tháng " + (months[currentMonth] + 1));
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentMonth < 11) {
                    currentMonth += 1;
                    updateTime();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentMonth > 0) {
                    currentMonth -= 1;
                    updateTime();
                }
            }
        });
        initData();
    }

    private void updateTime() {
        txtTime.setText("Tháng " + (months[currentMonth] + 1));
        initData();
    }

    private void initData() {
        int length = getLengthMonth(months[currentMonth]);
        int start = getStartDay(months[currentMonth]);

        start -= 1;

        int end = length + start;

        dataList.clear();
        int day = 1;
        int today = getToday();
        for (int i = 0; i < end; i++) {
            DayObject item = new DayObject();
            if (i >= start) {
                item.value = day;
                if (today == day) {
                    item.isToday = true;
                }
                day += 1;
            } else {
                item.value = 0;
            }
            dataList.add(item);
        }
        adapter.notifyDataSetChanged();
    }

    private int getToday() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    private int getLengthMonth(int month) {
        Calendar cs = Calendar.getInstance();
        cs.set(Calendar.MONTH, month);
        cs.set(Calendar.YEAR, 2018);

        int numberOfDays = cs.getActualMaximum(Calendar.DAY_OF_MONTH);

        return numberOfDays;
    }

    private int getStartDay(int month) {
        Calendar c = Calendar.getInstance();   // this takes current date
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.YEAR, 2018);

        return c.get(Calendar.DAY_OF_WEEK);
    }
}
