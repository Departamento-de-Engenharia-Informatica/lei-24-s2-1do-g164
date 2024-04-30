# US004 - Assign one or more skills

## 3. Design - User Story Realization 

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID                                                                                                          | Question: Which class is responsible for...                         | Answer                 | Justification (with patterns)                                                                                 |
|:------------------------------------------------------------------------------------------------------------------------|:--------------------------------------------------------------------|:-----------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1 (asks to add skills to a collaborator)  		                                                                       | 	... interacting with the actor?                                    | AssignSkillsUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
| 			  		                                                                                                                 | 	... coordinating the US?                                           | AssignSkillsController | Controller                                                                                                    |
| Step 2 (Shows collaborators list and asks to select one) 		 			  		                                                     | 	...getting the collaborators list?                                 | CollaboratorRepository | IE: has the data                                                                                              |
| 	 		  		                                                                                                                | ...displaying the list and form for input data?		                   | AssignSkillsUI         | Pure Fabrication                                                                                              |
| Step 3 (Selects a collaborator) 		 				  		                                                                             | ...storing the selected data temporarily?  				                     | AssignSkillsUI         | Pure Fabrication                                                                                              |
| Step 4 (Shows skilss list to add to a collaborator) 				  		                                                            | 	...getting the skills list?  						                                | SkillsRepository       | IE: has the data                                                                                              |
|                                                                                                                         | ...displaying the list and form for input data?								             | AssignSkillsUI         | Pure Fabrication                                                                                              |
| Step 5 (chooses skills from list to add) 		                                                                             | ...storing the selected data temporarily?                           | AssignSkillsUI         | Pure Fabrication                                                                                              |
| Step 6 (shows all skills to be appointed and requests confirmation)  		                                                 | 	...displaying the form for input data?	                            | AssignSkillsUI         | Pure Fabrication                                                                                              |
| Step 7 (confirms skills)  		                                                                                            | 	...validating data locally?                                        | AssignSkillsUI         | Pure Fabrication                                                                                              |
|                                                                                   | 	...getting the list of collaborator\'s current skills ?    							 |                        | SkillsRepository       | IE: has the data                                                                    |            
|                                                                          | 	 ...displaying the list and form for input data?	                      | AssignSkillsUI         | PureFabrication                                                                                               | 
| Step 8 (displays operation success)	            			  		                                                                 | 	...informing operation success?                       | AssignSkillsUI         | Pure Fabrication                                                                                              || 			  		                                                                                                                 | 	... saving the created task?                                       | Organization           | IE: owns all its tasks.                                                                                       | 
                   
### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

* Collaborators
* Skills

Other software classes (i.e. Pure Fabrication) identified: 

* AssignSkillsUI  
* AssignSkillsController
* CollaboratorRepository
* SkillsRepository


## 3.2. Sequence Diagram (SD)

_**Note that SSD - Alternative Two is adopted.**_

### Full Diagram
![Sequence Diagram - Full](svg/us004-sequence-diagram-full.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us04-class-diagram.svg)