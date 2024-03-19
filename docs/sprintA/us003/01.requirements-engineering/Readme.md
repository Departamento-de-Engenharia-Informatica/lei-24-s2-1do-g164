# US003 - Register a collaborator


## 1. Requirements Engineering

### 1.1. User Story Description
As  HRM, I intend to register a collaborator with profession and fundamental attributes.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

>	Each collaborator has a job and a set of skills that enable him to take on certain tasks.

>	As long as it is not published, access to the task is exclusive to the employees of the respective organization. 

**From the client clarifications:**

> **Question:** What are the fundamental attributes of a collaborator?
>
> **Answer:** Name, birthday, admission date, address, cellphone number, e-mail and ID number

> **Question:** Does the HRM select the job from a list that we display?
>
> **Answer:** Displaying or not, It's a matter of UX, the dev team should decide about it, but the valid jobs are the ones created within the US02.

### 1.3. Acceptance Criteria

* **AC1:** All required fields must be filled in.
* **AC2:** The cellphone number must have 9 digits.
* **AC3:** The admission date must be typed using the format _dd-mm-yyyy_
* **AC4:** Collaborators can't have the same ID number

### 1.4. Found out Dependencies

* There is a dependency on "US002 - Register a job" as there must be at least a job for a collaborator to be registered.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * name
    * birthday 
    * admission date
    * address
    * cellphone number
    * e-mail
    * ID document type
    * ID number
	
* Selected data:
    * a job

**Output Data:**

* List of existing jobs
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)
 
###

![System Sequence Diagram - Alternative One](svg/us003-system-sequence-diagram-alternative-one.svg)


### 1.7 Other Relevant Remarks

* 