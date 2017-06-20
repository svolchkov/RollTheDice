package com.example.rollthedice;

//import com.example.helloworldapp.R;

//import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	String rollHistoryText = "";
	TextView rollHistory;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		rollHistory = (TextView) findViewById(R.id.rollHistory);
		rollHistoryText = rollHistory.getText().toString();
	}
	
	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	public void rollDice(View v) {
        EditText editText = (EditText) findViewById(R.id.user_input);
        String message = editText.getText().toString();
        int user_number = 0;
        boolean correctInput = true;
        try {  
        	user_number = Integer.parseInt(message); 
        	if (user_number < 2 || user_number > 12) correctInput = false;
        } catch (NumberFormatException e) {  
        	correctInput = false;
        }
        if (!correctInput){
     		Toast.makeText(v.getContext(),"Invalid input - must be a number 2 to 12",Toast.LENGTH_LONG).show();
            editText.setText("");
     		return;
        }
        // set dice one
        int randomDiceOne = 0;
        randomDiceOne = (int) ((Math.random() * 6) + 1);
        String dicePicName = "dice" + Integer.toString(randomDiceOne);
        ImageView diceOne = (ImageView) findViewById(R.id.diceOne);
        int resId = MainActivity.this.getResources().getIdentifier(dicePicName, "drawable", MainActivity.this.getPackageName());
        diceOne.setImageResource(resId);
        // set dice two
        int randomDiceTwo = 0;
        randomDiceTwo = (int) ((Math.random() * 6) + 1);
        dicePicName = "dice" + Integer.toString(randomDiceTwo);
        ImageView diceTwo = (ImageView) findViewById(R.id.diceTwo);
        int resIdTwo = MainActivity.this.getResources().getIdentifier(dicePicName, "drawable", MainActivity.this.getPackageName());
        diceTwo.setImageResource(resIdTwo);
        
        int rollTotal = randomDiceOne + randomDiceTwo;
        TextView outcome = (TextView) findViewById(R.id.outcome);
        
        
        if (user_number == rollTotal){
        	outcome.setText(getString(R.string.winString));
        	outcome.setTextColor(ContextCompat.getColor(this,R.color.red));
        	rollHistoryText += "<font color=red>" + Integer.toString(rollTotal) + "</font>" + " ";
        }else{
        	outcome.setText(getString(R.string.loseString)); 
        	outcome.setTextColor(ContextCompat.getColor(this,R.color.black));
        	rollHistoryText += Integer.toString(rollTotal) + " ";
        }
        rollHistory.setText(Html.fromHtml(rollHistoryText));
        editText.setText("");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
