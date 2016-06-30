  private Spinner spn1;
    private List<String> estados = new ArrayList<String>();
    private String estado;
    private TextView nickName;
    private EditText edtTextTeste;
    public void getSpinner (){
        //Spinner pra popular o estado la no logar
        spn1 = (Spinner) findViewById(R.id.spinner);

        //Adicionando Nomes no ArrayList
        estados.add("SP");
        estados.add("MG");
        estados.add("SC");
        estados.add("RS");
        estados.add("PR");

        //Cria um ArrayAdapter usando um padrão de layout da classe R do android, passando o ArrayList nomes
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, estados);
        ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spn1.setAdapter(spinnerArrayAdapter);

        //Método do Spinner para capturar o item selecionado
        spn1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int posicao, long id) {
                //pega nome pela posição
                estado = parent.getItemAtPosition(posicao).toString();
                nickName = (TextView) findViewById(R.id.edtTextTeste);
                nickName.setText(estado);
                //imprime um Toast na tela com o nome que foi selecionado
                Toast.makeText(getApplicationContext(), "Nome Selecionado: " + estado, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //Este evento no edtText vai execultar quando o usuario digitar "gif", ai vc pode fazer um esquema tipo do Telegrão
        edtTextTeste = (EditText) findViewById(R.id.edtTextTeste);
        edtTextTeste.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(edtTextTeste.getText().toString().equals("gif"))
                    Toast.makeText(getApplicationContext(), "Teste", Toast.LENGTH_LONG).show();
            }
        });
    }
}
