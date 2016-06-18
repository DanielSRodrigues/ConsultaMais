package bestsolutions.net.consultamais;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import bestsolutions.net.consultamais.entidades.AtividadesCrud;

public class CrudMedicoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud_medico);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.new_doctor);

        Intent i = getIntent();
        if (i != null) {
            Fragment crudMedico;
            if (i.getBooleanExtra(AtividadesCrud.CONTEXTO_CRUD, false)) {
                crudMedico = AlterarMedicoFragment.newInstance();
                toolbar.setTitle(R.string.modify_doctor);
            } else {
                crudMedico = NovoMedicoFragment.newInstance();
            }

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content, crudMedico, "Listagem")
                    .commit();
        }
        setSupportActionBar(toolbar);
    }
}
