package com.app.adda.Adda;

        import android.app.ProgressDialog;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.android.volley.AuthFailureError;
        import com.android.volley.Request;
        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.StringRequest;
        import com.android.volley.toolbox.Volley;


        import java.util.HashMap;
        import java.util.Map;
        import java.util.regex.Matcher;
        import java.util.regex.Pattern;


public class se_parte extends AppCompatActivity {

    EditText firstname, lastname, email;
    Button insert, show;
    RequestQueue requestQueue;
    String insertUrl = "http://yamgo.com.co/adda/registro.php";
    ProgressDialog progressDialog;

    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_se_parte);

        progressDialog = new ProgressDialog(this);

        firstname = (EditText) findViewById(R.id.nombre);
        lastname = (EditText) findViewById(R.id.telefono);
        email = (EditText) findViewById(R.id.email);
        insert = (Button) findViewById(R.id.insert);


        requestQueue = Volley.newRequestQueue(getApplicationContext());



        insert.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {




                if(firstname.getText().toString().isEmpty()) {
                    Toast.makeText(se_parte.this, "Escribe tu nombre", Toast.LENGTH_SHORT).show();
                }
                else if(lastname.getText().toString().isEmpty())
                {
                    Toast.makeText(se_parte.this, "Escribe un telefono", Toast.LENGTH_SHORT).show();
                }
                    else if(email.getText().toString().isEmpty()) {
                    Toast.makeText(se_parte.this, "Escribe un Email", Toast.LENGTH_SHORT).show();
                }else if(!validateEmail(email.getText().toString())) {
                    email.setError("Invalid Email");
                    email.requestFocus();
                    Toast.makeText(se_parte.this, "Email no valido", Toast.LENGTH_SHORT).show();
                }
                else{

                    progressDialog.setMessage("Registrando...");
                    progressDialog.show();
                    StringRequest request = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            System.out.println(response.toString());
                            Log.d("LOGCAMILO", response.toString());
                            Toast.makeText(se_parte.this, "Usuario registrado", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            firstname.setText("");
                            lastname.setText("");
                            email.setText("");
                            Intent intent = new Intent(getApplicationContext(), registro_gracias.class)/*.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP)*/;
                            startActivity(intent);
                            finish();

                        }


                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }



                    }) {

                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> parameters  = new HashMap<String, String>();
                            parameters.put("nombre",firstname.getText().toString());
                            parameters.put("telefono",lastname.getText().toString());
                            parameters.put("email",email.getText().toString());



                            return parameters;
                        }
                    };
                    requestQueue.add(request);

                }






            }

        });


    }

    protected boolean validateEmail(String email) {
        String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

}