# thelearningtriangle
The Learning Triangle is going to be a evolving simulation in an specific environment.
<br />
Maven build-configurations:<br />
  For compiling the project with all dependencies:
 <br />
    Name:           LearningTriangleCleanCompile<br />
    Base directory: ${workspace_loc:/TheLearningTriangle}<br />
    Goals:          clean test compile assembly:single<br />
  <br />
  For running the applications tests:<br />
    Name:           TheLearningTriangle<br />
    Base directory: ${workspace_loc:/TheLearningTriangle}<br />
    Goals:          install       <br />
