# US003 - Register a collaborator

## 3. Design - User Story Realization

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID                                                                                                                      | Question: Which class is responsible for...             | Answer                         | Justification (with patterns)                                                                                                       |
|:------------------------------------------------------------------------------------------------------------------------------------|:--------------------------------------------------------|:-------------------------------|:------------------------------------------------------------------------------------------------------------------------------------|
| Step 1 (Asks to register a new collaborator.)  		                                                                                   | 	... interacting with the actor?                        | RegisterCollaboratorUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.                       |
| 			  		                                                                                                                             | 	... coordinating the US?                               | RegisterCollaboratorController | Controller                                                                                                                          |
| Step 2 (Shows jobs list and asks to select one) 		                                                                                  | 	...getting the jobs list from the JobRepository?						 | RegisterCollaboratorController | Controller                                                                                                                          |
| 		                                                                                                                                  | 	...displaying the list and form for input data?						  | RegisterCollaboratorUI         | IE: is responsible for user interaction.                                                                                            |
| Step 3 (Selects job) 		                                                                                                             | 	...accepting input?                                    | RegisterCollaboratorUI         | IE: is responsible for user interaction                       |
| Step 4 (Shows ID Documents type list and asks to select one)                                                                        | ...getting the types from the DocumentTypeRepository    | RegisterCollaboratorController | Controller                                                                                                                          |
| 		                                                                                                                                  | 	...displaying the list and form for input data?						  | RegisterCollaboratorUI         | IE: is responsible for user interaction.                                                                                            |
| Step 5 (Selects document type) 		                                                                                                   | 	...accepting input?                                    | RegisterCollaboratorUI         | IE: is responsible for user interaction                       |
| Step 6 (requests data (id document's number, name, birthday, admission date, address, cellphone number, e-mail, taxpayer number))		 | 	...displaying the form for input data?						           | RegisterSkillUI                | IE: is responsible for user interaction.                                                                                            |
| Step 7 (Types data) 		                                                                                                              | 	...validating data locally?                            | RegisterCollaboratorUI         | IE: Knows the inputted data                                                                                                         |
| 		                                                                                                                                  | 	...instanciating a new collaborator?                   | CollaboratorRepository         | Pure Fabrication: the CollaboratorRepository is the only class that follows the rules to be a creator class (contains Collaborator) |
|                                                                                                                                     | ...saving inputed data?                                 | Collaborator                   | IE: the created object has its own data.                                                                                            |
|                                                                                                                                     | ...validate the data globally?                          | CollaboratorRepository         | IE: knows all the collaborators                                                                                                     |
|                                                                                                                                     | ...registering the collaborator?                        | CollaboratorRepository         | IE: contains all the registered collaborators                                                                                       |
| Step 8 (Displays status of operation)		                                                                                             | 	...informing operation success?             | RegisterCollaboratorUI         | IE: Responsible for user interaction.                                                                                |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:

* Collaborator
* Job

Other software classes (i.e. Pure Fabrication) identified:

* RegisterCollaboratorUI
* RegisterCollaboratorController
* CollaboratorRepository
* JobRepository
* DocumentTypeRepository


## 3.2. Sequence Diagram (SD)

### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Sequence Diagram - Full](svg/us003-sequence-diagram.svg)

## 3.3. Class Diagram (CD)

![Class Diagram](svg/us003-class-diagram.svg)