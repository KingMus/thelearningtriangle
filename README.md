# The Learning Triangle

The Learning Triangle is a project that was developed during the Study of Applied Computer Science. It's mainly about artificial intelligence.

![logo](https://github.com/KingMus/thelearningtriangle/blob/master/TheLearningTriangle/Images/Logos/TLT_Logo_Full.png)

<hr>

The Learning Triangle uses artificial intelligence to create an evolving simulation.
Triangles live inside an overworld and try to survive as long as possible. They are controlled through an algorithm.
Afterwards, this main idea was developed into an educational game for the topic "AI".
<br>
--> That means there are two different code bases, one for the simulation of life and one for the educational game. You can find them both in this repository (software-engineering folder and unity folder).

## The two functions of TLT

### "Software-Engineering"-TLT (the Simulation)

While developing the simulation for software engineering class, we had to create a lot of important documents. They are stored in an own repository ([HERE](https://github.com/KingMus/thelearningtriangle-studienarbeit/tree/master/Software-Engineering)). Also we had to write a blog. Visit this [blog](https://thelearningtriangle.blogspot.de/) for more information about the documents we created.

### "Seminar-Paper"-TLT (the Edcuational Game)

After the software-engineering class, all students have to work on a seminar paper. The decision was to use the existing idea and codebase of TLT to create an educational game for Artificial Intelligence. This was realised in Unity. Also we had to wrote a documentation. Check it out ([HERE](https://github.com/KingMus/thelearningtriangle-studienarbeit)). 

<hr>

## Other stuff

### Map Builder for TLT

During the whole development of TLT the creation of maps was an important task. For this reason a map builder was created, without another use than building overworlds in an easy way. You can find it ([HERE](https://github.com/KingMus/thelearningtriangle-mapbuilder)), it has no relation to a given task.

### Maven

old Maven build-configurations (for software-engineering part of TLT), they don't work for sure: <br />

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
