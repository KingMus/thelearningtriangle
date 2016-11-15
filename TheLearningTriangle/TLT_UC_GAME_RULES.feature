Feature: Game rules 
	In order to set the game rules
As a triangle
I want to define the events for the different types of fields in the overworld

Scenario: Normal Field 
	Given I want to move in any direction 
	When I would move on a normal field 
	Then I move forward 
	
Scenario: Poison Field 
	Given I want to move in any direction 
	When I would move on a poison field 
	Then I move forward 
	And my energy consumption becomes higher 
	
Scenario: Energy Field 
	Given I want to move in any direction 
	When I would move on an energy field 
	Then I move forward 
	And my energy becomes higher 
	
Scenario: Wall Field 
	Given I want to move in any direction 
	When I would move on a poison field 
	Then I don't move forward 
	
Scenario: Death Field 
	Given I want to move in any direction 
	When I would move on a death field 
	Then I don't move forward 
	And I die