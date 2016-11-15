Feature: Calculate Score 
	In order to rate a playthrough of the AI
As a Player
I want to calculate a number named highscore to see immediately if it was good or bad

Scenario: Display the score 
	When I finished the game 
	Then summarize the distance for every single triangle 
	And the score is displayed 
	
Scenario: Add score in highscore list 
	Given the score is calculated 
	When the score is higher than the last entry of the highscore list 
	Then add score to correct position in highscore list