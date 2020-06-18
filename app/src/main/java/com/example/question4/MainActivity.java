package com.example.question4;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PeopleAdapter.PeopleAdapterListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView rvItems;
    private List<String> people;
    private PeopleAdapter adapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("People");

        rvItems = findViewById(R.id.rvItems);

        people = new ArrayList<>();
        people.add("Austeen Joel Joseph Murphy");
        people.add("Albert Einstein");
        people.add("Antonio Jose dos Santos");
        people.add("Belo Campelo");
        people.add("Barbara Lima");
        people.add("Carvalho Leal");
        people.add("Cynthia Araujo");
        people.add("Elizabeth Silva de Cavalcante");
        people.add("Ernest Albertine Josiane Pantova");
        people.add("Fabio Alexandre Loira");
        people.add("Fabiana Barros da Praia");
        people.add("Geovana Costa de Gato");
        people.add("George Weah Mario");
        people.add("Herbert Lewis");
        people.add("Hilary Clinton");


        adapter = new PeopleAdapter(this, people, this);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(getApplicationContext());
        rvItems.setLayoutManager(lm);
        rvItems.setItemAnimator(new DefaultItemAnimator());
        rvItems.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                adapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // close search view on back button pressed
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onSelected(String item) {
        Toast.makeText(this, "Selected: " + item, Toast.LENGTH_LONG).show();
    }
}