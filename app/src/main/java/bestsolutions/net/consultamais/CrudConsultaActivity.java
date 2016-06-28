package bestsolutions.net.consultamais;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import bestsolutions.net.consultamais.entidades.AtividadesCrud;

public class CrudConsultaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_consulta);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("New Consult");

        Intent i = getIntent();
        if (i != null) {
            Fragment crudConsulta = null;
            if (i.getBooleanExtra(AtividadesCrud.CONTEXTO_CRUD, false)) {
                //crudConsulta = AlterarMedicoFragment.newInstance();
                toolbar.setTitle(R.string.modify_doctor);
            } else {
                crudConsulta = NovaConsultaFragment.newInstance();
            }

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content, crudConsulta, "Listagem")
                    .commit();
        }
        setSupportActionBar(toolbar);
    }

}

