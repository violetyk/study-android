package jp.violetyk.android.hello;


import android.app.ListActivity;
import android.os.Bundle;
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
}