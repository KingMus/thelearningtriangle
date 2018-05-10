# The Learning Triangle
The Learning Triangle uses artificial intelligence to create an evolving simulation.
Triangles live inside an overworld and try to survive as long as possible. They are controlled through an algorithm.
This main idea will be developed into an educational game for the topic "AI". That means there are two different code bases, one for the simulation of life and one for the educational game. You can find them both in this repository.
<br />
Visit the [project blog](https://thelearningtriangle.blogspot.de/) for more information, but be aware: It isn't complete, it is only the homework for one lecture. 
<br />
<br />
![logo](https://github.com/KingMus/thelearningtriangle/blob/master/TheLearningTriangle/Images/Logos/TLT_Logo_Full.png)
<br />
<br />
old Maven build-configurations, they don't work for sure: <br />

| For compiling the project with all dependencies: | |
| ------: | :------ |
| Name | LearningTriangleCleanCompile |
| Base directory | ${workspace_loc:/TheLearningTriangle} |
| Goals | clean test compile assembly:single |

| For running the applications tests: | |
| ------: | :------ |
| Name | TheLearningTriangle |
| Base directory | ${workspace_loc:/TheLearningTriangle} |
| Goals | install |
