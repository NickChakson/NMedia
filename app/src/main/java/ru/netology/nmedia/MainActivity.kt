package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ru.netology.nmedia.databinding.ActivityMainBinding
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            published = "21 мая в 18:36",
            likedByMe = false,
            shareByMe = false
        )
        with(binding) {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            if (post.likedByMe) {
                likeIcon?.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }
            likeText?.text = post.likes.toString()

            root.setOnClickListener {
                Log.d("stuff", "stuff")
            }

            avatar.setOnClickListener {
                Log.d("stuff", "avatar")
            }

            likeIcon?.setOnClickListener {
                Log.d("stuff", "like")
                post.likedByMe = !post.likedByMe
                likeIcon.setImageResource(
                    if (post.likedByMe) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24
                )
                if (post.likedByMe) post.likes++ else post.likes--
                when {
                    post.likes == 1000 -> likeText?.text = "1K"
                    post.likes in (1001..9999) -> {
                        var sameData: Int = post.likes
                        sameData /= 100
                        val result = sameData.toDouble() / 10
                        likeText?.text = result.toString() + "K"
                    }
                    post.likes in (10000..999999) -> {
                        post.likes = post.likes / 1000
                        likeText?.text = post.likes.toString() + "K"
                    }
                    post.likes > 1000000 -> {
                        var sameData: Int = post.likes
                        sameData /= 100000
                        val result = sameData.toDouble() / 10
                        likeText?.text = result.toString() + "M"
                    }
                    else -> likeText?.text = post.likes.toString()
                }

            }


            repostIcon?.setOnClickListener {
                Log.d("stuff", "repost")
                post.shareByMe = !post.shareByMe
                if (post.shareByMe) post.share++ else post.share++
                when {
                    post.share == 1000 -> repost?.text = "1K"
                    post.share in (1000..9999) -> {
                        var sameData: Int = post.share
                        sameData /= 100
                        val result = sameData.toDouble() / 10
                        repost.text = result.toString() + "K"
                    }
                    post.share in (10000..999999) -> {
                        post.share = post.share / 1000
                        repost?.text = post.share.toString() + "K"
                    }
                    post.share > 1000000 -> {
                        var sameData: Int = post.share
                        sameData /= 100000
                        val result = sameData.toDouble() / 10
                        repost?.text = result.toString() + "M"
                    }
                    else -> repost?.text = post.share.toString()

                }
            }
        }

    }


}