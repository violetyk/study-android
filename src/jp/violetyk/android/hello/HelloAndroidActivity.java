package jp.violetyk.android.hello;


import android.app.ListActivity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

// ListActivityクラスを継承してOnItemClickListenerを実装
// public class HelloAndroidActivity extends Activity {
public class HelloAndroidActivity extends ListActivity implements OnItemClickListener {
	
	private final String TAG = "HELLOANDROID";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//      setContentView(R.layout.main);
        
        // 配列にstring.xmlの内容を格納
        String[] activities = getResources().getStringArray(R.array.activities);
        
        // アダプタの生成。コンテキスト、ビューをインスタンス化する時に使うレイアウトファイルのリソースID。
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.listitem, activities);
        
        // アダプタをリストビューのインスタンスに登録
        setListAdapter(adapter);
        
        // ListActivityが生成するListViewのインスタンスを取得。
        ListView lv = getListView();
        lv.setTextFilterEnabled(true);	//リストビューを表示した状態で任意のキーを押すと、この文字を先頭に持つアイテムを抽出できるようになる。
        
        //イベントリスナーを登録
        lv.setOnItemClickListener(this);
        

    }

	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		
		// パラメータvの値をTextView型にキャスト。
		TextView item = (TextView) v;
		Toast.makeText(this, item.getText(), Toast.LENGTH_SHORT).show();
		
	}
	
    // 端末の[menu]ボタンを押されたときに呼び出されるメソッド。
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.global, menu);
		return true;
	}	
	
	
	// オプションメニューのアイテムが選択されたときに呼び出されるメソッド。
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch(item.getItemId()) {
			case R.id.menu1:
				Toast.makeText(this, "menu1", Toast.LENGTH_SHORT).show();
				return true;
				
			case R.id.menu2:
				// メーラー起動インテントの作成
				Intent mailIntent = new Intent(Intent.ACTION_SEND);
				mailIntent.putExtra(Intent.EXTRA_SUBJECT, "テストの件");
				mailIntent.putExtra(Intent.EXTRA_TEXT, "テストです。");
				mailIntent.setType("message/rfc822");
				
				
				try {
					// システムにインストールされているメーラーを立ち上げる。
					
					startActivity(mailIntent);
					
				} catch (ActivityNotFoundException e) {
					// インテントの設定ミス？
					Log.e(TAG, e.getMessage());
					Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
				}
				
				return true;
			
			case R.id.menu3:
				Toast.makeText(this, "menu3", Toast.LENGTH_SHORT).show();
				return true;
		
			default:
				return super.onOptionsItemSelected(item);
		}
	}
}