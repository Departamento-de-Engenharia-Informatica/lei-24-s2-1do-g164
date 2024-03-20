# US005 - Create a Task 


## 1. Requirements Engineering

### 1.1. User Story Description

As HRM, I intend to automatically generate a team proposal.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

>	A team is characterized by having a specific number of collaborators, defined by the HRM.
  
>	There are specific skills that serve as criteria for selecting suitable team members.

**From the client clarifications:**

> **Question:** What are the input data to automatically generate a team?
>
> **Answer:** The maximum size of the team (for instance 4)
and the skill needed: 4 tree pruner and 1 light vehicle driver
meaning that one team member have 2 skills.

> **Question:** What should the output of the automation be? (should it just store the team proposal or show it to the customer?)  Will the team proposal be a document about all the instructions of each team member/worker?
>
> **Answer:** The systems provide team proposals and HRM can accept of refuse the proposals.

> **Question:** How does it generate the team if there are not enough employees?
>
> **Answer:** The system should provide information why it can't generate a team.

> **Question:**  Does the HRM need to specify the task first for the team to be generated?
> 
> **Answer:** no

> **Question:**  Should the generated team be assigned the task defined/default into the to-do list?
>
> **Answer:** no


### 1.3. Acceptance Criteria

* **AC1:** The size of the team must not exceed the value defined by the HRM.
* **AC2:** The team members must have the skills provided by the HRM.
* **AC3:** If there are not enough employees the system should provide information why it can´t generate a team. 
### 1.4. Found out Dependencies

* There is a dependency on "US003 - Register a collaborator" as there must be collaborators to create a team.
* There is also a dependency on "US004 - Assign one or more skills" since a collaborator can have more than one skill.
### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * number of collaborators
	
* Selected data:
    * skills of collaborators

**Output Data:**

* team proposal
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

**_Other alternatives might exist._**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us005-system-sequence-diagram.svg)



### 1.7 Other Relevant Remarks

* The created task stays in a "not published" state in order to distinguish from "published" tasks.