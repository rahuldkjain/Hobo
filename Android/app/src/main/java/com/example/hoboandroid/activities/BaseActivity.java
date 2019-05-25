package com.example.hoboandroid.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.support.v7.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hoboandroid.Api;
import com.example.hoboandroid.R;
import com.example.hoboandroid.adapters.CategoryAdapter;
import com.example.hoboandroid.adapters.NavigationAdapter;
import com.example.hoboandroid.models.category.Category;
import com.example.hoboandroid.models.category.ResponseCategory;
import com.example.hoboandroid.services.ProductService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BaseActivity extends AppCompatActivity implements NavigationAdapter.OnItemClickListener {


    private DrawerLayout drawerLayout;
    NavigationView navigationView;

    private TextView logoTextView;


    public ViewGroup fullLayout;
    public FrameLayout frameLayout;

    private List<String> categoryItems = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);
        drawerLayout = findViewById(R.id.drawer_layout);
        logoTextView = findViewById(R.id.logoTextView);
        //Log.d("BaseActivity",drawerLayout.toString());



        navigationView = findViewById(R.id.base_nav_view);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);


        // set up the drawer's list view with items and click listener
//        drawerList.setAdapter(new NavigationAdapter(mPlanetTitles, this));

        logoTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.START, true);
            }
        });

        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {

            }

            @Override
            public void onDrawerOpened(@NonNull View view) {
                //getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(@NonNull View view) {
                //getActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });

        ActionBarDrawerToggle mActionBarDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawerLayout,         /* DrawerLayout object */
                (Toolbar) findViewById(R.id.toolbar),  /* nav drawer image to replace 'Up' caret */
                R.string.buy_now, /* "open drawer" description for accessibility */
                R.string.attributes_zone /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {

                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
                super.onDrawerClosed(view);

            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        drawerLayout.addDrawerListener(mActionBarDrawerToggle);
        //ties together the the proper interactions
        // between the sliding drawer and the action bar app icon







        View header = getLayoutInflater().inflate(R.layout.activity_login, null);
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });

//        View toolbarView = findViewById(R.id.toolbar);


        getCategories();
        //makeSearchButton(toolbarView.findViewById(R.id.globalSearch));


    }


    private void getCategories() {


        Retrofit retrofit = Api.getclient(getString(R.string.category_api)+ "product/listcategory/");

        ProductService service = retrofit.create(ProductService.class);

        service.getCategories()
                .enqueue(new Callback<ResponseCategory>() {
                    @Override
                    public void onResponse(Call<ResponseCategory> call, Response<ResponseCategory> response) {

                        if(response.body() != null){

                            for(Category category:response.body().getData()){
                                categoryItems.add(category.getCategoryName());
                            }
                            //categoryList.addAll(response.body().getData());
                            Log.d("BaseActivity ",response.body().toString());




                        }

                    }
                    @Override
                    public void onFailure(Call<ResponseCategory> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"Check your connection",Toast.LENGTH_LONG).show();
                        Log.d("HOBOLandingPage",t.getMessage()+" failure");
                    }// happens when api is not able to be connect or getting any response(even a failure response is called a response)
                });




    }


    /*public void makeSearchButton(View view) {
        SearchView searchView = (SearchView) view;

        // Get SearchView autocomplete object.
        final SearchView.SearchAutoComplete searchAutoComplete = (SearchView.SearchAutoComplete)searchView.findViewById(R.id.globalSearch);
        searchAutoComplete.setBackgroundColor(Color.BLUE);
        searchAutoComplete.setTextColor(Color.GREEN);
        searchAutoComplete.setDropDownBackgroundResource(android.R.color.holo_blue_light);

        // Create a new ArrayAdapter and add data to search auto complete object.
        String dataArr[] = {"Apple" , "Amazon" , "Amd", "Microsoft", "Microwave", "MicroNews", "Intel", "Intelligence"};
        ArrayAdapter<String> newsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, dataArr);
        searchAutoComplete.setAdapter(newsAdapter);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        // Listen to search view item on click event.
        searchAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int itemIndex, long id) {
                String queryString=(String)adapterView.getItemAtPosition(itemIndex);
                searchAutoComplete.setText("" + queryString);
                Toast.makeText(getApplicationContext(), "you clicked " + queryString, Toast.LENGTH_LONG).show();
            }
        });

        // Below event is triggered when submit search query.
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                AlertDialog alertDialog = new AlertDialog.Builder(getApplicationContext()).create();
                alertDialog.setMessage("Search keyword is " + query);
                alertDialog.show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }

    public void setMenuItems(){

        Menu menu = navigationView.getMenu();

        for(String category:categoryItems){
            menu.add(category);
        }

    }

        /*navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id){
                    case R.id.nav_home:
                        startActivity(new Intent(getBaseContext(), MainActivity.class));
                        if (isFinish) ((Activity)getBaseContext()).finish();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_settings:
                        startActivity(new Intent(getBaseContext(), SettingsActivity.class));
                        if (isFinish) ((Activity)getBaseContext()).finish();
                        drawerLayout.closeDrawers();
                        break;
                }
                return true;
            }
        });*/



    @Override
    public void setContentView(int layoutResID) {

        fullLayout = (ViewGroup) getWindow().getDecorView();
        frameLayout = (FrameLayout) fullLayout.findViewById(R.id.base_activity_drawer_frame);

        getLayoutInflater().inflate(layoutResID, frameLayout, true);

        //        super.setContentView(fullLayout);

        //Your drawer content...

    }

    @Override
    public void onClick(View view, int position) {
        Intent intent = new Intent(getApplicationContext(),CategoryActivity.class);
        intent.putExtra("CategoryName",categoryItems.get(position));
        startActivity(intent);
    }
}
