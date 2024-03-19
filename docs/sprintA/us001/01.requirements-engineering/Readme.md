# US001 - Register a skill


## 1. Requirements Engineering

### 1.1. User Story Description

As HRM, I want to register skills that can be apointed to a collaborator.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

>	Each skill enables the collaborator to do a certain job or task and take on responsabilities.

>	The skills will be atributed to a collaborator.

**From the client clarifications:**

> **Question:** What type of information does a skill have?
>
> **Answer:** A skill only has a name, like: driver, prunner...

> **Question:** What criteria are needed to register a skill?
>
> **Answer:** For a skill to be registered the only requirement is the name.

### 1.3. Acceptance Criteria

* **AC1:** All required fields must be filled in.
* **AC2:** The skill cannot include non-alphanumeric digits such as _" # * ! ? & etc_.
* **AC3:** There cannot be duplicate skills.

### 1.4. Found out Dependencies

* The US001 doesn't have any dependencies. The US004 depends on the US001.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    * The name of the skill


**Output Data:**

* List of existing task categories
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

**_Other alternatives might exist._**

#### Alternative One

![System Sequence Diagram - Alternative One](svg/us006-system-sequence-diagram-alternative-one.svg)

### 1.7 Other Relevant Remarks

* The created task stays in a "not published" state in order to distinguish from "published" tasks.