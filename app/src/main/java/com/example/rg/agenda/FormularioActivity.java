package com.example.rg.agenda;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rg.agenda.dao.AlunoDao;
import com.example.rg.agenda.model.Aluno;

public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        helper = new FormularioHelper(this);
        Intent intent = getIntent();
        Aluno aluno = (Aluno) intent.getSerializableExtra("aluno");

        if (aluno != null){
            helper.preencherFormulario(aluno);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_formulario_ok:

                /*EditText campoNome =  (EditText) findViewById(R.id.formulario_nome);
                String nome = campoNome.getText().toString();

                //Fazendo tudo direto.
                String endereco = ((EditText) findViewById(R.id.formulario_endereco)).getText().toString();
                String site = ((EditText) findViewById(R.id.formulario_site)).getText().toString();
                String telefone = ((EditText) findViewById(R.id.formulario_telefone)).getText().toString();*/

                Aluno aluno = helper.obterAluno();
                AlunoDao alunoDao = new AlunoDao(this);

                if (aluno.getId() != 0){
                    alunoDao.alterar(aluno);
                }
                else {
                    alunoDao.inserir(aluno);
                }


                alunoDao.close();
                        
                Toast.makeText(FormularioActivity.this, "Aluno"+aluno.getNome()+" cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
