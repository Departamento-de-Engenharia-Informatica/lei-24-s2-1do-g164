# US007 -  Register Vehicle's Check-Up.


## 1. Requirements Engineering

### 1.1. User Story Description

As a VPM, I intend to register a vehicle for inspection.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

>	Each vehicle is characterized by having a unique vehicle ID.

>	Each vehicle has a check-up which needs the vehicle ID, the date and the current kms.

**From the client clarifications:**


> **Question:**
What information is needed when registering a vehicle for check up?
>
> **Answer:**
Vehicle ID,
Date,
current kms

> **Question:**
What is the unit of measurement used to estimate the check-up frequency (Kms, months, etc.)?
>
> **Answer:**
In real context all could be considered, in the scope of this project just kms will be considered.

> **Question:**
What are the validation requirements for the vehicle ID?
>
> **Answer:**
After 2020: AA-00-AA
between 2005-2020 00-AA-00
between 1992-2005 00-00-XX


> **Question:**
Can a vehicle have more than one check-up?
>
> **Answer:**
yes





### 1.3. Acceptance Criteria

* **AC1:** All required fields must be filled in.
* **AC2:** The task reference must have at least 5 alphanumeric characters.
* **AC3:** When creating a task with an existing reference, the system must reject such operation and the user must be able to modify the typed reference.

### 1.4. Found out Dependencies

* There is a dependency on "US003 - Create a task category" as there must be at least one task category to classify the task being created.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * a reference
    * a designation 
    * an informal description
    * a technical description
    * an estimated duration
    * an estimated cost
	
* Selected data:
    * a task category 

**Output Data:**

* List of existing task categories
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

**_Other alternatives might exist._**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us006-system-sequence-diagram-alternative-one.svg)

#### Alternative Two

![System Sequence Diagram - Alternative Two](svg/us006-system-sequence-diagram-alternative-two.svg)

### 1.7 Other Relevant Remarks

* The created task stays in a "not published" state in order to distinguish from "published" tasks.