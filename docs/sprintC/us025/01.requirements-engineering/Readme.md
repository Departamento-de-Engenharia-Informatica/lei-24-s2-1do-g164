# US025 - Cancel an entry in the Agenda.


## 1. Requirements Engineering

### 1.1. User Story Description
As a GSM, I want to Cancel an entry in the Agenda.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

> The Agenda is made up of entries that relate to a task with a status (Planned, Postponed, Canceled,
Done).


**From the client clarifications:**

> **Question:** Does the task associated with the Agenda entry in which this happens remain in the To-do List or can it be removed, unlike what happens in the Agenda? Or even, would this process be different between a completed task and a canceled task?
> 
> **Answer:** Yes. I suppose when a task goes to the Agenda, it leaves the To-Do list but maybe a different flow could be considered.


> **Question:** When a task is cancelled, is it possible to put it back on the agenda again later?
>
> **Answer:** yes

> **Question:** When we cancel a task, do we move it again to the To-Do List?
>
> **Answer:** no

> **Question:** When the GSM wants to cancel a task, this task can only be canceled if its status is PLANNED or POSTPONED, correct?
> 
> **Answer:** No, just planned because if there is a Postponed entry then there is also an Planned Entry with the new date.




### 1.3. Acceptance Criteria

* **AC1:**  A canceled task should not be deleted but rather change its
state.

### 1.4. Found out Dependencies

* There is a dependency on "US006 - Register a vehicle" as there must be at least one vehicle to list.

### 1.5 Input and Output Data

**Input Data:**

* Selected data
    * Entry

**Output Data:**

* (In)success of the operation 

### 1.6. System Sequence Diagram (SSD)

![System Sequence Diagram - Alternative One](svg/us025-system-sequence-diagram.svg)

### 1.7 Other Relevant Remarks
