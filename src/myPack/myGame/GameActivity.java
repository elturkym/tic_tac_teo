
package myPack.myGame;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class GameActivity extends Activity
{

	private LinearLayout l;
	private myView vi;
	private int col1;
	private int col2;
	private int col3;
	private int col4;
	

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		l = new LinearLayout(this);
		l.setVerticalFadingEdgeEnabled(true);
		vi = new myView(this);
		l.addView(vi, 0);
		setContentView(l);
		col1 = 0;
		col2 = 10;
		col3 = 8;
		col4 = 5;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		super.onOptionsItemSelected(item);
		switch (item.getItemId())
		{
			case R.string.newGame:
				vi.reINT();
				break;
			case R.string.clear:
				vi.clearScore();
				break;
			case R.string.help:
				setContentView(R.layout.help);
				final Button button1 = (Button) findViewById(R.id.button1);
				button1.setOnClickListener(new View.OnClickListener()
				{
					public void onClick(View v)
					{
						setContentView(l);
					}
				});
				break;
			case R.string.option:
				setContentView(R.layout.options);
				Spinner spinner = (Spinner) findViewById(R.id.spinner1);
				spinner.setSelection(col1);
				spinner = (Spinner) findViewById(R.id.spinner3);
				spinner.setSelection(col2);
				spinner = (Spinner) findViewById(R.id.spinner4);
				spinner.setSelection(col3);
				spinner = (Spinner) findViewById(R.id.spinner2);
				spinner.setSelection(col4);
				final Button button2 = (Button) findViewById(R.id.button3);
				button2.setOnClickListener(new View.OnClickListener()
				{
					public void onClick(View v)
					{
						setContentView(l);
					}
				});
				final Button button3 = (Button) findViewById(R.id.button2);
				button3.setOnClickListener(new View.OnClickListener()
				{
					public void onClick(View v)
					{
						Spinner spinner = (Spinner) findViewById(R.id.spinner1);
						col1 = spinner.getSelectedItemPosition();
						vi.colorback = getColor(col1);
						spinner = (Spinner) findViewById(R.id.spinner3);
						col2 = spinner.getSelectedItemPosition();
						vi.colorlines = getColor(col2);
						spinner = (Spinner) findViewById(R.id.spinner4);
						col3 = spinner.getSelectedItemPosition();
						vi.colorofY = getColor(col3);
						spinner = (Spinner) findViewById(R.id.spinner2);
						col4 = spinner.getSelectedItemPosition();
						vi.colorofX = getColor(col4);
						setContentView(l);
						vi.invalidate();
					}
				});
				break;
		}
		return true;
	}
	
	private int getColor(int i)
	{
		int res = 0;
		switch (i)
		{
			case 0:
				res = android.graphics.Color.BLACK;
				break;
			case 1:
				res = android.graphics.Color.BLUE;
				break;
			case 2:
				res = android.graphics.Color.CYAN;
				break;
			case 3:
				res = android.graphics.Color.DKGRAY;
				break;
			case 4:
				res = android.graphics.Color.GRAY;
				break;
			case 5:
				res = android.graphics.Color.GREEN;
				break;
			case 6:
				res = android.graphics.Color.LTGRAY;
				break;
			case 7:
				res = android.graphics.Color.MAGENTA;
				break;
			case 8:
				res = android.graphics.Color.RED;
				break;
			case 9:
				res = android.graphics.Color.TRANSPARENT;
				break;
			case 10:
				res = android.graphics.Color.WHITE;
				break;
			case 11:
				res = android.graphics.Color.YELLOW;
				break;
		}
		return res;
	}
}
