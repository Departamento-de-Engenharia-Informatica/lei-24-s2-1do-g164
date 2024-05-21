# US008 - List the vehicles needing the check-up


## 1. Requirements Engineering

### 1.1. User Story Description

As a FM, I want to list the vehicles needing the check-up. 

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

> The list must clearly identify the vehicles through: plate number, brand, model, current kms, checkup frequency, kms at last checkup, and the kms the vehicle must have at the next checkup.

**From the client clarifications:**

**From the client clarifications:**

> **Question:**
Can an employee record more than one completed task at a time?
>
> **Answer:** It's a matter of UX/UI, each dev team can decide about it.

> **Question:**
When a collaborator records a task, it should be asked for any observations regarding the completed task?
>
> **Answer:** Maybe if optional, not mandatory.

>**Question**
As far as I understand, when a GSM wants to cancel a task or a Collaborator wants to record the completion of a task, the task just changes its status in the Agenda to "Canceled" or "Done", respectively.
So, my question is the following: does the task associated with the Agenda entry in which this happens remain in the To-do List or can it be removed, unlike what happens in the Agenda? Or even, would this process be different between a completed task and a canceled task?
>
> **Answer**
Yes.
I suppose when a task goes to the Agenda, it leaves the To-Do list but maybe a different flow could be considered.


### 1.3. Acceptance Criteria

* A vehicle must fulfill the criteria to need a checkup in order to appear on the list.

### 1.4. Found out Dependencies

* There is a dependency on "US006 - Register a vehicle" as there must be at least one vehicle to list.

### 1.5 Input and Output Data

**Input Data:**

* n\a

**Output Data:**

* List of vehicles that need the check-up.

### 1.6. System Sequence Diagram (SSD)

![System Sequence Diagram - Alternative One](svg/us008-system-sequence-diagram.svg)

### 1.7 Other Relevant Remarks

* If a check-up has never been registered for a vehicle its kms at last check-up are 0. 