package besteburhan.artibir;

import android.app.Dialog;
import android.location.Geocoder;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

public class Maps extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap mGoogleMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        if(googleServicesAvailable()){//google haritalar yüklü hata var mı

            setContentView(R.layout.activity_maps);//eğer ki haritaları açamıyorsa görüntü sağlanmayacak
            initMap();
        }

    }

    private void initMap() {
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync( this);


    }

    public boolean googleServicesAvailable(){//play sevicese ulaşılabiliyor mu
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int isAvailable = api.isGooglePlayServicesAvailable(this);
        if(isAvailable== ConnectionResult.SUCCESS){
            return true;
        }
        //eğer ki kullanıcıda google maps yoksa?
        else if(api.isUserResolvableError(isAvailable)){
            Dialog dialog = api.getErrorDialog(this,isAvailable,0);
            dialog.show();
        }
        else{
            Toast.makeText(this,"Play Services'e bağlanılamıyor",Toast.LENGTH_LONG).show();
        }
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap =  googleMap;//googleMap object dönecek
        goToLocationZoom(39.908103, 32.784205,1);//haritalar başlatıldığında ilk neresini merkez alsın
    }



  /*  Zoom yokken
        private void goToLocation(double lat, double lng) {// latitude=enlem longitude =boylam
        LatLng ll=new LatLng(lat,lng);
        CameraUpdate update= CameraUpdateFactory.newLatLng(ll);//görüş alanını güncelle
        mGoogleMap.moveCamera(update);//hareket etme

    }*/
    private void goToLocationZoom(double lat, double lng,int zoom) {// latitude=enlem longitude =boylam
        LatLng ll=new LatLng(lat,lng);
        CameraUpdate update= CameraUpdateFactory.newLatLngZoom(ll,zoom);//görüş alanını güncelle ve zoomla
        mGoogleMap.moveCamera(update);

    }

    public void geoLocate(View view) throws IOException {
        EditText editTextWhereGo = (EditText) findViewById(R.id.editTextWhereGo);
        String location = editTextWhereGo.getText().toString();

        Geocoder gc = new Geocoder(this);//geo coder object geo kordinatları verecek,ana işi string alır ve enlem boylama dönüştürür
        List<android.location.Address> list = gc.getFromLocationName(location,1);//1 sonuça ihtiyacımız var en faazla demek
        //kullanıcının girdiği yer için adres listi döndürür


        //Address tipinde olmalıydı dikkat et!!

        android.location.Address address = list.get(0);//ilk itemi al listede ki
        String locality = address.getLocality();


        double lat = address.getLatitude();
        double lng = address.getLongitude();
        goToLocationZoom(lat,lng,15);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_maps, menu);
        return super.onCreateOptionsMenu(menu);

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {


            case R.id.mapTypeHybrid:
                mGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
            case R.id.mapTypeNormal:
                mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
