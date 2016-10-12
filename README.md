# thelearningtriangle
The Learning Triangle is going to be a evolving simulation in an specific environment.

Maven build-configurations:
  Name:           LearningTriangleCleanCompile
  Base directory: ${workspace_loc:/TheLearningTriangle}
  Goals:          clean test compile assembly:single
  
  Name:           TheLearningTriangle
  Base directory: ${workspace_loc:/TheLearningTriangle}
  Goals:          install       
