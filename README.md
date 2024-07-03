# Music-Recommender
Project Description
This project allows users to create a profile with their favorite music and receive music recommendations based on their favorites. The project includes functionalities for adding favorite music, generating recommendations, and handling exceptions when no recommendations can be made.

File Structure
Main.java: The main entry point of the project, which starts the music recommender menu.
Music.java: Represents a music track with a title, artist, and genre.
MusicFileFormatException.java: Custom exception for handling file format errors (not used in the current implementation).
MusicProfile.java: Manages a user's profile and their favorite music.
MusicRecommender.java: Provides recommendations based on the user's favorite music.
MusicRecommenderMenu.java: Provides a menu interface for interacting with the music recommender.
NoRecommendationException.java: Custom exception for handling cases where no recommendations can be made.
Prerequisites
Java Version: JDK 8 or higher
How to Run
Place all .java files (Main.java, Music.java, MusicFileFormatException.java, MusicProfile.java, MusicRecommender.java, MusicRecommenderMenu.java, NoRecommendationException.java) in the same directory.
Compile the Java files using the following command:
sh
Copy code
javac Main.java Music.java MusicFileFormatException.java MusicProfile.java MusicRecommender.java MusicRecommenderMenu.java NoRecommendationException.java
Run the program using the following command:
sh
Copy code
java Main
Code Overview
Main.java
This file is the entry point of the program. It starts the music recommender menu.

Music.java
This file represents a music track. Its main attributes include:

title: The title of the music track.
artist: The artist of the music track.
genre: The genre of the music track.
MusicFileFormatException.java
This file defines a custom exception for handling file format errors (not used in the current implementation).

MusicProfile.java
This file manages a user's profile and their favorite music. Its main functions include:

addFavoriteMusic(Music music): Adds a music track to the user's favorites.
getFavoriteMusic(): Returns the list of the user's favorite music.
MusicRecommender.java
This file provides recommendations based on the user's favorite music. Its main function includes:

recommendMusic(MusicProfile profile): Recommends a music track from the user's favorites.
MusicRecommenderMenu.java
This file provides a menu interface for interacting with the music recommender. Its main functions include:

Displaying the menu and handling user input.
Adding favorite music to the user's profile.
Generating and displaying music recommendations.
NoRecommendationException.java
This file defines a custom exception for handling cases where no recommendations can be made.

Sample Output
