#  User Manual ☆


## 1. Introduction

### 1.1 Glossary 


| **_TEA_** (EN)             | **_TEA_** (PT)                   | **_Description_** (EN)                                                                                                                                                                                                                 |                                       
|:---------------------------|----------------------------------|:---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **HRM**                    | Gestor de Recursos Humanos       | Human Resources Manager.                                                                                                                                                                                                               |
| **VFM**                    | Gestor da Frota                  | Vehicle and Equipment Fleet Manager, a person that manages the fleet park, the machines, equipment and vehichles, ensuring threir good condition and assignong them to the tasks to be carried out.                                    |
| **Collaborator**           | Colaborador                      | An employee who carries out _tasks_ such as design, maintenance or construction.                                                                                                                                                       |
| **GSM**                    | Gestor dos Espaços Verdes        | Green Spaces Manager, person responsible for managing.                                                                                                                                                                                 |
| **GSU**                    | Utilizador dos Espaços Verdes    | Person who uses the green spaces managed by the organization, they can make comments and report faults throught the portal.                                                                                                            |
| **Team**                   | Equipa                           | Teams are temporary associations of employees who will carry out a set of tasks in one or more green spaces.                                                                                                                           |
| **Task**                   | Tarefa                           | Tasks are carried out on an occasional or regular basis in one or more green spaces, for example: tree pruning; installation of an irrigation system; installation of a lighting system.                                               |
| **Vehicle**                | Veículo                          | Vehicles are needed to carry out the tasks assigned to the teams as well as to transport machines and equipment. This type of vehicle can be only for passengers or mixed, light or heavy, open box or closed vans or trucks.          |
| **Machine**                | Máquina                          | The machines include tractors, backhoe loaders and rotating machines, lawn mowers, among others.                                                                                                                                       |
| **Equipment**              | Equipamento                      | The equipment includes sprayers, lifting platforms, chainsaws, brush cutters, blowers, ladders, cisterns and the various elements that can be attached to tractors, such as disc harrows, weeders, aerators and scarifiers.            |
| **Green Spaces**           | Espaços verdes                   | A green space is a vegetated area, those can be forests, trees, big parks, allotments, small parks.                                                                                                                                    |
| **Irrigation system**      | Sistema de rega                  | The irrigation system is the system implemented for watering the different _green spaces._                                                                                                                                             |
| **Garden**                 | Jardim                           | A garden is a small green space, it can have trees, it may have a basic _irrigation system._                                                                                                                                           |
| **Medium-Sized park**      | Parque de tamanho médio          | A medium-sized park is a _green space_ with a few hundred or thousands of square meters with a wooded garden area, some infrastructures, drinking fountains, irrigation systems and lighting.                                          |
| **Large-sized park**       | Parque de grandes dimensões      | A Large-sized park is a _green space_ with multiple functions with divers garden spaces, and woods including varied equipment and services.                                                                                            |
| **MS**                     | MusgoSublime                     | Company in charge of managing the different _green spaces._                                                                                                                                                                            |
| **Skills**                 | Capacidades                      | A Skill is an attribute given to a _collaborator_, when registered, a collaborator is given one or more skills that will define in which groups of work he will be working.                                                            |
| **Diary**                  | Calendário                       | A diary is a mechanism for organization of the week's work. Each diary entry will define a task, a team that will carry out that task and a certain  time interval.                                                                    |
| **User Portal**            | Portal do Utilizador             | The user portal is used by the _GSU_ to report faults and leave comments.                                                                                                                                                              |
| **Check-up**               | Revisão                          | The check-up is a necessary and periodic procedure done to assure the good working of the fleet's vehicles.                                                                                                                            |
| **System Users**           | Utilizadores do sistema          | Individuals who interact with the system, including HRM, FM, Collaborators, GSM, GSU.                                                                                                                                                  |
| **Team Proposal**          | Proposta de equipa               | A proposal generated automatically by the system for the formation of teams, typically created by the Human Resources Manager (HRM) based on the skills and availability of collaborators to carry out specific tasks in green spaces. |
| **Job**                    | Emprego                          | A job is a collaborator´s main occupation and each collaborator must have one job.                                                                                                                                                     |
| **US**                     | História de utilizador           | User story, a tool in Agile software development used to capture a description of a software feature from a user's perspective.                                                                                                        |
| **SSD Diagram**            | Diagrama de sequência de sistema | System Sequence Diagram, is a visual representation used to depict the flow of interactions between external actors and the system under consideration.                                                                                |
| **Domain Model**           | Modelo de domínio                | A domain model is a visual representation used to depict the essential entities, attributes, relationships, and behaviors within a specific problem domain.                                                                            |
| **Software application**   | Aplicação de _Software_          | Is a computer program designed to help people perform an activity.                                                                                                                                                                     |











### 1.2 Purpose and Scope of the application

In an increasingly urbanized world, access to green spaces plays a vital role in enhancing the quality of life for residents. 

Recognizing the significance of green spaces in urban contexts, MusgoSublime (MS) is working to effectively manage these spaces. However, they encounter some obstacles when it comes to efficiently organizing, building, and sustaining urban green spaces.

Therefore, MS has adopted this Software that serves as a comprehensive solution to the innumerable challenges encountered by this organization and others involved in similar endeavours.

This manual is designed to provide you with all the necessary information to effectively utilize the application to manage green spaces for collective use in urban environments. 

Whether you are a Human Resources Manager (HRM), a Vehicle and Equipment Fleet Manager (VFM), a Collaborator, a Green Spaces Manager (GSM) or a Green Spaces User (GSU), this manual will guide you through the features and functionalities of the system.


## 2. System Overview

 ### 2.1 Overall description


Through innovative approaches and meticulous administration, this application delivers a comprehensive software solution for planning, constructing, and maintaining green spaces. 

In order to do it, the system integrates various functionalities and tools tailored to the specific needs of the organizations, which are:
*  **Collaborator Management:** Human Resources Managers can efficiently manage the workforce by registering new collaborators, assigning jobs, and managing skills. This functionality enables effective allocation of resources based on expertise and availability.


*  **Team Proposal Generation:** Human Resources Managers can generate team proposals automatically based on predefined criteria such as maximum team size and required skills. This feature streamlines the process of forming teams for specific tasks and projects.


* **Vehicle and Equipment Management:**  Fleet Managers can oversee the organization's fleet of vehicles, machinery, and equipment. This includes registering vehicles, tracking maintenance schedules, and ensuring optimal utilization of resources.


* **Agenda Management:** The system provides a centralized platform for scheduling tasks and activities within green spaces. Users can plan and coordinate various maintenance activities, ensuring timely execution and efficient resource allocation.


* **Green Spaces User Portal:** A user-friendly portal allows Green Spaces Users to interact with the system by posting comments, reporting faults, and providing feedback on the condition of green spaces. This fosters community engagement and enables proactive maintenance and improvement of green areas.


### 2.2 Structure of the application 

The following diagram illustrates an abstract structure of the application and it makes clear the relation between each role and the features.

![Use Case Diagram](01.requirements-engineering/svg/use-case-diagram.svg)


## 3. System Features

As soon as you open the application, a log-in possibility will be provided so than you can authenticate yourself.

In order to do this, you have to insert your credentials: type your e-mail in first and then your password. 
If your access data is correct then a list with the options you have attributed to your role will appear.

 ###  **3.1 Human Resource Manager(HRM)**
As an HRM, you're responsible for managing collaborator skills, jobs, and team assignments. Here's how you can accomplish your tasks:

**1. Registering Collaborator Skills:**
* Log in to the system
* Choose the option "Register skills"
* Enter the skill name

**2.  Registering Jobs:**
* Log in to the system
* Choose the option "Register a job"
* Enter the job name

**3. Registering a collaborator with a job and fundamental  characteristics:**
* Log in to the system
* Choose the option "Register a collaborator with a job and fundamental  characteristics"
* Select the desired job for the collaborator from the options given
* Insert the information about the collaborator(name, birthday, admission date, address, cellphone number, e-mail)
* Select the type of IdDocument of the collaborator
* Type the IdDocument's number of the collaborator 

**4. Assigning Skills to Collaborators:**

* Log in to the system
* Choose the option "Assign Skills to Collaborators"
* Select the collaborator to assign the skills from the options provided 
* Select the skills from the list to add to the collaborator

**5.  Generating Team Proposals:**


* Log in to the system
*  Choose the option "Generate Team Proposals"
* Type the minimum and then the maximum size of the desired team 
* Select the desired skills for the collaborators of the team



###  **3.2 Vehicle and Equipment Fleet Manager (VFM):**

As a VFM, your role involves managing the fleet of vehicles and equipment. Here's what you can do step by step:

**1. Registering Vehicles:**
* Log in to the system
* Choose the option "Register a vehicle"
* Type the Brand, Model, Vehicle Id; Type, Tare, Gross Weight, Current Km, Register Date, Acquisition Date, Maintenance/Checkup Frequency of the vehicle, as requested

**2. Registering Vehicles Check-up:**
* Log in to the system
* Choose the option "Register a vehicle check-up"
* Type the Vehicle ID, Date, Current kms of the vehicle





