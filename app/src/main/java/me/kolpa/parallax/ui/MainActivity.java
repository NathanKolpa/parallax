package me.kolpa.parallax.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.SubMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import javax.inject.Inject;

import me.kolpa.parallax.R;
import me.kolpa.parallax.di.MyApplication;
import me.kolpa.parallaxcore.domain.usecases.guild.GetGuildsInteractor;

public class MainActivity extends AppCompatActivity
{
	private AppBarConfiguration mAppBarConfiguration;

	@Inject
	GetGuildsInteractor getGuildsInteractor;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		((MyApplication) getApplicationContext()).appComponent.inject(this);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		DrawerLayout drawer = findViewById(R.id.drawer_layout);
		NavigationView navigationView = findViewById(R.id.nav_view);
		navigationView.setCheckedItem(R.id.nav_home);

		Menu drawerMenu = navigationView.getMenu();
		configureDrawerMenu(drawerMenu);

		mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home, R.id.nav_trending, R.id.nav_all).setOpenableLayout(drawer).build();
		NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
		NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
		NavigationUI.setupWithNavController(navigationView, navController);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onSupportNavigateUp()
	{
		NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
		return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
	}

	private void configureDrawerMenu(Menu drawerMenu)
	{
		SubMenu guildList = drawerMenu.findItem(R.id.nav_item_guilds).getSubMenu();

		guildList.add("test");
		guildList.add("test");
		guildList.add("test");
		guildList.add("test");
		guildList.add("test");
		guildList.add("test");

	}
}