package com.nestifff.rxjava_retrofit_flatmap_examplecodingwithmitch

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nestifff.rxjava_retrofit_flatmap_examplecodingwithmitch.models.Post
import com.nestifff.rxjava_retrofit_flatmap_examplecodingwithmitch.requests.ServiceGenerator
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    private lateinit var adapter: RecyclerAdapter
    private val disposables: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        initRecyclerView()

        getPostObservable()
            .subscribeOn(Schedulers.io())
            .flatMap {
                getCommentsObservable(it)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Post> {

                override fun onSubscribe(d: Disposable) {
                    disposables.add(d)
                    Log.i(TAG, "onSubscribe: ")
                }

                override fun onNext(t: Post) {
                    adapter.updatePost(t)
                    Log.i(TAG, "onNext: $t")
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG, "onError: ", e)
                }

                override fun onComplete() {

                }

            })
    }

    private fun getCommentsObservable(post: Post): Observable<Post> {

        return ServiceGenerator.getRequestsApi()
            .getComments(post.id!!)
            .subscribeOn(Schedulers.io())

            .map {

                val delay: Long = Random.nextInt(10) * 1000L
                Thread.sleep(delay)
                Log.i(
                    TAG, "this thread: ${Thread.currentThread().name} " +
                            "sleep for $delay ms"
                )
                post.comments = it
                post
            }
            .subscribeOn(Schedulers.io())

    }

    private fun getPostObservable(): Observable<Post> {

        return ServiceGenerator.getRequestsApi().getPosts()

            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

            .flatMap {
                adapter.setPosts(it as MutableList<Post>)
                return@flatMap Observable
                    .fromIterable(it)
                    .subscribeOn(Schedulers.io())
            }
    }


    private fun initRecyclerView() {
        adapter = RecyclerAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}

const val TAG = "rxjava_retrofit_flatmap_examplecodingwithmitch"