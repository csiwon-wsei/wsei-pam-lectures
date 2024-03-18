package pl.wsei.pam.lectures.lecture3.recycler

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pl.wsei.pam.lectures.R

class L3RecyclerViewActivity : AppCompatActivity() {
    val mMovies = listOf(
        Movie("Diuna", 9.5f),
        Movie("Barbie", 7.5f),
        Movie("Napoleon", 4.5f),
        Movie("Tenet", 4.8f),
        Movie("Diuna", 9.5f),
        Movie("Barbie", 7.5f),
        Movie("Napoleon", 4.5f),
        Movie("Tenet", 4.8f),
        Movie("Diuna", 9.5f),
        Movie("Barbie", 7.5f),
        Movie("Napoleon", 4.5f),
        Movie("Tenet", 4.8f),
        Movie("Diuna", 9.5f),
        Movie("Barbie", 7.5f),
        Movie("Napoleon", 4.5f),
        Movie("Tenet", 4.8f),
        Movie("Diuna", 9.5f),
        Movie("Barbie", 7.5f),
        Movie("Napoleon", 4.5f),
        Movie("Tenet", 4.8f),
        Movie("Diuna", 9.5f),
        Movie("Barbie", 7.5f),
        Movie("Napoleon", 4.5f),
        Movie("Tenet", 4.8f),
        Movie("Diuna", 9.5f),
        Movie("Barbie", 7.5f),
        Movie("Napoleon", 4.5f),
        Movie("Tenet", 4.8f)

    )
    lateinit var mRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_l3_recycler_view)
        mRecyclerView = findViewById(R.id.l3RecyclerView)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = MovieRecyclerViewAdapter(mMovies)
        mRecyclerView.adapter = adapter
        mRecyclerView.setOnClickListener { v -> Toast.makeText(this,"Cliked", Toast.LENGTH_SHORT).show()}

    }
}