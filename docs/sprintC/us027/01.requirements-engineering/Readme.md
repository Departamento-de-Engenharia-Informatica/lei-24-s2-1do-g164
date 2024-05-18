# US027 - List all green spaces managed by a GSM

## 1. Requirements Engineering

### 1.1. User Story Description

As a GSM, I need to list all green spaces managed by me.

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

>  The GSM is the person responsible for managing
the green spaces in charge of the organization.

**From the client clarifications:**

> **Question:** What is needed for a vehicle to be considered as needing a check-up?
>
> **Answer:** A vehicle appears on the list if its current kms exceed or there is a difference of less than 5% of the check-up frequency between the kms the vehicle had in the last check-up plus the check-up frequency.

> **Question:** What data should be displayed for each vehicle in the list?
>
> **Answer:** The list must clearly identify the vehicles through: plate number, brand, model and the reason that justified the checkup need.

### 1.3. Acceptance Criteria

* **AC1:** The list of green spaces must be sorted by size in descending order. The sorting algorithm to be used by the application
  must be defined through a configuration file. At least two sorting
  algorithms should be available.

### 1.4. Found out Dependencies

* There is a dependency on "US006 - Register a vehicle" as there must be at least one vehicle to list.

### 1.5 Input and Output Data

**Input Data:**



**Output Data:**

* List of green spaces
### 1.6. System Sequence Diagram (SSD)

![System Sequence Diagram - Alternative One](svg/us027-system-sequence-diagram.svg)

### 1.7 Other Relevant Remarks

* If a check-up has never been registered for a vehicle its kms at last check-up are 0. 