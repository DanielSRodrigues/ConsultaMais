package bestsolutions.net.consultamais;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.app.Dialog;

import java.text.CollationElementIterator;
import java.util.Calendar;

public class CadastrarConsulta extends AppCompatActivity {

    private int Ano;
    private int Mes;
    private int Dia;
    private int Hora;
    private int Minuto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_consulta);

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Nova consulta");
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_consulta);
        InicializaListeners();

        final Calendar cal = Calendar.getInstance();
        Ano = cal.get(Calendar.YEAR);
        Mes = cal.get(Calendar.MONTH);
        Dia = cal.get(Calendar.DAY_OF_MONTH);
        Hora = cal.get(Calendar.HOUR);
        Minuto = cal.get(Calendar.MINUTE);

        AtualizarData();
        AtualizarHora();
    }

    private void AtualizarData() {
        CollationElementIterator txtData = null;
        //txtData.setText(String.valueOf(new StringBuilder().append(Dia).append("/").append(Mes + 1).append("/").append(Ano).append(" ")));
    }

    private void AtualizarHora() {
        CollationElementIterator txtHora = null;
        txtHora.setText(String.valueOf(new StringBuilder().append(Hora).append(":").append(Minuto)));
    }


    public void InicializaListeners() {
       /* TextView txtData = (TextView) findViewById(R.id.txt/Data);
        TextView txtHora = (TextView) findViewById(R.id.txtHora);*/
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        Hora = c.get(Calendar.HOUR_OF_DAY);
        Minuto = c.get(Calendar.MINUTE);
        return null;
        /*return new TimePickerDialog(
                getApplication(),
                this,
                Hora,
                Minuto,
                DateFormat.is24HourFormat(getApplication());*/
    }
        /*Toolbar actionbar = (Toolbar) findViewById(R.id.actionbar);
        if (null != actionbar) {
            actionbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);

            actionbar.setTitle(R.string.title_activity_settings);
            actionbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavUtils.navigateUpFromSameTask(SettingsActivity.this);
                }
            });

            // Inflate a menu to be displayed in the toolbar
            actionbar.inflateMenu(R.menu.settings);
        }
*/
}

