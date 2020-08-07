package android.example.exercise3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class NewsListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)

        val recycler = findViewById<RecyclerView>(R.id.recycler)// ищем или биндим ресайклер
        recycler.adapter = RecyclerAdapter()//даем ему адаптер

        //recycler.layoutManager = LinearLayoutManager(this)
    }
}
