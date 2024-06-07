# US026 - Assign one or more vehicles to an entry in the Agenda

## 1. Requirements Engineering

### 1.1. User Story Description

- As a GSM, I want to assign one or more vehicles to an entry in
  the Agenda.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

> ??

**From the client clarifications:**

> **Question:**
Should all company vehicles be available to assign them to a calendar entry, or only vehicles with up-to-date maintenance?
>
> **Answer:** All vehicles that are not assigned to a task in the same period.
In a real context we also need to manage (un)availability of vehicles due to overhauls or breakdowns but it is not necessary in this proof of concept

> **Question:**
What are the criteries to accept a assign of a Vehicle to a Entry?
Only Vehicle with no Entry's can be assigned ?
Only Vehicles with no Entry on the day selected ?
It is possible to add any kind of vehicles?
what are the maximum number of vehicles that can be added to a entry?
>
> **Answer:** The vehicle needs to be available in the period.
Yes, any can of vehicles can be assigned.

> **Question:**
Dear client;
How will the Green Spaces Manager choose the vehicle to assign? By its plate?
Is the number of vehicles to be assigned provided by the Green Spaces Manager?
Best regards,
> 
> **Answer:**
Assuming you mean assingning to a Task/Agenda Entry, the data related with vehicle should be provided in order to ease the selection.
There is no specification concerning the number of vehicles, is upt to GSM decide what vehicles the task needs.
Best regards.

> **Question:**
Dear client,
should each GSM only be able to assign vehicles to its own entries or every GSM can assign vehicles to every entry, even if the green space associated with the task is not registered with their email?
Thanks.
> **Answer:**
Hi,
For the sake of simplicity, you can assume that GSM will only manage its Agenda Entries.
Best regards.


### 1.3. Acceptance Criteria

* **AC1** - All vehicles that are not assigned to the same task in the same period must be available to be assigned to a calendar date 
* **AC2** - The vehicle must be available within the period
### 1.4. Found out Dependencies

* There is a dependency on US022 - new entry in the Agenda is essential so  that Agenda entries exist.
* There is a dependency on US006 - creat a new vehicle is essential so that they can be assign.

### 1.5 Input and Output Data

**Input Data:**

* entry in the Agenda
* one/multiple vehicles

**Output Data:**

* List of collaborator's current skills
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

![System Sequence Diagram - Alternative One](svg/us026-system-sequence-diagram-System%20Sequence%20Diagram%20(SSD).svg)

### 1.7 Other Relevant Remarks

* none