# US028 - Consult the tasks assigned between two dates


## 1. Requirements Engineering

### 1.1. User Story Description

As a Collaborator, I wish to consult the tasks assigned to me between two dates.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

>The Agenda is a crucial mechanism for planning the week’s work. Each entry
in the Agenda defines a task (that was previously included in the to-do list).
A team will carry out that task in a green space at a certain time interval
on a specific date. Comparatively analyzing the Agenda entries and the
pending tasks (to-do list) allows you to evaluate the work still to be done,
the busyness of the week, and the work performed by a team in a green space
at a determined time interval and on a specific date.


**From the client clarifications:**

> **Question:**
"As a Collaborator, I wish to consult the tasks assigned to me". That means that the collaborator can have more than one task assigned to them. To ensure that, the same task should be availvable for more than one team, am I right? The reason for that question is so that if a task is strictly related to only one team, a collaborator will end up being in two teams simultaneously, which cannot happen.
>
> **Answer:**
A team can have multiple task assigned, hence, if a collaborator belongs to a team.
Besides, a collaborator can move from a team to another, so during, for instance, a month period a collaborator can belong to multiples teams.


> **Question:**
Dear client, I didn't end to understand a question answered here. A collaborator could have assigned at the same time different tasks from different teams, isnt it?
Apart, which atributes has a task ( maybe something like: name, frequency, creation_date, lenght, status)? Which id do you want (I think name is not a good idea)? Which status could a task have?
>
> **Answer:** No, in a specific period (for instance during a week) a collaborator just belongs to team. But if you consider a wider period you may find different teams to which a collaborator belonged.
In this project no distinction will be made between recorrent/occasional tasks; I suppose that the identification is a combination of Name, Date and Local, like Prunning Trees at 20/05/2024 in Parque da Cidade.

> **Question**
I noticed that some details were added to US028. Now the collaborator intends to consult the tasks assigned to him between two dates specified by him.
However, I have a doubt: in US022, when GSM wants to add a new entry to the Agenda, one of the necessary inputs is the starting date of the task itself, as I saw in one of the questions already asked to the client.
Therefore, are the tasks that should be shown to the Collaborator in this US028 those where the starting date is between the two mentioned by the Collaborator? Or, for example, when a task is recorded as complete in US029, the Collaborator would also have to input the task's completion date and then the tasks that begin and end within the date range introduced by the Collaborator in this US028 would be shown?
>
> **Answer**
I would suggest to consider any tasks which intersept the supplied period (defined by the two dates).

> **Question**
Dear client,
Could a task belong to different teams and collaborators? I mean, if the status, degree of urgency and expect duration belong to an entry of the to do list and the task is something generic that can be reuse, or if the task could belong only to one collaborator, and for another you have to create another one.
>
> **Answer**
No.
Maybe a distintion should be made between type of task and a task.
Task type is "Prunning trees", a specific task is "Prunning trees" in specific park in a specific date.


### 1.3. Acceptance Criteria

*  **AC1:** The list of tasks spaces must be sorted by date.
*  **AC2:** The Collaborator should be able to filter the results by the
  status of the task.


### 1.4. Found out Dependencies

* There is a dependency on "US006 - Register a vehicle" as there must be at least one vehicle to list.

### 1.5 Input and Output Data

**Input Data:**

Selected data:

* First Date
* Second Date
* Status of Entries


**Output Data:**

* List of entries assigned to the collaborator

### 1.6. System Sequence Diagram (SSD)

![System Sequence Diagram - Alternative One](svg/us008-system-sequence-diagram.svg)

### 1.7 Other Relevant Remarks

* none