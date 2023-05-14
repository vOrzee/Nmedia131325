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

        binding.content.setText(intent?.getStringExtra(Intent.EXTRA_TEXT))
        binding.ok.setOnClickListener {
            val text = binding.content.text.toString()

                if (text.isBlank()) {
                    setResult(Activity.RESULT_CANCELED)
                } else {
                    if (Intent.ACTION_EDIT == "1") {}
                    else {
                        setResult(
                            Activity.RESULT_OK,
                            Intent().apply { putExtra(Intent.EXTRA_TEXT, text) })
                    }
                }
                finish()
        }

    }

    object ContractAdd  :ActivityResultContract<String, String?>(){
        override fun createIntent(context: Context, input: String): Intent = Intent(context, NewPostActivity::class.java).putExtra(Intent.EXTRA_TEXT,input)
        override fun parseResult(resultCode: Int, intent: Intent?) = intent?.getStringExtra(Intent.EXTRA_TEXT)
    }

    object ContractEdit  :ActivityResultContract<String, String?>(){
        override fun createIntent(context: Context, input: String): Intent = Intent(context, NewPostActivity::class.java).putExtra((Intent.EXTRA_TEXT),input)
        override fun parseResult(resultCode: Int, intent: Intent?) = intent?.getStringExtra(Intent.EXTRA_TEXT)
    }
}

