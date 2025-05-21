package com.example.mohamed_achek

// Object holding the list of countries for the game
object CountryData {
    // Each country has a name, flag resource, capital, and a unique feature
    val countries = listOf(
        Country("Bhutan", R.drawable.bhutan, "Thimphu", "Measures success with Gross National Happiness instead of GDP"),
        Country("Iceland", R.drawable.iceland, "Reykjavik", "Runs almost entirely on renewable geothermal energy"),
        Country("Japan", R.drawable.japan, "Tokyo", "Known for its bullet trains (Shinkansen), which are among the fastest in the world"),
        Country("Brazil", R.drawable.brazil, "Brasília", "Home to the Amazon Rainforest, the largest tropical rainforest on Earth"),
        Country("Egypt", R.drawable.egypt, "Cairo", "Famous for its ancient pyramids and the Sphinx"),
        Country("Canada", R.drawable.canada, "Ottawa", "Has the longest coastline of any country in the world"),
        Country("Australia", R.drawable.australia, "Canberra", "The only country that is also a continent"),
        Country("Switzerland", R.drawable.switzerland, "Bern", "Renowned for its neutrality in global conflicts and high-quality watches"),
        Country("Thailand", R.drawable.thailand, "Bangkok", "Known for its temples"),
        Country("South Korea", R.drawable.korea, "Seoul", "Leader in technology and innovation, especially in electronics"),
        Country("New Zealand", R.drawable.newzealand, "Wellington", "Known for its Maori culture and stunning landscapes"),
        Country("Italy", R.drawable.italy, "Rome", "Birthplace of Renaissance art and architecture"),
        Country("Norway", R.drawable.norway, "Oslo", "Frequently ranked as one of the happiest and most prosperous countries"),
        Country("Kenya", R.drawable.kenya, "Nairobi", "World-famous for its wildlife safaris and the Great Migration"),
        Country("Turkey", R.drawable.turkey, "Ankara", "Straddles two continents: Europe and Asia")
    )
}

