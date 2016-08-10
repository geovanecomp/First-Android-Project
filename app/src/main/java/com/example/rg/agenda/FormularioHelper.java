package com.example.rg.agenda;

import android.widget.EditText;
import android.widget.RatingBar;

import com.example.rg.agenda.model.Aluno;

/**
 * Created by rg on 09/08/16.
 */
public class FormularioHelper {

    private Aluno aluno;
    private EditText campoNome;
    private EditText campoEndereco;
    private EditText campoTelefone;
    private EditText campoSite;
    private RatingBar campoNota;

    public FormularioHelper(FormularioActivity activity){
        aluno = new Aluno();

        campoNome = (EditText) activity.findViewById(R.id.formulario_nome);
        campoEndereco= (EditText) activity.findViewById(R.id.formulario_endereco);
        campoTelefone = (EditText) activity.findViewById(R.id.formulario_telefone);
        campoSite = (EditText) activity.findViewById(R.id.formulario_site);
        campoNota = (RatingBar) activity.findViewById(R.id.formulario_nota);
    }

    public Aluno obterAluno() {
        aluno.setNome(campoNome.getText().toString());
        aluno.setEndereco(campoEndereco.getText().toString());
        aluno.setTelefone(campoTelefone.getText().toString());
        aluno.setSite(campoSite.getText().toString());
        aluno.setNota(Double.valueOf(campoNota.getProgress()));

        return aluno;
    }

    public void preencherFormulario(Aluno aluno) {
        this.aluno = aluno;

        campoNome.setText(aluno.getNome());
        campoEndereco.setText(aluno.getEndereco());
        campoTelefone.setText(aluno.getTelefone());
        campoSite.setText(aluno.getSite());
        campoNota.setProgress((int) aluno.getNota());

    }
}
