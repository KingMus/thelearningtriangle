Feature: Update View 
	In order to update the visible gameworld
As a view
I want to react on events which influences the UI

Scenario: Randomly Created Overworld 
	Given I started the game 
	When I finished creating a random overworld 
	Then I draw this overworld on my UI 
	
Scenario: Create Triangles 
	Given I created the overworld map 
	When I created my triangles 
	Then I display the triangles on my UI 
	
Scenario: One Triangle Moves 
	Given I display the triangles 
	When one triangle moves 
	Then I redraw the triangle on his new position 
	
Scenario: One Triangle loses or consume energy 
	Given I display the triangles 
	When one triangles energy changes through a field event 
	Then I redraw the energy value of this triangle 
	
Scenario: One triangle dies 
	Given I display the triangles 
	When one triangle dies 
	Then I delete this driangle from my UI 
