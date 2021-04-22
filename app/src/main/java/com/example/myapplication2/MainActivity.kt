package com.example.myapplication2


import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication2.databinding.ActivityMainBinding
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        shareTelegram()
    }

    private fun shareTelegram(){
        val btnShare = findViewById<Button>(R.id.btn_share_telegram)
        btnShare?.setOnClickListener {

            val nomeProduto = binding.editNomeProduto.text.toString()
            val precoProduto = binding.editProductPrice.text.toString()
            val nomeMembro = binding.editMemberName.text.toString()
            val linkAppMatchfood = Uri.parse("https://play.google.com/store/apps/details?id=com.matchfood.app")
            val linkSiteMatchfood = Uri.parse("https://matchfood.com/baixe_agora")

            val text: String = "*$nomeProduto* "+"por apenas"+" *R$$precoProduto?* \n\n" +
                    "Você só encontra na nossa loja *$nomeMembro* "+"no APP"+" *Matchfood* " +
                    "\uD83D\uDE03 \n"+ //emoji
                    "$linkAppMatchfood \n\n" +
                    "Aceitamos várias formas de pagamento! \uD83D\uDCB3 \n\n" + //emoji
                    "Baixe o app Matchfood e faça seu pedido: \n$linkSiteMatchfood"
            if (!text.isEmpty()) {
                startShareTextTelegram(text)
            } else {
                Toast.makeText(applicationContext, "Texto esta vazio", Toast.LENGTH_SHORT).show()
            }

        }

    }

    private fun startShareTextTelegram(text: String?) {
        val sendIntent = Intent()
        try {
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(Intent.EXTRA_TEXT, text)
            sendIntent.type = "text/plain"
            startActivity(sendIntent)

            // Set package only if you do not want to show all the options by which you can share the text.
            // Setting package bypass the system picker and directly share the data on WhatsApp.
            // TODO uncomment code to show whatsapp directly
            sendIntent.setPackage("org.telegram.messenger")

            startActivity(sendIntent)
        }catch (e: Exception){
            Toast.makeText(applicationContext, "Telegram not Installed", Toast.LENGTH_SHORT).show()
        }

    }


}