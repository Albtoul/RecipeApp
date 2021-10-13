package com.example.recipeapp

class RecipeDetails {

    var data: List<RecipeDetailsItem>? = null


    class RecipeDetailsItem {

        var id: Int? = null

        var title: String? = null

        var author: String? = null

        constructor(title: String?, author: String?, ingredients: String?, instructions: String?) {
            this.title = title
            this.author = author
            this.ingredients = ingredients
            this.instructions = instructions
        }

        var ingredients: String? = null
        var instructions: String? = null
    }
}