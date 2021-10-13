package com.example.recipeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.ProgressDialog
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity2 : AppCompatActivity() {

    private var recipeDetails: List<RecipeDetails.RecipeDetailsItem>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val responseText = findViewById<View>(R.id.textView2) as TextView

        getRecipes(onResult = {
            recipeDetails = it
            Log.e("Data", recipeDetails.toString())

            var stringToBePritined:String? = "";
            for(recipe in recipeDetails!!){
                stringToBePritined = stringToBePritined +recipe.title + "\n"+recipe.author + "\n"+recipe.ingredients + "\n"+recipe.instructions+ "\n\n"
            }
            responseText.text= stringToBePritined
        } );
    }

    private fun getRecipes(onResult: (List<RecipeDetails.RecipeDetailsItem>?) -> Unit) {
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        val progressDialog = ProgressDialog(this@MainActivity2)
        progressDialog.setMessage("Please wait")
        progressDialog.show()
        if (apiInterface != null) {
            apiInterface.getRecipies()?.enqueue(object : Callback<List<RecipeDetails.RecipeDetailsItem>> {
                override fun onResponse(
                    call: Call<List<RecipeDetails.RecipeDetailsItem>>,
                    response: Response<List<RecipeDetails.RecipeDetailsItem>>
                ) {
                    onResult(response.body())
                    progressDialog.dismiss()

                }

                override fun onFailure(call: Call<List<RecipeDetails.RecipeDetailsItem>>, t: Throwable) {
                    onResult(null)
                    progressDialog.dismiss()
                    Toast.makeText(applicationContext, ""+t.message, Toast.LENGTH_SHORT).show();
                }

            })
        }
    }
}