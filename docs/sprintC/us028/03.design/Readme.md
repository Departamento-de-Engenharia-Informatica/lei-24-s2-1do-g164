# US008 - List the vehicles needing the check-up

## 3. Design - User Story Realization 

### 3.1. Rationale

_**Note that SSD - Alternative One is adopted.**_

| Interaction ID                                     | Question: Which class is responsible for...      | Answer                       | Justification (with patterns)                                                                                                                                                                      |
|:---------------------------------------------------|:-------------------------------------------------|:-----------------------------|:---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Step 1 (asks to consult tasks between two dates)   | ... interacting with the actor?                  | `:TasksBetweenDatesUI`       | Pure Fabrication: There is no reason to assign this responsibility to any existing class in the Domain Model.                                                                                      |
|                                                    | ... coordinating the use case (US)?              | `:TasksBetweenDatesController` | Controller: Deals with how to delegate the request from the UI layer.                                                                                                                              |
| Step 2 (create the controller)                     | ... creating the controller?                     | `:TasksBetweenDatesUI`       | Creator: A class should be responsible for creating objects of other classes if the first class aggregates, contains, or has a composition relationship with the second class.                     |
| Step 3 (get the list of agenda entries)            | ... getting the agenda entry DTO list?           | `:TasksBetweenDatesController` | Controller: Responsible for fetching and transforming data to be displayed.<br/><br/> Data Transfer Object (DTO): Reduces the number of transactions made throughout the application.              |
|                                                    | ... retrieving the agenda entries?               | `AgendaEntryRepository`      | Information Expert (IE): Has access to the data needed to fulfill the request.                                                                                                                     |
|                                                    | ... transforming agenda entries to DTOs?         | `AgendaEntryMapper`          | Pure Fabrication: A utility class to handle the transformation.<br/><br/> Data Transfer Object (DTO): Reduces the number of transactions made throughout the application.                         |
| Step 4 (display agenda entries)                    | ... displaying the list of agenda entry DTOs?    | `:TasksBetweenDatesUI`       | Pure Fabrication: Responsible for interacting with the user.                                                                                                                                       |
| Step 5 (request dates for search)                  | ... displaying input for dates?                  | `:TasksBetweenDatesUI`       | Pure Fabrication: Responsible for displaying input fields to the user.                                                                                                                             |
|                                                    | ... storing the data temporarily?                | `:TasksBetweenDatesUI`       | Information Expert (IE): Has the data input from the user.                                                                                                                                         |
| Step 6 (confirm date input)                        | ... sending the dates to the controller?         | `:TasksBetweenDatesUI`       | Information Expert (IE): Has the data input from the user.                                                                                                                                         |
| Step 7 (fetching agenda entries between dates)     | ... fetching agenda entries between dates?       | `:TasksBetweenDatesController` | Controller: Coordinates the process of retrieving the data.                                                                                                                                        |
| Step 8 (retrieving the specific agenda entries)    | ... retrieving the specific agenda entries?      | `AgendaEntryRepository`      | Information Expert (IE): Has access to the specific agenda entries.                                                                                                                                |
| Step 9 (transforming to DTO)                       | ... transforming agenda entries to DTOs?         | `AgendaEntryMapper`          | Pure Fabrication: A utility class to handle the transformation.<br/><br/> Data Transfer Object (DTO): Reduces the number of transactions made throughout the application.                         |
| Step 10 (return DTO list to UI)                    | ... sending DTO list to UI?                      | `:TasksBetweenDatesController` | Controller: Responsible for sending data to the UI for display.                                                                                                                                    |
| Step 11 (displaying the list)                      | ... displaying the success or failure message?   | `:TasksBetweenDatesUI`       | Pure Fabrication: Responsible for user feedback.                                                                                                                                                  |

According to the taken rationale, the conceptual classes promoted to software classes are: 

* Vehicle


Other software classes (i.e. Pure Fabrication) identified: 

*  ListVehivclesCheckupsUI
*  ListVehivclesCheckupsController
*  VehiclesRepodsitory


## 3.2. Sequence Diagram (SD)
### Full Diagram


This diagram shows the full sequence of interactions between the classes involved in the realization of this user story.

![us008-sequence-diagram-full.svg](svg%2Fus008-sequence-diagram-full.svg)
### Partial Diagrams

1.  Initial Request and UI Interaction

![us028-sequence-diagram-InitialRequestUIInteraction-0.svg](svg%2Fus028-sequence-diagram-InitialRequestUIInteraction-0.svg)

2. Controller and Repository Initialization

![us028-sequence-diagram-ControllerRepositoryInitialization-0.svg](svg%2Fus028-sequence-diagram-ControllerRepositoryInitialization-0.svg)

3. Fetching and Mapping Data
  ![us028-sequence-diagram-FetchingMappingData-0.svg](svg%2Fus028-sequence-diagram-FetchingMappingData-0.svg)

## 3.3. Class Diagram (CD)
![us029-class-diagram-0.svg](..%2F..%2Fus029%2F03.design%2Fsvg%2Fus029-class-diagram-0.svg)