package bestsolutions.net.consultamais;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class CadastrarConsulta extends AppCompatActivity {

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
}
