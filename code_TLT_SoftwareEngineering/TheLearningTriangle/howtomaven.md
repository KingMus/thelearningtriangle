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
