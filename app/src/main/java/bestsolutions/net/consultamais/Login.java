package bestsolutions.net.consultamais;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Login extends AppCompatActivity {

    @Bind(R.id.edtLogin)
    protected EditText mEdtLogin;
    @Bind(R.id.edtSenha)
    protected EditText mEdtSenha;
    @Bind(R.id.btnEntrar)
    protected Button mBtnEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mBtnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), PrincipalAgente.class);
                startActivity(i);
            }
        });
    }

}
