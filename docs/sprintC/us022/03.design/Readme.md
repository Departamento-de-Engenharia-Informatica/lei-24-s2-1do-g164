# US0022 - REGISTER AN AGENDA ENTRY

## 3. Design - User Story Realization 

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID                                                                                                     | Question: Which class is responsible for...                | Answer                          | Justification (with patterns)                                                                                            |
|:-------------------------------------------------------------------------------------------------------------------|:-----------------------------------------------------------|:--------------------------------|:-------------------------------------------------------------------------------------------------------------------------|
| Step 1 (asks to add a new entry from the "To Do List" to the Agenda)                                               | ...interacting with the actor?                             | :AgendaUI                       | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.            |
|                                                                                                                    | ...coordinating the use case?                              | :AgendaController               | Controller                                                                                                               |
| Step 2 (getAvailableToDoEntries)                                                                                   | ...retrieving the available To Do entries?                 | ToDoEntryRepository             | IE: has the data                                                                                                         |
|                                                                                                                    | ...managing repository instances?                          | RepositorySingleton             | IE: ensures a single instance of repositories                                                                            |
| Step 3 (getToDoEntriesDTOs)                                                                                        | ...transforming To Do entries to DTOs?                     | ToDoEntryMapper                 | Pure Fabrication: handles transformation logic                                                                            |
|                                                                                                                    | ...storing the list of DTOs?                               | List<ToDoEntryDTO>              | Pure Fabrication: simple data structure to hold DTOs                                                                     |
| Step 4 (displays the "To Do List" entries that are not in the Agenda)                                              | ...displaying the available To Do entries?                 | :AgendaUI                       | Pure Fabrication                                                                                                         |
| Step 5 (selects the desired entry)                                                                                 | ...interacting with the actor to select an entry?          | :AgendaUI                       | Pure Fabrication: handles UI interaction                                                                                 |
| Step 6 (asks for a date for the entry on the Agenda)                                                               | ...interacting with the actor to select a date?            | :AgendaUI                       | Pure Fabrication                                                                                                         |
| Step 7 (selects date for the entry)                                                                                | ...interacting with the actor to get the date?             | :AgendaUI                       | Pure Fabrication                                                                                                         |
| Step 8 (create AgendaEntryDTO)                                                                                     | ...creating the Agenda entry DTO?                          | AgendaEntryDTO                  | IE: responsible for representing the Agenda entry as a DTO                                                               |
| Step 9 (addEntryToAgenda)                                                                                          | ...coordinating the addition of the entry to the Agenda?   | :AgendaController               | Controller                                                                                                               |
| Step 10 (registerAgendaEntry)                                                                                      | ...transforming DTO to entity?                             | AgendaEntryMapper               | Pure Fabrication: handles transformation logic                                                                            |
| Step 11 (addEntryToAgenda)                                                                                         | ...adding the entry to the Agenda repository?              | AgendaEntryRepository           | IE: has the data                                                                                                         |
| Step 12 (displays message of (in)success)                                                                          | ...displaying the result of the operation?                 | :AgendaUI                       | Pure Fabrication                                                                                                         |

According to the taken rationale, the conceptual classes promoted to software classes are: 

* AgendaEntry


  Other software classes (i.e. Pure Fabrication) identified: 

*  RegisterAgendaEntryUI
*  RegisterAgendaEntryController
*  AgendaEntryRepository
*  AgendaEntryDTO
*  AgendaEntryMapper


## 3.2. Sequence Diagram (SD)
### Full Diagram

This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![Class Diagram](svg/us022-sequence-diagram-full.svg)

## 3.3. Class Diagram (CD)

![Sequence Diagram - Full](svg/us022-class-diagram-Class_Diagram.svg)
