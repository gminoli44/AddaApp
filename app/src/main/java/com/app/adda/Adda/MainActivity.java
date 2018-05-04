package com.app.adda.Adda;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.app.ProgressDialog;


import com.kosalgeek.android.photoutil.CameraPhoto;
import com.kosalgeek.android.photoutil.GalleryPhoto;
import com.kosalgeek.android.photoutil.ImageBase64;
import com.kosalgeek.android.photoutil.ImageLoader;
import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.EachExceptionsHandler;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getName();

    private ProgressDialog progressDialog;

    ImageView ivCamera, ivGallery, ivImage;
    Button ivUpload;
    EditText nombre, telefono, comentario;

    CameraPhoto cameraPhoto;

    GalleryPhoto galleryPhoto;

    final int CAMERA_REQUEST = 1100;
    final int GALLERY_REQUEST = 2200;


    String selectedPhoto;



    LocationManager locationManager;
    double longitudeBest, latitudeBest;
    double longitudeGPS, latitudeGPS;
    double longitudeNetwork, latitudeNetwork;
    AlertDialog alert = null;

    FloatingActionButton fab;

    String arrayName[] = {"Información",
            "Enviar denuncia"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        nombre = (EditText)findViewById(R.id.nombre);
        telefono = (EditText)findViewById(R.id.telefono);
        comentario = (EditText)findViewById(R.id.comentario);


        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder(); StrictMode.setVmPolicy(builder.build());


        progressDialog = new ProgressDialog(this);


        cameraPhoto = new CameraPhoto(getApplicationContext());
        galleryPhoto = new GalleryPhoto(getApplicationContext());

        ivCamera = (ImageView) findViewById(R.id.ivCamera);
        ivGallery = (ImageView) findViewById(R.id.ivGallery);
        ivImage = (ImageView) findViewById(R.id.ivImage);
        //ivUpload = (Button) findViewById(R.id.ivUpload);


        fab = (FloatingActionButton)findViewById(R.id.fab);


        ivCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    startActivityForResult(cameraPhoto.takePhotoIntent(), CAMERA_REQUEST);
                    cameraPhoto.addToGallery();
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(),
                            "SALIO ALGO MAL", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ivGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(galleryPhoto.openGalleryIntent(), GALLERY_REQUEST);
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(selectedPhoto == null){
                    Toast.makeText(getApplicationContext(),"NO HAY IMAGEN", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(telefono.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Escribe un teléfono", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(comentario.getText().toString().trim().length()<10) {
                    Toast.makeText(MainActivity.this, "Mínimo 6 carácteres en observaciones", Toast.LENGTH_SHORT).show();
                    return;
                }

                if((telefono.getText().toString().length()<5)) {
                    Toast.makeText(MainActivity.this, "Teléfono demasiado corto", Toast.LENGTH_SHORT).show();
                    return;
                }


                String cadenaDondeBuscar = telefono.getText().toString();
                String loQueQuieroBuscar = "00000";
                String[] palabras = loQueQuieroBuscar.split("\\s+");
                for (String palabra : palabras) {
                    if ((cadenaDondeBuscar.contains(palabra))&&(cadenaDondeBuscar.length()<=5)) {
                        Toast.makeText(MainActivity.this, "Teléfono inválido", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }






                if(comentario.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Escribe observaciones", Toast.LENGTH_SHORT).show();
                    return;
                }


                if ( !locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
                    AlertNoGps();
                    return;
                }

                if(latitudeGPS==0.0 && longitudeGPS==0.0){
                    Toast.makeText(getApplicationContext(),"NO SE PUDO OBTENER TU UBICACIÓN... VUELVE A INTENTARLO", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    Bitmap bitmap = ImageLoader.init().from(selectedPhoto).requestSize(200, 200).getBitmap();
                    String encodedImage = ImageBase64.encode(bitmap);
                    // Log.d(TAG, encodedImage);

                    //SEND TO PHP SCRIPT
                    HashMap<String, String> postData = new HashMap<String, String>();
                    postData.put("image", encodedImage);
                    postData.put("nombre", String.valueOf(nombre.getText()));
                    postData.put("telefono", String.valueOf(telefono.getText()));
                    postData.put("comentario", String.valueOf(comentario.getText()));
                    postData.put("latitud", Double.toString(latitudeGPS));
                    postData.put("longitud", Double.toString(longitudeGPS));

                    if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    }
                    locationManager.requestLocationUpdates(
                            //MinTime Changed
                            LocationManager.GPS_PROVIDER, 500, 10, locationListenerGPS);

                    PostResponseAsyncTask task = new PostResponseAsyncTask(MainActivity.this, postData, new AsyncResponse() {
                        @Override
                        public void processFinish(String s) {
                            Log.d("RESPUESTA PHP",s);

                            if(s.contains("upload_success")){
                                Intent intent = new Intent(getApplicationContext(), Denuncia_gracias.class)/*.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP)*/;
                                startActivity(intent);
                                Toast.makeText(getApplicationContext(),"ENVIADO CON EXITO", Toast.LENGTH_SHORT).show();
                                finish();

                            /*    try {
                                    enviar();
                                } catch (ExecutionException e) {
                                    e.printStackTrace();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }*/

                            }else{
                                //TODO
                                //Change text, error por exito
                                Toast.makeText(getApplicationContext(),
                                        "Imagen no enviada", Toast.LENGTH_SHORT).show();
                            }
                            progressDialog.dismiss();
                        }
                    });

                    task.execute("http://yamgo.com.co/adda/connection.php");
                    progressDialog.setMessage("Enviando tu denuncia, por favor espera, puede tardar unos segundos...");
                    progressDialog.show();
                    task.setEachExceptionsHandler(new EachExceptionsHandler() {
                        @Override
                        public void handleIOException(IOException e) {
                            Toast.makeText(getApplicationContext(),
                                    "ERROR CONECTANDO", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void handleMalformedURLException(MalformedURLException e) {
                            Toast.makeText(getApplicationContext(),
                                    "URL ERROR", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void handleProtocolException(ProtocolException e) {
                            Toast.makeText(getApplicationContext(),
                                    "ERROR PROTOCOLO", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void handleUnsupportedEncodingException(UnsupportedEncodingException e) {
                            Toast.makeText(getApplicationContext(),
                                    "ENCODING ERROR", Toast.LENGTH_SHORT).show();
                        }
                    });


                } catch (FileNotFoundException e) {
                    Toast.makeText(getApplicationContext(),
                            "SALIO ALGO MAL CODIFICANDO LAS FOTOS", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ///////////////////////////gps/////////////////////////////

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        /****Mejora****/
        if ( !locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            AlertNoGps();
        }
        /********/

        // locationManager.removeUpdates(locationListenerGPS);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        }
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 2 * 20 * 1000, 10, locationListenerGPS);


        //////////////////////////////////////////////////////////

    }

    /*public void enviar() throws ExecutionException, InterruptedException {
      //  gps g = new gps();
        String method = "enviar";
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method,nombre.getText().toString(),telefono.getText().toString(),comentario.getText().toString(),"22","44");
        //String e = backgroundTask.get();

    }*/

    private void AlertNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("El sistema GPS esta desactivado, øDesea activarlo?")
                .setCancelable(false)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        alert = builder.create();
        alert.show();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(alert != null)
        {
            alert.dismiss ();
        }
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            if(requestCode == CAMERA_REQUEST){
                String photoPath = cameraPhoto.getPhotoPath();
                selectedPhoto = photoPath;
                try {
                    Bitmap bitmap = ImageLoader.init().from(photoPath).requestSize(512, 512).getBitmap();
                    ivImage.setImageBitmap(bitmap);
                    //ivImage.setImageBitmap(getRotateBitmap(bitmap, 90));
                } catch (FileNotFoundException e) {
                    Toast.makeText(getApplicationContext(),
                            "SALIO ALGO MAL SUBIENDO LAS FOTOS", Toast.LENGTH_SHORT).show();
                }

            }

            else if(requestCode == GALLERY_REQUEST){
                Uri uri = data.getData();
                galleryPhoto.setPhotoUri(uri);
                String photoPath = galleryPhoto.getPath();
                selectedPhoto = photoPath;
                try {
                    Bitmap bitmap = ImageLoader.init().from(photoPath).requestSize(512, 512).getBitmap();
                    ivImage.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    Toast.makeText(getApplicationContext(),
                            "SALIO ALGO MAL ESCOGIENDO LAS FOTOS", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }

    /*
    private Bitmap getRotateBitmap(Bitmap source, int angle){
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        Bitmap bitmap1 = Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
        return bitmap1;
    }*/

    /////////////TODO LO DEL GPS ////////////

    private boolean checkLocation() {
        if (!isLocationEnabled())
            showAlert();
        return isLocationEnabled();
    }

    private void showAlert() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Enable Location")
                .setMessage("Su ubicaciÛn esta desactivada.\npor favor active su ubicaciÛn " +
                        "usa esta app")
                .setPositiveButton("ConfiguraciÛn de ubicaciÛn", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(myIntent);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    }
                });
        dialog.show();
    }

    private boolean isLocationEnabled() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    private final LocationListener locationListenerBest = new LocationListener() {
        public void onLocationChanged(Location location) {
            longitudeBest = location.getLongitude();
            latitudeBest = location.getLatitude();

        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
        }

        @Override
        public void onProviderEnabled(String s) {
        }

        @Override
        public void onProviderDisabled(String s) {
        }
    };

    private final LocationListener locationListenerNetwork = new LocationListener() {
        public void onLocationChanged(Location location) {
            longitudeNetwork = location.getLongitude();
            latitudeNetwork = location.getLatitude();

        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
        }

        @Override
        public void onProviderEnabled(String s) {

        }
        @Override
        public void onProviderDisabled(String s) {

        }
    };

    private final LocationListener locationListenerGPS = new LocationListener() {
        public void onLocationChanged(Location location) {
            longitudeGPS = location.getLongitude();
            latitudeGPS = location.getLatitude();
            //  Toast.makeText(getApplicationContext(), Double.toString(longitudeGPS) , Toast.LENGTH_SHORT).show();

        }
        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
        }

        @Override
        public void onProviderEnabled(String s) {
        }
        @Override
        public void onProviderDisabled(String s) {
        }
    };

}