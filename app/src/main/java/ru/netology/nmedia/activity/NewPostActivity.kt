package ru.netology.nmedia.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ActivityNewPostBinding

class NewPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =ActivityNewPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (editFlag == 1) {
//            val Context=findViewById<EditText>(R.id.content)
//            val context=Context.text.toString()
//            val intent1=Intent(this,ActivityNewPostBinding::class.java).also {
//                it.putExtra("Context",context)
//                startActivity(it);
//            }
            binding.content.setText("Редактируемое значение")
//            intent. { putExtra(Intent.EXTRA_TEXT, text)}
        }

        binding.ok.setOnClickListener {
            val text = binding.content.text.toString()
            if (editFlag == 0){
                if (text.isBlank()) {

                    setResult(Activity.RESULT_CANCELED)
                } else {
                    setResult(
                        Activity.RESULT_OK,
                        Intent().apply { putExtra(Intent.EXTRA_TEXT, text) })
                }
            } else {
                if (text.isBlank()) {
                    setResult(Activity.RESULT_CANCELED)
                } else {
                    setResult(
                        Activity.RESULT_OK,
                        Intent().apply { putExtra(Intent.EXTRA_TEXT, text) })
                }
            }
            finish()
        }

    }

    object ContractAdd  :ActivityResultContract<Unit, String?>(){
        override fun createIntent(context: Context, input: Unit): Intent = Intent(context, NewPostActivity::class.java)
        override fun parseResult(resultCode: Int, intent: Intent?) = intent?.getStringExtra(Intent.EXTRA_TEXT)
    }

    object ContractEdit  :ActivityResultContract<Unit, String?>(){
        override fun createIntent(context: Context, input: Unit): Intent = Intent(context, NewPostActivity::class.java)
        override fun parseResult(resultCode: Int, intent: Intent?) = intent?.getStringExtra(Intent.EXTRA_TEXT)
    }
}

