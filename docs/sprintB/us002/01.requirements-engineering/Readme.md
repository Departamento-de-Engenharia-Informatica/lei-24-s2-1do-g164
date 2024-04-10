# US002 - Register a job

## 1. Requirements Engineering

### 1.1. User Story Description
As an HRM, I want to register a job that a collaborator needs to have.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**  

>A job is a main occupation that must be attributed to a collaborator.
> 

**From the client clarifications:**

> **Question:** What data defines a job?
>
> **Answer:** A job is only defined by its name.

> **Question:** What should be shown as output?
>
> **Answer:** The UX/UI is decided by development team.

> **Question:** Can a job have duplicates?
>
> **Answer:** It can't.

> **Question:** Can special characters and numbers be entered when registering a job?
>
> **Answer:** No.

### 1.3. Acceptance Criteria

* **AC1:** The "Name" field must be filled.
* **AC2:** A job's name cannot have special characters and numbers.
* **AC3:** A job's name must be unique.

### 1.4. Found out Dependencies

* None. However, us003 will have a dependency on this user story, as there will need to be jobs to register a collaborator

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * name of the job

**Output Data:**

* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)


![System Sequence Diagram](svg/us002-system-sequence-diagram-alternative-one.svg)

### 1.7 Other Relevant Remarks

* None
