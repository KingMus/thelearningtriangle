Feature: Create World 
	In order to create the World
As a WorldCreator
I want to use a seed to place every element of the world on it's position

Scenario: Use a seed 
	Given the world shall be created 
	When the seed is set 
	Then use this seed instead of a randomized one 
	
Scenario: Create a seed 
	Given the world shall be created 
	When the seed is not set 
	Then create a new randomized seed after the preconditions 
	
Scenario: Add the fields 
	Given the seed is available 
	Then add the fields through the informations from the seed