package com.example.questionario_frigorifico;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.simple.JSONValue;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("SimpleDateFormat")
public class CadastroActivity extends Activity {
	// ----------
	private Calendar calendar;
	private int dia, mes, ano;
	private String dataCompleta;
	public CheckBox q17a, q17b;
	public CheckBox q25a, q25b, q25c, q25d, q25e, q25f, q25g, q25h, q25i, q25j;
	public Spinner spinner17a_tipo, spinner17b_tipo, spinner17a_intensidade,
			spinner17b_intensidade;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro);

		calendar = Calendar.getInstance();
		ano = calendar.get(Calendar.YEAR);

		mes = calendar.get(Calendar.MONTH);
		dia = calendar.get(Calendar.DAY_OF_MONTH);
		dataCompleta = dia + "/" + mes + "/" + ano;
		TextView tv_dataAtual = (TextView) findViewById(R.id.tv_get_data_atual);
		tv_dataAtual.setText(dataCompleta);

		spinner17a_tipo = (Spinner) findViewById(R.id.spinner_17a_tipo);
		spinner17b_tipo = (Spinner) findViewById(R.id.spinner_17b_tipo);
		spinner17a_intensidade = (Spinner) findViewById(R.id.spinner_17a_intensidade);
		spinner17b_intensidade = (Spinner) findViewById(R.id.spinner_17b_intensidade);

		// Create an ArrayAdapter using the string array and a default spinner
		// layout
		ArrayAdapter<CharSequence> adapter_tipo = ArrayAdapter
				.createFromResource(this, R.array.tipo_desconforto,
						android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter_tipo
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// Create an ArrayAdapter using the string array and a default spinner
		// layout
		ArrayAdapter<CharSequence> adapter_intensidade = ArrayAdapter
				.createFromResource(this, R.array.intensidade_desconforto,
						android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter_intensidade
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// Apply the adapter to the spinner
		spinner17a_tipo.setAdapter(adapter_tipo);
		spinner17b_tipo.setAdapter(adapter_tipo);

		// Apply the adapter to the spinner
		spinner17a_intensidade.setAdapter(adapter_intensidade);
		spinner17b_intensidade.setAdapter(adapter_intensidade);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastro, menu);
		return true;
	}

	@SuppressWarnings("unchecked")
	public void salvarsd(View view) {
		String errosParse = "";

		@SuppressWarnings("rawtypes")
		Map json_hash = new LinkedHashMap();
		// Toast.makeText(getApplicationContext(), "debug", Toast.LENGTH_SHORT)
		// .show();

		// dados do funcionario

		// empresa
		String q_empresa = ((TextView) findViewById(R.id.tv_empresa)).getText()
				.toString();
		String r_empresa = ((EditText) findViewById(R.id.et_empresa)).getText()
				.toString();
		
		if (r_empresa.equals("")) {
			if (errosParse.equals("")) {
				errosParse += q_empresa + " vazio.\n";
			}
			Toast.makeText(getApplicationContext(), errosParse,
					Toast.LENGTH_SHORT).show();
		} else {

			try {
				json_hash.put(q_empresa, r_empresa);
			} catch (Exception e1) {
				Log.v("JSON ERROR", "Erro ao criar JSON com " + q_empresa);
				e1.printStackTrace();
			}

		}

		// nome
		String q_nome = ((TextView) findViewById(R.id.tv_nome)).getText()
				.toString();
		String r_nome = ((EditText) findViewById(R.id.et_nome)).getText()
				.toString();
		if (r_nome.equals("")) {
			if (errosParse.equals("")) {
				errosParse += q_nome + " vazio.\n";
			}
			Toast.makeText(getApplicationContext(), errosParse,
					Toast.LENGTH_SHORT).show();
		} else {

			try {
				json_hash.put(q_nome, r_nome);
			} catch (Exception e1) {
				Log.v("JSON ERROR", "Erro ao criar JSON com " + q_nome);
				e1.printStackTrace();
			}

		}

		if (errosParse.equals("")) {

			// idade
			String q_idade = ((TextView) findViewById(R.id.tv_idade)).getText()
					.toString();
			String r_idade = ((EditText) findViewById(R.id.et_idade)).getText()
					.toString();
			if (r_idade.equals("")) {
				if (errosParse.equals("")) {
					errosParse += q_idade + " vazio.\n";
				}
				Toast.makeText(getApplicationContext(), errosParse,
						Toast.LENGTH_SHORT).show();
			} else {

				try {
					json_hash.put(q_idade, r_idade);
				} catch (Exception e1) {
					Log.v("JSON ERROR", "Erro ao criar JSON com " + q_idade);
					e1.printStackTrace();
				}

			}
		}

		if (errosParse.equals("")) {
			// sexo
			String q_sexo = ((TextView) findViewById(R.id.tv_sexo)).getText()
					.toString();

			RadioGroup radio_sexo = (RadioGroup) findViewById(R.id.radio_sexo);
			String r_sexo = "";
			if (radio_sexo.getCheckedRadioButtonId() != -1) {
				r_sexo = ((RadioButton) findViewById(radio_sexo
						.getCheckedRadioButtonId())).getText().toString();
			}
			if (r_sexo.equals("")) {
				if (errosParse.equals("")) {
					errosParse += q_sexo + " vazio.\n";
				}
				Toast.makeText(getApplicationContext(), errosParse,
						Toast.LENGTH_SHORT).show();
			} else {

				try {
					json_hash.put(q_sexo, r_sexo);
				} catch (Exception e1) {
					Log.v("JSON ERROR", "Erro ao criar JSON com " + q_sexo);
					e1.printStackTrace();
				}

			}
		}

		if (errosParse.equals("")) {

			// tempo empresa
			String q_tempoEmpresa = ((TextView) findViewById(R.id.tv_tempoEmpresa))
					.getText().toString();
			String r_tempoEmpresa = ((EditText) findViewById(R.id.et_tempoEmpresa))
					.getText().toString();
			if (r_tempoEmpresa.equals("")) {
				if (errosParse.equals("")) {
					errosParse += q_tempoEmpresa + " vazio.\n";
				}
				Toast.makeText(getApplicationContext(), errosParse,
						Toast.LENGTH_SHORT).show();
			} else {

				try {
					json_hash.put(q_tempoEmpresa, r_tempoEmpresa);
				} catch (Exception e1) {
					Log.v("JSON ERROR", "Erro ao criar JSON com "
							+ q_tempoEmpresa);
					e1.printStackTrace();
				}

			}
		}

		if (errosParse.equals("")) {

			// tempo atividade
			String q_tempoAtividade = ((TextView) findViewById(R.id.tv_tempoAtividade))
					.getText().toString();
			String r_tempoAtividade = ((EditText) findViewById(R.id.et_tempoAtividade))
					.getText().toString();
			if (r_tempoAtividade.equals("")) {
				if (errosParse.equals("")) {
					errosParse += q_tempoAtividade + " vazio.\n";
				}
				Toast.makeText(getApplicationContext(), errosParse,
						Toast.LENGTH_SHORT).show();
			} else {

				try {
					json_hash.put(q_tempoAtividade, r_tempoAtividade);
				} catch (Exception e1) {
					Log.v("JSON ERROR", "Erro ao criar JSON com "
							+ q_tempoAtividade);
					e1.printStackTrace();
				}

			}
		}

		if (errosParse.equals("")) {

			// turno
			String q_turno = ((TextView) findViewById(R.id.tv_turno)).getText()
					.toString();

			RadioGroup radio_turno = (RadioGroup) findViewById(R.id.radio_turno);
			String r_turno = "";
			if (radio_turno.getCheckedRadioButtonId() != -1) {
				r_turno = ((RadioButton) findViewById(radio_turno
						.getCheckedRadioButtonId())).getText().toString();
			}
			if (r_turno.equals("")) {
				if (errosParse.equals("")) {
					errosParse += q_turno + " vazio.\n";
				}
				Toast.makeText(getApplicationContext(), errosParse,
						Toast.LENGTH_SHORT).show();
			} else {

				try {
					json_hash.put(q_turno, r_turno);
				} catch (Exception e1) {
					Log.v("JSON ERROR", "Erro ao criar JSON com " + q_turno);
					e1.printStackTrace();
				}

			}
		}

		if (errosParse.equals("")) {

			// horario trabalho
			String q_horarioTrabalho = ((TextView) findViewById(R.id.tv_horarioTrabalho))
					.getText().toString();

			String r_horarioTrabalho_hora = ((EditText) findViewById(R.id.et_horarioTrabalho_hora))
					.getText().toString();

			String r_horarioTrabalho_minuto = ((EditText) findViewById(R.id.et_horarioTrabalho_minuto))
					.getText().toString();

			String r_horarioTrabalho = r_horarioTrabalho_hora + ":"
					+ r_horarioTrabalho_minuto;

			if (r_horarioTrabalho.equals("")) {
				if (errosParse.equals("")) {
					errosParse += q_horarioTrabalho
							+ " preenchido de maneira incorreta.\n";
				}
				Toast.makeText(getApplicationContext(), errosParse,
						Toast.LENGTH_SHORT).show();
			} else {

				try {
					json_hash.put(q_horarioTrabalho, r_horarioTrabalho);
				} catch (Exception e1) {
					Log.v("JSON ERROR", "Erro ao criar JSON com "
							+ q_horarioTrabalho);
					e1.printStackTrace();
				}

			}
		}

		if (errosParse.equals("")) {

			// horario parada
			String q_horarioParada = ((TextView) findViewById(R.id.tv_parada_alimentacao))
					.getText().toString();

			String r_horarioParada_hora = ((EditText) findViewById(R.id.et_horaParada_hora))
					.getText().toString();

			String r_horarioParada_minuto = ((EditText) findViewById(R.id.et_horaParada_minuto))
					.getText().toString();

			String r_horarioParada = r_horarioParada_hora + ":"
					+ r_horarioParada_minuto;

			if (r_horarioParada.equals("")) {
				if (errosParse.equals("")) {
					errosParse += q_horarioParada
							+ " preenchido de maneira incorreta.\n";
				}
				Toast.makeText(getApplicationContext(), errosParse,
						Toast.LENGTH_SHORT).show();
			} else {

				try {
					json_hash.put(q_horarioParada, r_horarioParada);
				} catch (Exception e1) {
					Log.v("JSON ERROR", "Erro ao criar JSON com "
							+ q_horarioParada);
					e1.printStackTrace();
				}

			}
		}

		// dados da atividade

		if (errosParse.equals("")) {
			// pergunta1
			String q1 = ((TextView) findViewById(R.id.pergunta1)).getText()
					.toString();

			String r_q1 = ((EditText) findViewById(R.id.et_pergunta1))
					.getText().toString();

			if (r_q1.equals("")) {

				errosParse += q1 + " vazio.\n";
				Toast.makeText(getApplicationContext(), errosParse,
						Toast.LENGTH_SHORT).show();
			} else {

				try {
					json_hash.put(q1, r_q1);
				} catch (Exception e1) {
					Log.v("JSON ERROR", "Erro ao criar JSON com " + q1);
					e1.printStackTrace();
				}

			}
		}

		if (errosParse.equals("")) {
			// pergunta2
			String q2 = ((TextView) findViewById(R.id.pergunta2)).getText()
					.toString();
			RadioGroup radio_q2 = (RadioGroup) findViewById(R.id.radio_pergunta2);
			String r_q2 = "";

			if (radio_q2.getCheckedRadioButtonId() != -1) {
				r_q2 = ((RadioButton) findViewById(radio_q2
						.getCheckedRadioButtonId())).getText().toString();
			}
			if (r_q2.equals("")) {

				errosParse += q2 + " vazio.\n";
				Toast.makeText(getApplicationContext(), errosParse,
						Toast.LENGTH_SHORT).show();
			} else {

				try {
					json_hash.put(q2, r_q2);
				} catch (Exception e1) {
					Log.v("JSON ERROR", "Erro ao criar JSON com " + q2);
					e1.printStackTrace();
				}

			}
		}
		if (errosParse.equals("")) {
			// pergunta3
			String q3 = ((TextView) findViewById(R.id.pergunta3)).getText()
					.toString();
			String r_q3 = ((EditText) findViewById(R.id.et_pergunta3))
					.getText().toString();
			if (r_q3.equals("")) {

				errosParse += q3 + " vazio.\n";
				Toast.makeText(getApplicationContext(), errosParse,
						Toast.LENGTH_SHORT).show();
			} else {

				try {
					json_hash.put(q3, r_q3);
				} catch (Exception e1) {
					Log.v("JSON ERROR", "Erro ao criar JSON com " + q3);
					e1.printStackTrace();
				}

			}
		}
		if (errosParse.equals("")) {
			// pergunta4
			String q4 = ((TextView) findViewById(R.id.pergunta4)).getText()
					.toString();
			RadioGroup radio_q4 = (RadioGroup) findViewById(R.id.radio_pergunta4);
			String r_q4 = "";
			if (radio_q4.getCheckedRadioButtonId() != -1) {
				r_q4 = ((RadioButton) findViewById(radio_q4
						.getCheckedRadioButtonId())).getText().toString();
			}
			if (r_q4.equals("")) {

				errosParse += q4 + " vazio.\n";
				Toast.makeText(getApplicationContext(), errosParse,
						Toast.LENGTH_SHORT).show();
			} else {

				try {
					json_hash.put(q4, r_q4);
				} catch (Exception e1) {
					Log.v("JSON ERROR", "Erro ao criar JSON com " + q4);
					e1.printStackTrace();
				}

			}
		}
		if (errosParse.equals("")) {
			// pergunta5
			String q5 = ((TextView) findViewById(R.id.pergunta5)).getText()
					.toString();
			RadioGroup radio_q5 = (RadioGroup) findViewById(R.id.radio_pergunta5);
			String r_q5 = "";
			if (radio_q5.getCheckedRadioButtonId() != -1) {
				r_q5 = ((RadioButton) findViewById(radio_q5
						.getCheckedRadioButtonId())).getText().toString();
			}
			if (r_q5.equals("")) {

				errosParse += q5 + " vazio.\n";
				Toast.makeText(getApplicationContext(), errosParse,
						Toast.LENGTH_SHORT).show();
			} else {

				try {
					json_hash.put(q5, r_q5);
				} catch (Exception e1) {
					Log.v("JSON ERROR", "Erro ao criar JSON com " + q5);
					e1.printStackTrace();
				}

			}
		}
		if (errosParse.equals("")) {
			// pergunta6
			String q6 = ((TextView) findViewById(R.id.pergunta6)).getText()
					.toString();
			RadioGroup radio_q6 = (RadioGroup) findViewById(R.id.radio_pergunta6);
			String r_q6 = "";
			if (radio_q6.getCheckedRadioButtonId() != -1) {
				r_q6 = ((RadioButton) findViewById(radio_q6
						.getCheckedRadioButtonId())).getText().toString();
			}
			if (r_q6.equals("")) {

				errosParse += q6 + " vazio.\n";
				Toast.makeText(getApplicationContext(), errosParse,
						Toast.LENGTH_SHORT).show();
			} else {

				try {
					json_hash.put(q6, r_q6);
				} catch (Exception e1) {
					Log.v("JSON ERROR", "Erro ao criar JSON com " + q6);
					e1.printStackTrace();
				}

			}
		}
		if (errosParse.equals("")) {
			// pergunta7
			String q7 = ((TextView) findViewById(R.id.pergunta7)).getText()
					.toString();
			String r_q7 = ((EditText) findViewById(R.id.et_pergunta7))
					.getText().toString();
			if (r_q7.equals("")) {

				errosParse += q7 + " vazio.\n";
				Toast.makeText(getApplicationContext(), errosParse,
						Toast.LENGTH_SHORT).show();
			} else {

				try {
					json_hash.put(q7, r_q7);
				} catch (Exception e1) {
					Log.v("JSON ERROR", "Erro ao criar JSON com " + q7);
					e1.printStackTrace();
				}

			}
		}
		if (errosParse.equals("")) {
			// pergunta8
			String q8 = ((TextView) findViewById(R.id.pergunta8)).getText()
					.toString();
			RadioGroup radio_q8 = (RadioGroup) findViewById(R.id.radio_pergunta8);
			String r_q8 = "";
			if (radio_q8.getCheckedRadioButtonId() != -1) {
				r_q8 = ((RadioButton) findViewById(radio_q8
						.getCheckedRadioButtonId())).getText().toString();
			}
			if (r_q8.equals("")) {

				errosParse += q8 + " vazio.\n";
				Toast.makeText(getApplicationContext(), errosParse,
						Toast.LENGTH_SHORT).show();
			} else {

				try {
					json_hash.put(q8, r_q8);
				} catch (Exception e1) {
					Log.v("JSON ERROR", "Erro ao criar JSON com " + q8);
					e1.printStackTrace();
				}

			}
		}
		if (errosParse.equals("")) {
			// pergunta9
			String q9 = ((TextView) findViewById(R.id.pergunta9)).getText()
					.toString();
			RadioGroup radio_q9 = (RadioGroup) findViewById(R.id.radio_pergunta9);
			String r_q9 = "";
			if (radio_q9.getCheckedRadioButtonId() != -1) {
				r_q9 = ((RadioButton) findViewById(radio_q9
						.getCheckedRadioButtonId())).getText().toString();
			}
			if (r_q9.equals("")) {

				errosParse += q9 + " vazio.\n";
				Toast.makeText(getApplicationContext(), errosParse,
						Toast.LENGTH_SHORT).show();
			} else {

				try {
					json_hash.put(q9, r_q9);
				} catch (Exception e1) {
					Log.v("JSON ERROR", "Erro ao criar JSON com " + q9);
					e1.printStackTrace();
				}

			}
		}

		if (errosParse.equals("")) {
			// pergunta10
			String q10 = ((TextView) findViewById(R.id.pergunta10)).getText()
					.toString();

			String r_q10_hora = ((EditText) findViewById(R.id.et_pergunta10_hora))
					.getText().toString();

			String r_q10_minuto = ((EditText) findViewById(R.id.et_pergunta10_minuto))
					.getText().toString();

			String r_q10 = r_q10_hora + ":" + r_q10_minuto;

			if (r_q10.equals("")) {

				errosParse += q10 + " vazio.\n";
				Toast.makeText(getApplicationContext(), errosParse,
						Toast.LENGTH_SHORT).show();
			} else {

				try {
					json_hash.put(q10, r_q10);
				} catch (Exception e1) {
					Log.v("JSON ERROR", "Erro ao criar JSON com " + q10);
					e1.printStackTrace();
				}

			}
		}
		if (errosParse.equals("")) {
			// pergunta11
			String q11 = ((TextView) findViewById(R.id.pergunta11)).getText()
					.toString();
			RadioGroup radio_q11 = (RadioGroup) findViewById(R.id.radio_pergunta11);
			String r_q11 = "";
			if (radio_q11.getCheckedRadioButtonId() != -1) {
				r_q11 = ((RadioButton) findViewById(radio_q11
						.getCheckedRadioButtonId())).getText().toString();
			}
			if (r_q11.equals("")) {

				errosParse += q11 + " vazio.\n";
				Toast.makeText(getApplicationContext(), errosParse,
						Toast.LENGTH_SHORT).show();
			} else {

				try {
					json_hash.put(q11, r_q11);
				} catch (Exception e1) {
					Log.v("JSON ERROR", "Erro ao criar JSON com " + q11);
					e1.printStackTrace();
				}

			}

			// pergunta11:descrever
			String q11a = ((TextView) findViewById(R.id.pergunta11a)).getText()
					.toString();
			String r_q11a = ((EditText) findViewById(R.id.et_pergunta11_descrever))
					.getText().toString();
			if (r_q11a.equals("")) {

				errosParse += q11 + ":" + q11a + " vazio.\n";
				Toast.makeText(getApplicationContext(), errosParse,
						Toast.LENGTH_SHORT).show();
			} else {

				try {
					json_hash.put(q11a, r_q11a);
				} catch (Exception e1) {
					Log.v("JSON ERROR", "Erro ao criar JSON com " + q11 + ":"
							+ q11a);
					e1.printStackTrace();
				}

			}
		}
		if (errosParse.equals("")) {
			// pergunta12
			String q12 = ((TextView) findViewById(R.id.pergunta12)).getText()
					.toString();
			RadioGroup radio_q12 = (RadioGroup) findViewById(R.id.radio_pergunta12);
			String r_q12 = "";
			if (radio_q12.getCheckedRadioButtonId() != -1) {
				r_q12 = ((RadioButton) findViewById(radio_q12
						.getCheckedRadioButtonId())).getText().toString();
			}
			if (r_q12.equals("")) {

				errosParse += q12 + " vazio.\n";
				Toast.makeText(getApplicationContext(), errosParse,
						Toast.LENGTH_SHORT).show();
			} else {

				try {
					json_hash.put(q12, r_q12);
				} catch (Exception e1) {
					Log.v("JSON ERROR", "Erro ao criar JSON com " + q12);
					e1.printStackTrace();
				}

			}
		}

		if (errosParse.equals("")) {
			// pergunta13
			String q13 = ((TextView) findViewById(R.id.pergunta13)).getText()
					.toString();
			RadioGroup radio_q13 = (RadioGroup) findViewById(R.id.radio_pergunta13);
			String r_q13 = "";
			if (radio_q13.getCheckedRadioButtonId() != -1) {
				r_q13 = ((RadioButton) findViewById(radio_q13
						.getCheckedRadioButtonId())).getText().toString();
			}
			if (r_q13.equals("")) {

				errosParse += q13 + " vazio.\n";
				Toast.makeText(getApplicationContext(), errosParse,
						Toast.LENGTH_SHORT).show();
			} else {

				try {
					json_hash.put(q13, r_q13);
				} catch (Exception e1) {
					Log.v("JSON ERROR", "Erro ao criar JSON com " + q13);
					e1.printStackTrace();
				}

			}
		}

		if (errosParse.equals("")) {

			// pergunta14
			String q14 = ((TextView) findViewById(R.id.pergunta14)).getText()
					.toString();
			RadioGroup radio_q14 = (RadioGroup) findViewById(R.id.radio_pergunta14);
			String r_q14 = "";
			if (radio_q14.getCheckedRadioButtonId() != -1) {
				r_q14 = ((RadioButton) findViewById(radio_q14
						.getCheckedRadioButtonId())).getText().toString();
			}
			if (r_q14.equals("")) {

				errosParse += q14 + " vazio.\n";
				Toast.makeText(getApplicationContext(), errosParse,
						Toast.LENGTH_SHORT).show();
			} else {

				try {
					json_hash.put(q14, r_q14);
				} catch (Exception e1) {
					Log.v("JSON ERROR", "Erro ao criar JSON com " + q14);
					e1.printStackTrace();
				}

			}
		}

		if (errosParse.equals("")) {
			// pergunta15
			String q15 = ((TextView) findViewById(R.id.pergunta15)).getText()
					.toString();
			RadioGroup radio_q15 = (RadioGroup) findViewById(R.id.radio_pergunta15);
			String r_q15 = "";
			if (radio_q15.getCheckedRadioButtonId() != -1) {
				r_q15 = ((RadioButton) findViewById(radio_q15
						.getCheckedRadioButtonId())).getText().toString();
			}
			if (r_q15.equals("")) {

				errosParse += q15 + " vazio.\n";
				Toast.makeText(getApplicationContext(), errosParse,
						Toast.LENGTH_SHORT).show();
			} else {

				try {
					json_hash.put(q15, r_q15);
				} catch (Exception e1) {
					Log.v("JSON ERROR", "Erro ao criar JSON com " + q15);
					e1.printStackTrace();
				}

			}
		}

		if (errosParse.equals("")) {

			// pergunta16
			String q16 = ((TextView) findViewById(R.id.pergunta16)).getText()
					.toString();
			RadioGroup radio_q16 = (RadioGroup) findViewById(R.id.radio_pergunta16);
			String r_q16 = "";
			if (radio_q16.getCheckedRadioButtonId() != -1) {
				r_q16 = ((RadioButton) findViewById(radio_q16
						.getCheckedRadioButtonId())).getText().toString();
			}
			if (r_q16.equals("")) {

				errosParse += q16 + " vazio.\n";
				Toast.makeText(getApplicationContext(), errosParse,
						Toast.LENGTH_SHORT).show();
			} else {

				try {
					json_hash.put(q16, r_q16);
				} catch (Exception e1) {
					Log.v("JSON ERROR", "Erro ao criar JSON com " + q16);
					e1.printStackTrace();
				}

			}
		}

		if (errosParse.equals("")) {
			// pergunta17
			String q17 = ((TextView) findViewById(R.id.pergunta17)).getText().toString();

			String r_q17 = "";
			q17a = (CheckBox) findViewById(R.id.cb_17a);
			q17b = (CheckBox) findViewById(R.id.cb_17b);
			String r_q17a = "";
			String r_q17b = "";

			if (q17a.isChecked()) {
				r_q17a += q17a.getText().toString();
				r_q17a += "," + spinner17a_tipo.getSelectedItem().toString();
				r_q17a += "," + spinner17a_intensidade.getSelectedItem().toString();
			} else {

				r_q17a = "";
			}

			if (q17b.isChecked()) {
				r_q17b += q17b.getText().toString();
				r_q17b += "," + spinner17b_tipo.getSelectedItem().toString();
				r_q17b += "," + spinner17b_intensidade.getSelectedItem().toString();
			} else {
				r_q17b = "";
			}

			r_q17 = r_q17a + r_q17b;

			if (r_q17.equals("")) {

				errosParse += q17 + " vazio.\n";
				Toast.makeText(getApplicationContext(), errosParse,
						Toast.LENGTH_SHORT).show();
			} else {

				try {
					json_hash.put(q17, r_q17);
				} catch (Exception e1) {
					Log.v("JSON ERROR", "Erro ao criar JSON com " + q17);
					e1.printStackTrace();
				}

			}
		}

		if (errosParse.equals("")) {

			// pergunta18
			String q18 = ((TextView) findViewById(R.id.pergunta18)).getText()
					.toString();
			RadioGroup radio_q18 = (RadioGroup) findViewById(R.id.radio_pergunta18);
			String r_q18 = "";
			if (radio_q18.getCheckedRadioButtonId() != -1) {
				r_q18 = ((RadioButton) findViewById(radio_q18
						.getCheckedRadioButtonId())).getText().toString();
			}
			if (r_q18.equals("")) {

				errosParse += q18 + " vazio.\n";
				Toast.makeText(getApplicationContext(), errosParse,
						Toast.LENGTH_SHORT).show();
			} else {

				try {
					json_hash.put(q18, r_q18);
				} catch (Exception e1) {
					Log.v("JSON ERROR", "Erro ao criar JSON com " + q18);
					e1.printStackTrace();
				}

			}
		}

		if (errosParse.equals("")) {
			// pergunta19
			String q19 = ((TextView) findViewById(R.id.pergunta19)).getText()
					.toString();
			RadioGroup radio_q19 = (RadioGroup) findViewById(R.id.radio_pergunta19);
			String r_q19 = "";
			if (radio_q19.getCheckedRadioButtonId() != -1) {
				r_q19 = ((RadioButton) findViewById(radio_q19
						.getCheckedRadioButtonId())).getText().toString();
			}
			if (r_q19.equals("")) {

				errosParse += q19 + " vazio.\n";
				Toast.makeText(getApplicationContext(), errosParse,
						Toast.LENGTH_SHORT).show();
			} else {

				try {
					json_hash.put(q19, r_q19);
				} catch (Exception e1) {
					Log.v("JSON ERROR", "Erro ao criar JSON com " + q19);
					e1.printStackTrace();
				}

			}
		}

		if (errosParse.equals("")) {
			// pergunta20
			String q20 = ((TextView) findViewById(R.id.pergunta20)).getText()
					.toString();
			RadioGroup radio_q20 = (RadioGroup) findViewById(R.id.radio_pergunta20);
			String r_q20 = "";
			if (radio_q20.getCheckedRadioButtonId() != -1) {
				r_q20 = ((RadioButton) findViewById(radio_q20
						.getCheckedRadioButtonId())).getText().toString();
			}
			if (r_q20.equals("")) {

				errosParse += q20 + " vazio.\n";
				Toast.makeText(getApplicationContext(), errosParse,
						Toast.LENGTH_SHORT).show();
			} else {

				try {
					json_hash.put(q20, r_q20);
				} catch (Exception e1) {
					Log.v("JSON ERROR", "Erro ao criar JSON com " + q20);
					e1.printStackTrace();
				}

			}
		}

		if (errosParse.equals("")) {
			// pergunta21
			String q21 = ((TextView) findViewById(R.id.pergunta21)).getText()
					.toString();
			String r_q21 = ((EditText) findViewById(R.id.et_pergunta21_descrever))
					.getText().toString();
			if (r_q21.equals("")) {

				errosParse += q21 + " vazio.\n";
				Toast.makeText(getApplicationContext(), errosParse,
						Toast.LENGTH_SHORT).show();
			} else {

				try {
					json_hash.put(q21, r_q21);
				} catch (Exception e1) {
					Log.v("JSON ERROR", "Erro ao criar JSON com " + q21);
					e1.printStackTrace();
				}

			}
		}

		if (errosParse.equals("")) {
			// pergunta22
			String q22 = ((TextView) findViewById(R.id.pergunta22)).getText()
					.toString();
			RadioGroup radio_q22 = (RadioGroup) findViewById(R.id.radio_pergunta22);
			String r_q22 = "";
			if (radio_q22.getCheckedRadioButtonId() != -1) {
				r_q22 = ((RadioButton) findViewById(radio_q22
						.getCheckedRadioButtonId())).getText().toString();
			}
			if (r_q22.equals("")) {

				errosParse += q22 + " vazio.\n";
				Toast.makeText(getApplicationContext(), errosParse,
						Toast.LENGTH_SHORT).show();
			} else {

				try {
					json_hash.put(q22, r_q22);
				} catch (Exception e1) {
					Log.v("JSON ERROR", "Erro ao criar JSON com " + q22);
					e1.printStackTrace();
				}

			}
		}

		if (errosParse.equals("")) {
			// pergunta23
			String q23 = ((TextView) findViewById(R.id.pergunta23)).getText()
					.toString();
			RadioGroup radio_q23 = (RadioGroup) findViewById(R.id.radio_pergunta23);
			String r_q23 = "";
			if (radio_q23.getCheckedRadioButtonId() != -1) {
				r_q23 = ((RadioButton) findViewById(radio_q23
						.getCheckedRadioButtonId())).getText().toString();
			}
			if (r_q23.equals("")) {

				errosParse += q23 + " vazio.\n";
				Toast.makeText(getApplicationContext(), errosParse,
						Toast.LENGTH_SHORT).show();
			} else {

				try {
					json_hash.put(q23, r_q23);
				} catch (Exception e1) {
					Log.v("JSON ERROR", "Erro ao criar JSON com " + q23);
					e1.printStackTrace();
				}

			}

			// pergunta23 - remedio
			String q23a = ((TextView) findViewById(R.id.pergunta23a)).getText()
					.toString();
			String r_q23a = ((EditText) findViewById(R.id.et_pergunta23a_descrever))
					.getText().toString();
			if (r_q23a.equals("")) {

				errosParse += q23 + ":" + q23a + " vazio.\n";
				Toast.makeText(getApplicationContext(), errosParse,
						Toast.LENGTH_SHORT).show();
			} else {

				try {
					json_hash.put(q23a, r_q23a);
				} catch (Exception e1) {
					Log.v("JSON ERROR", "Erro ao criar JSON com " + q23a);
					e1.printStackTrace();
				}

			}

			// pergunta23 - quantas vezes
			String q23b = ((TextView) findViewById(R.id.pergunta23b)).getText()
					.toString();
			String r_q23b = ((EditText) findViewById(R.id.et_pergunta23b_descrever))
					.getText().toString();
			if (r_q23b.equals("")) {

				errosParse += q23 + ":" + q23b + " vazio.\n";
				Toast.makeText(getApplicationContext(), errosParse,
						Toast.LENGTH_SHORT).show();
			} else {

				try {
					json_hash.put(q23b, r_q23b);
				} catch (Exception e1) {
					Log.v("JSON ERROR", "Erro ao criar JSON com " + q23b);
					e1.printStackTrace();
				}

			}
		}

		if (errosParse.equals("")) {
			// pergunta24
			String q24 = ((TextView) findViewById(R.id.pergunta24)).getText()
					.toString();
			RadioGroup radio_q24 = (RadioGroup) findViewById(R.id.radio_pergunta24);
			String r_q24 = "";
			if (radio_q24.getCheckedRadioButtonId() != -1) {
				r_q24 = ((RadioButton) findViewById(radio_q24
						.getCheckedRadioButtonId())).getText().toString();
			}
			if (r_q24.equals("")) {

				errosParse += q24 + " vazio.\n";
				Toast.makeText(getApplicationContext(), errosParse,
						Toast.LENGTH_SHORT).show();
			} else {

				try {
					json_hash.put(q24, r_q24);
				} catch (Exception e1) {
					Log.v("JSON ERROR", "Erro ao criar JSON com " + q24);
					e1.printStackTrace();
				}

			}
		}

		if (errosParse.equals("")) {
			// pergunta25

			String q25 = ((TextView) findViewById(R.id.pergunta25)).getText()
					.toString();
			ArrayList<String> q25_cb_list = new ArrayList<String>();
			String r_q25 = "";
			q25a = (CheckBox) findViewById(R.id.q25a);
			q25b = (CheckBox) findViewById(R.id.q25b);
			q25c = (CheckBox) findViewById(R.id.q25c);
			q25d = (CheckBox) findViewById(R.id.q25d);
			q25e = (CheckBox) findViewById(R.id.q25e);
			q25f = (CheckBox) findViewById(R.id.q25f);
			q25g = (CheckBox) findViewById(R.id.q25g);
			q25h = (CheckBox) findViewById(R.id.q25h);
			q25i = (CheckBox) findViewById(R.id.q25i);
			q25j = (CheckBox) findViewById(R.id.q25j);

			if (q25a.isChecked()) {
				q25_cb_list.add(String.valueOf(q25a.getText().toString()));
			} else {
				q25_cb_list.remove(String.valueOf(q25a.getText().toString()));
			}
			if (q25b.isChecked()) {
				q25_cb_list.add(String.valueOf(q25b.getText().toString()));
			} else {
				q25_cb_list.remove(String.valueOf(q25b.getText().toString()));
			}
			if (q25c.isChecked()) {
				q25_cb_list.add(String.valueOf(q25c.getText().toString()));
			} else {
				q25_cb_list.remove(String.valueOf(q25c.getText().toString()));
			}
			if (q25d.isChecked()) {
				q25_cb_list.add(String.valueOf(q25d.getText().toString()));
			} else {
				q25_cb_list.remove(String.valueOf(q25d.getText().toString()));
			}
			if (q25e.isChecked()) {
				q25_cb_list.add(String.valueOf(q25e.getText().toString()));
			} else {
				q25_cb_list.remove(String.valueOf(q25e.getText().toString()));
			}
			if (q25f.isChecked()) {
				q25_cb_list.add(String.valueOf(q25f.getText().toString()));
			} else {
				q25_cb_list.remove(String.valueOf(q25f.getText().toString()));
			}
			if (q25g.isChecked()) {
				q25_cb_list.add(String.valueOf(q25g.getText().toString()));
			} else {
				q25_cb_list.remove(String.valueOf(q25g.getText().toString()));
			}
			if (q25h.isChecked()) {
				q25_cb_list.add(String.valueOf(q25h.getText().toString()));
			} else {
				q25_cb_list.remove(String.valueOf(q25h.getText().toString()));
			}
			if (q25i.isChecked()) {
				q25_cb_list.add(String.valueOf(q25i.getText().toString()));
			} else {
				q25_cb_list.remove(String.valueOf(q25i.getText().toString()));
			}
			if (q25j.isChecked()) {
				q25_cb_list.add(String.valueOf(q25j.getText().toString()));
			} else {
				q25_cb_list.remove(String.valueOf(q25j.getText().toString()));
			}

			for (String s : q25_cb_list) {
				r_q25 += s + ";";
			}

			r_q25 = r_q25.replaceAll("; $", ""); // remove virgula no fim da
													// string

			if (r_q25.equals("")) {

				errosParse += q25 + " vazio.\n";
				Toast.makeText(getApplicationContext(), errosParse,
						Toast.LENGTH_SHORT).show();
			} else {

				try {
					json_hash.put(q25, r_q25);
				} catch (Exception e1) {
					Log.v("JSON ERROR", "Erro ao criar JSON com " + q25);
					e1.printStackTrace();
				}

			}
		}

		if (errosParse.equals("")) {
			// pergunta26
			String q26 = ((TextView) findViewById(R.id.pergunta26)).getText()
					.toString();
			RadioGroup radio_q26 = (RadioGroup) findViewById(R.id.radio_pergunta26);
			String r_q26 = "";
			if (radio_q26.getCheckedRadioButtonId() != -1) {
				r_q26 = ((RadioButton) findViewById(radio_q26
						.getCheckedRadioButtonId())).getText().toString();
			}
			if (r_q26.equals("")) {

				errosParse += q26 + " vazio.\n";
				Toast.makeText(getApplicationContext(), errosParse,
						Toast.LENGTH_SHORT).show();
			} else {

				try {
					json_hash.put(q26, r_q26);
				} catch (Exception e1) {
					Log.v("JSON ERROR", "Erro ao criar JSON com " + q26);
					e1.printStackTrace();
				}

			}

			// pergunta26 - disturbio
			String q26a = ((TextView) findViewById(R.id.pergunta26a)).getText()
					.toString();
			String r_q26a = ((EditText) findViewById(R.id.et_pergunta26a_descrever))
					.getText().toString();
			if (r_q26a.equals("")) {

				errosParse += q26 + ":" + q26a + " vazio.\n";
				Toast.makeText(getApplicationContext(), errosParse,
						Toast.LENGTH_SHORT).show();
			} else {

				try {
					json_hash.put(q26a, r_q26a);
				} catch (Exception e1) {
					Log.v("JSON ERROR", "Erro ao criar JSON com " + q26a);
					e1.printStackTrace();
				}

			}

			// pergunta26 - acompanhamento
			String q26b = ((TextView) findViewById(R.id.pergunta26b)).getText()
					.toString();
			String r_q26b = ((EditText) findViewById(R.id.et_pergunta26b_descrever))
					.getText().toString();
			if (r_q26b.equals("")) {

				errosParse += q26 + ":" + q26b + " vazio.\n";
				Toast.makeText(getApplicationContext(), errosParse,
						Toast.LENGTH_SHORT).show();
			} else {

				try {
					json_hash.put(q26b, r_q26b);
				} catch (Exception e1) {
					Log.v("JSON ERROR", "Erro ao criar JSON com " + q26b);
					e1.printStackTrace();
				}

			}
		}

		if (errosParse.equals("")) {

			// pergunta27
			String q27 = ((TextView) findViewById(R.id.pergunta27)).getText()
					.toString();
			String r_q27 = ((EditText) findViewById(R.id.et_pergunta27_descrever))
					.getText().toString();
			if (r_q27.equals("")) {

				errosParse += q27 + ":" + q27 + " vazio.\n";
				Toast.makeText(getApplicationContext(), errosParse,
						Toast.LENGTH_SHORT).show();
			} else {

				try {
					json_hash.put(q27, r_q27);
				} catch (Exception e1) {
					Log.v("JSON ERROR", "Erro ao criar JSON com " + q27);
					e1.printStackTrace();
				}

			}
		}

		if (errosParse.equals("")) {
			// pergunta28
			String q28 = ((TextView) findViewById(R.id.pergunta28)).getText()
					.toString();

			RadioGroup radio_q28 = (RadioGroup) findViewById(R.id.radio_pergunta28);
			String r_q28 = "";
			if (radio_q28.getCheckedRadioButtonId() != -1) {
				r_q28 = ((RadioButton) findViewById(radio_q28
						.getCheckedRadioButtonId())).getText().toString();
			}
			if (r_q28.equals("")) {

				errosParse += q28 + " vazio.\n";
				Toast.makeText(getApplicationContext(), errosParse,
						Toast.LENGTH_SHORT).show();
			} else {

				try {
					json_hash.put(q28, r_q28);
				} catch (Exception e1) {
					Log.v("JSON ERROR", "Erro ao criar JSON com " + q28);
					e1.printStackTrace();
				}

			}
		}

		if (errosParse.equals("")) {

			// pergunta29
			String q29 = ((TextView) findViewById(R.id.pergunta29)).getText()
					.toString();
			RadioGroup radio_q29 = (RadioGroup) findViewById(R.id.radio_pergunta29);
			String r_q29 = "";
			if (radio_q29.getCheckedRadioButtonId() != -1) {
				r_q29 = ((RadioButton) findViewById(radio_q29
						.getCheckedRadioButtonId())).getText().toString();
			}
			if (r_q29.equals("")) {

				errosParse += q29 + " vazio.\n";
				Toast.makeText(getApplicationContext(), errosParse,
						Toast.LENGTH_SHORT).show();
			} else {

				try {
					json_hash.put(q29, r_q29);
				} catch (Exception e1) {
					Log.v("JSON ERROR", "Erro ao criar JSON com " + q29);
					e1.printStackTrace();
				}

			}

			// pergunta29a
			String q29a = ((TextView) findViewById(R.id.pergunta29_motivo))
					.getText().toString();
			String r_q29a = ((EditText) findViewById(R.id.et_pergunta29_descrever))
					.getText().toString();
			if (r_q29a.equals("")) {

				errosParse += q29 + ":" + q29a + " vazio.\n";
				Toast.makeText(getApplicationContext(), errosParse,
						Toast.LENGTH_SHORT).show();
			} else {

				try {
					json_hash.put(q29a, r_q29a);
				} catch (Exception e1) {
					Log.v("JSON ERROR", "Erro ao criar JSON com " + q29a);
					e1.printStackTrace();
				}

			}
		}

		if (errosParse.equals("")) {
			// pergunta30
			String q30 = ((TextView) findViewById(R.id.pergunta30)).getText()
					.toString();
			RadioGroup radio_q30 = (RadioGroup) findViewById(R.id.radio_pergunta30);
			String r_q30 = "";
			if (radio_q30.getCheckedRadioButtonId() != -1) {
				r_q30 = ((RadioButton) findViewById(radio_q30
						.getCheckedRadioButtonId())).getText().toString();
			}
			if (r_q30.equals("")) {

				errosParse += q30 + " vazio.\n";
				Toast.makeText(getApplicationContext(), errosParse,
						Toast.LENGTH_SHORT).show();
			} else {

				try {
					json_hash.put(q30, r_q30);
				} catch (Exception e1) {
					Log.v("JSON ERROR", "Erro ao criar JSON com " + q30);
					e1.printStackTrace();
				}

			}

			// pergunta30a
			String q30a = ((TextView) findViewById(R.id.pergunta30_doenca))
					.getText().toString();
			String r_q30a = ((EditText) findViewById(R.id.et_pergunta30_descrever))
					.getText().toString();
			if (r_q30a.equals("")) {

				errosParse += q30 + ":" + q30a + " vazio.\n";
				Toast.makeText(getApplicationContext(), errosParse,
						Toast.LENGTH_SHORT).show();
			} else {

				try {
					json_hash.put(q30a, r_q30a);
				} catch (Exception e1) {
					Log.v("JSON ERROR", "Erro ao criar JSON com " + q30a);
					e1.printStackTrace();
				}

			}
		}

		if (errosParse.equals("")) {
			// pergunta31
			String q31 = ((TextView) findViewById(R.id.pergunta31)).getText()
					.toString();
			RadioGroup radio_q31 = (RadioGroup) findViewById(R.id.radio_pergunta31);
			String r_q31 = "";
			if (radio_q31.getCheckedRadioButtonId() != -1) {
				r_q31 = ((RadioButton) findViewById(radio_q31
						.getCheckedRadioButtonId())).getText().toString();
			}
			if (r_q31.equals("")) {

				errosParse += q31 + " vazio.\n";
				Toast.makeText(getApplicationContext(), errosParse,
						Toast.LENGTH_SHORT).show();
			} else {

				try {
					json_hash.put(q31, r_q31);
				} catch (Exception e1) {
					Log.v("JSON ERROR", "Erro ao criar JSON com " + q31);
					e1.printStackTrace();
				}

			}

			// pergunta31a
			String q31a = ((TextView) findViewById(R.id.pergunta31a)).getText()
					.toString();
			String r_q31a = ((EditText) findViewById(R.id.et_pergunta31_descrever))
					.getText().toString();
			if (r_q31a.equals("")) {

				errosParse += q31 + ":" + q31a + " vazio.\n";
				Toast.makeText(getApplicationContext(), errosParse,
						Toast.LENGTH_SHORT).show();
			} else {

				try {
					json_hash.put(q31a, r_q31a);
				} catch (Exception e1) {
					Log.v("JSON ERROR", "Erro ao criar JSON com " + q31a);
					e1.printStackTrace();
				}

			}
		}

		if (errosParse.equals("")) {

			// pergunta32
			String q32 = ((TextView) findViewById(R.id.pergunta32)).getText()
					.toString();
			RadioGroup radio_q32 = (RadioGroup) findViewById(R.id.radio_pergunta32);
			String r_q32 = "";
			if (radio_q32.getCheckedRadioButtonId() != -1) {
				r_q32 = ((RadioButton) findViewById(radio_q32
						.getCheckedRadioButtonId())).getText().toString();
			}
			if (r_q32.equals("")) {

				errosParse += q32 + " vazio.\n";
				Toast.makeText(getApplicationContext(), errosParse,
						Toast.LENGTH_SHORT).show();
			} else {

				try {
					json_hash.put(q32, r_q32);
				} catch (Exception e1) {
					Log.v("JSON ERROR", "Erro ao criar JSON com " + q32);
					e1.printStackTrace();
				}

			}

			// pergunta32a
			String q32a = ((TextView) findViewById(R.id.pergunta32a)).getText()
					.toString();
			String r_q32a = ((EditText) findViewById(R.id.et_pergunta32a_descrever))
					.getText().toString();
			
			if (r_q32a.equals("") && r_q32.equals("Sim")) {

				errosParse += q32 + ":" + q32a + " vazio.\n";
				Toast.makeText(getApplicationContext(), errosParse,
						Toast.LENGTH_SHORT).show();
			} else {

				try {
					json_hash.put(q32a, r_q32a);
				} catch (Exception e1) {
					Log.v("JSON ERROR", "Erro ao criar JSON com " + q32a);
					e1.printStackTrace();
				}

			}

			// pergunta32b
			String q32b = ((TextView) findViewById(R.id.pergunta32b)).getText()
					.toString();

			RadioGroup radio_q32b = (RadioGroup) findViewById(R.id.radio_pergunta32b);
			String r_q32b = "";
			if (radio_q32b.getCheckedRadioButtonId() != -1) {
				r_q32b = ((RadioButton) findViewById(radio_q32b
						.getCheckedRadioButtonId())).getText().toString();
			}
			if (r_q32b.equals("")) {

				errosParse += q32 + ":" + r_q32b + " vazio.\n";
				Toast.makeText(getApplicationContext(), errosParse,
						Toast.LENGTH_SHORT).show();
			} else {

				try {
					json_hash.put(q32b, r_q32b);
				} catch (Exception e1) {
					Log.v("JSON ERROR", "Erro ao criar JSON com " + q32b);
					e1.printStackTrace();
				}

			}
		}

		if (errosParse.equals("")) {

			// pergunta33
			String q33 = ((TextView) findViewById(R.id.pergunta33)).getText()
					.toString();
			RadioGroup radio_q33 = (RadioGroup) findViewById(R.id.radio_pergunta33);
			String r_q33 = "";
			if (radio_q33.getCheckedRadioButtonId() != -1) {
				r_q33 = ((RadioButton) findViewById(radio_q33
						.getCheckedRadioButtonId())).getText().toString();
			}
			if (r_q33.equals("")) {

				errosParse += q33 + " vazio.\n";
				Toast.makeText(getApplicationContext(), errosParse,
						Toast.LENGTH_SHORT).show();
			} else {

				try {
					json_hash.put(q33, r_q33);
				} catch (Exception e1) {
					Log.v("JSON ERROR", "Erro ao criar JSON com " + q33);
					e1.printStackTrace();
				}

			}
		}

		if (errosParse.equals("")) {

			// pergunta34
			String q34 = ((TextView) findViewById(R.id.pergunta34)).getText()
					.toString();
			String r_q34 = ((EditText) findViewById(R.id.et_pergunta34))
					.getText().toString();
			if (r_q34.equals("")) {

				errosParse += q34 + " vazio.\n";
				Toast.makeText(getApplicationContext(), errosParse,
						Toast.LENGTH_SHORT).show();
			} else {

				try {
					json_hash.put(q34, r_q34);
				} catch (Exception e1) {
					Log.v("JSON ERROR", "Erro ao criar JSON com " + q34);
					e1.printStackTrace();
				}

			}
		}

		// se não tiver erro, grava
		if (errosParse.equals("")) {
			// salva em formato JSON

			// Cria a pasta com as respostas JSON
			File pastaRespostas = new File(Environment
					.getExternalStorageDirectory().toString()
					+ "/respostasFrigo");
			if (pastaRespostas.exists() && pastaRespostas.isDirectory()) {
				Log.v("Diretório Respostas", "Diretório: "
						+ Environment.getExternalStorageDirectory().toString()
						+ "/respostasFrigo" + " já existe.");
			} else {
				pastaRespostas.mkdirs();
			}

			// Path da pasta de respostas adequada para o formato String
			String pastaRespostasString = pastaRespostas.toString();

			// Pega a data e hora que foi respondido o questionário
			SimpleDateFormat dataHora = new SimpleDateFormat(
					"dd_MM_yyyy_HH_mm_");
			String currentDateandTime = dataHora.format(new Date());

			// passa a hash list para JSON Value, desta forma o JSON fica
			// ordenado
			StringWriter jsonString_hash = new StringWriter();

			try {
				JSONValue.writeJSONString(json_hash, jsonString_hash);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			String jsonText = jsonString_hash.toString();

			// Cria o arquivo JSON e salva na pasta previamente criada
			FileWriter respostaJSON;
			try {
				respostaJSON = new FileWriter(pastaRespostasString + "/"
						+ r_nome + currentDateandTime + ".json");
				respostaJSON.write(jsonText);
				respostaJSON.flush();
				respostaJSON.close();

				// informativo que deu tudo certo
				Toast.makeText(
						getApplicationContext(),
						"Questionário gravado com sucesso em: "
								+ pastaRespostasString + "/" + r_nome
								+ currentDateandTime + ".json",
						Toast.LENGTH_LONG).show();

			} catch (IOException e) {
				// informativo que deu tudo errado
				Toast.makeText(getApplicationContext(),
						"Erro ao gravar aquivo com os resultados",
						Toast.LENGTH_LONG).show();
				Log.v("Arquivo Resposta",
						"Erro ao salvar no disco o JSON de respostas");
				e.printStackTrace();
			}

			//clear all
			
			((EditText) findViewById(R.id.et_empresa)).setText("");
			((EditText) findViewById(R.id.et_nome)).setText("");
			((EditText) findViewById(R.id.et_idade)).setText("");
			RadioGroup radio_sexo = (RadioGroup)findViewById(R.id.radio_sexo);
			radio_sexo.clearCheck();
			((EditText) findViewById(R.id.et_tempoEmpresa)).setText("");
			((EditText) findViewById(R.id.et_tempoAtividade)).setText("");
			RadioGroup radio_turno = (RadioGroup) findViewById(R.id.radio_turno);
			radio_turno.clearCheck();
			((EditText) findViewById(R.id.et_horarioTrabalho_hora)).setText("");
			((EditText) findViewById(R.id.et_horarioTrabalho_minuto)).setText("");
			((EditText) findViewById(R.id.et_horaParada_hora)).setText("");
			((EditText) findViewById(R.id.et_horaParada_minuto)).setText("");
			((EditText) findViewById(R.id.et_pergunta1)).setText("");
			RadioGroup radio_q2 = (RadioGroup) findViewById(R.id.radio_pergunta2);
			radio_q2.clearCheck();
			((EditText) findViewById(R.id.et_pergunta3)).setText("");
			RadioGroup radio_q4 = (RadioGroup) findViewById(R.id.radio_pergunta4);
			radio_q4.clearCheck();
			RadioGroup radio_q5 = (RadioGroup) findViewById(R.id.radio_pergunta5);
			radio_q5.clearCheck();
			RadioGroup radio_q6 = (RadioGroup) findViewById(R.id.radio_pergunta6);
			radio_q6.clearCheck();
			((EditText) findViewById(R.id.et_pergunta7)).setText("");
			RadioGroup radio_q8 = (RadioGroup) findViewById(R.id.radio_pergunta8);
			radio_q8.clearCheck();
			RadioGroup radio_q9 = (RadioGroup) findViewById(R.id.radio_pergunta9);
			radio_q9.clearCheck();
			((EditText) findViewById(R.id.et_pergunta10_hora)).setText("");
			((EditText) findViewById(R.id.et_pergunta10_minuto)).setText("");
			RadioGroup radio_q11 = (RadioGroup) findViewById(R.id.radio_pergunta11);
			radio_q11.clearCheck();
			((EditText) findViewById(R.id.et_pergunta11_descrever)).setText("");
			RadioGroup radio_q12 = (RadioGroup) findViewById(R.id.radio_pergunta12);
			radio_q12.clearCheck();
			RadioGroup radio_q13 = (RadioGroup) findViewById(R.id.radio_pergunta13);
			radio_q13.clearCheck();
			RadioGroup radio_q14 = (RadioGroup) findViewById(R.id.radio_pergunta14);
			radio_q14.clearCheck();
			RadioGroup radio_q15 = (RadioGroup) findViewById(R.id.radio_pergunta15);
			radio_q15.clearCheck();
			RadioGroup radio_q16 = (RadioGroup) findViewById(R.id.radio_pergunta16);
			radio_q16.clearCheck();
			q17a.setChecked(false);
			q17b.setChecked(false);
			RadioGroup radio_q18 = (RadioGroup) findViewById(R.id.radio_pergunta18);
			radio_q18.clearCheck();
			RadioGroup radio_q19 = (RadioGroup) findViewById(R.id.radio_pergunta19);
			radio_q19.clearCheck();
			RadioGroup radio_q20 = (RadioGroup) findViewById(R.id.radio_pergunta20);
			radio_q20.clearCheck();
			((EditText) findViewById(R.id.et_pergunta21_descrever)).setText("");
			RadioGroup radio_q22 = (RadioGroup) findViewById(R.id.radio_pergunta22);
			radio_q22.clearCheck();
			RadioGroup radio_q23 = (RadioGroup) findViewById(R.id.radio_pergunta23);
			radio_q23.clearCheck();
			((EditText) findViewById(R.id.et_pergunta23a_descrever)).setText("");
			((EditText) findViewById(R.id.et_pergunta23b_descrever)).setText("");
			RadioGroup radio_q24 = (RadioGroup) findViewById(R.id.radio_pergunta24);
			radio_q24.clearCheck();
			q25a.setChecked(false);
			q25b.setChecked(false);
			q25c.setChecked(false);
			q25d.setChecked(false);
			q25e.setChecked(false);
			q25f.setChecked(false);
			q25g.setChecked(false);
			q25h.setChecked(false);
			q25i.setChecked(false);
			q25j.setChecked(false);
			RadioGroup radio_q26 = (RadioGroup) findViewById(R.id.radio_pergunta26);
			radio_q26.clearCheck();
			((EditText) findViewById(R.id.et_pergunta26a_descrever)).setText("");
			((EditText) findViewById(R.id.et_pergunta26b_descrever)).setText("");
			((EditText) findViewById(R.id.et_pergunta27_descrever)).setText("");
			RadioGroup radio_q28 = (RadioGroup) findViewById(R.id.radio_pergunta28);
			radio_q28.clearCheck();
			RadioGroup radio_q29 = (RadioGroup) findViewById(R.id.radio_pergunta29);
			radio_q29.clearCheck();
			((EditText) findViewById(R.id.et_pergunta29_descrever)).setText("");
			RadioGroup radio_q30 = (RadioGroup) findViewById(R.id.radio_pergunta30);
			radio_q30.clearCheck();
			((EditText) findViewById(R.id.et_pergunta30_descrever)).setText("");
			RadioGroup radio_q31 = (RadioGroup) findViewById(R.id.radio_pergunta31);
			radio_q31.clearCheck();
			((EditText) findViewById(R.id.et_pergunta31_descrever)).setText("");
			RadioGroup radio_q32 = (RadioGroup) findViewById(R.id.radio_pergunta32);
			radio_q32.clearCheck();
			((EditText) findViewById(R.id.et_pergunta32a_descrever)).setText("");
			RadioGroup radio_q32b = (RadioGroup) findViewById(R.id.radio_pergunta32b);
			radio_q32b.clearCheck();
			RadioGroup radio_q33 = (RadioGroup) findViewById(R.id.radio_pergunta33);
			radio_q33.clearCheck();
			((EditText) findViewById(R.id.et_pergunta34)).setText("");
		}
	}
}
