package com.example.codingo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.TimePicker
import org.w3c.dom.Text

class activity_tips : AppCompatActivity() {
    private lateinit var next: Button
    private lateinit var homeButton: Button
    private lateinit var lessonContent:TextView
    private lateinit var lessonIntro:TextView
    private lateinit var lessonPic1: ImageView
    private lateinit var tip1: TextView
    private lateinit var tip2: TextView
    private lateinit var lessonPic2:ImageView
    private lateinit var tip3: TextView
    private lateinit var lessonPic3:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tips)
        //Views
        lessonContent = findViewById(R.id.lessonContent)
        lessonIntro = findViewById(R.id.intro)
        lessonPic1 = findViewById(R.id.image1)
        tip1 = findViewById(R.id.tip1)
        tip2 = findViewById(R.id.tip2)
        lessonPic2 = findViewById(R.id.image2)
        tip3 = findViewById(R.id.tip3)
        lessonPic3 = findViewById(R.id.image3)
        homeButton = findViewById(R.id.homeButton)
        //Intent Vars
        val prevIntent:Intent = getIntent()
        val lessonNumber = prevIntent.getIntExtra("lessonNumber", 0)
        val lessonName = prevIntent.getStringExtra("lessonName")
        lessonContent.text = lessonName

        fun loadTips (lessonNum:Int) {
            if (lessonNum == 1) {
                lessonIntro.text = resources.getString(R.string.tip_1)
                lessonPic1.setImageResource(R.drawable.lesson1_pic_1)
                tip1.text = resources.getString(R.string.tip_3)
                tip2.text = resources.getString(R.string.tip_5)
                lessonPic2.setImageResource(R.drawable.lesson1_pic_2)
                tip3.text = resources.getString(R.string.input2)
                lessonPic3.setImageResource(R.drawable.lesson1_pic_3)
            }
            else if (lessonNum == 2) {
                lessonIntro.text = resources.getString(R.string.if_statements)
                tip1.text = resources.getString(R.string.common_operators)
                lessonPic1.setImageResource(R.drawable.lesson2_pic_1)
                lessonPic2.setImageResource(R.drawable.lesson2_pic_1)

            }

            else if (lessonNum == 3) {
                lessonIntro.text = resources.getString((R.string.loops))
                tip1.text = resources.getString((R.string.while_loops))
                lessonPic1.setImageResource(R.drawable.while_loop)
                tip2.text = resources.getString((R.string.for_loops))
                lessonPic2.setImageResource(R.drawable.for_loop)
                tip3.text = resources.getString((R.string.char_loop))
                lessonPic3.setImageResource(R.drawable.char_loop)
            }

            else if (lessonNum == 5) {
                lessonIntro.text = resources.getString((R.string.tip1))
                lessonPic1.setImageResource(R.drawable.lesson5_pic_2_tip3)
                tip2.text = resources.getString(R.string.tip2)
                lessonPic2.setImageResource(R.drawable.lesson5_pic_4_tip5)
                tip3.text = resources.getString(R.string.tip3)
                lessonPic3.setImageResource(R.drawable.lesson5_pic_5_tip6)

            }
        }

        loadTips(lessonNumber)


        next = findViewById(R.id.next)
        next.setOnClickListener{
            val intent = Intent(this, MultipleChoice::class.java)
            intent.putExtra("lessonNumber", lessonNumber)
            intent.putExtra("lessonName", lessonName)
            startActivity(intent)
            finish()
        }

        homeButton.setOnClickListener {
            val intent2 = Intent(this, Welcome::class.java)
            startActivity(intent2)
            finish()
        }
    }
}