package bestsolutions.net.consultamais;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;

public class CalendarioActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        final CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView1);

        // quando selecionado alguma data diferente da padrão
        calendarView.setOnDateChangeListener(new OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year,
                                            int month, int dayOfMonth) {
                Intent intent = new Intent(CalendarioActivity.this,
                        CapturaDados.class);
                intent.putExtra("dataLongMiliseconds",
                        (Long) calendarView.getDate());
                startActivity(intent);

            }
        });
    }

}