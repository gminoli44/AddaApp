package com.app.adda.Adda;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;



public class Main2Activity extends AppCompatActivity {



    private static final int SOLICITUD_PERMISO_CALL_PHONE = 1;
    private static final int SOLICITUD_PERMISO_CAMARA = 2;
    private static final int SOLICITUD_PERMISO_STORAGE_READ = 3;
    private static final int SOLICITUD_PERMISO_STORAGE_WRITE = 4;
    private static final int SOLICITUD_PERMISO_GPS = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView textView = (TextView) findViewById(R.id.cerca);
        SpannableString mitextoU = new SpannableString("Acerca de Adda");
        mitextoU.setSpan(new UnderlineSpan(), 0, mitextoU.length(), 0);
        textView.setText(mitextoU);

        //Permiso CALL

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            // startActivity(intentLLamada);
            // Toast.makeText(this, "Permiso Concedido", Toast.LENGTH_SHORT).show();
        } else {
            explicarUsoPermisoLlamada();
            solicitarPermisoHacerLlamada();
        }

        //Permiso CAMARA

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            // startActivity(intentLLamada);
            // Toast.makeText(this, "Permiso Concedido", Toast.LENGTH_SHORT).show();
        } else {
            explicarUsoPermisoCamara();
            //solicitarPermisoHacerCamara();
        }

        //Permiso STORAGE READ

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            // startActivity(intentLLamada);
            // Toast.makeText(this, "Permiso Concedido", Toast.LENGTH_SHORT).show();
        } else {
            explicarUsoPermisoStogareRead();
            //solicitarPermisoHacerStorageRead();
        }

        //Permiso STORAGE WRITE

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            // startActivity(intentLLamada);
            // Toast.makeText(this, "Permiso Concedido", Toast.LENGTH_SHORT).show();
        } else {
            explicarUsoPermisoStogareWrite();
            //solicitarPermisoHacerStorageWrite();
        }

        //Permiso GPS fine

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // startActivity(intentLLamada);
            // Toast.makeText(this, "Permiso Concedido", Toast.LENGTH_SHORT).show();
        } else {
            explicarUsoPermisoGPSFine();
            //solicitarPermisoHacerGPSFine();
        }
    }



    public void denuncias(View view) {

        Intent intent = new Intent(getApplicationContext(), MainActivity.class)/*.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP)*/;
        startActivity(intent);
    }

    public void button_info(View view) {

        Intent intent = new Intent(getApplicationContext(), info_adda.class)/*.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP)*/;
        startActivity(intent);
    }

    public void se_parte(View view) {

        Intent intent = new Intent(getApplicationContext(), se_parte.class)/*.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP)*/;
        startActivity(intent);
    }

    public void llamadas(View view) {

        Intent intent = new Intent(getApplicationContext(), llamadas.class)/*.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP)*/;
        startActivity(intent);
    }

    public void usar_adda(View view) {

        Intent intent = new Intent(getApplicationContext(), Mas_info.class)/*.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP)*/;
        startActivity(intent);
    }

    /*---------- LLAMADAS ------------*/

    private void explicarUsoPermisoLlamada() {
        //Este IF es necesario para saber si el usuario ha marcado o no la casilla [] No volver a preguntar
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
            Toast.makeText(this, "Se necesita permiso para realizar llamadas de emergencia", Toast.LENGTH_SHORT).show();
            //Explicarle al usuario porque necesitas el permiso (Opcional)
            alertDialogLlamada();
        }
    }

    private void solicitarPermisoHacerLlamada() {
        //Pedimos el permiso o los permisos con un cuadro de dialogo del sistema
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CALL_PHONE,
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_FINE_LOCATION
                },SOLICITUD_PERMISO_GPS);
        //   Toast.makeText(this, "Pedimos el permiso con un cuadro de dialogo del sistema", Toast.LENGTH_SHORT).show();
    }

    /*---------- CAMARA ------------*/

    private void explicarUsoPermisoCamara() {
        //Este IF es necesario para saber si el usuario ha marcado o no la casilla [] No volver a preguntar
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
            Toast.makeText(this, "Se necesita permiso para capturar fotografÌas", Toast.LENGTH_SHORT).show();
            //Explicarle al usuario porque necesitas el permiso (Opcional)
            alertDialogCamara();
        }
    }



    /*---------- STORAGE WRITE ------------*/

    private void explicarUsoPermisoStogareWrite() {
        //Este IF es necesario para saber si el usuario ha marcado o no la casilla [] No volver a preguntar
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(this, "Se necesita permiso para guardar", Toast.LENGTH_SHORT).show();
            //Explicarle al usuario porque necesitas el permiso (Opcional)
            alertDialogWriteStorage();
        }
    }


    /*---------- STORAGE READ ------------*/

    private void explicarUsoPermisoStogareRead() {
        //Este IF es necesario para saber si el usuario ha marcado o no la casilla [] No volver a preguntar
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Toast.makeText(this, "Se necesita permiso para guardar", Toast.LENGTH_SHORT).show();
            //Explicarle al usuario porque necesitas el permiso (Opcional)
            alertDialogReadStorage();
        }
    }

    /*---------- STORAGE READ ------------*/

    private void explicarUsoPermisoGPSFine() {
        //Este IF es necesario para saber si el usuario ha marcado o no la casilla [] No volver a preguntar
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            Toast.makeText(this, "Se necesita permiso para usar GPS", Toast.LENGTH_SHORT).show();
            //Explicarle al usuario porque necesitas el permiso (Opcional)
            alertDialogGPS();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        /**
         * Si tubieramos diferentes permisos solicitando permisos de la aplicacion, aqui habria varios IF
         */
        if (requestCode == SOLICITUD_PERMISO_CALL_PHONE) {

            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Realizamos la accion
                //  startActivity(intentLLamada);
                //    Toast.makeText(this, "Permiso Concedido", Toast.LENGTH_SHORT).show();
            } else {
                //1-Seguimos el proceso de ejecucion sin esta accion: Esto lo recomienda Google
                //2-Cancelamos el proceso actual
                //3-Salimos de la aplicacion
                //    Toast.makeText(this, "Permiso No Concedido", Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == SOLICITUD_PERMISO_CAMARA) {

            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Realizamos la accion
                //  startActivity(intentLLamada);
                //    Toast.makeText(this, "Permiso Concedido", Toast.LENGTH_SHORT).show();
            } else {
                //1-Seguimos el proceso de ejecucion sin esta accion: Esto lo recomienda Google
                //2-Cancelamos el proceso actual
                //3-Salimos de la aplicacion
                //    Toast.makeText(this, "Permiso No Concedido", Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == SOLICITUD_PERMISO_STORAGE_READ) {

            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Realizamos la accion
                //  startActivity(intentLLamada);
                //    Toast.makeText(this, "Permiso Concedido", Toast.LENGTH_SHORT).show();
            } else {
                //1-Seguimos el proceso de ejecucion sin esta accion: Esto lo recomienda Google
                //2-Cancelamos el proceso actual
                //3-Salimos de la aplicacion
                //    Toast.makeText(this, "Permiso No Concedido", Toast.LENGTH_SHORT).show();
            }
        }


        if (requestCode == SOLICITUD_PERMISO_STORAGE_WRITE) {

            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Realizamos la accion
                //  startActivity(intentLLamada);
                //    Toast.makeText(this, "Permiso Concedido", Toast.LENGTH_SHORT).show();
            } else {
                //1-Seguimos el proceso de ejecucion sin esta accion: Esto lo recomienda Google
                //2-Cancelamos el proceso actual
                //3-Salimos de la aplicacion
                //    Toast.makeText(this, "Permiso No Concedido", Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == SOLICITUD_PERMISO_GPS) {

            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Realizamos la accion
                //  startActivity(intentLLamada);
                //    Toast.makeText(this, "Permiso Concedido", Toast.LENGTH_SHORT).show();
            } else {
                //1-Seguimos el proceso de ejecucion sin esta accion: Esto lo recomienda Google
                //2-Cancelamos el proceso actual
                //3-Salimos de la aplicacion
                //    Toast.makeText(this, "Permiso No Concedido", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void alertDialogLlamada() {
        // 1. Instancia de AlertDialog.Builder con este constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 2. Encadenar varios mÈtodos setter para ajustar las caracterÌsticas del di·logo
        builder.setMessage("Permiso para realizar llamadas concedido");
        builder.setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        builder.show();
    }

    public void alertDialogCamara() {
        // 1. Instancia de AlertDialog.Builder con este constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 2. Encadenar varios mÈtodos setter para ajustar las caracterÌsticas del di·logo
        builder.setMessage("Permiso para realizar capturar fotografias");
        builder.setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        builder.show();
    }


    public void alertDialogReadStorage() {
        // 1. Instancia de AlertDialog.Builder con este constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 2. Encadenar varios mÈtodos setter para ajustar las caracterÌsticas del di·logo
        builder.setMessage("Permiso para leer datos de almacenamiento");
        builder.setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        builder.show();
    }

    public void alertDialogWriteStorage() {
        // 1. Instancia de AlertDialog.Builder con este constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 2. Encadenar varios mÈtodos setter para ajustar las caracterÌsticas del di·logo
        builder.setMessage("Permiso para almacenar datos");
        builder.setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        builder.show();
    }

    public void alertDialogGPS() {
        // 1. Instancia de AlertDialog.Builder con este constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 2. Encadenar varios mÈtodos setter para ajustar las caracterÌsticas del di·logo
        builder.setMessage("Permiso para user GPS");
        builder.setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        builder.show();
    }


}
